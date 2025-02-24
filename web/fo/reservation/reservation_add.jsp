<%@ page import="entity.TypeSiege" %>
<%@ page import="java.util.List" %>
<%@ page import="form.ReservationFormData" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%@ page import="java.util.Optional" %>
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
        <input type="datetime-local"
               name="formData.date_reservation"
               class="form-control"
               value="<%= lastInput != null ? lastInput.getDate_reservation() : "" %>"/>
        <%
            if (lastInput != null) {
                Optional<ValidationError> vErr = vLog.getErrorByInput("formData.date_reservation");
                if (vErr.isPresent()) {
                    out.print(vErr.get().toHtml());
                }
            }
        %>
    </div>

    <%-- Type Siege --%>
    <div class="mb-3">
        <label class="form-label">Type Siege: </label>
        <select name="formData.id_type_siege">
            <option value="">Choisir le siege</option>
            <%for (TypeSiege typeSiege : typeSieges) { %>
            <option
                    value="<%= typeSiege.getId()%>"
                    <%
                        if (lastInput != null) {
                            if (lastInput.getId_type_siege() == typeSiege.getId()) {
                                out.print("selected");
                            }
                        }
                    %>
            >
                <%= typeSiege.getNom() %>
            </option>
            <% } %>
        </select>
        <%
            if (lastInput != null) {
                Optional<ValidationError> vErr = vLog.getErrorByInput("formData.id_type_siege");
                if (vErr.isPresent()) {
                    out.print(vErr.get().toHtml());
                }
            }
        %>
    </div>

    <input type="submit" value="Valider ma reservation"/>
</form>
