<%@ page import="rs.raf.Domaci5.Domaci5.Forma" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">

    <!-- Title Page-->
    <title>Hakaton</title>

    <%@include file="style.jsp"%>


</head>
<body  data-aos-easing="ease" data-aos-duration="400" data-aos-delay="0">

<jsp:useBean id="input" scope="session" class="rs.raf.Domaci5.Domaci5.Forma" />

<%@ include file="navbar.jsp" %>
<div class="priajavaObavestenje" style="color: white; margin-top: 300px;margin-left: 500px;">
    <h1 style="font-size: 57px;" class="imeTima"><%=input.getName()%> Hvala na prijavi!</h1>

    <br>
    <div class="pregledanje">
        <h3 style="font-size: 30px;">Nas tim pregleda prijavu i  obavestice vas o konacnoj
            odluci do 11. decembra na email: <br><%=input.getEmail()%> </h3>

    </div>
<%--    <br><br>--%>
<%--    <div  class="pisite">--%>
<%--        <h4 style="font-size: 30px;">Za sva dodatna pitanja mozete pisati na <a style="color: rgb(223, 30, 5);">hakaton@raf.rs </a> </h4>--%>

<%--&lt;%&ndash;        <h4 style="color: rgb(223, 30, 5); font-size: 30px; margin-top: -1px; margin-left: 160px;">hakaton@raf.rs</h4>&ndash;%&gt;--%>
<%--    </div>--%>
</div>

</body>
</html>