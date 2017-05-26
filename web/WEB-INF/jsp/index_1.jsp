
<%@page import="java.security.Principal" %>
<%@page import="waffle.windows.auth.WindowsAccount" %>
<%@page import="waffle.servlet.WindowsPrincipal" %>
<%@page import="com.sun.jna.platform.win32.Secur32" %>
<%@page import="com.sun.jna.platform.win32.Secur32Util" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<%
 if (request.getParameter("logoff") != null) {
  session.invalidate();
  response.sendRedirect("index.jsp");
  return;
 }
%>

<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Spring 4 Web MVC via Annotations</title>
  <link rel="stylesheet" type="text/css" href="{cp}/resources/css/site.css" />
  <script src="${cp}/resources/js/js.js"></script>
 </head>
 <body>
  <h4>Spring 4 Web MVC com annotations</h4>
  Spring está dizendo: <span class="blue">${msg}</span>

  You are logged in as remote user <b><%= request.getRemoteUser()%></b> in session <b><%= session.getId()%></b>.<br>
  You are impersonating user <b><%= Secur32Util.getUserNameEx(Secur32.EXTENDED_NAME_FORMAT.NameSamCompatible)%></b>.
  <br><br>
  <%
   if (request.getUserPrincipal() != null) {
  %>
  Your user principal name is <b><%= request.getUserPrincipal().getName()%></b>.
  <br><br>
  <%
  } else {
  %>
  No user principal could be identified.
  <br><br>
  <%
   }
  %>
  <%
   String role = request.getParameter("role");
   if (role == null) {
    role = "";
   }
   if (role.length() > 0) {
    if (request.isUserInRole(role)) {
  %>
  You have been granted role <b><%= role%></b>.
  <br><br>
  <%
  } else {
  %>
  You have <i>not</i> been granted role <b><%= role%></b>.
  <br><br>
  <%
    }
   }
  %>
  To check whether your username has been granted a particular role, enter it here:
  <form method="GET" action='<%= response.encodeURL("index.jsp")%>'>
   <input type="text" name="role" value="<%= role%>">
  </form>
  <br><br> 
  You can logoff by clicking
  <a href='<%= response.encodeURL("index.jsp?logoff=true")%>'>here</a>.
  This should cause automatic re-logon with Waffle and a new session ID.
  <br><br>
  All user groups:
  <ul>
   <%
    Principal principal = request.getUserPrincipal();
    if (principal instanceof WindowsPrincipal) {
     WindowsPrincipal windowsPrincipal = (WindowsPrincipal) principal;
     for (WindowsAccount account : windowsPrincipal.getGroups().values()) {
   %>
   <li><%= account.getFqn()%> (<%= account.getSidString()%>)
    <%
      }
     }
    %>
  </ul>
 </body>
</html>