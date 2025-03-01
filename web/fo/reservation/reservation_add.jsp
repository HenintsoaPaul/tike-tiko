<%@ page import="entity.TypeSiege" %>
<%@ page import="java.util.List" %>
<%@ page import="form.ReservationFormData" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%@ page import="java.util.Optional" %>
<%@ page import="views.VVol" %>
<%@ page import="entity.Utilisateur" %>
<%@ page import="dto.PlaceDTO" %>
<%@ page import="dto.ConfigDTO" %>
<%
    Utilisateur client = (Utilisateur) request.getAttribute("utilisateur");
    VVol v_vol = (VVol) request.getAttribute("v_vol");

    PlaceDTO placeDTO = (PlaceDTO) request.getAttribute("placeDTO");

    ConfigDTO configDTO = (ConfigDTO) request.getAttribute("configDTO");

    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");

    // form validation
    ReservationFormData lastInput = null;
    ValidationLog vLog = (ValidationLog) request.getAttribute("validationLog");
    if (vLog != null && vLog.hasErrors()) {
        lastInput = (ReservationFormData) vLog.getLastInput();
    }

    pageContext.setAttribute("activePage", "reservationAdd");
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

    <title>Reserver un vol</title>

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
                        <%-- Form --%>
                        <div class="col-xl">
                            <div class="card mb-4" id="analyse-site">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h1 class="text-center mb-0">
                                        Reserver un vol
                                    </h1>
                                </div>
                                <div class="card-body">
                                    <form
                                            action="reservation_save"
                                            method="POST"
                                            class="form-inline mb-3"
                                    >
                                        <!-- vol+nom_client -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <label>Vol</label>
                                                    <input type="number"
                                                           name="formData.id_vol"
                                                           value="<%= v_vol.getId() %>"
                                                           class="form-control"
                                                           readonly>
                                                </div>
                                                <div class="col-6">
                                                    <label>Client:</label>
                                                    <input type="text"
                                                           name="formData.nom_client"
                                                           value="<%= client.getNom() %>"
                                                           class="form-control"
                                                           readonly>
                                                    <input type="hidden"
                                                           name="formData.id_client"
                                                           value="<%= client.getId() %>"
                                                           class="form-control"
                                                           readonly>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- ville depart+destination -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <label class="form-label">Date Reservation</label>
                                                    <input type="datetime-local"
                                                           name="formData.date_reservation"
                                                           class="form-control"
                                                           value="<%= lastInput != null ? lastInput.getDate_reservation() : "" %>"/>
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("formData.date_reservation");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                </div>
                                                <div class="col-6">
                                                    <label class="form-label">Type Siege: </label>
                                                    <select name="formData.id_type_siege"
                                                            class="form-control">
                                                        <option value="">Choisir le siege</option>
                                                        <%for (TypeSiege typeSiege : typeSieges) { %>
                                                        <option
                                                                value="<%= typeSiege.getId()%>"
                                                                <%
                                                                    if (lastInput != null) {
                                                                        if (lastInput.getId_type_siege() == typeSiege.getId()) {
                                                                            out.print("selected");
                                                                        }
                                                                    }
                                                                %>
                                                        >
                                                            <%= typeSiege.getNom() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("formData.id_type_siege");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                </div>
                                            </div>
                                        </div>

                                        <button type="submit" class="btn btn-primary">
                                            Confimer
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Data vol -->
                        <div class="container mt-4">
                            <h4 class="text-center">
                                A propos du vol
                            </h4>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
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
                                Places Restants
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
                                    <td>
                                        <%= configDTO.getLimiteReservation() %>
                                    </td>
                                    <td>
                                        <%= configDTO.getLimiteAnnulation() %>
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

                        <%-- Configs actuels --%>
                        <%@ include file="/parts/current_config.jsp" %>
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
