package rs.raf.Domaci4;

public class Obrok {

    private String dan;
    private String jelo;
    private int brojNarudzbina;


    public Obrok(String dan, String jelo) {
        this.dan = dan;
        this.jelo = jelo;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getJelo() {
        return jelo;
    }

    public void setJelo(String jelo) {
        this.jelo = jelo;
    }

    public int getBrojNarudzbina() {
        return brojNarudzbina;
    }

    public void setBrojNarudzbina(int brojNarudzbina) {
        synchronized (this) {
            this.brojNarudzbina = brojNarudzbina;
        }
    }
}
