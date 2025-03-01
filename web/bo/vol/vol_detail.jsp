<%@ page import="views.VVol" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="dto.PlaceDTO" %>
<%
    VVol v_vol = (VVol) request.getAttribute("v_vol");

    PlaceDTO placeDTO = (PlaceDTO) request.getAttribute("placeDTO");

    LocalDateTime limiteReservation = (LocalDateTime) request.getAttribute("limiteReservation");
    LocalDateTime limiteAnnulation = (LocalDateTime) request.getAttribute("limiteAnnulation");

    pageContext.setAttribute("activePage", "boVolAdd");
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

    <title>Detail vol</title>

    <div>
        <%@ include file="/layout/link_header.jsp" %>
    </div>
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
                        <!-- Data vol -->
                        <div class="container mt-4">
                            <h4 class="text-center">
                                Detail vol
                            </h4>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Id</th>
                                    <th>Avion</th>
                                    <th>Ville Depart</th>
                                    <th>Ville Destination</th>
                                    <th>Heure Depart</th>
                                    <th>Heure Arrivee</th>
                                    <th>Prix place business</th>
                                    <th>Prix place eco</th>
                                    <th>Nb promo business</th>
                                    <th>Nb promo eco</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><%= v_vol.getId() %>
                                    </td>
                                    <td><%= v_vol.getId_avion() %>
                                    </td>
                                    <td><%= v_vol.getNom_ville_depart() %>
                                    </td>
                                    <td><%= v_vol.getNom_ville_destination() %>
                                    </td>
                                    <td><%= v_vol.getHeure_depart() %>
                                    </td>
                                    <td><%= v_vol.getHeure_arrivee() %>
                                    </td>
                                    <td><%= v_vol.getPrix_place_business() %>
                                    </td>
                                    <td><%= v_vol.getPrix_place_eco() %>
                                    </td>
                                    <td><%= v_vol.getNb_place_promo_business() %>
                                    </td>
                                    <td><%= v_vol.getNb_place_promo_eco() %>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Data Reste Places -->
                        <div class="container mt-4">
                            <h5 class="text-center">
                                Informations Supplementaires
                            </h5>
                            <table class="table table-bordered table-striped mt-3">
                                <thead>
                                <tr>
                                    <th>Limite Reservation</th>
                                    <th>Limite Annulation</th>
                                    <th>Confirmee business</th>
                                    <th>Confirmee eco</th>
                                    <th>En Attente business</th>
                                    <th>En Attente eco</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><%= limiteReservation %>
                                    </td>
                                    <td><%= limiteAnnulation %>
                                    </td>
                                    <td><%= placeDTO.getValidatedBusiness() %>
                                    </td>
                                    <td><%= placeDTO.getValidatedEco() %>
                                    </td>
                                    <td><%= placeDTO.getPendingBusiness() %>
                                    </td>
                                    <td><%= placeDTO.getPendingEco() %>
                                    </td>
                                </tr>
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
</html>
