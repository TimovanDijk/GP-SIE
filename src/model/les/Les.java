package model.les;

import model.les.afwezigheid.Absentie;

import java.util.ArrayList;

public class Les {
    private String ziek;
    private String naam;
    private String cursuscode;
    private String startdag;
    private String startdatum; // even checken met date-format uit de csv....
    private String starttijd; //same als heirboven met tijd
    private String eindtijd; //^^
    private String werkvorm;
    private String docent;
    private String lokaalnummer;
    private String klas;
    private String faculteit;
    private int grootte;
    private ArrayList<Absentie> afwezigen = new ArrayList<Absentie>();

    public Les(String naam, String cursuscode, String startdag, String startdatum, String starttijd, String eindtijd, String werkvorm, String docent, String lokaalnummer, String klas, String faculteit) {
        this.naam = naam;
        this.cursuscode = cursuscode;
        this.startdag = startdag;
        this.startdatum = startdatum;
        this.starttijd = starttijd;
        this.eindtijd = eindtijd;
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

    public int getGrootte() {
        return grootte;
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
            afwezigen.add(new Absentie(lln, "Ziek"));
        }
    }

    public String checkAbsentiereden(String lln) {
        String reden = "";
        for (Absentie absentie : afwezigen) {
            if (absentie.matchLeerling(lln)) {
                reden = absentie.getReden();
            }
        }
        return reden;
    }

    public String getKleurcode(String lln) {
        String code = "#003FFF";
        for (Absentie absentie : afwezigen) {
            if (absentie.matchLeerling(lln)) {
                String reden = absentie.getReden();

                if (reden == "Niet gegeven") {
                    code = "#FC0000";
                }
                if (reden == "Ziek") {
                    code = "#8F3795";
                } else {
                    code = "#FB7600";
                }
            }
        }
        return code;
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
            afwezigen.add(new Absentie(lln, reden));
        }
    }

    public String afwezigenString(){
        String s = "";
        for (Absentie absentie : afwezigen){
            s += absentie.toString() + "\n";
        }
    return s;
    }

    public ArrayList<Absentie> getAfwezigen() {
        return afwezigen;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

