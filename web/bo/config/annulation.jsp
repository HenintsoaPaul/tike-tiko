<%@ page import="entity.config.MinNbHeureAnnulation" %>
<%@ page import="java.util.List" %>

<%
    List<MinNbHeureAnnulation> minNbHeureAnnulations = (List<MinNbHeureAnnulation>) request.getAttribute("minNbHeureAnnulations");
%>

<%-- promotion --%>
<div>
    <div class="row">
        <%-- Form --%>
        <div class="col-xl">
            <div class="card mb-4" id="analyse-site">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h3 class="text-center mb-0">
                        Limite Annulation
                    </h3>
                </div>
                <div class="card-body">
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
        </div>

        <!-- Data -->
        <div class="container mt-4">
            <h4 class="text-center">
                Historique de modification des limites de annulation
            </h4>
            <table class="table table-bordered table-striped mt-3">
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
    </div>
</div>