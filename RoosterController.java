package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.sun.javafx.geom.AreaOp.AddOp;

import model.PrIS;
import model.klas.Klas;
import model.les.Les;
import model.persoon.Student;
import server.Conversation;
import server.Handler;
import server.Conversation;

public class RoosterController implements Handler
{
	private PrIS informatieSysteem;

	
	public RoosterController(PrIS infoSys) {
		informatieSysteem = infoSys;
	}
	public void handle(Conversation conversation) {
		if (conversation.getRequestedURI().startsWith("rooster/ophalen")) {
			rooster(conversation);
		} else {
			rooster(conversation);
		}
	}
	
	
	public void rooster(Conversation conversation) {
		JsonObject lJsonObjectIn = (JsonObject) conversation.getRequestBodyAsJSON();
		String lGebruikersnaam = lJsonObjectIn.getString("username");
		Student lStudentZelf = informatieSysteem.getStudent(lGebruikersnaam);
		
		Klas lKlas = informatieSysteem.getKlasVanStudent(lStudentZelf);

		ArrayList<ArrayList<String>> rooster = Klas.getRooster(lKlas);
				
		JsonArrayBuilder lJsonArrayBuilder = Json.createArrayBuilder();
		System.out.println(rooster);
				
				for (ArrayList<String> les : rooster) {
					JsonObjectBuilder lJsonObjectBuilderVoorLessen = Json.createObjectBuilder();
					lJsonObjectBuilderVoorLessen
						.add("naam", les.get(0))
						.add("datum", les.get(1))
						.add("starttijd", les.get(2))
						.add("eindtijd", les.get(3))
						.add("docent", les.get(4))
						.add("lokaal", les.get(5))
						.add("klas", les.get(6));
					lJsonArrayBuilder.add(lJsonObjectBuilderVoorLessen);
				}
				
			System.out.println(rooster);
			String lJsonOutStr = lJsonArrayBuilder.build().toString();
			conversation.sendJSONMessage(lJsonOutStr);
	}		
}
