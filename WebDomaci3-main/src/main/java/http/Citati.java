package http;

import java.util.HashMap;

public class Citati {


    private static Citati insstance = null;

    protected HashMap<String,String> citati;

    public Citati() {
        this.citati = new HashMap<>();
        String autor1 = "Joca Zvani Komi";
        String citat1 = "Ko rano rani u NCR gazi";
        String autor2 = "Maksa Zvani Pegaz";
        String citat2 = "Paun je plemenita ptica";


        citati.put(autor1, citat1);
        citati.put(autor2, citat2);
    }


    public static Citati getInstance() {
        if (insstance == null)
            insstance = new Citati();
        return insstance;
    }

    public HashMap<String,String> getCitati() {
        return citati;
    }
}
