package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.PreparableStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.product;

public class order implements Initializable {
	
	Connection conn  ;
 public PreparedStatement st ;
public ResultSet res;
	product product = null ;
    @FXML
    private TextField quantity;
    @FXML
    private Button home;
    @FXML
    private Button logout;
    @FXML
    private Button analytic;
   
    @FXML
    private ComboBox<String> namecomb;
    
    @FXML
    private TextField change;

    @FXML
    private TextField title;


    @FXML
    private TextField mantant;

    @FXML
    private Button minusbtn;

    @FXML
    private Button produit;

    @FXML
    private Button plusbtn;

    @FXML
    private TextField price;

    @FXML
    private TextField qte;

    @FXML
    private TextField total;
    
    int i=1;
    @FXML
	private Stage stage;
	 private Scene scene;
	 private Parent root;

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
    private ComboBox<String> comboPaiement;
    
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
    void product(ActionEvent event) throws IOException {
    	Window owner =produit.getScene().getWindow();
    	root = FXMLLoader.load(getClass().getResource("pastrylistcontroller.fxml"));
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
    
   public void settoTXTf() {
	 String selectedvalue=namecomb.getValue().toString();
   	title.setText(selectedvalue);
   	String sql="SELECT price FROM produit where name='"+selectedvalue+"'";
   	String userData ;
   	
   	try {
		st=conn.prepareStatement(sql);
		 res = st.executeQuery();
		 while (res.next()) {
		   	 userData =  res.getString("price");
		   	 price.setText( userData);   
		 }
		 
   	
   } catch (Exception e) {
	   throw new RuntimeException("uncaught", e);
	 };
	}

   	
   
    @FXML
    void search(ActionEvent event) {
    	 settoTXTf() ;
    }
   public void fillcombo()
    {
        String sql="SELECT name FROM produit";
        List<String> product=new ArrayList<String>();
        
        try {
    		st=conn.prepareStatement(sql);
    		 res = st.executeQuery();
             
             while (res.next()){
            	 product.add(res.getString("name"));
           
            }
             conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        namecomb.setItems(FXCollections.observableArrayList(product));
       
	   }
  
    @FXML
    void minusbtn(ActionEvent event) {
    	if(i>1) {
    		i--;
        	qte.setText(String.valueOf(i));
        	Double priceValue = Double.parseDouble(price.getText());
        	int qteValue = Integer.parseInt(qte.getText());
        	
        	Double totalprice = (priceValue * qteValue);
        	String totalfinal=totalprice.toString();
        	this.total.setText(totalfinal);
    	}else {
    		 showAlert(Alert.AlertType.ERROR, "Form Error!",
    				 "quantity must be 1 at least ");
    	}
    }

    private void showAlert(AlertType error, String string, String string2) {
		
	}
	@FXML
    void plusbtn(ActionEvent event) {
		i++;
    	qte.setText(String.valueOf(i));
    	Double priceValue = Double.parseDouble(price.getText());
    	int qteValue = Integer.parseInt(qte.getText());
    	
    	Double totalprice = (priceValue * qteValue);
    	String totalfinal=totalprice.toString();
    	this.total.setText(totalfinal);
    }
    
    @FXML
    void calculechange(KeyEvent event) {
    	Double totalValue = Double.parseDouble(total.getText());
    	int moneyValue = Integer.parseInt(mantant.getText());
    	
    	Double change = (moneyValue - totalValue);
    	String changefinal=change.toString();
    	this.change.setText(changefinal);
    }

    @FXML
    void calculetotal(KeyEvent event) {
    	Double priceValue = Double.parseDouble(price.getText());
    	int qteValue = Integer.parseInt(qte.getText());
    	
    	Double totalprice = (priceValue * qteValue);
    	String totalfinal=totalprice.toString();
    	this.total.setText(totalfinal);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		conn = connection.getConnect();
		fillcombo();
		
		 ObservableList<String> List = FXCollections.observableArrayList("Cash","CreditCard");
         comboPaiement.setItems(List);
         
        
	}
	  @FXML
	    void validorders(ActionEvent event) throws IOException {
		  
		 
		   
		   String titre=title.getText();
		  int nbpice=Integer.parseInt(qte.getText());
		  float Price=Float.valueOf(price.getText());
		  String paiement=comboPaiement.getValue();
		 // LocalDate today = LocalDate.now(ZoneId.systemDefault());
		  
		 
		   
		 // sql2="INSERT INTO vente (nomprod,price,datevente, Qte ,paiementmode)values('"+titre+"','"+ Price+"','"+today+"','"+nbpice+"','"+paiement+"') ";
		   try {
				
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakerybd","root","");
			     PreparedStatement  st=conn.prepareStatement("INSERT INTO vente (nomprod,price,datevente, Qte ,paiementmode)values(?,?,?,?,?)");
			
				st.setString(1,titre);
				st.setFloat(2,Price);
				st.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
				st.setInt(4,nbpice);
				st.setString(5,paiement);
			
				st.executeUpdate();
				conn.close();
				title.setText("");
				qte.setText("");
				price.setText("");
				comboPaiement.setValue("");
				
		   	}
				 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			
		   Alert alert = new Alert(AlertType.CONFIRMATION);
		  Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				root = FXMLLoader.load(getClass().getResource("orders.fxml"));
	        	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        	  scene = new Scene(root);
	        	  stage.setScene(scene);
	        	  stage.show();
		  
	    }
	    }
	  @FXML
	    void changedcombo(MouseEvent event) throws IOException {
		  String selectedvalue=comboPaiement.getValue().toString();
	         if (selectedvalue=="CreditCard") {
	        	
       }  }
@FXML
void pay(ActionEvent event) throws IOException {
	
		root = FXMLLoader.load(getClass().getResource("banquecard.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
}}

