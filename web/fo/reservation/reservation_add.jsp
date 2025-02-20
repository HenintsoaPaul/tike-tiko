<%@ page import="entity.TypeSiege" %>
<%@ page import="java.util.List" %>
<%@ page import="form.ReservationFormData" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%
    String idVol = (String) request.getAttribute("idVol");

    int resteEco = (int) request.getAttribute("resteEco");
    int resteBusiness = (int) request.getAttribute("resteBusiness");

    List<TypeSiege> typeSieges = (List<TypeSiege>) request.getAttribute("typeSieges");

    // form validation
    ReservationFormData lastInput = null;
    ValidationLog vLog = (ValidationLog) request.getAttribute("validationLog");
    if (vLog != null && vLog.hasErrors()) {
        lastInput = (ReservationFormData) vLog.getLastInput();
    }
%>

<section id="table">
    <table>
        <thead>
        <tr>
            <th>Vol</th>
            <th>Reste business</th>
            <th>Reste eco</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><%= idVol %>
            </td>
            <td><%= resteBusiness %>
            </td>
            <td><%= resteEco %>
            </td>
        </tr>
        </tbody>
    </table>
</section>

<form action="reservation_save" method="POST">
    <div>
        <label>Vol</label>
        <input type="number"
               name="formData.id_vol"
               value="<%= idVol %>"
               readonly>
    </div>

    <div>
        <label>Client:</label>
        <input type="text"
               name="formData.nom_client"
               value="Anonymous">
    </div>

    <%-- Date reservation --%>
    <div class="mb-3">
        <label class="form-label">Date Reservation</label>
        <% if (lastInput != null) {
            ValidationError vErr = vLog.getErrorByInput("formData.date_reservation");
            if (vErr != null) {
        %>
        <div class="alert alert-danger p-2">
            <ul class="mb-0">
                <% for (String err : vErr.getErrors()) { %>
                <li><%= err %>
                </li>
                <% } %>
            </ul>
        </div>
        <%
                }
            }
        %>
        <input type="datetime-local"
               name="formData.date_reservation"
               class="form-control"
               value="<%= lastInput != null ? lastInput.getDate_reservation() : "" %>"/>
    </div>

    <div>
        <label>Type Siege: </label>
        <select name="formData.id_type_siege">
            <option value="">Choisir le siege</option>
            <%for (TypeSiege typeSiege : typeSieges) { %>
            <option value="<%= typeSiege.getId()%>">
                <%= typeSiege.getNom() %>
            </option>
            <% } %>
        </select>
    </div>

    <input type="submit" value="Valider ma reservation"/>
</form>
