<%@ page import="src.summer.beans.validation.ValidationError" %>
<%@ page import="java.util.Optional" %>
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
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <title>Tike-Tiko Login</title>
    <div>
        <%@ include file="/layout/link_header.jsp" %>
    </div>

    <style>
        :root {
            --dark-blue: #1A365D;
            --black-20: rgba(0, 0, 0, 0.2);
            --white-60: rgba(255, 255, 255, 0.6);
        }

        body {
            background: linear-gradient(45deg, var(--white-60) 60%, var(--dark-blue) 20%, var(--black-20) 20%);
            min-height: 100vh;
            overflow: hidden;
        }

        .login-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
            backdrop-filter: blur(10px);
            transform: translateY(0);
            transition: all 0.3s ease;
        }

        .login-card:hover {
            transform: translateY(-5px);
        }

        .floating-circle {
            position: absolute;
            background: var(--dark-blue);
            opacity: 0.1;
            border-radius: 50%;
            animation: float 15s infinite;
        }

        @keyframes float {
            0%, 100% {
                transform: translateY(0) rotate(0deg);
            }
            50% {
                transform: translateY(-20px) rotate(180deg);
            }
        }
    </style>
</head>

<body class="d-flex align-items-center">
<!-- Animated background elements -->
<div class="floating-circle" style="width: 300px; height: 300px; top: -50px; left: -50px;"></div>
<div class="floating-circle" style="width: 200px; height: 200px; bottom: -30px; right: -30px;"></div>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-xl-4 col-lg-5 col-md-7">
            <div class="login-card p-5">
                <h2 class="text-center mb-4 fw-bold text-dark">Login Client</h2>

                <form action="fo_login_auth" method="POST">
                    <!-- Email Input -->
                    <div class="mb-4">
                        <% if (lastInput != null) {
                            Optional<ValidationError> vErr = vLog.getErrorByInput("utilisateur.email");
                            if (vErr.isPresent()) { %>
                        <div class="alert alert-danger p-2">
                            <%= vErr.get().toHtml() %>
                        </div>
                        <% }
                        } %>
                        <input
                                type="email"
                                class="form-control form-control-lg rounded-pill"
                                id="email"
                                placeholder="Adresse email"
                                name="utilisateur.email"
                                required
                        >
                    </div>

                    <!-- Password Input -->
                    <div class="mb-4">
                        <% if (lastInput != null) {
                            Optional<ValidationError> vErr = vLog.getErrorByInput("utilisateur.password");
                            if (vErr.isPresent()) { %>
                        <div class="alert alert-danger p-2">
                            <%= vErr.get().toHtml() %>
                        </div>
                        <% }
                        } %>
                        <input
                                type="password"
                                class="form-control form-control-lg rounded-pill"
                                id="password"
                                placeholder="Mot de passe"
                                name="utilisateur.password"
                                required
                        >
                    </div>

                    <!-- Submit Button -->
                    <button type="submit" class="btn btn-dark btn-lg w-100 rounded-pill py-3">
                        Se connecter
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>