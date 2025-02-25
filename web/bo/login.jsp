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
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <title>Tike-Tiko Login</title>

    <div>
        <%@ include file="/layout/link_header.jsp" %>
    </div>
</head>

<body class="g-sidenav-show bg-gray-100">
<div class="container">
    <div class="row mt-lg-n10 mt-md-n11 mt-n10">
        <div class="col-xl-4 col-lg-5 col-md-7 mx-auto">
            <div class="card z-index-0">
                <div class="card-body">
                    <form
                            action="login_auth"
                            method="POST"
                    >

                        <div class="mb-3">
                            <%
                                if (lastInput != null) {
                                    Optional<ValidationError> vErr = vLog.getErrorByInput("utilisateur.email");
                                    if (vErr.isPresent()) {
                                        out.print(vErr.get().toHtml());
                                    }
                                }
                            %>
                            <label
                                    for="email"
                                    class="form-label"
                            >Email
                            </label>
                            <input
                                    type="email"
                                    required
                                    class="form-control"
                                    id="email"
                                    placeholder="Entrer votre email"
                                    name="utilisateur.email"
                            />
                        </div>

                        <div class="mb-3">
                            <%
                                if (lastInput != null) {
                                    Optional<ValidationError> vErr = vLog.getErrorByInput("utilisateur.password");
                                    if (vErr.isPresent()) {
                                        out.print(vErr.get().toHtml());
                                    }
                                }
                            %>
                            <label
                                    for="password"
                                    class="form-label"
                            >Mot de passe
                            </label>
                            <input
                                    type="password"
                                    required
                                    class="form-control"
                                    id="password"
                                    name="utilisateur.password"
                            />
                        </div>

                        <div class="text-center">
                            <button
                                    type="submit"
                                    class="btn bg-gradient-dark w-100 my-4 mb-2"
                            >
                                Confirmer
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
