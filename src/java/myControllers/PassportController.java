package myControllers;

import entity.Reservation;
import service.DatabaseService;
import service.ReservationService;
import src.summer.annotations.Param;
import src.summer.annotations.controller.*;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;
import src.summer.beans.SummerFile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class PassportController {

    private final ReservationService reservationService = new ReservationService();
    private final DatabaseService databaseService = new DatabaseService();

    @Get
    @UrlMapping(url = "passeport_add")
    public ModelView add(
            @Param(name = "idReservation") String idReservation
    ) {
        ModelView mv = new ModelView("fo/reservation/reservation_passeport.jsp");
        mv.addObject("idReservation", idReservation);
        return mv;
    }

    @Post
    @UrlMapping(url = "passeport_save")
    public String save(
            @Param(name = "passeportFile", isFile = true) SummerFile passeportFile,
            @Param(name = "idReservation", isFile = false) String idReservation
    ) throws SQLException, IOException {

        System.out.println("FileName > " + passeportFile.getFileName());
        System.out.println("byte > " + passeportFile.getFileBytes().length);


        // todo: set passeport for reservation
        try (Connection conn = databaseService.getConnection()) {
            String dir = "C:\\Users\\Henintsoa\\Documents\\tike_tiko_data",
                    filePath = dir + "/" + passeportFile.getFileName();

            passeportFile.saveToFile(dir);
            System.out.println("Saved file on the server: " + filePath);

            Reservation reservation = reservationService.selectById(conn, idReservation);

            reservation.setImg_passeport(filePath);

            reservationService.update(conn, reservation);
            System.out.println("Reservation updated");

            // save file local on the server
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw e;
        }
        // todo: set passeport for reservation

        return "fichier";
    }
}
