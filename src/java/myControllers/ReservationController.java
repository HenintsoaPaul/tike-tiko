package myControllers;

import entity.Avion;
import entity.PlaceVol;
import entity.Reservation;
import entity.Vol;
import entity.config.MinNbHeureAnnulation;
import entity.config.MinNbHeureReservation;
import form.ReservationFormData;
import service.*;
import service.config.MinNbHeureAnnulationService;
import service.config.MinNbHeureReservationService;
import src.summer.annotations.Validate;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;
import src.summer.annotations.Param;
import views.VReservation;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReservationController {

    private final MinNbHeureAnnulationService minNbHeureAnnulationService = new MinNbHeureAnnulationService();
    private final MinNbHeureReservationService minNbHeureReservationService = new MinNbHeureReservationService();
    private final TypeSiegeService typeSiegeService = new TypeSiegeService();

    private final VolService volService = new VolService();
    private final AvionService avionService = new AvionService();
    private final PlaceVolService placeVolService = new PlaceVolService();

    private final ReservationService reservationService = new ReservationService();
    private final VReservationService vReservationService = new VReservationService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv, String idReservation) {
        List<VReservation> vReservations = vReservationService.select(conn, "select * from v_reservation where id = " + idReservation);
        VReservation vr = vReservations.isEmpty() ? null : vReservations.get(0);
        mv.addObject("vReservation", vr);
    }

    @Get
    @UrlMapping(url = "reservation_detail")
    public ModelView list(
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

    @Get
    @UrlMapping(url = "reservation_cancel")
    public ModelView cancel(
            @Param(name = "idReservation") String idReservation,
            @Param(name = "idVol") String idVol,
            @Param(name = "dateAnnulation") LocalDateTime dateAnnulation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_detail.jsp", null);

            // annulation
            // is possible
            MinNbHeureAnnulation minNbHeureAnnulation = this.minNbHeureAnnulationService.selectCurrent(conn);
            Vol vol = this.volService.selectById(conn, idVol);
            LocalDateTime heureAnnulationLimite = vol.getHeure_depart().plusHours((long) minNbHeureAnnulation.getVal());

            boolean isLate = heureAnnulationLimite.isBefore(dateAnnulation);
            if (!isLate) {
                System.out.println("Annulation begins...");

                Reservation reservation = this.reservationService.select(conn, "select * from reservation where id = " + idReservation).get(0);
                reservation.setId_etat_reservation(2);
                this.reservationService.update(conn, reservation);

                System.out.println("Annulation ends...");
            } else {
                throw new IllegalArgumentException("Annulation Impossible car l'heure limite d'annulation a ete depassee.");
            }

            // message
            // annulation

            fetchData(conn, mv, idReservation);

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Get
    @UrlMapping(url = "reservation_add")
    public ModelView add(
            @Param(name = "idVol") String idVol
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_add.jsp", null);

            mv.addObject("typeSieges", typeSiegeService.selectAll(conn));
            mv.addObject("idVol", idVol);

            Vol vol = this.volService.selectById(conn, idVol);
            Avion avion = this.avionService.selectById(conn, vol.getId_avion());

            int nbPlacesPrisBusiness = reservationService.getNbPlacesPris(conn, 1),
                    nbPlacesPrisEco = reservationService.getNbPlacesPris(conn, 2);
            mv.addObject("resteBusiness", avion.getSiege_business() - nbPlacesPrisBusiness);
            mv.addObject("resteEco", avion.getSiege_eco() - nbPlacesPrisEco);

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "reservation_save")
    public ModelView save(
            @Validate(errorPage = "reservation_add?idVol=2")
            // todo maka nlay params nle url de redirection dynamiquement...
            @Param(name = "formData") ReservationFormData reservationFormData
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_detail.jsp", null);

            // isLate
            MinNbHeureReservation minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
            Vol vol = this.volService.selectById(conn, idVol);
            LocalDateTime heureReservationLimite = vol.getHeure_depart().minusHours((long) minNbHeureReservation.getVal());
            boolean isLate = heureReservationLimite.isBefore(dateReservation);

            if (!isLate) {
                PlaceVol placeVol = placeVolService.selectNextPlaceLibre(conn, idVol, idTypeSiege);

                // save reservation
                Reservation reservation = new Reservation();
                reservation.setId_etat_reservation(3);
                reservation.setId_place_vol(placeVol.getId());
                reservation.setNom_client(nomClient);
                reservation.setHeure_reservation(dateReservation);

                int idRes = reservationService.insert(conn, reservation);

                // set nom client for place_vol
                placeVol.setNom_client(nomClient);
                placeVolService.update(conn, placeVol);

                fetchData(conn, mv, String.valueOf(idRes));
            } else {
                throw new IllegalArgumentException("Reservation Impossible car l'heure limite est depassee.");
            }

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
