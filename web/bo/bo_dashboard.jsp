<%@ page import="views.VVol" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="dto.PlaceDTO" %>
<%@ page import="service.util.DateFormatterService" %>
<%@ page import="views.VReservation" %>
<%@ page import="java.util.List" %>
<%@ page import="views.VPromotion" %>
<%@ page import="dto.DashboardDTO" %>
<%
    DashboardDTO dto = (DashboardDTO) request.getAttribute("dto");

    pageContext.setAttribute("activePage", "boDashboard");

    // nb total of flights
    // nb total of bookings
    // confirmed
    // pending
    // cancelled
    // admin
    // customer
%>

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

    <title>Dashboard admin</title>

    <%@ include file="/layout/link_header.jsp" %>
</head>

<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->
        <div>
            <%@ include file="/layout/menu_admin.jsp" %>
        </div>
        <!-- / Menu -->

        <!-- Layout container -->
        <div class="layout-page">
            <!-- Content wrapper -->
            <div class="content-wrapper">
                <!-- Content -->
                <div class="container-xxl flex-grow-1 container-p-y">
                    <div class="row">

                        <!-- Dashboard Cards -->
                        <div class="col-12">
                            <h4 class="fw-bold py-3 mb-4">Dashboard</h4>
                            <div class="row g-4">
                                <!-- Total Flights -->
                                <div class="col-lg-4 col-md-6 col-sm-12">
                                    <div class="card h-100">
                                        <div class="card-body text-center">
                                            <div class="mb-3">
                                                <i class="bx bx-plane bx-lg text-primary"></i>
                                            </div>
                                            <h5 class="card-title">Total Flights</h5>
                                            <h3 class="card-text"><%= dto.getNbFlights() %>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                                <!-- Total Bookings -->
                                <div class="col-lg-4 col-md-6 col-sm-12">
                                    <div class="card h-100">
                                        <div class="card-body text-center">
                                            <div class="mb-3">
                                                <i class="bx bx-book bx-lg text-info"></i>
                                            </div>
                                            <h5 class="card-title">Total Bookings</h5>
                                            <h3 class="card-text"><%= dto.getNbBookings().getTotal() %>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                                <!-- Confirmed Bookings -->
                                <div class="col-lg-4 col-md-6 col-sm-12">
                                    <div class="card h-100">
                                        <div class="card-body text-center">
                                            <div class="mb-3">
                                                <i class="bx bx-check-circle bx-lg text-success"></i>
                                            </div>
                                            <h5 class="card-title">Confirmed Bookings</h5>
                                            <h3 class="card-text"><%= dto.getNbBookings().getNbConfirmed() %>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                                <!-- Pending Bookings -->
                                <div class="col-lg-4 col-md-6 col-sm-12">
                                    <div class="card h-100">
                                        <div class="card-body text-center">
                                            <div class="mb-3">
                                                <i class="bx bx-time bx-lg text-warning"></i>
                                            </div>
                                            <h5 class="card-title">Pending Bookings</h5>
                                            <h3 class="card-text"><%= dto.getNbBookings().getNbPending() %>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                                <!-- Cancelled -->
                                <div class="col-lg-4 col-md-6 col-sm-12">
                                    <div class="card h-100">
                                        <div class="card-body text-center">
                                            <div class="mb-3">
                                                <i class="bx bx-x-circle bx-lg text-danger"></i>
                                            </div>
                                            <h5 class="card-title">Cancelled</h5>
                                            <h3 class="card-text"><%= dto.getNbBookings().getNbCancelled() %>
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- / Dashboard Cards -->

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
<!-- script -->
<div>
    <%@ include file="/layout/script.jsp" %>
</div>
</body>
</html>
