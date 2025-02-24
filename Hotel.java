package t3FX_TT1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Hotel extends HotelObject {
	private String email;
	private String telefon;
	private double lageBewertung;
	private double zimmerBewertung;
	private double serviceBewertung;
	private ArrayList<Path> bilder = new ArrayList<>();
	private ArrayList<Preis> preise = new ArrayList<>();

	public Hotel(String n, String a, String email, String telefon, double lageBewertung, double zimmerBewertung,
			double serviceBewertung, Preis... p) {
		super(n, a);
		this.email = email;
		this.telefon = telefon;
		this.lageBewertung = lageBewertung;
		this.zimmerBewertung = zimmerBewertung;
		this.serviceBewertung = serviceBewertung;
		for (Preis ein : p) {
			preise.add(ein);
		}
		// Sacher
		// Sacher 1.jpg, Sacher 2.jpg
		try {
			DirectoryStream<Path> dsp = Files.newDirectoryStream(Path.of(".\\resources"), n + "*.{png,jpg,gif}"); //no space between patterns!!
			for (Path ein : dsp) {
				bilder.add(ein);
				//System.out.println(ein);
			}
			dsp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getEmail() {
		return email;
	}

	public String getTelefon() {
		return telefon;
	}

	public double getLageBewertung() {
		return lageBewertung;
	}

	public double getZimmerBewertung() {
		return zimmerBewertung;
	}

	public double getServiceBewertung() {
		return serviceBewertung;
	}

	public ArrayList<Path> getBilder() {
		return bilder;
	}

	public ArrayList<Preis> getPreise() {
		return preise;
	}

}
