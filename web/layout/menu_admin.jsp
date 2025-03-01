<%
    String menuItemClass = "menu-item", classes = "";
    String activePage = (String) pageContext.getAttribute("activePage");
%>

<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
    <div class="app-brand demo">
        <a href="index.html" class="app-brand-link">
      <span class="app-brand-logo demo">
        <svg
                xmlns="http://www.w3.org/2000/svg"
                width="32"
                height="32"
                class="bi bi-currency-dollar"
                viewBox="0 0 12 12"
        >
          <path
                  fill="#696cff"
                  d="M4 10.781c.148 1.667 1.513 2.85 3.591 3.003V15h1.043v-1.216c2.27-.179 3.678-1.438 3.678-3.3 0-1.59-.947-2.51-2.956-3.028l-.722-.187V3.467c1.122.11 1.879.714 2.07 1.616h1.47c-.166-1.6-1.54-2.748-3.54-2.875V1H7.591v1.233c-1.939.23-3.27 1.472-3.27 3.156 0 1.454.966 2.483 2.661 2.917l.61.162v4.031c-1.149-.17-1.94-.8-2.131-1.718zm3.391-3.836c-1.043-.263-1.6-.825-1.6-1.616 0-.944.704-1.641 1.8-1.828v3.495l-.2-.05zm1.591 1.872c1.287.323 1.852.859 1.852 1.769 0 1.097-.826 1.828-2.2 1.939V8.73z"></path>
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
            <a href="<%= request.getContextPath() %>/login" class="menu-link">
                <i class="menu-icon tf-icons bx bx-log-in"></i>
                <div data-i18n="Login">Login</div>
            </a>
        </li>
        <li class="menu-item">
            <a href="<%= request.getContextPath() %>/logout" class="menu-link">
                <i class="menu-icon tf-icons bx bx-log-out"></i>
                <div data-i18n="Logout">Logout</div>
            </a>
        </li>

        <!-- Configs admins (bo) -->
        <li
                <%
                    classes = menuItemClass;
                    if (activePage.equals("boConfig")) {
                        classes += " active";
                    }
                    out.print("class=\"" + classes + "\"");
                %>
        >
            <a href="<%= request.getContextPath() %>/config" class="menu-link">
                <div data-i18n="Container">Nos Configurations</div>
            </a>
        </li>

        <!-- Vols (bo) -->
        <li
                <%
                    classes = menuItemClass;
                    if (activePage.equals("boVolList") || activePage.equals("boVolDetail") || activePage.equals("volAdd")) {
                        classes += " active open";
                    }
                    out.print("class=\"" + classes + "\"");
                %>
        >
            <a class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-wallet-alt"></i>
                <div data-i18n="Tables">Vols (bo)</div>
            </a>
            <ul class="menu-sub">
                <li
                        <%
                            classes = menuItemClass;
                            if (activePage.equals("boVolList")) {
                                classes += " active";
                            }
                            out.print("class=\"" + classes + "\"");
                        %>
                >
                    <a href="<%= request.getContextPath() %>/vol_list" class="menu-link">
                        <div data-i18n="Container">Liste vol</div>
                    </a>
                </li>
                <li
                        <%
                            classes = menuItemClass;
                            if (activePage.equals("boVolAdd")) {
                                classes += " active";
                            }
                            out.print("class=\"" + classes + "\"");
                        %>
                >
                    <a href="<%= request.getContextPath() %>/vol_add" class="menu-link">
                        <div data-i18n="Fluid">Ajout vol</div>
                    </a>
                </li>
            </ul>
        </li>

        <!-- Reservation admin -->
        <li
                <%
                    classes = menuItemClass;
                    if (activePage.equals("boReservationList") || activePage.equals("boReservationDetail")) {
                        classes += " active open";
                    }
                    out.print("class=\"" + classes + "\"");
                %>
        >
            <a class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-plane-alt"></i>
                <div data-i18n="Planes">Reservations</div>
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
                    <div data-i18n="Container">Liste des Reservations</div>
                </li>
                <li
                        <%
                            classes = menuItemClass;
                            if (activePage.equals("foReservationDetail")) {
                                classes += " active";
                            }
                            out.print("class=\"" + classes + "\"");
                        %>
                >
                    <div data-i18n="Container">Detail reservation</div>
                </li>
            </ul>
        </li>
    </ul>
</aside>
