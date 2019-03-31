package model.les;

import java.sql.Time;
import java.util.Date;

public class Les {
    private String naam;
    private String cursuscode;
    private int startweek;
    private String startdag;
    private String startdatum; // even checken met date-format uit de csv....
    private String starttijd; //same als heirboven met tijd
    private String eindtijd; //^^
    private double duur; // deze zal in uren zijn
    private String werkvorm;
    private String docent;
    private String lokaalnummer;
    private String klas;
    private String faculteit;
    private int grootte;

    public Les(String naam, String cursuscode, int startweek, String startdag, String startdatum, String starttijd, String eindtijd, double duur, String werkvorm, String docent, String lokaalnummer, String klas, String faculteit){
        this.naam = naam;
        this.cursuscode = cursuscode;
        this.startweek = startweek;
        this.startdag = startdag;
        this.startdatum = startdatum;
        this.starttijd = starttijd;
        this.eindtijd = eindtijd;
        this.duur = duur;
        this.werkvorm = werkvorm;
        this.docent = docent;
        this.lokaalnummer = lokaalnummer;
        this.klas = klas;
        this.faculteit = faculteit;
    }

    public String getNaam() {
        return naam;
    }

    public String getStartdatum() {
        return startdatum;
    }

    public double getDuur() {
        return duur;
    }

    public int getGrootte() {
        return grootte;
    }

    public int getStartweek() {
        return startweek;
    }

    public String getCursuscode() {
        return cursuscode;
    }

    public String getDocent() {
        return docent;
    }

    public String getFaculteit() {
        return faculteit;
    }

    public String getKlas() {
        return klas;
    }

    public String getLokaalnummer() {
        return lokaalnummer;
    }

    public String getStartdag() {
        return startdag;
    }

    public String getWerkvorm() {
        return werkvorm;
    }

    public String getEindtijd() {
        return eindtijd;
    }

    public String getStarttijd() {
        return starttijd;
    }

    public boolean equalsKlas(String kl){
        boolean eq = false;
        if (klas.contains(kl)){
            eq = true;
        }
        return eq;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

