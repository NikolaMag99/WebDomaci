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

<%--<%@ include file="navbar.jsp" %>--%>

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

<%
    try {
    Date date = new Date();
    File file = new File("C:\\Users\\PC\\Desktop\\WebDomaci5\\src\\main\\resources\\"+ input.getName() + ".txt");
    if(file.createNewFile()) {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("Ime tima " + input.getName() + "\n" + "Email: " + input.getEmail() + "\n" + "Telefon: " + input.getPhone() + "\n" + "Moto tima: " +
                input.getMoto() + "\n" + "Broj clanova:" + input.getCount() + "\n");

        %>

<jsp:setProperty name="input" property="nameMember1" param="nameMember1" />
<jsp:setProperty name="input" property="surnameMember1" param="surnameMember1" />
<jsp:setProperty name="input" property="emailMember1" param="emailMember1" />
<jsp:setProperty name="input" property="positionMember1" param="positionMember1" />
<jsp:setProperty name="input" property="opis1" param="opis1" />
<jsp:setProperty name="input" property="cvMember1" param="cvMember1" />

<jsp:setProperty name="input" property="nameMember2" param="nameMember2" />
<jsp:setProperty name="input" property="surnameMember2" param="surnameMember2" />
<jsp:setProperty name="input" property="emailMember2" param="emailMember2" />
<jsp:setProperty name="input" property="positionMember2" param="positionMember2" />
<jsp:setProperty name="input" property="opis2" param="opis2" />
<jsp:setProperty name="input" property="cvMember2" param="cvMember2" />

<jsp:setProperty name="input" property="nameMember3" param="nameMember3" />
<jsp:setProperty name="input" property="surnameMember3" param="surnameMember3" />
<jsp:setProperty name="input" property="emailMember3" param="emailMember3" />
<jsp:setProperty name="input" property="positionMember3" param="positionMember3" />
<jsp:setProperty name="input" property="opis3" param="opis3" />
<jsp:setProperty name="input" property="cvMember3" param="cvMember3" />

<jsp:setProperty name="input" property="nameMember4" param="nameMember4" />
<jsp:setProperty name="input" property="surnameMember4" param="surnameMember4" />
<jsp:setProperty name="input" property="emailMember4" param="emailMember4" />
<jsp:setProperty name="input" property="positionMember4" param="positionMember4" />
<jsp:setProperty name="input" property="opis4" param="opis4" />
<jsp:setProperty name="input" property="cvMember4" param="cvMember4" />

<%
        fileWriter.write("--------------------------------------\n");
        fileWriter.write("Haker " + input.getNameMember1() + "\n");
        fileWriter.write("Prezime " + input.getSurnameMember1() + "\n");
        fileWriter.write("Email " + input.getEmailMember1() + "\n");
        fileWriter.write("Broj hakatona " + input.getPositionMember1() + "\n");
        fileWriter.write("Opis " + input.getOpis1() + "\n");
        fileWriter.write("Opis " + input.getCvMember1() + "\n");

        fileWriter.write("--------------------------------------\n");
        fileWriter.write("Haker " + input.getNameMember2() + "\n");
        fileWriter.write("Prezime " + input.getSurnameMember2() + "\n");
        fileWriter.write("Email " + input.getEmailMember2() + "\n");
        fileWriter.write("Broj hakatona " + input.getPositionMember2() + "\n");
        fileWriter.write("Opis " + input.getOpis2() + "\n");
        fileWriter.write("Opis " + input.getCvMember2() + "\n");

        fileWriter.write("--------------------------------------\n");
        fileWriter.write("Haker " + input.getNameMember3() + "\n");
        fileWriter.write("Prezime " + input.getSurnameMember3() + "\n");
        fileWriter.write("Email " + input.getEmailMember3() + "\n");
        fileWriter.write("Broj hakatona " + input.getPositionMember3() + "\n");
        fileWriter.write("Opis " + input.getOpis3() + "\n");
        fileWriter.write("Opis " + input.getCvMember3() + "\n");

        if(input.getNameMember4() != null){
        fileWriter.write("--------------------------------------\n");
        fileWriter.write("Haker " + input.getNameMember4() + "\n");
        fileWriter.write("Prezime " + input.getSurnameMember4() + "\n");
        fileWriter.write("Email " + input.getEmailMember4() + "\n");
        fileWriter.write("Broj hakatona " + input.getPositionMember4() + "\n");
        fileWriter.write("Opis " + input.getOpis4() + "\n");
        fileWriter.write("Opis " + input.getCvMember4() + "\n");
    }
    fileWriter.write("--------------------------------------");
        fileWriter.close();
        response.sendRedirect("poslataPrijava.jsp");
    }else {
            System.out.println("Fajl mosa");
        }
    }  catch (IOException e) {

        e.printStackTrace();
}
%>

</body>
</html>