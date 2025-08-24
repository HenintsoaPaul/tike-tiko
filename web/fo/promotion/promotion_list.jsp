<%@ page import="views.VPromotion" %>
<%@ page import="java.util.List" %>
<%@ page import="service.DateFormatterService" %>
<%
    List<VPromotion> vPromotions = (List<VPromotion>) request.getAttribute("vPromotions");

    pageContext.setAttribute("activePage", "foPromotionList");

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

    <title>Liste des promotions</title>

    <%@ include file="/layout/link_header.jsp" %>
</head>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <div>
            <%@ include file="/layout/menu.jsp" %>
        </div>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">
                    <div class="row">
<%--                        &lt;%&ndash; Filtre &ndash;%&gt;--%>
<%--                        <%@ include file="/parts/filtre_vol.jsp" %>--%>

                        <!-- Data -->
                        <div class="container mt-4">
                            <h2 class="text-center">
                                Liste des promotions
                            </h2>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Vol</th>
                                    <th>Type place</th>
                                    <th>Date butoir</th>
                                    <th>Prix</th>
                                    <th>Max place</th>
                                    <th>Nb places restantes</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (VPromotion vPromo : vPromotions) { %>
                                <tr>
                                    <td><%= vPromo.getId_vol() %>
                                    </td>
                                    <td><%= vPromo.getNom_type_siege() %>
                                    </td>
                                    <td><%= formatterService.format(vPromo.getDate_fin()) %>
                                    </td>
                                    <td><%= vPromo.getPrix_promo() %>
                                    </td>
                                    <td><%= vPromo.getNb_place() %>
                                    </td>
<%--                                    <td><%= vPromo.getPrix_place_eco() %>--%>
                                    <td>TODO
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
<!-- / Layout wrapper -->

<!-- script -->
<div>
    <%@ include file="/layout/script.jsp" %>
</div>
</body>
</html>
