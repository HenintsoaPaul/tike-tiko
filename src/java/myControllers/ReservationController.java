package myControllers;

import dto.ConfigDTO;
import dto.PlaceDTO;
import entity.*;
import entity.config.age.ReductionTrancheAge;
import entity.config.MinNbHeureAnnulation;
import entity.config.Promotion;
import form.ReservationFormData;
import service.*;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;
import service.config.PromotionService;
import service.views.VReservationService;
import service.views.VVolService;
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

    private final MinNbHeureAnnulationService minNbHeureAnnulationService = new MinNbHeureAnnulationService();
    private final MinNbHeureReservationService minNbHeureReservationService = new MinNbHeureReservationService();
    private final TypeSiegeService typeSiegeService = new TypeSiegeService();
    private final TrancheAgeService trancheAgeService = new TrancheAgeService();
    private final ReductionTrancheAgeService reductionTrancheAgeService = new ReductionTrancheAgeService();

    private final VVolService vVolService = new VVolService();
    private final VolService volService = new VolService();
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
                } catch (SummerSessionException ignored) {
                }
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
            mv.addObject("configDTO", new ConfigDTO(conn, minNbHeureReservationService, minNbHeureAnnulationService, vol));

            mv.addObject("v_vol", vVolService.selectById(conn, idVol));
            mv.addObject("typeSieges", typeSiegeService.selectAll(conn));
            mv.addObject("trancheAges", trancheAgeService.selectAll(conn));
            mv.addObject("utilisateur", summerSession.getAttribute("utilisateur"));

//            // error on submit
//            Object err = summerSession.getAttribute("err");
//            mv.addObject("err", err);
//            if (err != null) {
//                try {
//                    summerSession.addAttribute("err", null);
//                } catch (SummerSessionException ignored) {
//                }
//            }
//            // error on submit

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
            // TODO: maka nlay params nle url de redirection dynamiquement...
//            @Validate(errorPage = "reservation_add?idVol=2")
            @Validate(errorPage = "fo_vol_list")
            @Param(name = "formData") ReservationFormData reservationFormData
    ) throws SummerSessionException {
        try (Connection conn = databaseService.getConnection()) {
            int nbReservation = reservationService.getNbReservationsFaits(conn, reservationFormData);
            System.out.println("Vous avez " + nbReservation + " reservation(s) sur ce vol.");

//            if (nbReservation != 0) {
//                summerSession.addAttribute("err", "Vous avez deja reservez ce vol.");
//                return "redirect:GET:/fo_reservation_list";
//            }

            int idVol = reservationFormData.getId_vol(),
                    idTypeSiege = reservationFormData.getId_type_siege();

            LocalDateTime dateReservation = reservationFormData.getDate_reservation();

            Vol vol = this.volService.selectById(conn, String.valueOf(idVol));

            boolean isLate = reservationService.isLateReservation(conn, vol, dateReservation);
            if (isLate) {
                summerSession.addAttribute("err", "Reservation impossible car l'heure limite est depassee.");
                String url = "redirect:GET:/reservation_add?idVol=" + idVol;
                // System.out.println("url: " + url);
                // TODO: handle redirection with parameters

                url = "redirect:GET:/fo_reservation_list";
                return url;
            }

            PlaceVol placeVol = placeVolService.selectNextPlaceLibre(conn, idVol, idTypeSiege);

            if (placeVol == null) {
                summerSession.addAttribute("err", "Reservation Impossible car toutes les places ont ete deja prises.");
                return "redirect:GET:/fo_reservation_list";
            }

            // Get prix normal
            double prix_final = idTypeSiege == 1
                    ? vol.getPrix_place_business() : vol.getPrix_place_eco();

            // Get prix promotion (si existe une promotion valide pour le couple [id_vol, id_type_siege])
            List<Promotion> promotions = new PromotionService()
                    .getPromotionForVol(conn, idVol, idTypeSiege, dateReservation);

            System.out.println("Prix place: " + prix_final);
            boolean onPromotion = false;
            if (!promotions.isEmpty()) {
                prix_final = promotions.get(0).getPrix_promo();
                onPromotion = true;
                System.out.println("Prix place: " + prix_final + " | onPromotion: " + onPromotion);
            }

            // Get prix apres deduction de reduction en fonction de la tranche d'age
            int idTrancheAge = reservationFormData.getId_tranche_age();
            ReductionTrancheAge rta = reductionTrancheAgeService.selectCurrentByTrancheAge(conn, idTrancheAge);
            prix_final = reductionTrancheAgeService.applyReduction(rta, prix_final);

            // Save
            Reservation reservation = new Reservation(placeVol, reservationFormData, prix_final, onPromotion);
            reservation.setId_reduction_tranche_age(rta.getId());
            reservationService.insert(conn, reservation);

            return "redirect:GET:/fo_reservation_list";
        } catch (SQLException e) {
            summerSession.addAttribute("err", e.getMessage());
            return "redirect:GET:/fo_reservation_list";
        }
    }
}
