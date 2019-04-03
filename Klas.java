package model.klas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.les.Les;
import model.persoon.Student;

public class Klas {

	private String klasCode;
	private String naam;
	private ArrayList<Student> deStudenten = new ArrayList<Student>();

	public Klas(String klasCode, String naam) {
		this.klasCode = klasCode;
		this.naam = naam;
	}

	public String getKlasCode() {
		return klasCode;
	}

	public String getNaam() {
		return naam;
	}

	public List<Student> getStudenten() {
		return Collections.unmodifiableList(deStudenten);
	}

	public boolean bevatStudent(Student pStudent) {
		return this.getStudenten().contains(pStudent);
	}
	
	public static ArrayList<ArrayList<String>> getRooster(Klas klasStudent)
	{
		int n = 11;

		ArrayList<ArrayList<String>> hetRooster = new ArrayList<ArrayList<String>>(n);
		
		String csvFile = "././CSV/rooster.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
			br = new BufferedReader(new FileReader(csvFile));
			br.readLine();
			while ((line = br.readLine()) != null) {
				line = line.replace("\"", "");
				String[] element = line.split(cvsSplitBy);
				if((element[13].substring(4,7)).equals(klasStudent)) // Hieraan moet een equals methode worden toegevoegd, dan moet het werken.
				{
					ArrayList<String> tmp = new ArrayList<String>();

					String naam = element[0];
					tmp.add(naam);
					String datum = element[4];
					tmp.add(datum);
					String starttijd = element[5];
					tmp.add(starttijd);
					String eindtijd = element[8];
					tmp.add(eindtijd);
					String docent = element[11];
					if(docent.contentEquals(""))
					{
						docent = "Geen docent";
					}
					tmp.add(docent);
					String lokaal = element[12];
					tmp.add(lokaal);
					String klas = element[13];
					tmp.add(klas);
				
					hetRooster.add(tmp);
				}
				else
				{
					continue;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// close the bufferedReader if opened.
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return hetRooster;
	}

	public void voegStudentToe(Student pStudent) {
		if (!this.getStudenten().contains(pStudent)) {
			this.deStudenten.add(pStudent);
		}
	}
}
