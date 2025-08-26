package service;

import dto.DashboardBookingsDTO;
import dto.DashboardDTO;
import entity.Avion;
import entity.Reservation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DashboardService {

    DatabaseService databaseService = new DatabaseService();
    ReservationService reservationService = new ReservationService();
    VolService volService = new VolService();

//    public DashboardDTO select(Connection conn, String query) {
//        List<DashboardDTO> dtos = this.databaseService.select(
//                conn,
//                query,
//                rs -> {
//                    try {
//                        DashboardBookingsDTO bookingsDTO = new DashboardBookingsDTO(
//                                rs.getInt("nb_pending"),
//                                rs.getInt("nb_confirmed"),
//                                rs.getInt("nb_cancelled")
//                        );
//                        return new DashboardDTO(
//                                rs.getInt("nb_flights"),
//                                bookingsDTO
//                        );
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//
//        return dtos.isEmpty() ? null : dtos.get(0);
//    }

    public DashboardDTO getDto(Connection conn) {
        Predicate<Reservation> isPending = n -> n.getId_etat_reservation() == 3,
                isConfirmed = n -> n.getId_etat_reservation() == 1,
                isCancelled = n -> n.getId_etat_reservation() == 2 || n.getId_etat_reservation() == 4;

        List<Reservation> reservations = reservationService.select(conn, "select * from reservation");

        DashboardBookingsDTO bookingsDTO = new DashboardBookingsDTO(
                (int) reservations.stream().filter(isConfirmed).count(),
                (int) reservations.stream().filter(isPending).count(),
                (int) reservations.stream().filter(isCancelled).count()
        );

        int nbVols = volService.select(conn, "select * from vol").size();
        return new DashboardDTO(nbVols, bookingsDTO);
    }
}
