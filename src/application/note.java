package application;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class note  implements Initializable {
	@FXML
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	  @FXML
	    private Button ajouternote;

	    @FXML
	    private TextArea description;

	    @FXML
	    private TextField title;
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	}
	 @FXML
	 void ajoutenote(ActionEvent event) throws IOException {
		 

		        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		        BorderPane root1 = FXMLLoader.load(getClass().getResource("ANALYTIC.fxml"));
		        
		        analytic.titlecopy.setText(title.getText());
		    	analytic.desccopy.setText(description.getText());
			   
				scene = new Scene(root1,1071, 716);

			    stage.setScene(scene);
	 }
}
