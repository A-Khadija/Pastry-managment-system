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

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.Window;


public class home  implements Initializable{
	
	 @FXML
	    private Button facebook;

	    @FXML
	    private Button google;
	    @FXML
	    private Button produit;
	    @FXML
	    private Button twitter;

	    @FXML
	    private VBox vbox;

	    @FXML
	    private WebView webview;

	    @FXML
	    private Button youtube;
	    
	    @FXML
	    private Button analytic;
	    @FXML
	    private Button order;

	    @FXML
	    private Button logout;
	  
	    @FXML
		private Stage stage;
		 private Scene scene;
		 private Parent root;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	WebEngine engine = webview.getEngine();	
	engine.load("https://www.google.com/");
	
    }
	 @FXML
	    void initializefacebook(ActionEvent event) {
		 WebEngine engine = webview.getEngine();	
		 engine.load("https://www.facebook.com/");
	    }

	    @FXML
	    void initializegoogle(ActionEvent event) {
	    	WebEngine engine = webview.getEngine();	
			engine.load("https://www.google.com/");
	    }

	    @FXML
	    void initializetwitter(ActionEvent event) {
	    	WebEngine engine = webview.getEngine();	
			engine.load("https://www.twitter.com/");
	    }

	    @FXML
	    void initializeyoutube(ActionEvent event) {
	    	WebEngine engine = webview.getEngine();	
			engine.load("https://www.youtube.com/");
	    }
	    @FXML
	    void order(ActionEvent event) throws IOException {
	    	Window owner =order.getScene().getWindow();
	    	root = FXMLLoader.load(getClass().getResource("orders.fxml"));
      	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	  scene = new Scene(root);
      	  stage.setScene(scene);
      	  stage.show();
	    }
	    @FXML
	    void product(ActionEvent event) throws IOException {
	    	Window owner =produit.getScene().getWindow();
	    	root = FXMLLoader.load(getClass().getResource("pastrylistcontroller.fxml"));
	  	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  	  scene = new Scene(root);
	  	  stage.setScene(scene);
	  	  stage.show();
	    }
	    @FXML
	    void analytic(ActionEvent event) throws IOException {
	   	 Window owner =analytic.getScene().getWindow();
	    	root = FXMLLoader.load(getClass().getResource("ANALYTIC.fxml"));
      	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	  scene = new Scene(root);
      	  stage.setScene(scene);
      	  stage.show();
	    }
	    @FXML
	    void logout(ActionEvent event) throws IOException {
	    	 Window owner =logout.getScene().getWindow();
		    	root = FXMLLoader.load(getClass().getResource("login.fxml"));
	      	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	      	  scene = new Scene(root);
	      	  stage.setScene(scene);
	      	  stage.show();
	    }
	  
}