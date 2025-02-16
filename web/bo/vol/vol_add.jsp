<%@ page import="entity.Ville" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Avion" %>
<%
    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
%>

<form action="vol_save" method="POST">
    <section id="heure">
        <div>
            <label>Heure Depart: </label>
            <input type="datetime-local"
                   name="vol.heure_depart"
                   required/>
        </div>
        <div>
            <label>Heure Arrivee: </label>
            <input type="datetime-local"
                   name="vol.heure_arrivee"
                   required/>
        </div>
    </section>

    <section id="ville">
        <div>
            <label>Avion: </label>
            <select name="vol.id_ville_depart" required>
                <%for (Avion avion : avions) { %>
                <option value="<%= avion.getId()%>">
                    <%= avion.getId() %>
                </option>
                <% } %>
            </select>
        </div>
        <div>
            <label>Ville Depart: </label>
            <select name="vol.id_ville_depart" required>
                <%for (Ville ville : villes) { %>
                <option value="<%= ville.getId()%>">
                    <%= ville.getNom() %>
                </option>
                <% } %>
            </select>
        </div>
        <div>
            <label>Ville Destination: </label>
            <select name="vol.id_ville_destination" required>
                <%for (Ville ville : villes) { %>
                <option value="<%= ville.getId()%>">
                    <%= ville.getNom() %>
                </option>
                <% } %>
            </select>
        </div>
    </section>

    <section id="prix">
        <div>
            <label>Prix place business: </label>
            <input type="number"
                   name="vol.prix_place_business"
                   required/>
        </div>
        <div>
            <label>Prix place eco: </label>
            <input type="number"
                   name="vol.prix_place_eco"
                   required/>
        </div>
    </section>

    <section id="promotion">
        <div>
            <label>Promotion en place business: </label>
            <input type="number"
                   name="vol.nb_place_promo_business"
                   required/>
        </div>
        <div>
            <label>Promotion en place eco: </label>
            <input type="number"
                   name="vol.nb_place_promo_eco"
                   required/>
        </div>
    </section>

    <input type="submit" value="Envoyer"/>
</form>
