<div class="col-xl">
    <div class="card mb-4" id="analyse-site">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h3 class="text-center mb-0">
                Filtrer
            </h3>
        </div>
        <div class="card-body">
            <form
                    action="fo_vol_filter"
                    method="POST"
                    class="form-inline mb-3"
            >
                <!-- heure depart -->
                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label class="form-label">Heure Depart Min: </label>
                            <input class="form-control" type="datetime-local"
                                   name="volFiltre.heure_depart_min"
                            />
                        </div>
                        <div class="col-6">
                            <label class="form-label">Heure Depart Max: </label>
                            <input class="form-control" type="datetime-local"
                                   name="volFiltre.heure_depart_max"
                            />
                        </div>
                    </div>
                </div>

                <!-- heure arrivee -->
                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label class="form-label">Heure Arrivee Min: </label>
                            <input class="form-control" type="datetime-local"
                                   name="volFiltre.heure_arrivee_min"
                            />
                        </div>
                        <div class="col-6">
                            <label class="form-label">Heure Arrivee Max: </label>
                            <input class="form-control" type="datetime-local"
                                   name="volFiltre.heure_arrivee_max"
                            />
                        </div>
                    </div>
                </div>

                <!-- ville -->
                <div class="mb-3">
                    <div class="row">
                        <div class="col-6">
                            <label class="form-label">Ville Depart: </label>
                            <select class="form-control" name="volFiltre.id_ville_depart">
                                <option value="-1">
                                    Choisir ville Depart
                                </option>
                                <%for (Ville ville : villes) { %>
                                <option value="<%= ville.getId()%>">
                                    <%= ville.getNom() %>
                                </option>
                                <% } %>
                            </select>
                        </div>
                        <div class="col-6">
                            <label class="form-label">Ville Destination: </label>
                            <select class="form-control" name="volFiltre.id_ville_destination">
                                <option value="-1">
                                    Choisir ville Destination
                                </option>
                                <%for (Ville ville : villes) { %>
                                <option value="<%= ville.getId()%>">
                                    <%= ville.getNom() %>
                                </option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                </div>

                <!-- avion -->
                <div class="mb-3">
                    <label class="form-label">Avion: </label>
                    <select class="form-control" name="volFiltre.id_avion">
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

                <button type="submit" class="btn btn-primary">
                    Confimer
                </button>
            </form>
        </div>
    </div>
</div>