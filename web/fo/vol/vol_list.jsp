<%@ page import="views.VVol" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Avion" %>
<%@ page import="entity.Ville" %>
<%
    List<VVol> vvols = (List<VVol>) request.getAttribute("vvols");

    List<Ville> villes = (List<Ville>) request.getAttribute("villes");
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
%>

<section id="filtre">
    <form method="post" action="fo_vol_filter">
        <section id="heure_depart">
            <div>
                <label>Heure Depart Min: </label>
                <input type="datetime-local"
                       name="volFiltre.heure_depart_min"
                />
            </div>
            <div>
                <label>Heure Depart Max: </label>
                <input type="datetime-local"
                       name="volFiltre.heure_depart_max"
                />
            </div>
        </section>
        <section id="heure_arrivee">
            <div>
                <label>Heure Arrivee Min: </label>
                <input type="datetime-local"
                       name="volFiltre.heure_arrivee_min"
                />
            </div>
            <div>
                <label>Heure Arrivee Max: </label>
                <input type="datetime-local"
                       name="volFiltre.heure_arrivee_max"
                />
            </div>
        </section>
        <section id="ville_depart">
            <div>
                <label>Ville Depart: </label>
                <select name="volFiltre.id_ville_depart">
                    <option value="-1">
                        Choisir ville depart
                    </option>
                    <%for (Ville ville : villes) { %>
                    <option value="<%= ville.getId()%>">
                        <%= ville.getNom() %>
                    </option>
                    <% } %>
                </select>
            </div>
        </section>
        <section id="ville_destination">
            <div>
                <label>Ville Destination: </label>
                <select name="volFiltre.id_ville_destination">
                    <option value="-1">
                        Choisir ville destination
                    </option>
                    <%for (Ville ville : villes) { %>
                    <option value="<%= ville.getId()%>">
                        <%= ville.getNom() %>
                    </option>
                    <% } %>
                </select>
            </div>
        </section>
        <section id="avion">
            <div>
                <label>Avion: </label>
                <select name="volFiltre.id_avion">
                    <option value="-1">
                        Choisir avion
                    </option>
                    <%for (Avion avion : avions) { %>
                    <option value="<%= avion.getId()%>">
                        <%= avion.getId() %>
                    </option>
                    <% } %>
                </select>
            </div>
        </section>

        <input type="submit" value="Filter les vols">
    </form>
</section>

<section id="liste">
    <h2>Liste des vols</h2>
    <table>
        <thead>
        <tr>
            <th>Avion</th>
            <th>Ville Depart</th>
            <th>Ville Destination</th>
            <th>Heure Depart</th>
            <th>Heure Arrivee</th>
            <th>Prix place business</th>
            <th>Prix place eco</th>
            <th>Nb promo business</th>
            <th>Nb promo eco</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (VVol v_vol : vvols) { %>
        <tr>
            <td><%= v_vol.getId_avion() %>
            </td>
            <td><%= v_vol.getNom_ville_depart() %>
            </td>
            <td><%= v_vol.getNom_ville_destination() %>
            </td>
            <td><%= v_vol.getHeure_depart() %>
            </td>
            <td><%= v_vol.getHeure_arrivee() %>
            </td>
            <td><%= v_vol.getPrix_place_business() %>
            </td>
            <td><%= v_vol.getPrix_place_eco() %>
            </td>
            <td><%= v_vol.getNb_place_promo_business() %>
            </td>
            <td><%= v_vol.getNb_place_promo_eco() %>
            </td>
            <td>
                <a href="reservation_add?idVol=<%= v_vol.getId() %>">
                    Reserver
                </a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</section>
