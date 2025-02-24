package t3FX_TT1;

import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HotelDetailDialog extends Dialog<ButtonType> {

	private Hotel hotel;
	private int curser = 0;
	private Button prevButton = new Button("<<");
	private Button nextButton = new Button(">>");
	private ArrayList<Path> bilder;
	private Label lblBild = new Label();

	public HotelDetailDialog(Hotel h) {
		hotel = h;

		Accordion ac = new Accordion(bewertungTP());
		ac.setPrefSize(400, 400);
		ac.setPadding(new Insets(10));
		ac.getPanes().add(kontakt());
		ac.getPanes().add(zimmerPreis());
		ac.getPanes().add(bilder());

		this.getDialogPane().setContent(ac);
		this.getDialogPane().getButtonTypes().add(new ButtonType("Beenden", ButtonData.CANCEL_CLOSE));

	}

	private TitledPane bilder() {
		bilder = hotel.getBilder();
		lblBild.setPrefSize(270, 270);
		ImageView iv = new ImageView(bilder.get(0).toUri().toString());
		iv.setFitHeight(lblBild.getPrefHeight());
		iv.setFitWidth(lblBild.getPrefWidth());
		iv.setPreserveRatio(true);
		lblBild.setGraphic(iv);
		
        prevButton.setOnAction(e -> {
        	curser--;
        	showImage();
        });
        nextButton.setOnAction(e -> {
        	curser++;
        	showImage();
        });
        
        HBox hb = new HBox(10, prevButton, lblBild, nextButton);
        hb.setPadding(new Insets(10));
        
		return new TitledPane("Bildergalerie", hb);		
	}

	private void showImage() {
		prevButton.setDisable(true);	
		nextButton.setDisable(true);	
		
		if(bilder.size() == 0) 
			return;
		if(curser < bilder.size() - 1) {
			nextButton.setDisable(false);
		}
		if(curser > 0) {
			prevButton.setDisable(false);
		}
		ImageView iv = new ImageView(bilder.get(curser).toUri().toString());
		iv.setFitHeight(lblBild.getPrefHeight());
		iv.setFitWidth(lblBild.getPrefWidth());
		iv.setPreserveRatio(true);
		lblBild.setGraphic(iv);
	}

	private TitledPane zimmerPreis() {
		ObservableList<Preis> olPreis = FXCollections.observableArrayList(hotel.getPreise());

		TableColumn<Preis, String> typCol = new TableColumn<>("Typ");
		typCol.setCellValueFactory(new PropertyValueFactory<>("typ"));
		typCol.setPrefWidth(150);
		TableColumn<Preis, String> preisCol = new TableColumn<>("Preis pro Person und Nacht ab");
		preisCol.setCellValueFactory(new PropertyValueFactory<>("preis"));
		preisCol.setPrefWidth(200);

		TableView<Preis> tvPreis = new TableView<>(olPreis);
		tvPreis.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tvPreis.getColumns().addAll(typCol, preisCol);

		return new TitledPane("Zimmerpreis", tvPreis);
	}

	private TitledPane kontakt() {
		GridPane gp = new GridPane();
		gp.setHgap(50);
		gp.setVgap(10);
		gp.setPadding(new Insets(5));
		gp.add(new Label("Adresse"), 0, 0);
		Label adr = new Label();
		adr.setText(hotel.getAdresse());
		gp.add(adr, 1, 0);
		gp.add(new Label("Telefonnummer"), 0, 1);
		Label tel = new Label();
		tel.setText(hotel.getTelefon());
		gp.add(tel, 1, 1);
		gp.add(new Label("Email"), 0, 2);
		Label email = new Label();
		email.setText(hotel.getEmail());
		gp.add(email, 1, 2);

		return new TitledPane("Kontakt", gp);
	}

	private TitledPane bewertungTP() {
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setPadding(new Insets(5));

		Slider lageSlider = new Slider(0, 10, hotel.getLageBewertung());
		lageSlider.setShowTickLabels(true);
		lageSlider.setShowTickMarks(true);
		lageSlider.setMajorTickUnit(5);
		lageSlider.setMinorTickCount(4);

		Label lblLage = new Label();
		lblLage.setText(String.valueOf(hotel.getLageBewertung()));
		gp.add(new Label("Lage"), 0, 0);
		gp.add(lageSlider, 1, 0);
		gp.add(lblLage, 2, 0);
		return new TitledPane("Bewertung", gp);
	}
}
