<%@ page import="service.util.DateFormatterService" %>
<%@ page import="views.VReservation" %>
<%@ page import="java.util.List" %>
<%@ page import="views.VPromotion" %>
<%
    VPromotion vPromotion = (VPromotion) request.getAttribute("vPromotion");

    List<VReservation> vReservations = (List<VReservation>) request.getAttribute("vReservations");

    pageContext.setAttribute("activePage", "boPromotionDetail");

    DateFormatterService formatterService = new DateFormatterService();
%>

<!DOCTYPE html>

<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta name="description"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Detail promotion</title>

    <%@ include file="/layout/link_header.jsp" %>
</head>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <div>
            <%@ include file="/layout/menu_admin.jsp" %>
        </div>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">
                    <div class="row">

                        <!-- Data promotion -->
                        <div class="container mt-4">
                            <h4 class="text-center">
                                Detail promotion
                            </h4>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Id</th>
                                    <th>Vol</th>
                                    <th>Type place</th>
                                    <th>Date butoir</th>
                                    <th>Prix</th>
                                    <th>Max place</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><%= vPromotion.getId() %>
                                    </td>
                                    <td><%= vPromotion.getId_vol() %>
                                    </td>
                                    <td><%= vPromotion.getNom_type_siege() %>
                                    </td>
                                    <td><%= formatterService.format(vPromotion.getDate_fin()) %>
                                    </td>
                                    <td><%= vPromotion.getPrix_promo() %>
                                    </td>
                                    <td><%= vPromotion.getNb_place() %>
                                    </td>
                                    <td>
                                        <a href="promotion_decaler_reservations_non_payes?idPromotion=<%= vPromotion.getId() %>">
                                            Decaler les reservations non payees
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Data Reservations -->
                        <div class="container mt-4">
                            <h5 class="text-center">
                                Reservations
                            </h5>
                            <table class="table table-bordered table-striped mt-3">
                                <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Fais le</th>
                                    <th>Utilisateur</th>
                                    <th>Prix</th>
                                    <th>Etat</th>
                                    <th>Type siege</th>
                                    <th>Id promotion</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (VReservation vReservation : vReservations) { %>
                                <tr>
                                    <td><%= vReservation.getId() %>
                                    </td>
                                    <td><%= formatterService.format(vReservation.getHeure_reservation()) %>
                                    </td>
                                    <td><%= vReservation.getNom_utilisateur() %>
                                    </td>
                                    <td><%= vReservation.getPrix_final() %>
                                    </td>
                                    <td><%= vReservation.getNom_etat_reservation() %>
                                    </td>
                                    <td><%= vReservation.getNom_type_siege() %>
                                    </td>
                                    <td><%= vReservation.getId_promotionStr() %>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- / Content -->

            <div class="content-backdrop fade"></div>
        </div>
        <!-- Content wrapper -->
    </div>
    <!-- / Layout page -->
</div>

<!-- Overlay -->
<div class="layout-overlay layout-menu-toggle"></div>
<!-- script -->
<div>
    <%@ include file="/layout/script.jsp" %>
</div>
</body>
</html>
