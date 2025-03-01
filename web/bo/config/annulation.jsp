<%@ page import="entity.config.MinNbHeureAnnulation" %>
<%@ page import="java.util.List" %>

<%
    List<MinNbHeureAnnulation> minNbHeureAnnulations = (List<MinNbHeureAnnulation>) request.getAttribute("minNbHeureAnnulations");
%>

<%-- promotion --%>
<div class="card mt-4">
    <h3 class="card-header">
        Heure limite d'annulation
    </h3>
    <div class="card-body">
        <table class="table table-hover">
            <thead class="thead-dark">
            <tr>
                <th>#</th>
                <th>Valeur</th>
                <th>Derniere modification le</th>
            </tr>
            </thead>
            <tbody>
            <% for (MinNbHeureAnnulation res : minNbHeureAnnulations) { %>
            <tr>
                <td>
                    <%= res.getId() %>
                </td>
                <td>
                    <%= res.getVal() %>
                </td>
                <td>
                    <%= res.getDate_modification() %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <div class="card-footer">
        <form
                action="config_annulation"
                method="POST"
                class="form-inline mb-3"
        >
            <div class="mb-3">
                <div class="row">
                    <!-- val -->
                    <div class="col-6">
                        <label class="form-label">Nb d'heure avant depart vol: </label>
                        <input type="number"
                               name="minNbHeureAnnulation.val"
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
