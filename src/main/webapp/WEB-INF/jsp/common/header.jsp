<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ruokit.device.monitor.model.data.Menu" %>
<%@ page import="com.ruokit.device.monitor.model.service.user.LoginUserSession" %>
<%
    HttpSession httpSession = request.getSession();
    List<Menu> menuList = (List<Menu>) httpSession.getAttribute("menuList");
    LoginUserSession loginInfo = (LoginUserSession)httpSession.getAttribute("loginUser");
    String cmpnNm = loginInfo.getCmpnNm();
%>
<nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="/main"><%=cmpnNm%></a>
    <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="/logout">Sign out</a>
        </li>
    </ul>
</nav>
<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <%
                        for(Menu menu : menuList) {
                            String menuPath = menu.getMenuPath();
                            String menuNm = menu.getMenuNm();
                            String menuFeather = menu.getMenuFeather();
                            String menuSrvVwNm = menu.getMenuSrvVwNm();
                    %>
                    <li class="nav-item">
                        <a class="nav-link" service-view-name="<%=menuSrvVwNm%>" href="<%=menuPath%>">
                            <span data-feather="<%=menuFeather%>"></span>
                                <%=menuNm%>
                        </a>
                    </li>
                    <%
                        }
                    %>
                </ul>
                <!-- <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>Saved reports</span>
                    <a class="d-flex align-items-center text-muted" href="#">
                        <span data-feather="plus-circle"></span>
                    </a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                                Current month
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                                Last quarter
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                                Social engagement
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            <span data-feather="file-text"></span>
                                Year-end sale
                        </a>
                    </li>
                </ul> -->
            </div>
        </nav>
    </div>
</div>