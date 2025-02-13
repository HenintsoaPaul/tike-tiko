<%@ page import="entity.Utilisateur" %>
<%@ page import="src.summer.beans.validation.ValidationLog" %>
<%@ page import="src.summer.beans.validation.ValidationError" %>
<%
    Utilisateur lastInput = null;
    ValidationLog vLog = ( ValidationLog ) request.getAttribute( "validationLog" );
    if ( vLog != null && vLog.hasErrors() ) {
        lastInput = ( Utilisateur ) vLog.getLastInput();
    }
%>

<div>
    <p>
        <a href="logout">Logout</a>
    </p>
</div>

<form action="login_auth" method="POST">
    <div>
        <label>nom: </label>
        <% if ( lastInput != null ) { %>
        <%
            ValidationError vErr = vLog.getErrorByInput( "utilisateur.nom" );
            if ( vErr != null ) {
                out.print( "<ul style=\"color: red\">" );
                for ( String err : vErr.getErrors() ) {
                    out.print( "<li>" + err + "</li>" );
                }
                out.print( "</ul>" );
            }
        %>
        <label>
            <input type="text" name="utilisateur.nom" value="<%= lastInput.getNom() %>"/>
        </label>
        <% } else { %>
        <label>
            <input type="text" name="utilisateur.nom"/>
        </label>
        <% } %>
    </div>

    <div>
        <label>password: </label>
        <% if ( lastInput != null ) { %>
        <%
            ValidationError vErr = vLog.getErrorByInput( "utilisateur.password" );
            if ( vErr != null ) {
                out.print( "<ul style=\"color: red\">" );
                for ( String err : vErr.getErrors() ) {
                    out.print( "<li>" + err + "</li>" );
                }
                out.print( "</ul>" );
            }
        %>
        <label>
            <input type="password" name="utilisateur.password" value="<%= lastInput.getPassword() %>"/>
        </label>
        <% } else { %>
        <label>
            <input type="password" name="utilisateur.password"/>
        </label>
        <% } %>
    </div>

    <input type="submit" value="Envoyer"/>
</form>
