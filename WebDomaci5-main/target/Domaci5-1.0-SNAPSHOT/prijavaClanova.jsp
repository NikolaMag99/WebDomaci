<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileWriter" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">
    <title>Hakaton</title>
    <%@include file="style.jsp"%>

</head>
<body data-aos-easing="ease" data-aos-duration="400" data-aos-delay="0">

<%@ include file="navbar.jsp" %>

<%--Kreiranje instance bean-a. Scope:
application - instanca beana je dostupna celoj web aplikaciji
session - instanca beana se kreira za svaku novu sesiju
request - instanca beana se kreira kada stigne novi zahtev i dostupna je samo na nivou tog zahteva
sto znaci da ce biti dostupan i drugim stranicama, na primer ako uradimo
 <jsp:forward page="stranica.jsp"/> ili <jsp:include page="stranica.jsp"/>
page - instanca beana je dostupna samo jednoj jsp stranici --%>


<jsp:useBean id="input" scope="session" class="rs.raf.Domaci5.Domaci5.Forma" />

<jsp:setProperty name="input" property="name" param="name" />
<jsp:setProperty name="input" property="email" param="email" />
<jsp:setProperty name="input" property="phone" param="phone" />
<jsp:setProperty name="input" property="moto" param="moto" />
<jsp:setProperty name="input" property="culi" param="culi" />
<jsp:setProperty name="input" property="count" param="count" />


<div class="page-wrapper bg-gra-03 p-t-45 p-b-50" style="margin-top: 100px;">
    <div class="wrapper wrapper--w790">
        <div class="card card-5">
            <div class="card-heading">
                <h2 class="title">PRIJAVA CLANOVA</h2>
            </div>
            <div class="card-body">
                <h1 style="margin-left: 300px;">Clan 1</h1>
                    <br><br>
                    <form action="submit.jsp" method="POST">
                        <div class="form-row">
                            <div class="name">Ime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="nameMember1" id="nameMember1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Prezime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="surnameMember1" id="surnameMember1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Kontakt email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" name="emailMember1" id="emailMember1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Broj hakatona</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="number" name="positionMember1" id="positionMember1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Uloga u timu</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="opis1" id="opis1">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Link CV</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="url" name="cvMember1" id="cvMember1">
                                </div>
                            </div>
                        </div>

                        <!-- <div>
                            <button style="margin-left: 230px;" class="btn btn--radius-2 btn--red" type="button" onclick = "location.href= 'poslataPrijava.html'">Clanovi</button>

                        </div> -->
<%--                    </form>--%>
<%--            </div>--%>
<%--            <div style="margin-top: -90px;" class="card-body">--%>
                <h1 style="margin-left: 300px;">Clan 2</h1>
                    <br><br>
<%--                    <form method="POST">--%>
                        <div class="form-row">
                            <div class="name">Ime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="nameMember2" id="nameMember2">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Prezime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="surnameMember2" id="surnameMember2">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Kontakt email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" name="emailMember2" id="emailMember2">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Broj hakatona</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="number"  name="positionMember2" id="positionMember2">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Uloga u timu</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="opis2" id="opis2">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Link CV</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="url" name="cvMember2" id="cvMember2">
                                </div>
                            </div>
                        </div>

<%--                    </form>--%>
<%--            </div>--%>
<%--            <div style="margin-top: -90px;" class="card-body">--%>
                <h1 style="margin-left: 300px;">Clan 3</h1>
                    <br><br>
<%--                    <form method="POST">--%>
                        <div class="form-row">
                            <div class="name">Ime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="nameMember3" id="nameMember3">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Prezime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="surnameMember3"  id="surnameMember3">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Kontakt email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" name="emailMember3" id="emailMember3">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Broj hakatona</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="number" name="positionMember3"  id="positionMember3">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Uloga u timu</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="opis3" id="opis3">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Link CV</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="url" name="cvMember3" id="cvMember3">
                                </div>
                            </div>
                        </div>


                        <% if (input.getCount() == 4) { %>
                        <h1 style="margin-left: 300px;">Clan 4</h1>
                        <br><br>
                        <%--                    <form method="POST">--%>
                        <div class="form-row">
                            <div class="name">Ime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="nameMember4" id="nameMember4">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Prezime</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="surnameMember4" id="surnameMember4">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Kontakt email</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="email" name="emailMember4" id="emailMember4">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Broj hakatona</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="number" name="positionMember4" id="positionMember4">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Uloga u timu</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="text" name="opis4" id="opis4">
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="name">Link CV</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5" type="url" name="cvMember4" id="cvMember4">
                                </div>
                            </div>
                        </div>
                        <% } %>

                        <div>
                            <button style="margin-left: 230px;" class="btn btn--radius-2 btn--red" type="submit" >Posalji prijavu</button>

                        </div>
                    </form>
            </div>
        </div>
    </div>
</div>

<!-- Jquery JS-->
<script src="vendor/jquery/jquery.min.js"></script>
<!-- Vendor JS-->
<script src="vendor/select2/select2.min.js"></script>
<script src="vendor/datepicker/moment.min.js"></script>
<script src="vendor/datepicker/daterangepicker.js"></script>

<!-- Main JS-->
<script src="js/global.js"></script>

</body>
</html>