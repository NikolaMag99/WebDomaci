package rs.raf.Domaci4;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "Narudzbine", value = "/narudzbine")
public class Narudzbine extends HttpServlet {
    private File f;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pass = request.getParameter("lozinka");
        Scanner scanner = new Scanner(new File("C:\\Users\\PC\\Desktop\\Domaci4\\src\\lozinka.txt"));
        if(pass.equals(scanner.nextLine())){
            out.println("<html><body><form method=\"POST\" action = \"narudzbine?lozinka=pass\">");
            out.println("<h1>" +"Jela" + "</h1>");
            out.println("<html><body>");

            List<Obrok> lista = (List<Obrok>) getServletContext().getAttribute("SvaJela");
            List<String> dani = (List<String>) getServletContext().getAttribute("dani");

            for(String dan: dani) {

                out.println("<h1>" + dan + "</h1>");
                out.println("<h1></h1>");
                out.println("<table style=\"width:100 % \">");
                out.println("<tr> <th>#</th> <th>Jelo</th> <th>Kolicina</th> </tr>");
                int j = 0;
                for (int i = 0; i <lista.size(); i++) {
                    if(lista.get(i).getDan().equals(dan)) {
                        j++;
                        out.println("<tr> <th>" + j + "</th> <th>" + lista.get(i).getJelo() + "</th> <th>" + lista.get(i).getBrojNarudzbina() + "</th> </tr>");
                    }
                }
                out.println("<style>\n" +
                        "table, th, td {\n" +
                        "  border: 1px solid black;\n" +
                        "}\n" +
                        "</style></table>\n");
                out.println("<h1></h1>");
            }


            out.println("--------------------------------<br>");
            out.println("<br><input type=\"submit\" name\"submit\" value\"Ocisti;\"/></form>");
            out.println("</body></html>");
        }
        else {
            out.println("<h3>" +"Pogresna lozinka" + "</h3>");
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Obrok> lista = (List<Obrok>) getServletContext().getAttribute("SvaJela");
        for(Obrok o: lista){
            o.setBrojNarudzbina(0);
        }
        Map<String, List<Obrok>> map = (Map<String, List<Obrok>>) getServletContext().getAttribute("mapa");
        for (Map.Entry<String, List<Obrok>> m: map.entrySet()){
            getServletContext().setAttribute(m.getKey(), null);
        }
        map = new HashMap<>();
        getServletContext().setAttribute("mapa",map);


    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
