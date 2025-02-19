package myControllers;

import entity.Reservation;
import entity.Vol;
import entity.config.MinNbHeureAnnulation;
import service.*;
import service.config.MinNbHeureAnnulationService;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Get;
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

    private final VolService volService = new VolService();

    private final ReservationService reservationService = new ReservationService();
    private final VReservationService vReservationService = new VReservationService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("vReservation", vReservationService.select(conn, "select * from v_reservation"));
    }

    @Get
    @UrlMapping(url = "reservation_detail")
    public ModelView list(
            @Param(name = "id") String idReservation
    ) {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/reservation/reservation_detail.jsp", null);

//            fetchData(conn, mv);
            List<VReservation> vReservations = vReservationService.select(conn, "select * from v_reservation where id = " + idReservation);
            VReservation vr = vReservations.isEmpty() ? null : vReservations.get(0);
            mv.addObject("vReservation", vr);

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
            String query = "select * from vol where id = " + idVol;
            System.out.println("query = " + query);
            Vol vol = this.volService.select(conn, query).get(0);
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

            List<VReservation> vReservations = vReservationService.select(conn, "select * from v_reservation where id = " + idReservation);
            VReservation vr = vReservations.isEmpty() ? null : vReservations.get(0);
            mv.addObject("vReservation", vr);

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
