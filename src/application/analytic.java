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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;


public class analytic  implements Initializable{
	 
	 @FXML
	    private Button analytic;
	 @FXML
	    private Button produit;

	    @FXML
	    private Button home;
	    @FXML
	    private Button logout;

	    @FXML
		private Stage stage;
		 private Scene scene;
		 private Parent root;
	    
	    @FXML
	    private Button note;
	    @FXML
	    private Button order;
	    
	    @FXML
	    private Label title;
	    
	    @FXML
	    private Button btnnote;

	    @FXML
	    private Label description;

	    public static Label titlecopy;
	    public static Label desccopy;
	    
	    @FXML
	    void addnote(ActionEvent event) {
	    try{
	    	root = FXMLLoader.load(getClass().getResource("note.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	     	 scene = new Scene(root);
	     	 stage.setScene(scene);
	     	 stage.show();

		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    }
	    
	    @FXML
	    void note(ActionEvent event) throws IOException, InterruptedException {
	    	 Window owner = note.getScene().getWindow();
	    	root = FXMLLoader.load(getClass().getResource("note.fxml"));
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
	    @FXML
	    private BarChart<?, ?> barchart;
	
	   
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
	    void home(ActionEvent event) throws IOException {
	    	Window owner = home.getScene().getWindow();
	    	root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
      	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	  scene = new Scene(root);
      	  stage.setScene(scene);
      	  stage.show();
	    }

	    @FXML
	    void order(ActionEvent event) throws IOException {
	    	Window owner = order.getScene().getWindow();
	    	root = FXMLLoader.load(getClass().getResource("orders.fxml"));
      	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
      	  scene = new Scene(root);
      	  stage.setScene(scene);
      	  stage.show();
	    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		XYChart.Series serie = new XYChart.Series();
		serie.setName("2021");
		serie.getData().add(new XYChart.Data("Cheese cake", 290));
		serie.getData().add(new XYChart.Data("Macaron", 1000));
		serie.getData().add(new XYChart.Data("Mose cake", 509));
		serie.getData().add(new XYChart.Data("Choclate cake", 340));
		serie.getData().add(new XYChart.Data("Croissant", 2010));
		serie.getData().add(new XYChart.Data("Blueberry Cake", 1290));
		
		XYChart.Series serie2 = new XYChart.Series();
		serie2.setName("2022");
		serie2.getData().add(new XYChart.Data("Cheese cake", 280));
		serie2.getData().add(new XYChart.Data("Macaron", 100));
		serie2.getData().add(new XYChart.Data("Mose cake", 349));
		serie2.getData().add(new XYChart.Data("Choclate cake", 150));
		serie2.getData().add(new XYChart.Data("Croissant", 1810));
		serie2.getData().add(new XYChart.Data("Blueberry Cake", 1000));
		
		barchart.getData().addAll(serie,serie2);
		
		
		
		
		
		
		
		//code dyal note
		
		titlecopy=title ;
		desccopy=description;
		
	}

}

 

	
	    
	    


