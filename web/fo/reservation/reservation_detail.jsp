<%@ page import="views.VReservation" %>
<%@ page import="views.VVol" %>
<%
    VReservation vReservation = (VReservation) request.getAttribute("vReservation");

    VVol v_vol = (VVol) request.getAttribute("v_vol");

    pageContext.setAttribute("activePage", "foReservationDetail");
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

    <title>Detail Reservation</title>

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
                        <%-- Annulation --%>
                        <div class="col-xl">
                            <div class="card mb-4">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h4 class="text-center mb-0">
                                        Annulation Reservation
                                    </h4>
                                </div>
                                <div class="card-body">
                                    <form
                                            action="reservation_cancel"
                                            method="GET"
                                            class="form-inline mb-3"
                                    >
                                        <!-- date_annulation -->
                                        <div class="mb-3">
                                            <label class="form-label">Date annulation</label>
                                            <input type="datetime-local"
                                                   name="dateAnnulation"
                                                   class="form-control"
                                                   required>
                                        </div>

                                        <input type="hidden"
                                               name="idReservation"
                                               value="<%= vReservation.getId() %>">

                                        <input type="hidden"
                                               name="idVol"
                                               value="<%= vReservation.getId_vol() %>">

                                        <button type="submit" class="btn btn-danger">
                                            Annuler
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <%-- Validation --%>
                        <div class="col-xl">
                            <div class="card mb-4">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h4 class="text-center mb-0">
                                        Validation Reservation
                                    </h4>
                                </div>
                                <div class="card-body">
                                    <form
                                            method="POST"
                                            action="passeport_save"
                                            enctype="multipart/form-data"
                                    >
                                        <div class="mb-3">
                                            <label class="form-label">Passeport</label>
                                            <input type="file"
                                                   name="passeportFile"
                                                   class="form-control"
                                                   required>

                                            <input type="hidden"
                                                   name="idReservation"
                                                   value="<%= vReservation.getId() %>">
                                        </div>

                                        <button type="submit" class="btn btn-success">
                                            Valider
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Data Reservation -->
                        <div class="container mt-4">
                            <h2 class="text-center">
                                Detail Reservation
                            </h2>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Type Siege</th>
                                    <th>Date Reservation</th>
                                    <th>Prix final</th>
                                    <th>Etat Reservation</th>
                                    <th>Passeport</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><%= vReservation.getNom_type_siege() %>
                                    </td>
                                    <td><%= vReservation.getHeure_reservation() %>
                                    </td>
                                    <td><%= vReservation.getPrix_final() %>
                                    </td>
                                    <td><%= vReservation.getNom_etat_reservation() %>
                                    </td>
                                    <td>
                                        <%= vReservation.getImg_passeport() %>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                        <!-- Data vol -->
                        <div class="container mt-4">
                            <h4 class="text-center">
                                A propos du vol
                            </h4>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
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
