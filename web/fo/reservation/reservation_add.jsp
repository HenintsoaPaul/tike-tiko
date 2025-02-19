<%@ page import="entity.TypeSiege" %>
<%@ page import="java.util.List" %>
<%
    String idVol = (String) request.getAttribute("idVol");
    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");
%>

<form action="reservation_save" method="POST">
    <div>
        <label>Vol</label>
        <input type="text"
               name="idVol"
               value="<%= idVol %>"
               readonly>
    </div>

    <div>
        <label>Client:</label>
        <input type="text"
               name="nomClient"
               value="Anonymous">
    </div>

    <div>
        <label>Date Reservation</label>
        <input type="datetime-local"
               name="dateReservation"
               required>
    </div>

    <div>
        <label>Type Siege: </label>
        <select name="idTypeSiege"
                required>
            <%for (TypeSiege typeSiege : typeSieges) { %>
            <option value="<%= typeSiege.getId()%>">
                <%= typeSiege.getNom() %>
            </option>
            <% } %>
        </select>
    </div>

    <input type="submit" value="Envoyer"/>
</form>
