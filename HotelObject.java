package t3FX_TT1;

import javafx.beans.property.SimpleStringProperty;

public class HotelObject {
	private SimpleStringProperty name;
	private SimpleStringProperty adresse;

	public HotelObject(String n, String a) {
		name = new SimpleStringProperty(n);
		adresse = new SimpleStringProperty(a);
	}

	public HotelObject(String n) {
		this(n, "");
	}

	public final SimpleStringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	

	public final SimpleStringProperty adresseProperty() {
		return this.adresse;
	}
	

	public final String getAdresse() {
		return this.adresseProperty().get();
	}
	

	public final void setAdresse(final String adresse) {
		this.adresseProperty().set(adresse);
	}
	
}
