<%@ page import="entity.Utilisateur" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%
    Utilisateur lastInput = null;
    ValidationLog vLog = (ValidationLog) request.getAttribute("validationLog");
    if (vLog != null && vLog.hasErrors()) {
        lastInput = (Utilisateur) vLog.getLastInput();
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Connexion</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center justify-content-center vh-100 bg-light">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg">
                <div class="card-header bg-primary text-white text-center">
                    <h3>Connexion</h3>
                </div>
                <div class="card-body">
                    <p class="text-center">
                        <a href="logout" class="btn btn-danger">Se déconnecter</a>
                    </p>

                    <form action="login_auth" method="POST">
                        <!-- Nom d'utilisateur -->
                        <div class="mb-3">
                            <label class="form-label">Nom :</label>
                            <% if (lastInput != null) { %>
                            <% ValidationError vErr = vLog.getErrorByInput("utilisateur.nom"); %>
                            <% if (vErr != null) { %>
                            <div class="alert alert-danger p-2">
                                <ul class="mb-0">
                                    <% for (String err : vErr.getErrors()) { %>
                                    <li><%= err %>
                                    </li>
                                    <% } %>
                                </ul>
                            </div>
                            <% } %>
                            <% } %>
                            <input type="text" class="form-control" name="utilisateur.nom"
                                   value="<%= lastInput != null ? lastInput.getNom() : "" %>"/>
                        </div>

                        <!-- Mot de passe -->
                        <div class="mb-3">
                            <label class="form-label">Mot de passe :</label>
                            <% if (lastInput != null) { %>
                            <% ValidationError vErr = vLog.getErrorByInput("utilisateur.password"); %>
                            <% if (vErr != null) { %>
                            <div class="alert alert-danger p-2">
                                <ul class="mb-0">
                                    <% for (String err : vErr.getErrors()) { %>
                                    <li><%= err %>
                                    </li>
                                    <% } %>
                                </ul>
                            </div>
                            <% } %>
                            <% } %>
                            <input type="password" class="form-control" name="utilisateur.password"
                                   value="<%= lastInput != null ? lastInput.getPassword() : "" %>"/>
                        </div>

                        <div class="text-center">
                            <input type="submit" class="btn btn-primary w-100" value="Se connecter"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS (optionnel pour certaines fonctionnalités comme les dropdowns) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
