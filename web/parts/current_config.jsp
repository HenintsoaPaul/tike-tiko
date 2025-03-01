<%@ page import="dto.ConfigDTO" %>

<%
    ConfigDTO configDTO = (ConfigDTO) request.getAttribute("configDTO");
%>

<div class="container mt-4">
    <h4 class="text-center">
        Configurations Actuelles
    </h4>
    <table class="table table-bordered table-striped mt-3">
        <thead>
        <tr>
            <th>Promo Business</th>
            <th>Promo Eco</th>
            <th>Heure Reservation</th>
            <th>Heure Annulation</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <%= configDTO.getPourcentagePromotionBusiness().getVal() %>
            </td>
            <td>
                <%= configDTO.getPourcentagePromotionEco().getVal() %>
            </td>
            <td>
                <%= configDTO.getMinNbHeureReservation().getVal() %>
            </td>
            <td>
                <%= configDTO.getMinNbHeureAnnulation().getVal() %>
            </td>
        </tr>
        </tbody>
    </table>
</div>