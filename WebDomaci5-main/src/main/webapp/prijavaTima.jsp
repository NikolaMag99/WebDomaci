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
<!-- Navigation -->
<% if(session.getAttribute("input") == null) { %>
<%@ include file="navbar.jsp" %>


<div class="page-wrapper bg-gra-03 p-t-45 p-b-50" style="margin-top: 100px;">
    <div class="wrapper wrapper--w790">
        <div class="card card-5">
            <div class="card-heading">
                <h2 class="title">PRIJAVA TIMA</h2>
            </div>
            <div class="card-body">
                <form action="prijavaClanova.jsp" method="POST">
                    <div class="form-row">
                        <div class="name">Ime Tima</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-5" type="text" name="name" id = "name" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Kontakt email</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-5" type="email" name="email" id = "email" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Kontakt telefon</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-5" type="tel" name="phone" id = "phone" required>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name">Moto tima</div>
                        <div class="value">
                            <div class="input-group">
                                <input class="input--style-5" type="text" name="moto" id = "moto" required>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="name"></div>
                        <div class="value">
                            <div class="comboBox">
                                <select name="culi"  id="culi" class="combo-inputBox" required>
                                    <option value="" disabled="disabled" selected="selected">Kako ste culi za hakaton</option>
                                    <option value="Facebook">Facebook</option>
                                    <option value="Fakultet">Fakultet</option>
                                    <option value="Drugo">Drugo</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="name"></div>
                        <div class="value">
                    <div class="comboBox">
                        <select class="combo-inputBox" name="count" id="count" required>
                            <option value="" disabled="disabled" selected="selected">Broj clanova tima</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                        </select>
                    </div>
                        </div>
                    </div>
                    <div>
                        <button style="margin-left: 230px;" class="btn btn--radius-2 btn--red" type="submit">Clanovi</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<% }
else

%>
<h1> Vec ste se prijavili! </h1>
<%
%>

</body>
</html>