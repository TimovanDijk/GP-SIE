package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import model.PrIS;
import model.klas.Klas;
import model.les.Les;
import model.persoon.Docent;
import model.persoon.Student;
import server.Conversation;
import server.Handler;
import server.Conversation;

public class RoosterController implements Handler {
    private PrIS informatieSysteem;
    private ArrayList<Les> deLessen;


    public RoosterController(PrIS infoSys) {
        informatieSysteem = infoSys;
    }

    public void handle(Conversation conversation) {
        if (conversation.getRequestedURI().startsWith("/rooster/ophalen")) {
            rooster(conversation);
        } else {
            roosterDocent(conversation);
        }
    }


    private void rooster(Conversation conversation) {
        JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String lGebruikersnaam = lJsonObjectIn.getString("naam");
        Student lStudentZelf = informatieSysteem.getStudent(lGebruikersnaam);
        String lGroepIdZelf = lStudentZelf.getGroepId();

        Klas lKlas = informatieSysteem.getKlasVanStudent(lStudentZelf);        // klas van de student opzoeken
        ArrayList<Les> prooster = informatieSysteem.getHetRooster();
        JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();

        for (Les lLes : prooster) {
            if (lLes.equalsKlas(lKlas.getKlasCode())) {
                System.out.println(lLes.getKleurcode(lGebruikersnaam));
                JsonObjectBuilder lJsonObjectBuilderVoorLessen = Json.createObjectBuilder();
                lJsonObjectBuilderVoorLessen
                        .add("naam", lLes.getNaam())
                        .add("datum", lLes.getStartdatum())
                        .add("starttijd", lLes.getStarttijd())
                        .add("eindtijd", lLes.getEindtijd())
                        .add("docent", lLes.getDocent())
                        .add("lokaal", lLes.getLokaalnummer())
                        .add("klas", lLes.getKlas())
                        .add("afwezig", lLes.getKleurcode(lGebruikersnaam))
                        .add("reden", lLes.checkAbsentiereden(lGebruikersnaam));

                lJsonArrayBuilder.add(lJsonObjectBuilderVoorLessen);
            }
        }
        String lJsonOutStr = lJsonArrayBuilder.build().toString();                                                // maak er een string van
        conversation.sendJSONMessage(lJsonOutStr);

    }

    private void roosterDocent(Conversation conversation) {
        JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
        String lGebruikersnaam = lJsonObjectIn.getString("naam");
        Docent lDocentZelf = informatieSysteem.getDocent(lGebruikersnaam);

        ArrayList<Les> prooster = informatieSysteem.getHetRooster();

        JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();

        for (Les lLes : prooster) {
            System.out.println(lLes);
            if (lLes.getDocent().contains(lDocentZelf.getGebruikersnaam())) {
                JsonObjectBuilder lJsonObjectBuilderVoorLessen = Json.createObjectBuilder();
                lJsonObjectBuilderVoorLessen
                        .add("naam", lLes.getNaam())
                        .add("datum", lLes.getStartdatum())
                        .add("starttijd", lLes.getStarttijd())
                        .add("eindtijd", lLes.getEindtijd())
                        .add("docent", lLes.getDocent())
                        .add("lokaal", lLes.getLokaalnummer())
                        .add("klas", lLes.getKlas())
                        .add("afwezigen", lLes.afwezigenString());
                lJsonArrayBuilder.add(lJsonObjectBuilderVoorLessen);
            }
        }
        String lJsonOutStr = lJsonArrayBuilder.build().toString();                                                // maak er een string van
        conversation.sendJSONMessage(lJsonOutStr);

    }

}
