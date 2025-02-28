<%@ page import="java.util.List" %>
<%@ page import="entity.TypeSiege" %>
<%@ page import="views.VPourcentagePromotion" %>

<%
    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");

    List<VPourcentagePromotion> vPourcentagePromotions = (List<VPourcentagePromotion>) request.getAttribute("vPourcentagePromotions");
%>

<%-- promotion --%>
<div>
    <div class="row">
        <%-- Form --%>
        <div class="col-xl">
            <div class="card mb-4" id="analyse-site">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h3 class="text-center mb-0">
                        Pourcentage promotion
                    </h3>
                </div>
                <div class="card-body">
                    <form
                            action="config_pourcentage"
                            method="POST"
                            class="form-inline mb-3"
                    >
                        <div class="mb-3">
                            <div class="row">
                                <!-- type_siege -->
                                <div class="col-6">
                                    <label class="form-label">Type Siege: </label>
                                    <select name="pourcentagePromotion.id_type_siege"
                                            class="form-control"
                                            required>
                                        <% for (TypeSiege ts : typeSieges) { %>
                                        <option value="<%= ts.getId() %>">
                                            <%= ts.getNom() %>
                                        </option>
                                        <% } %>
                                    </select>
                                </div>
                                <!-- type_siege -->
                                <div class="col-6">
                                    <label class="form-label">Pourcentage: </label>
                                    <input type="number"
                                           name="pourcentagePromotion.val"
                                           class="form-control"
                                           required>
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
            <h4 class="text-center">
                Historique de modification des pourcentages de promotion
            </h4>
            <table class="table table-bordered table-striped mt-3">
                <thead class="thead-dark">
                <tr>
                    <th>#</th>
                    <th>Pourcentage</th>
                    <th>TypeSiege</th>
                    <th>Derniere modification le</th>
                </tr>
                </thead>
                <tbody>
                <% for (VPourcentagePromotion pp : vPourcentagePromotions) { %>
                <tr>
                    <td>
                        <%= pp.getId() %>
                    </td>
                    <td>
                        <%= pp.getVal() %>
                    </td>
                    <td>
                        <%= pp.getNom_type_siege() %>
                    </td>
                    <td>
                        <%= pp.getDate_modification() %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>