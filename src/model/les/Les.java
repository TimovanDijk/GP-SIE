package model.les;

import model.les.afwezigheid.Absentie;
import model.les.afwezigheid.Gepland;
import model.les.afwezigheid.Ziek;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class Les {
    private String ziek;
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
    private ArrayList<Absentie> afwezigen;

    public Les(String naam, String cursuscode, int startweek, String startdag, String startdatum, String starttijd, String eindtijd, double duur, String werkvorm, String docent, String lokaalnummer, String klas, String faculteit) {
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

    public String isAfwezig(String lln) {
        String s = "false";

        for (int i = 0; i < afwezigen.size(); i++) {
            if (afwezigen.get(i).matchLeerling(lln)) {
                s = "true: " + afwezigen.get(i).getReden();
            }
        }
        return s;
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

    public String getZieken() {
        return ziek;
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

    public boolean equalsKlas(String kl) {
        boolean eq = false;
        if (klas.contains(kl)) {
            eq = true;
        }
        return eq;
    }

    public boolean bestaatAl(String lln) {
        boolean bestaatal = false;
        for (int i = 0; i < afwezigen.size(); i++) {
            if (afwezigen.get(i).matchLeerling(lln)) {
                bestaatal = true;
            }
        }
        return bestaatal;
    }

    public void addZieke(String lln) {
        boolean bestaatal = bestaatAl(lln);
        if (!bestaatal) {
            afwezigen.add(new Ziek(lln));
        }
    }

    public void removeAbsentie(String lln) {
        for (int i = 0; i < afwezigen.size(); i++) {
            if (afwezigen.get(i).matchLeerling(lln)) {
                afwezigen.remove(i);
            }
        }
    }

    public void addAbsentie(String lln) {
        boolean bestaatal = bestaatAl(lln);
        if (!bestaatal) {
            afwezigen.add(new Absentie(lln));
        }
    }

    public void addGepland(String lln, String reden) {
        boolean bestaatal = bestaatAl(lln);
        if (!bestaatal) {
            afwezigen.add(new Gepland(lln, reden));
        }
    }

    public ArrayList<Absentie> getAfwezigen() {
        return afwezigen;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

