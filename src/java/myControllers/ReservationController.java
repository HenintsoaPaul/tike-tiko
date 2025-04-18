package myControllers;

import dto.ConfigDTO;
import dto.PlaceDTO;
import entity.*;
import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import form.ReservationFormData;
import service.*;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;
import service.config.PourcentagePromotionService;
import src.summer.annotations.Validate;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;
import src.summer.annotations.Param;
import src.summer.beans.SummerSession;
import src.summer.exception.SummerSessionException;
import views.VReservation;
import views.VVol;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReservationController {

    private final PourcentagePromotionService pourcentagePromotionService = new PourcentagePromotionService();
    private final MinNbHeureAnnulationService minNbHeureAnnulationService = new MinNbHeureAnnulationService();
    private final MinNbHeureReservationService minNbHeureReservationService = new MinNbHeureReservationService();
    private final TypeSiegeService typeSiegeService = new TypeSiegeService();
    private final TrancheAgeService trancheAgeService = new TrancheAgeService();
    private final ReductionTrancheAgeService reductionTrancheAgeService = new ReductionTrancheAgeService();

    private final VVolService vVolService = new VVolService();
    private final VolService volService = new VolService();
    private final AvionService avionService = new AvionService();
    private final PlaceVolService placeVolService = new PlaceVolService();

    private final ReservationService reservationService = new ReservationService();
    private final VReservationService vReservationService = new VReservationService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv, String idReservation) {
        VReservation vReservation = vReservationService.selectById(conn, idReservation);
        VVol vVol = vVolService.selectById(conn, String.valueOf(vReservation.getId_vol()));

        mv.addObject("vReservation", vReservation);
        mv.addObject("v_vol", vVol);
    }

    private SummerSession summerSession;

    @Get
    @UrlMapping(url = "reservation_detail")
    public ModelView reservation_detail(
            @Param(name = "id") String idReservation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_detail.jsp", null);

            fetchData(conn, mv, idReservation);

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // FrontOffice
    @Get
    @UrlMapping(url = "fo_reservation_list")
    public ModelView fo_reservation_list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_list.jsp", null);

            // get user id from session
            Utilisateur u = (Utilisateur) summerSession.getAttribute("utilisateur");
            List<VReservation> vReservations = vReservationService.selectByUtilisateur(conn, u);

            Object err = summerSession.getAttribute("err");
            mv.addObject("err", err);
            if (err != null) {
                try {
                summerSession.addAttribute("err", null);
                } catch (SummerSessionException ignored) {}
            }

            mv.addObject("vReservations", vReservations);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Get
    @UrlMapping(url = "reservation_add")
    public ModelView reservation_add(
            @Param(name = "idVol") String idVol
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_add.jsp", null);

            Vol vol = this.volService.selectById(conn, idVol);
            mv.addObject("placeDTO", new PlaceDTO(conn, reservationService, vol));
            mv.addObject("configDTO", new ConfigDTO(conn, pourcentagePromotionService, minNbHeureReservationService, minNbHeureAnnulationService, vol));

            mv.addObject("v_vol", vVolService.selectById(conn, idVol));
            mv.addObject("typeSieges", typeSiegeService.selectAll(conn));
            mv.addObject("trancheAges", trancheAgeService.selectAll(conn));
            mv.addObject("utilisateur", summerSession.getAttribute("utilisateur"));

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Get
    @UrlMapping(url = "reservation_cancel")
    public String cancel(
            @Param(name = "idReservation") String idReservation,
            @Param(name = "idVol") String idVol,
            @Param(name = "dateAnnulation") LocalDateTime dateAnnulation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            Vol vol = this.volService.selectById(conn, idVol);
            MinNbHeureAnnulation minNbHeureAnnulation = this.minNbHeureAnnulationService.selectCurrent(conn);

            boolean isLate = volService.getLimiteAnnulation(vol, minNbHeureAnnulation)
                    .isBefore(dateAnnulation);

            if (!isLate) {
                Reservation reservationMere = this.reservationService.selectById(conn, idReservation);

                int etatCanceled = 2;
                this.reservationService.insert(conn, new Reservation(reservationMere, etatCanceled));
            } else {
                throw new IllegalArgumentException("Annulation Impossible car l'heure limite d'annulation a ete depassee.");
            }

            // message
            // annulation

            return "redirect:GET:/fo_reservation_list";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "reservation_save")
    public String save(
            @Validate(errorPage = "reservation_add?idVol=2")
            // todo maka nlay params nle url de redirection dynamiquement...
            @Param(name = "formData") ReservationFormData reservationFormData
    ) throws SummerSessionException {
        try (Connection conn = databaseService.getConnection()) {
            if (reservationService.getNbReservationsFaits(conn, reservationFormData) != 0) {
//                throw new IllegalArgumentException("Vous avez deja reservez ce vol.");
                summerSession.addAttribute("err", "Vous avez deja reservez ce vol.");
                return "redirect:GET:/fo_reservation_list";
            }

            MinNbHeureReservation minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
            Vol vol = this.volService.selectById(conn, String.valueOf(reservationFormData.getId_vol()));

            LocalDateTime heureReservationLimite = volService.getLimiteReservation(vol, minNbHeureReservation);

            boolean isLate = heureReservationLimite.isBefore(reservationFormData.getDate_reservation());
            System.out.println("Limite: " + heureReservationLimite + " | res: " + reservationFormData.getDate_reservation());

            if (!isLate) {
                PlaceVol placeVol = placeVolService.selectNextPlaceLibre(
                        conn,
                        String.valueOf(reservationFormData.getId_vol()),
                        String.valueOf(reservationFormData.getId_type_siege())
                );
                if (placeVol == null) {
//                    throw new IllegalArgumentException("Reservation Impossible car toutes les places ont ete deja prises.");
                    summerSession.addAttribute("err", "Reservation Impossible car toutes les places ont ete deja prises.");
                    return "redirect:GET:/fo_reservation_list";
                }

                double prix_final = placeVol.getIs_promotion() ?
                        placeVol.getPrix_avec_promo() :
                        placeVol.getPrix_sans_promo();

                int idTrancheAge = reservationFormData.getId_tranche_age();
                ReductionTrancheAge rta = reductionTrancheAgeService.selectCurrentByTrancheAge(conn, idTrancheAge);
                prix_final = reductionTrancheAgeService.applyReduction(rta, prix_final);

                // save reservation
                Reservation reservation = new Reservation(placeVol, reservationFormData, prix_final);
                reservation.setId_reduction_tranche_age(rta.getId());
                reservationService.insert(conn, reservation);

                // set nom client for place_vol
                placeVol.setNom_client(reservationFormData.getNom_client());
                placeVolService.update(conn, placeVol);

                return "redirect:GET:/fo_reservation_list";
            } else {
//                throw new IllegalArgumentException("Reservation Impossible car l'heure limite est depassee.");
                summerSession.addAttribute("err", "Reservation Impossible car l'heure limite est depassee.");
                return "redirect:GET:/fo_reservation_list";
            }

        } catch (SQLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            summerSession.addAttribute("err", e.getMessage());
            return "redirect:GET:/fo_reservation_list";
        }
    }
}
