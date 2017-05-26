<%-- 
    Document   : cadastro
    Created on : 10/05/2017, 11:07:46
    Author     : romulo.douro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
 </head>
 <body>
  <h1>Cadastro</h1>
  <form action="${pageContext.servletContext.contextPath}/pessoa/cadastro" method="POST">
   <fieldset>
    <legend>Nova Pessoa</legend>
    <label for="nome">Nome:</label>
    <input type="text" name="nome">
    <input type="submit" value="Salvar"/>
   </fieldset>
  </form>
 </body>
</html>
