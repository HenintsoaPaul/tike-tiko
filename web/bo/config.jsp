<%@ page import="entity.config.PourcentagePromotion" %>
<%@ page import="entity.config.MinNbHeureReservation" %>
<%@ page import="entity.config.MinNbHeureAnnulation" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.TypeSiege" %>
<%@ page import="views.VPourcentagePromotion" %>
<%
    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");

    List<VPourcentagePromotion> vPourcentagePromotions = (List<VPourcentagePromotion>) request.getAttribute("vPourcentagePromotions");

    List<PourcentagePromotion> pourcentagePromotions = (List<PourcentagePromotion>) request.getAttribute("pourcentagePromotions");
    List<MinNbHeureReservation> minNbHeureReservations = (List<MinNbHeureReservation>) request.getAttribute("minNbHeureReservations");
    List<MinNbHeureAnnulation> minNbHeureAnnulations = (List<MinNbHeureAnnulation>) request.getAttribute("minNbHeureAnnulations");
%>
<html>
<body>
<h1>Nos configurations</h1>

<section id="pourcentagePromotions">
    <div>
        <form method="post" action="config_pourcentage">
            <div>
                <label>Type Siege</label>
                <select name="pourcentagePromotion.id_type_siege"
                        required>
                    <% for (TypeSiege ts : typeSieges) { %>
                    <option value="<%= ts.getId() %>">
                        <%= ts.getNom() %>
                    </option>
                    <% } %>
                </select>
            </div>
            <div>
                <label>Poucentage de Promotion</label>
                <input type="number"
                       name="pourcentagePromotion.val"
                       required>
            </div>

            <div>
                <input type="submit">
            </div>
        </form>
    </div>

    <table>
        <thead>
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
</section>

<hr>

<section id="minNbHeureReservations">
    <div>
        <form method="post" action="config_reservation">
            <div>
                <label>Heure limite reservation</label>
                <input type="number"
                       name="minNbHeureReservation.val"
                       required>
            </div>

            <div>
                <input type="submit">
            </div>
        </form>
    </div>

    <table>
        <thead>
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
</section>

<hr>

<section id="minNbHeureAnnulations">
    <div>
        <form method="post" action="config_annulation">
            <div>
                <label>Heure limite annulation</label>
                <input type="number"
                       name="minNbHeureAnnulation.val"
                       required>
            </div>

            <div>
                <input type="submit">
            </div>
        </form>
    </div>

    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Valeur</th>
            <th>Derniere modification le</th>
        </tr>
        </thead>
        <tbody>
        <% for (MinNbHeureAnnulation ann : minNbHeureAnnulations) { %>
        <tr>
            <td>
                <%= ann.getId() %>
            </td>
            <td>
                <%= ann.getVal() %>
            </td>
            <td>
                <%= ann.getDate_modification() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</section>
</body>
</html>
