<%@ page import="views.VReservation" %>
<%@ page import="java.util.List" %>
<%
    List<VReservation> vReservations = (List<VReservation>) request.getAttribute("vReservations");

    pageContext.setAttribute("activePage", "foReservationList");
%>

<!DOCTYPE html>

<!DOCTYPE html>

<html
        lang="en"
        class="light-style layout-menu-fixed"
        dir="ltr"
        data-theme="theme-default"
        data-template="vertical-menu-template-free"
>
<head>
    <meta charset="utf-8"/>
    <meta name="description"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Mes Reservations</title>

    <div>
        <%@ include file="/layout/link_header.jsp" %>
    </div>

    <script>
        function downloadPdf(reservationId) {
            fetch(`http://localhost:8081/download-pdf/api?id=${reservationId}`)
                .then(response => response.blob())
                .then(blob => {
                    const url = window.URL.createObjectURL(blob);
                    const a = document.createElement('a');
                    a.style.display = 'none';
                    a.href = url;
                    a.download = 'reservation.pdf';
                    document.body.appendChild(a);
                    a.click();
                    window.URL.revokeObjectURL(url);
                })
                .catch(error => console.error('Error downloading the PDF:', error));
        }
    </script>
</head>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <div>
            <%@ include file="/layout/menu.jsp" %>
        </div>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">
                    <div class="row">
                        <!-- Data -->
                        <div class="container mt-4">
                            <h2 class="text-center">
                                Mes Reservations
                            </h2>
                            <table class="table table-bordered table-striped mt-3">
                                <thead class="thead-dark">
                                <tr>
                                    <th>Id</th>
                                    <th>Id Mere</th>
                                    <th>Id vol</th>
                                    <th>Type Siege</th>
                                    <th>Date Reservation</th>
                                    <th>Prix final</th>
                                    <th>Etat Reservation</th>
                                    <th>Passeport</th>
                                    <th>Get pdf</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for (VReservation vReservation : vReservations) {%>
                                <tr>
                                    <td>
                                        <a href="reservation_detail?id=<%= vReservation.getId() %>">
                                            <%= vReservation.getId() %>
                                        </a>
                                    </td>
                                    <td>
                                        <%= vReservation.getId_reservation_mere() %>
                                    </td>
                                    <td><%= vReservation.getId_vol() %>
                                    </td>
                                    <td><%= vReservation.getNom_type_siege() %>
                                    </td>
                                    <td><%= vReservation.getHeure_reservation() %>
                                    </td>
                                    <td><%= vReservation.getPrix_final() %>
                                    </td>
                                    <td><%= vReservation.getNom_etat_reservation() %>
                                    </td>
                                    <td>
                                        <%= vReservation.getImg_passeport() %>
                                    </td>
                                    <td>
                                        <button onclick="downloadPdf(<%= vReservation.getId() %>)">Download PDF</button>
                                    </td>
                                </tr>
                                <%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!-- / Content -->

            <div class="content-backdrop fade"></div>
        </div>
        <!-- Content wrapper -->
    </div>
    <!-- / Layout page -->
</div>

<!-- Overlay -->
<div class="layout-overlay layout-menu-toggle"></div>
<!-- / Layout wrapper -->

<!-- script -->
<div>
    <%@ include file="/layout/script.jsp" %>
</div>
</body>
</html>
