<%@ page import="entity.config.PourcentagePromotion" %>
<%@ page import="entity.config.MinNbHeureReservation" %>
<%@ page import="entity.config.MinNbHeureAnnulation" %>
<%@ page import="java.util.List" %>
<%
    List<PourcentagePromotion> pourcentagePromotions = (List<PourcentagePromotion>) request.getAttribute("pourcentagePromotions");
    List<MinNbHeureReservation> minNbHeureReservations = (List<MinNbHeureReservation>) request.getAttribute("minNbHeureReservations");
    List<MinNbHeureAnnulation> minNbHeureAnnulations = (List<MinNbHeureAnnulation>) request.getAttribute("minNbHeureAnnulations");
%>
<html>
<body>
<h1>Nos configurations</h1>

<section id="pourcentagePromotions">
    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Pourcentage</th>
            <th>Derniere modification le</th>
        </tr>
        </thead>
        <tbody>
        <% for (PourcentagePromotion pp : pourcentagePromotions) { %>
        <tr>
            <td>
                <%= pp.getId() %>
            </td>
            <td>
                <%= pp.getVal() %>
            </td>
            <td>
                <%= pp.getDate_modification() %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</section>

<section id="minNbHeureReservations">
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

<section id="minNbHeureAnnulations">
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
