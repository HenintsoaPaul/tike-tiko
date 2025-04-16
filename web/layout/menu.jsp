<%
    String menuItemClass = "menu-item", classes = "";
    String activePage = (String) pageContext.getAttribute("activePage");
%>

<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
    <div class="app-brand demo">
        <a href="<%= request.getContextPath() %>/" class="app-brand-link">
      <span class="app-brand-logo demo">
                <svg xmlns="http://www.w3.org/2000/svg"
                     width="32"
                     height="32"
                     viewBox="0 0 24 24"
                     fill="none"
                     class="feather feather-aircraft"
                >
                    <path d="M12 2L3 9l3 6M12 2l9 7-3 6M12 2v20M9 9l6 6"/>
                </svg>
      </span>
            <span class="app-brand-text demo menu-text fw-bolder ms-2">
        Tike Tiko
      </span>
        </a>
        <a
                href="javascript:void(0);"
                class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none"
        >
            <i class="bx bx-chevron-left bx-sm align-middle"></i>
        </a>
    </div>

    <div class="menu-inner-shadow"></div>

    <ul class="menu-inner py-1">
        <!-- Log(in/out) -->
        <li class="menu-item">
            <a href="<%= request.getContextPath() %>/fo_login" class="menu-link">
                <i class="menu-icon tf-icons bx bx-log-in"></i>
                <div data-i18n="Login">Login</div>
            </a>
        </li>
        <li class="menu-item">
            <a href="<%= request.getContextPath() %>/fo_logout" class="menu-link">
                <i class="menu-icon tf-icons bx bx-log-out"></i>
                <div data-i18n="Logout">Logout</div>
            </a>
        </li>

        <!-- Vols client -->
        <li
                <%
                    classes = menuItemClass;
                    if (activePage.equals("foVolList") || activePage.equals("foVolDetail")) {
                        classes += " active open";
                    }
                    out.print("class=\"" + classes + "\"");
                %>
        >
            <a class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-plane-alt"></i>
                <div data-i18n="Flights">Vols client</div>
            </a>
            <ul class="menu-sub">
                <li
                        <%
                            classes = menuItemClass;
                            if (activePage.equals("foVolList")) {
                                classes += " active";
                            }
                            out.print("class=\"" + classes + "\"");
                        %>
                >
                    <a href="<%= request.getContextPath() %>/fo_vol_list" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-list-ul"></i>
                        <div data-i18n="Container">Liste des Vols</div>
                    </a>
                </li>
            </ul>
        </li>

        <!-- Reservation client -->
        <li
                <%
                    classes = menuItemClass;
                    if (activePage.equals("foReservationList")) {
                        classes += " active open";
                    } else if (activePage.equals("foReservationDetail")) {
                        classes += " active";
                    }
                    out.print("class=\"" + classes + "\"");
                %>
        >
            <a class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-plane-alt"></i>
                <div data-i18n="Planes">Reservations client</div>
            </a>
            <ul class="menu-sub">
                <li
                        <%
                            classes = menuItemClass;
                            if (activePage.equals("foReservationList")) {
                                classes += " active";
                            }
                            out.print("class=\"" + classes + "\"");
                        %>
                >
                    <a href="<%= request.getContextPath() %>/fo_reservation_list" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-list-ul"></i>
                        <div data-i18n="Container">Mes Reservations</div>
                    </a>
                </li>
            </ul>
        </li>
    </ul>
</aside>
