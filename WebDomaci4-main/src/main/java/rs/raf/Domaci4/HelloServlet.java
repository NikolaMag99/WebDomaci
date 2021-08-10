package rs.raf.Domaci4;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;



@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private List<String> dani = new ArrayList<>();

    String pon;
    String uto;
    String sre;
    String cet;
    String pet;
    ArrayList<Obrok> zapamti = new ArrayList<>();
    Map<String, List<Obrok>> mapa = new HashMap<>();

    public void init() {
        dani.add("ponedeljak");
        dani.add("utorak");
        dani.add("sreda");
        dani.add("cetvrtak");
        dani.add("petak");
        getServletContext().setAttribute("dani", dani);
        for(String dan: dani){ try {
            Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\"+dan+".txt"));
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Obrok obrok = new Obrok(dan, data);
                zapamti.add(obrok);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }}
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if(getServletContext().getAttribute(request.getSession().getId()) != null){
            out.println("<h3>" +"Zavrsio" + "</h3>");
            List<Obrok> obroci = mapa.get(request.getSession().getId());
            for (Obrok obrok: obroci){
                out.println(obrok.getDan());
                out.println("<br>");
                out.println(obrok.getJelo());
                out.println("<br>");
            }
            return;
        }

        out.println("<html><body><form method=\"POST\" action = \"hello-servlet\">");
        out.println("<h1>" +"Izaberi rucak" + "</h1>");
//        out.println("<h3>" +"Ponedeljak" + "</h3>");
//        out.println("<select name = \"ponedeljak\">");
        for (String dan: dani){
            out.println("<h3>" + dan + "</h3>");
            out.println("<select name = \""+ dan + "\" id=\"" + dan+ "\">");
            System.out.println("<select name = \" "+ dan + "\" id=\"" + dan+ "\">" );
            for (Obrok obrok: zapamti){
                if(obrok.getDan().equals(dan)) {
                    out.println("<option value = \"" + obrok.getJelo() + "\" selected>" + obrok.getJelo() + "</option>");
                }
            }
            out.println("</select><br>");
            out.println("--------------------------------");
        }

//        try {
//            Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\ponedeljak.txt"));
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                Obrok obrok = new Obrok("ponedeljak", data);
//                zapamti.add(obrok);
//                out.println("<option value = \"" + data + "\" selected>" + data + "</option>");
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        out.println("</select><br>");
//        out.println("--------------------------------");
//        out.println("<h3>" +"Utorak" + "</h3>");
//        out.println("<select name = \"utorak\">");
//        try {
//            Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\utorak.txt"));
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                Obrok obrok = new Obrok("utorak", data);
//                zapamti.add(obrok);
//                out.println("<option value = \"" + data + "\" selected>" + data + "</option>");
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        out.println("</select><br>");
//        out.println("--------------------------------");
//        out.println("<h3>" +"Sreda" + "</h3>");
//        out.println("<select name = \"sreda\">");
//        try {
//            Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\sreda.txt"));
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                Obrok obrok = new Obrok("sreda", data);
//                zapamti.add(obrok);
//                out.println("<option value = \"" + data + "\" selected>" + data + "</option>");
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        out.println("</select><br>");
//        out.println("--------------------------------");
//        out.println("<h3>" +"Cetvrtak" + "</h3>");
//        out.println("<select name = \"cetvrtak\">");
//        try {
//            Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\cetvrtak.txt"));
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                Obrok obrok = new Obrok("cetvrtak", data);
//                zapamti.add(obrok);
//                out.println("<option value = \"" + data + "\" selected>" + data + "</option>");
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        out.println("</select><br>");
//        out.println("--------------------------------");
//        out.println("<h3>" +"Petak" + "</h3>");
//        out.println("<select name = \"petak\">");
//        try {
//            Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\petak.txt"));
//            while (scanner.hasNextLine()) {
//                String data = scanner.nextLine();
//                Obrok obrok = new Obrok("petak", data);
//                zapamti.add(obrok);
//                out.println("<option value = \"" + data + "\" selected>" + data + "</option>");
//            }
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        out.println("</select><br><br>");
        out.println("<br><input type=\"submit\" name\"submit\" value\"Zapamti\"/></form>");
        out.println("</body></html>");
        getServletContext().setAttribute("SvaJela", zapamti);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("/index2.html");
        //request.getSession().setAttribute("key", true);
        getServletContext().setAttribute(request.getSession().getId(),true);
        List<Obrok> lista = new ArrayList<>();
        for(String dan: dani){
            String jelo = request.getParameter(dan);
            System.out.println(jelo);
            Obrok o = returnObrok(dan, jelo);
            o.setBrojNarudzbina(o.getBrojNarudzbina()  + 1);
            lista.add(o);
        }
        mapa.put(request.getSession().getId(), lista);
        getServletContext().setAttribute("mapa", mapa);
    }

    private Obrok returnObrok( String dan, String jelo){
        for(Obrok o : zapamti){
            if(o.getDan().equals(dan) && o.getJelo().equals(jelo))
                return o;
        }
        System.out.println(dan + " " + jelo);
        return null;
    }

    public void destroy() {
    }

}