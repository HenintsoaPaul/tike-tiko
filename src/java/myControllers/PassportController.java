package myControllers;

import entity.Reservation;
import service.DatabaseService;
import service.ReservationService;
import src.summer.annotations.Param;
import src.summer.annotations.controller.*;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.SummerFile;
import src.summer.beans.SummerSession;
import src.summer.exception.SummerSessionException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class PassportController {

    private final ReservationService reservationService = new ReservationService();
    private final DatabaseService databaseService = new DatabaseService();
    private SummerSession summerSession;

//    @Get
//    @UrlMapping(url = "passeport_add")
//    public ModelView add(
//            @Param(name = "idReservation") String idReservation
//    ) {
//        ModelView mv = new ModelView("fo/reservation/reservation_passeport.jsp");
//        mv.addObject("idReservation", idReservation);
//        return mv;
//    }

    @Post
    @UrlMapping(url = "passeport_save")
    public String save(
            @Param(name = "passeportFile", isFile = true) SummerFile passeportFile,
            @Param(name = "idReservation") String idReservation
    ) throws SQLException, IOException, SummerSessionException {

        System.out.println("FileName > " + passeportFile.getFileName());
        System.out.println("byte > " + passeportFile.getFileBytes().length);

        try (Connection conn = databaseService.getConnection()) {
            String dir = "C:\\Users\\Henintsoa\\Documents\\tike_tiko_data",
                    filePath = dir + "\\" + passeportFile.getFileName();

            Reservation reservation = reservationService.selectById(conn, idReservation);

            reservation.setImg_passeport(filePath);
            reservation.setId_etat_reservation(1);

            int result = reservationService.update(conn, reservation);

            if (result != 1) {
                summerSession.addAttribute("err", "Erreur lors de la modification de la reservation.");
                return "redirect:GET:/fo_reservation_list";
            }

            // save file locally on the server
            passeportFile.saveToFile(dir);
            System.out.println("Saved file on the server: " + filePath);

            System.out.println("Reservation Confirmed");
        }

        return "redirect:GET:/fo_reservation_list";
    }
}
