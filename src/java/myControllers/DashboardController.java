package myControllers;

import service.DashboardService;
import service.DatabaseService;
import src.summer.annotations.Authorized;
import src.summer.annotations.controller.Controller;
import src.summer.annotations.controller.UrlMapping;
import src.summer.annotations.controller.verb.Post;
import src.summer.beans.ModelView;

import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class DashboardController {

    private final DashboardService dashboardService = new DashboardService();
    private final DatabaseService databaseService = new DatabaseService();

    @Authorized
    @Post
    @UrlMapping(url = "bo_dashboard")
    public ModelView bo_dashboard() {
        try (Connection conn = databaseService.getConnection()) {
            ModelView mv = new ModelView("bo/bo_dashboard.jsp", null);

            mv.addObject("dto", dashboardService.getDto(conn));

            return mv;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
