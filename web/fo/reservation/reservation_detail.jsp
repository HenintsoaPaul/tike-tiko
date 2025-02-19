<%@ page import="views.VReservation" %>
<%
    VReservation vReservation = (VReservation) request.getAttribute("vReservation");
%>

<section id="liste">
    <h2>Details reservation vol</h2>
    <hr>
    <% if (vReservation == null) { %>
    <p>Reservation Introuvable</p>
    <% } else { %>
    <table>
        <thead>
        <tr>
            <th>Id vol</th>
            <th>Type Siege</th>
            <th>Date Reservation</th>
            <th>Prix sans promo</th>
            <th>Prix avec promo</th>
            <th>Etat Reservation</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= vReservation.getId_vol() %>
            </td>
            <td><%= vReservation.getNom_type_siege() %>
            </td>
            <td><%= vReservation.getHeure_reservation() %>
            </td>
            <td><%= vReservation.getPrix_sans_promo() %>
            </td>
            <td><%= vReservation.getPrix_avec_promo() %>
            </td>
            <td><%= vReservation.getNom_etat_reservation() %>
            </td>
        </tr>
        </tbody>
    </table>
    <hr>
    <form method="get" action="reservation_cancel">
        <input type="datetime-local"
               name="dateAnnulation"
               required>

        <input type="hidden"
               name="idReservation"
               value="<%= vReservation.getId() %>">

        <input type="hidden"
               name="idVol"
               value="<%= vReservation.getId_vol() %>">

        <input type="submit" value="Annuler la reservation">
    </form>
    <% } %>
</section>
