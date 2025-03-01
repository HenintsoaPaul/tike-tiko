<%@ page import="entity.config.MinNbHeureReservation" %>
<%@ page import="java.util.List" %>

<%
    List<MinNbHeureReservation> minNbHeureReservations = (List<MinNbHeureReservation>) request.getAttribute("minNbHeureReservations");
%>

<%-- reservation --%>
<div class="card mt-4">
    <h3 class="card-header">
        Heure limite de reservation
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
            <% for (MinNbHeureReservation res : minNbHeureReservations) { %>
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
                action="config_reservation"
                method="POST"
                class="form-inline mb-3"
        >
            <div class="mb-3">
                <div class="row">
                    <!-- val -->
                    <div class="col-6">
                        <label class="form-label">Nb d'heure avant depart vol: </label>
                        <input type="number"
                               name="minNbHeureReservation.val"
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