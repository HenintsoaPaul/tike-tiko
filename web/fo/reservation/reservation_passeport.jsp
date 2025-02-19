<%
    String idReservation = (String) request.getAttribute("idReservation");

    if (idReservation == null || idReservation.isEmpty()) {
        idReservation = "1";
    }
%>

<form method="POST"
      action="passeport_save"
      enctype="multipart/form-data">

    <input type="text"
           name="idReservation"
           value="<%= idReservation %>"
           readonly>

    <div>
        <label>Passeport</label>
        <input type="file"
               name="passeportFile"
               required>
    </div>

    <input type="submit" value="Envoyer passeport"/>
</form>
