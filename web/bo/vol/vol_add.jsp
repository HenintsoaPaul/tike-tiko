<%@ page import="entity.Ville" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Avion" %>
<%@ page import="views.VVol" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="entity.Vol" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%@ page import="java.util.Optional" %>
<%
    List<VVol> vvols = (List<VVol>) request.getAttribute("vvols");

    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");

    // form validation
    Vol lastInput = null;
    ValidationLog vLog = (ValidationLog) request.getAttribute("validationLog");
    if (vLog != null && vLog.hasErrors()) {
        lastInput = (Vol) vLog.getLastInput();
    }

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

    <title>Ajouter vol</title>

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
                        <%-- Form --%>
                        <div class="col-xl">
                            <div class="card mb-4" id="analyse-site">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h1 class="text-center mb-0">
                                        Ajouter vol
                                    </h1>
                                </div>
                                <div class="card-body">
                                    <form
                                            action="vol_save"
                                            method="POST"
                                            class="form-inline mb-3"
                                    >
                                        <!-- avion -->
                                        <div class="mb-3">
                                            <%
                                                if (lastInput != null) {
                                                    Optional<ValidationError> vErr = vLog.getErrorByInput("vol.id_avion");
                                                    if (vErr.isPresent()) {
                                                        out.print(vErr.get().toHtml());
                                                    }
                                                }
                                            %>
                                            <label>Avion: </label>
                                            <select name="vol.id_avion" required>
                                                <%for (Avion avion : avions) { %>
                                                <option
                                                        value="<%= avion.getId()%>"
                                                        <%
                                                            if (lastInput != null) {
                                                                if (lastInput.getId_avion() == avion.getId()) {
                                                                    out.print("selected");
                                                                }
                                                            }
                                                        %>
                                                >
                                                    <%= avion.getId() %>
                                                </option>
                                                <% } %>
                                            </select>
                                        </div>

                                        <!-- heure depart+arrivee -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.heure_depart");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Heure Depart: </label>
                                                    <input type="datetime-local"
                                                           name="vol.heure_depart"
                                                           value="<%= lastInput != null ? lastInput.getHeure_depart() : "" %>"
                                                           required/>
                                                </div>
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.heure_arrivee");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Heure Arrivee: </label>
                                                    <input type="datetime-local"
                                                           name="vol.heure_arrivee"
                                                           value="<%= lastInput != null ? lastInput.getHeure_arrivee() : "" %>"
                                                           required/>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- ville depart+destination -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.id_ville_depart");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Ville Depart: </label>
                                                    <select name="vol.id_ville_depart" required>
                                                        <%for (Ville ville : villes) { %>
                                                        <option
                                                                value="<%= ville.getId()%>"
                                                                <%
                                                                    if (lastInput != null) {
                                                                        if (lastInput.getId_ville_depart() == ville.getId()) {
                                                                            out.print("selected");
                                                                        }
                                                                    }
                                                                %>
                                                        >
                                                            <%= ville.getNom() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.id_ville_destination");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Ville Destination: </label>
                                                    <select name="vol.id_ville_destination" required>
                                                        <%for (Ville ville : villes) { %>
                                                        <option
                                                                value="<%= ville.getId()%>"
                                                                <%
                                                                    if (lastInput != null) {
                                                                        if (lastInput.getId_ville_destination() == ville.getId()) {
                                                                            out.print("selected");
                                                                        }
                                                                    }
                                                                %>
                                                        >
                                                            <%= ville.getNom() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- prix -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.prix_place_business");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Prix place business: </label>
                                                    <input type="number"
                                                           name="vol.prix_place_business"
                                                           value="<%= lastInput != null ? lastInput.getPrix_place_business() : "" %>"
                                                           required/>
                                                </div>
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.prix_place_eco");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Prix place eco: </label>
                                                    <input type="number"
                                                           name="vol.prix_place_eco"
                                                           value="<%= lastInput != null ? lastInput.getPrix_place_eco() : "" %>"
                                                           required/>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- promotion -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.nb_place_promo_business");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Promotion en place business: </label>
                                                    <input type="number"
                                                           name="vol.nb_place_promo_business"
                                                           value="<%= lastInput != null ? lastInput.getNb_place_promo_business() : "" %>"
                                                           required/>
                                                </div>
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("vol.nb_place_promo_eco");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label>Promotion en place eco: </label>
                                                    <input type="number"
                                                           name="vol.nb_place_promo_eco"
                                                           value="<%= lastInput != null ? lastInput.getNb_place_promo_eco() : "" %>"
                                                           required/>
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

                        <!-- Data -->
                        <div class="container mt-4">
                            <h2 class="text-center">
                                Liste des vols
                            </h2>
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
                                <% for (VVol v_vol : vvols) { %>
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
