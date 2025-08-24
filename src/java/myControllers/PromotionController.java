package myControllers;

import entity.Reservation;
import entity.config.Promotion;
import entity.config.age.ReductionTrancheAge;
import service.*;
import service.config.PromotionService;
import service.views.VPromotionService;
import src.summer.annotations.Authorized;
import src.summer.annotations.Param;
import src.summer.annotations.Validate;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Get;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PromotionController {

    private final ReductionTrancheAgeService reductionTrancheAgeService = new ReductionTrancheAgeService();
    private final ReservationService reservationService = new ReservationService();
    private final VolService volService = new VolService();
    private final TypeSiegeService typeSiegeService = new TypeSiegeService();
    private final PromotionService promotionService = new PromotionService();

    private final VPromotionService vPromotionService = new VPromotionService();

    private final DatabaseService databaseService = new DatabaseService();

    private void fetchData(Connection conn, ModelView mv) {
        mv.addObject("vPromotions", vPromotionService.selectAll(conn));
    }

    @Authorized
    @Get
    @UrlMapping(url = "promotion_list")
    public ModelView list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/promotion/promotion_list.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Get
    @UrlMapping(url = "fo_promotion_list")
    public ModelView fo_list() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("fo/promotion/promotion_list.jsp", null);
            fetchData(conn, mv);
            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    @Post
//    @UrlMapping(url = "promotion_filter")
//    public ModelView filter(

    /// /            @Validate(errorPage = "promotion_list")
//            @Param(name = "volFiltre") VolFilterFormData volFilterFormData
//    ) {
//        try (Connection conn = databaseService.getConnection()) {
//            ModelView mv = new ModelView("bo/promotion/promotion_list.jsp", null);
//            mv.addObject("vvols", this.vVolService.selectWithFilter(conn, volFilterFormData));
//            fetchData(conn, mv);
//            return mv;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Post
//    @UrlMapping(url = "fo_promotion_filter")
//    public ModelView fo_filter(
//           @Validate(errorPage = "promotion_list")
//            @Param(name = "volFiltre") VolFilterFormData volFilterFormData
//    ) {
//        try (Connection conn = databaseService.getConnection()) {
//            ModelView mv = new ModelView("fo/promotion/promotion_list.jsp", null);
//            mv.addObject("vvols", this.vVolService.selectWithFilter(conn, volFilterFormData));
//            fetchData(conn, mv);
//            return mv;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Get
    @UrlMapping(url = "promotion_add")
    @Authorized(roleLevel = 10)
    public ModelView add() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/promotion/promotion_add.jsp", null);

            mv.addObject("vols", this.volService.select(conn, "select * from vol"));
            mv.addObject("typeSieges", this.typeSiegeService.selectAll(conn));

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Post
    @UrlMapping(url = "promotion_save")
    @Authorized(roleLevel = 10)
    public String save(
            @Validate(errorPage = "promotion_add")
            @Param(name = "promotion") Promotion promotion
    ) throws SQLException {
        Connection conn = null;
        try {
            conn = databaseService.getConnection();
            conn.setAutoCommit(false);

            this.promotionService.insert(conn, promotion);
            System.out.println("Insert promotion done...");

            conn.commit();

            return "redirect:GET:/promotion_add";
        } catch (Exception e) {
            assert conn != null;
            conn.rollback();
            throw new RuntimeException(e);
        }
    }

    @Authorized
    @Get
    @UrlMapping(url = "promotion_decaler_reservations_non_payes")
    public String promotion_decaler_reservations_non_payes(
            String idPromotion
    ) throws SQLException {
        Connection conn = null;
        try {
            conn = databaseService.getConnection();

            Promotion promotion = promotionService.select(conn, "select * from promotion where id = " + idPromotion).get(0);
            int idVol = promotion.getId_vol(), idTypeSiege = promotion.getId_type_siege();

            // get reservations non payes lies a cette promotion
            int idEtatReservation = 3; // en attente
            String queryUnvalidatedReservations = "select r.* from reservation r"
                    + " join place_vol pv on pv.id = r.id_place_vol"
                    + " where r.id_etat_reservation = " + idEtatReservation
                    + "     and r.id_promotion = " + idPromotion
                    + " order by id asc";

            List<Reservation> unvalidatedReservations = reservationService.select(conn, queryUnvalidatedReservations);
            int nbADecaler = unvalidatedReservations.size();

            // Aucune reservation en attente a decaler...
            if (nbADecaler == 0) return "redirect:GET:/promotion_list";

            // get next promotions meme ktq
            String queryNextPossiblePromotions = "select * from promotion"
                    + " where id_vol = " + idVol
                    + "     and id_type_siege = " + idTypeSiege
                    + "     and date_fin >= '" + promotion.getDate_fin() + "'"
                    + "     and id < " + promotion.getId()
                    + " order by id asc";

            List<Promotion> nextPossiblePromotions = promotionService.select(conn, queryNextPossiblePromotions);

            conn.setAutoCommit(false);

            for (Promotion nextPromotion : nextPossiblePromotions) {
                List<Reservation> paidReservations = this.promotionService
                        .getPaidReservationsForPromotion(conn, nextPromotion);

                int placeLibre = nextPromotion.getNb_place() - paidReservations.size();

                if (placeLibre <= 0) continue;

                decalerManyReservationsTo(conn, nextPromotion, unvalidatedReservations, placeLibre);
            }

            // Annuler toutes les reservations restantes
            int nbAnnuler = annulerBatchReservation(conn, unvalidatedReservations);
            System.out.println("[Decaler reservation]: " + nbADecaler + " initalement a decaler.");
            System.out.println("[Decaler reservation]: " + nbAnnuler + " annulee");
            // ...

            conn.commit();

            return "redirect:GET:/promotion_list";
        } catch (Exception e) {
            assert conn != null;
            conn.rollback();
            throw new RuntimeException(e);
        }
    }

    private int annulerBatchReservation(Connection conn, List<Reservation> unvalidatedReservations) {
        List<Integer> ids = unvalidatedReservations.stream()
                .map(Reservation::getId).collect(Collectors.toList());

        String idsString = ids.stream().map(Object::toString).collect(Collectors.joining(", "));

        String query = "update reservation\n" +
                "set id_etat_reservation = 3\n" +
                "where id in ( " + idsString + " )";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            return stmt.executeUpdate(); // Return affected rows
        } catch (SQLException e) {
            e.printStackTrace();
            return -1; // Indicate failure
        }
    }

    private void decalerManyReservationsTo(
            Connection conn,
            Promotion nextPromotion,
            List<Reservation> unvalidatedReservations,
            int placeLibre
    ) {
        for (int i = 0; i < placeLibre; i++) {
            decalerReservationTo(conn, nextPromotion, unvalidatedReservations.get(0));
            unvalidatedReservations.remove(0);
        }
    }

    private void decalerReservationTo(Connection conn, Promotion nextPromotion, Reservation reservation) {
        double prix_final = nextPromotion.getPrix_promo();

        int idRta = reservation.getId_reduction_tranche_age();
        if (idRta > 0) {
            String query = "select * from reduction_tranche_age where id = " + idRta;
            ReductionTrancheAge rta = reductionTrancheAgeService.select(conn, query).get(0);
            prix_final = reductionTrancheAgeService.applyReduction(rta, prix_final);
        }

        reservation.setPrix_final(prix_final);
        reservation.setId_promotion(nextPromotion.getId());

        reservationService.update(conn, reservation);
        System.out.println("[Decaler reservation individu]: id_ " + reservation.getId() + " decaler vers promotion_id_ " + nextPromotion.getId());
    }

//    // BackOffice
//    @Get
//    @UrlMapping(url = "promotion_detail")
//    public ModelView promotion_detail(
//            @Param(name = "idVol") String idVol
//    ) {
//        try (Connection conn = databaseService.getConnection()) {
//            ModelView mv = new ModelView("bo/promotion/promotion_detail.jsp", null);
//
//            Vol vol = this.volService.selectById(conn, idVol);
//            mv.addObject("placeDTO", new PlaceDTO(conn, reservationService, vol));
//
//            MinNbHeureReservation minNbHeureReservation = minNbHeureReservationService.selectCurrent(conn);
//            mv.addObject("limiteReservation", vol.getHeure_depart().minusHours((long) minNbHeureReservation.getVal()));
//
//            MinNbHeureAnnulation minNbHeureAnnulation = minNbHeureAnnulationService.selectCurrent(conn);
//            mv.addObject("limiteAnnulation", vol.getHeure_depart().minusHours((long) minNbHeureAnnulation.getVal()));
//
//            mv.addObject("v_vol", vVolService.selectById(conn, idVol));
//            return mv;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
