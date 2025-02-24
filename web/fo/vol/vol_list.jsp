<%@ page import="views.VVol" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Avion" %>
<%@ page import="entity.Ville" %>
<%
    List<VVol> vvols = (List<VVol>) request.getAttribute("vvols");

    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");

    pageContext.setAttribute("activePage", "volList");
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

    <title>liste article</title>

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
                        <%-- Filtre --%>
                        <div class="col-xl">
                            <div class="card mb-4" id="analyse-site">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h3 class="text-center mb-0">
                                        Filtrer
                                    </h3>
                                </div>
                                <div class="card-body">
                                    <form
                                            action="fo_vol_filter"
                                            method="POST"
                                            class="form-inline mb-3"
                                    >
                                        <!-- heure depart -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <label>Heure Depart Min: </label>
                                                    <input type="datetime-local"
                                                           name="volFiltre.heure_depart_min"
                                                    />
                                                </div>
                                                <div class="col-6">
                                                    <label>Heure Depart Max: </label>
                                                    <input type="datetime-local"
                                                           name="volFiltre.heure_depart_max"
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                        <!-- heure arrivee -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <label>Heure Arrivee Min: </label>
                                                    <input type="datetime-local"
                                                           name="volFiltre.heure_arrivee_min"
                                                    />
                                                </div>
                                                <div class="col-6">
                                                    <label>Heure Arrivee Max: </label>
                                                    <input type="datetime-local"
                                                           name="volFiltre.heure_arrivee_max"
                                                    />
                                                </div>
                                            </div>
                                        </div>

                                        <!-- ville -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <label>Ville Depart: </label>
                                                    <select name="volFiltre.id_ville_depart">
                                                        <option value="-1">
                                                            Choisir ville Depart
                                                        </option>
                                                        <%for (Ville ville : villes) { %>
                                                        <option value="<%= ville.getId()%>">
                                                            <%= ville.getNom() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                                <div class="col-6">
                                                    <label>Ville Destination: </label>
                                                    <select name="volFiltre.id_ville_destination">
                                                        <option value="-1">
                                                            Choisir ville Destination
                                                        </option>
                                                        <%for (Ville ville : villes) { %>
                                                        <option value="<%= ville.getId()%>">
                                                            <%= ville.getNom() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- avion -->
                                        <div class="mb-3">
                                            <label>Avion: </label>
                                            <select name="volFiltre.id_avion">
                                                <option value="-1">
                                                    Choisir avion
                                                </option>
                                                <%for (Avion avion : avions) { %>
                                                <option value="<%= avion.getId()%>">
                                                    <%= avion.getId() %>
                                                </option>
                                                <% } %>
                                            </select>
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
                                    <th>Actions</th>
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
                                    <td>
                                        <a href="reservation_add?idVol=<%= v_vol.getId() %>">
                                            Reserver
                                        </a>
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
<div th:replace="~{layout/script}"></div>
</body>
</html>
