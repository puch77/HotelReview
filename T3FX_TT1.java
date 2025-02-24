package t3FX_TT1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Stage;

public class T3FX_TT1 extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		TreeItem<HotelObject> wien = new TreeItem<>(new HotelObject("Wien"));
		TreeItem<HotelObject> kat5 = new TreeItem<>(new HotelObject("Kategorie 5"));
		TreeItem<HotelObject> sacher = new TreeItem<>(new Hotel("Sacher", "1010 Wien Ringstrasse 5", "+43 13256874", "sacherOffice@sacher.at", 9.8, 9.5, 8.7,
				new Preis("Standard Doppelzimmer", 395),
				new Preis("Junior Suite", 525)));
		kat5.getChildren().addAll(sacher);
		wien.getChildren().addAll(kat5);
		
		TreeTableColumn<HotelObject, String> nameCol = new TreeTableColumn<>("Name");
		nameCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("name")); //setCellFactory for the graphical outfit!!
		nameCol.setPrefWidth(150);
		TreeTableColumn<HotelObject, String> adrCol = new TreeTableColumn<>("Adresse");
		adrCol.setCellValueFactory(new TreeItemPropertyValueFactory<>("adresse"));
		adrCol.setPrefWidth(200);
		
		TreeTableView<HotelObject> ttv = new TreeTableView<>(wien);
		ttv.getColumns().addAll(nameCol, adrCol);
		ttv.setTableMenuButtonVisible(true);
		ttv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		ttv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<HotelObject>>() {
			@Override
			public void changed(ObservableValue<? extends TreeItem<HotelObject>> arg0, TreeItem<HotelObject> arg1,
					TreeItem<HotelObject> arg2) {
				if(arg2 != null && arg2.getValue() instanceof Hotel) {
					new HotelDetailDialog((Hotel) arg2.getValue()).showAndWait();
				}
				
			}
			
		});
		
		arg0.setScene(new Scene(ttv));
		arg0.setTitle("T3FX_TT1");
		arg0.show();
	}

}
