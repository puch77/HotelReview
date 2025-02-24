package t3FX_TT1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Preis {
	private SimpleStringProperty typ;
	private SimpleDoubleProperty preis;

	public Preis(String t, double p) {
		typ = new SimpleStringProperty(t);
		preis = new SimpleDoubleProperty(p);
	}

	public final SimpleStringProperty typProperty() {
		return this.typ;
	}

	public final String getTyp() {
		return this.typProperty().get();
	}

	public final void setTyp(final String typ) {
		this.typProperty().set(typ);
	}

	public final SimpleDoubleProperty preisProperty() {
		return this.preis;
	}

	public final double getPreis() {
		return this.preisProperty().get();
	}

	public final void setPreis(final double preis) {
		this.preisProperty().set(preis);
	}

}
