package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;

public class vente  implements Initializable{
	
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bakerybd";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
   
    private static Connection connection;
    
    public static Connection getConnect (){
        try {
            connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return  connection;
        }
    
    @FXML
    private ComboBox<String> combocat;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		 ObservableList<String> List = FXCollections.observableArrayList("crusts","Pastry ","Cheesecak","cakes","Cookie","bread");
		 combocat.setItems(List);
		 ToggleGroup group = new ToggleGroup();
		   available.setToggleGroup(group);
		   nonavailable.setToggleGroup(group);
		   available.setSelected(true);
	}
	

	 @FXML
	    private RadioButton available;
	 @FXML
	    private RadioButton nonavailable;
	    @FXML
	    private TextArea txtdesc;

	    @FXML
	    private TextField txtname;

	    @FXML
	    private TextField txtpc;

	    @FXML
	    private TextField txtprice;
	    
	   
	   
	    @FXML
	    private TextField pmode;
	    @FXML
	    private TextField status;

	    
	    Connection conn =null ;

	     String sql1=null;
	     
	   @FXML
	   private Stage stage;
		 private Scene scene;
		 private Parent root;
		  @FXML 
	    void ajouterVente(ActionEvent event) throws SQLException, IOException {
		  
		  
		  
		    
		    
		  String status = null;
		   if(available.isSelected()){
	        status = "available";
		   }
		else if(nonavailable.isSelected()){
		status = "non available";
	   }
		   
		   String title=txtname.getText();
		   String description=txtdesc.getText();
		  int nbpice=Integer.parseInt(txtpc.getText());
		  float price=Float.valueOf(txtprice.getText());
		  String categorie=combocat.getValue();
		  
		  
		 
		   
		  sql1="INSERT INTO produit (name,description,categorie, numberpiec ,status,price)values('"+title+"','"+description+"','"+categorie+"','"+nbpice+"','"+status+"','"+price+"') ";
		   try {
				
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakerybd","root","");
			     PreparedStatement  st=conn.prepareStatement("INSERT INTO produit (name,description,categorie, numberpiec ,status,price) values(?,?,?,?,?,?)");
			
				st.setString(1,title);
				st.setString(2,description);
				st.setString(3,categorie);
				st.setInt(4,nbpice);
				st.setString(5,status);
				st.setFloat(6,price);
				st.executeUpdate();
				conn.close();
				txtname.setText("");
				txtdesc.setText("");
				combocat.setValue("");
				txtpc.setText("");
				//status.setValue("");
				txtprice.setText("");
		   	}
				 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			
		   Alert alert = new Alert(AlertType.CONFIRMATION);
		  Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				root = FXMLLoader.load(getClass().getResource("pastrylistcontroller.fxml"));
	        	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        	  scene = new Scene(root);
	        	  stage.setScene(scene);
	        	  stage.show();
		  
	    }
	   }
		  
	
}
