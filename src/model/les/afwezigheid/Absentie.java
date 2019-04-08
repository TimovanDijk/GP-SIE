package model.les.afwezigheid;

public class Absentie {
    private String leerlingnummer;
    private String reden;


    public Absentie(String lln) {
        this.leerlingnummer = lln;
        reden = "Niet gegeven";
    }

    public Absentie(String lln, String reden) {
        this.leerlingnummer = lln;
        this.reden = reden;
    }

    public boolean matchLeerling(String lln) {
        boolean waar = false;
        if (leerlingnummer == lln) {
            waar = true;
        }
        return waar;
    }

    public String getReden() {
        return reden;
    }

    @Override
    public String toString() {
        return leerlingnummer + " is afwezig vanwege reden: " + reden;
    }
}
