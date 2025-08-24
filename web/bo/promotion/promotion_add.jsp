<%@ page import="java.util.List" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="entity.Vol" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%@ page import="java.util.Optional" %>
<%@ page import="entity.TypeSiege" %>
<%@ page import="entity.config.Promotion" %>
<%
    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");

    // form validation
    Promotion lastInput = null;
    ValidationLog vLog = (ValidationLog) request.getAttribute("validationLog");
    if (vLog != null && vLog.hasErrors()) {
        lastInput = (Promotion) vLog.getLastInput();
    }

    pageContext.setAttribute("activePage", "promotionAdd");
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

    <title>Ajouter promotion</title>

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
                        <%-- Form --%>
                        <div class="col-xl">
                            <div class="card mb-4" id="analyse-site">
                                <div class="card-header d-flex justify-content-between align-items-center">
                                    <h1 class="text-center mb-0">
                                        Ajouter un nouvelle promotion
                                    </h1>
                                </div>
                                <div class="card-body">
                                    <form
                                            action="promotion_save"
                                            method="POST"
                                            class="form-inline mb-3"
                                    >
                                        <!-- date fin -->
                                        <div class="mb-3">
                                            <%
                                                if (lastInput != null) {
                                                    Optional<ValidationError> vErr = vLog.getErrorByInput("promotion.date_fin");
                                                    if (vErr.isPresent()) {
                                                        out.print(vErr.get().toHtml());
                                                    }
                                                }
                                            %>
                                            <label for="date_fin"
                                                   class="form-label">Date fin: </label>
                                            <input id="date_fin"
                                                   type="datetime-local"
                                                   class="form-control"
                                                   name="promotion.date_fin"
                                                   value="<%= lastInput != null ? lastInput.getDate_fin() : "" %>"
                                                   required/>
                                        </div>

                                        <!-- vol + type siege -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("promotion.id_vol");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label for="id_vol"
                                                           class="form-label">Vol: </label>
                                                    <select id="id_vol"
                                                            name="promotion.id_vol"
                                                            required
                                                            class="form-control">
                                                        <%for (Vol vol : vols) { %>
                                                        <option
                                                                value="<%= vol.getId()%>"
                                                                <%
                                                                    if (lastInput != null) {
                                                                        if (lastInput.getId_vol() == vol.getId()) {
                                                                            out.print("selected");
                                                                        }
                                                                    }
                                                                %>
                                                        >
                                                            <%= vol.getId() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("promotion.id_type_siege");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label for="id_type_siege"
                                                           class="form-label">Type siege: </label>
                                                    <select id="id_type_siege"
                                                            name="promotion.id_type_siege"
                                                            required
                                                            class="form-control">
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
                                                            <%= typeSiege.getId() %>
                                                        </option>
                                                        <% } %>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- prix_promo + nb_place -->
                                        <div class="mb-3">
                                            <div class="row">
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("promotion.prix_promo");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label for="prix_promo"
                                                           class="form-label">Prix promo: </label>
                                                    <input id="prix_promo"
                                                           type="number"
                                                           class="form-control"
                                                           name="promotion.prix_promo"
                                                           value="<%= lastInput != null ? lastInput.getPrix_promo() : "" %>"
                                                           required/>
                                                </div>
                                                <div class="col-6">
                                                    <%
                                                        if (lastInput != null) {
                                                            Optional<ValidationError> vErr = vLog.getErrorByInput("promotion.nb_place");
                                                            if (vErr.isPresent()) {
                                                                out.print(vErr.get().toHtml());
                                                            }
                                                        }
                                                    %>
                                                    <label for="nb_place" class="form-label">Nb place: </label>
                                                    <input id="nb_place"
                                                           type="number"
                                                           class="form-control"
                                                           name="promotion.nb_place"
                                                           value="<%= lastInput != null ? lastInput.getNb_place() : "" %>"
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
