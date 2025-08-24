<%@ page import="java.util.List" %>
<%@ page import="views.VReductionTrancheAge" %>
<%@ page import="entity.config.age.TrancheAge" %>

<%
    List<VReductionTrancheAge> reductionTrancheAges = (List<VReductionTrancheAge>) request.getAttribute("vReductionTrancheAges");

    List<TrancheAge> trancheAges = (List<TrancheAge>) request.getAttribute("trancheAges");
%>

<%-- reduction tranche age --%>
<div class="card mt-4">
    <h3 class="card-header">
        Reduction par tranche d'age
    </h3>
    <div class="card-body">
        <table class="table table-hover">
            <thead class="thead-dark">
            <tr>
                <th>#</th>
                <th>Valeur</th>
                <th>Tranche</th>
                <th>Derniere modification le</th>
            </tr>
            </thead>
            <tbody>
            <% for (VReductionTrancheAge res : reductionTrancheAges) { %>
            <tr>
                <td>
                    <%= res.getId() %>
                </td>
                <td>
                    <%= res.getVal_pourcentage() %> %
                </td>
                <td>
                    <%= res.getTranche() %>
                </td>
                <td>
                    <%= formatterService.format(res.getDate_modification()) %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="card-footer">
        <form
                action="config_reduction_tranche_age"
                method="POST"
                class="form-inline mb-3"
        >
            <div class="mb-3">
                <div class="row">
                    <!-- val -->
                    <div class="col-6">
                        <label for="val_pourcentage_inp"
                               class="form-label">Reduction (%): </label>
                        <input type="number"
                               name="reductionTrancheAge.val_pourcentage"
                               class="form-control"
                               min="0"
                               max="100"
                               id="val_pourcentage_inp"
                               required>
                    </div>
                    <div class="col-6">
                        <label for="id_tranche_age_inp"
                               class="form-label">Tranche: </label>
                        <select id="id_tranche_age_inp"
                                class="form-control"
                                name="reductionTrancheAge.id_tranche_age"
                                required>
                            <% for (TrancheAge ta : trancheAges) { %>
                            <option value="<%= ta.getId() %>">
                                <%= ta.getNom() %>
                            </option>
                            <% } %>
                        </select>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">
                Confimer
            </button>
        </form>
    </div>
</div>