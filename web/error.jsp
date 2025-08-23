<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">

            <div class="card shadow border-danger">
                <div class="card-header bg-danger text-white">
                    <h4 class="mb-0">An Error Occurred</h4>
                </div>
                <div class="card-body">

                    <!-- Main Exception -->
                    <div class="alert alert-danger">
                        <h5>Main Exception</h5>
                        <p><strong>Type:</strong> <%= exception.getClass().getName() %>
                        </p>
                        <p><strong>Message:</strong> <%= exception.getMessage() %>
                        </p>
                    </div>

                    <!-- Root Cause(s) -->
                    <%
                        Throwable cause = exception.getCause();
                        if (cause != null) {
                    %>
                    <div class="alert alert-warning">
                        <h5>Caused by</h5>
                        <p><strong>Type:</strong> <%= cause.getClass().getName() %>
                        </p>
                        <p><strong>Message:</strong> <%= cause.getMessage() %>
                        </p>
                    </div>
                    <%
                        }
                    %>

                    <!-- Show full chain -->
                    <div class="accordion" id="errorAccordion">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="headingChain">
                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseChain" aria-expanded="false"
                                        aria-controls="collapseChain">
                                    Show Exception Chain
                                </button>
                            </h2>
                            <div id="collapseChain" class="accordion-collapse collapse" aria-labelledby="headingChain"
                                 data-bs-parent="#errorAccordion">
                                <div class="accordion-body">
                                    <%
                                        Throwable t = exception;
                                        int level = 1;
                                        while (t != null) {
                                    %>
                                    <div class="mb-3">
                                        <h6>Level <%= level %>:</h6>
                                        <p><strong>Type:</strong> <%= t.getClass().getName() %>
                                        </p>
                                        <p><strong>Message:</strong> <%= t.getMessage() %>
                                        </p>
                                    </div>
                                    <%
                                            t = t.getCause();
                                            level++;
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="card-footer text-muted text-center">
                    Please contact support if the problem persists.
                </div>
            </div>

        </div>
    </div>
</div>

<!-- Bootstrap JS (link not included per your request) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
