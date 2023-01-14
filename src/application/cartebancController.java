package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class cartebancController implements Initializable  {

    @FXML
    private TextField ccv;

    @FXML
    private TextField date;

    @FXML
    private TextField localisation;

    @FXML
    private TextField numcarte;

    @FXML
    private Button save;

    @FXML
    private ComboBox<String> type;
    @FXML
   	private Stage stage;
   	 private Scene scene;
   	 private Parent root;
    
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
    
    
    private static void infoBox(String infoMessage, String headerText, String title) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
        }
    public static boolean validitychk(long cnumber) {
	      return (thesize(cnumber) >= 13 && thesize(cnumber) <= 16) && (prefixmatch(cnumber, 4)
	         || prefixmatch(cnumber, 5) || prefixmatch(cnumber, 37) || prefixmatch(cnumber, 6))
	         && ((sumdoubleeven(cnumber) + sumodd(cnumber)) % 10 == 0);
	}
	   
	   public static int sumdoubleeven(long cnumber) {
	      int sum = 0;
	      String num = cnumber + "";
	      for (int i = thesize(cnumber) - 2; i >= 0; i -= 2)
	         sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
	      return sum;
	   }
	   public static int getDigit(int cnumber) {
		      if (cnumber < 9)
		         return cnumber;
		      return cnumber / 10 + cnumber % 10;
		   }
		 
	   
		   public static int sumodd(long cnumber) {
		      int sum = 0;
		      String num = cnumber + "";
		      for (int i = thesize(cnumber) - 1; i >= 0; i -= 2)
		         sum += Integer.parseInt(num.charAt(i) + "");
		      return sum;
		   }
		   
		   
		   public static boolean prefixmatch(long cnumber, int d) {
		      return getprefx(cnumber, thesize(d)) == d;
		  }
		   
		   
		   public static int thesize(long d) {
			      String num = d + "";
			      return num.length();
		}
			 
			   public static long getprefx(long cnumber, int k) {
			      if (thesize(cnumber) > k) {
			         String num = cnumber + "";
			         return Long.parseLong(num.substring(0, k));
			      }
			      return cnumber;
            }
			   public static boolean isValidCVVNumber(String str){
					
					String regex = "^[0-9]{3,4}$";

				    Pattern p = Pattern.compile(regex);
					
					if (str == null)
					{
					return false;
					}
					
					Matcher m = p.matcher(str);
					return m.matches();
					}
			   public String GetCardType(String cardNumber)
			     {
					 if (cardNumber.startsWith("4"))
			     {
			         return "Visa";
			     }
			     // MasterCard
			     else if (cardNumber.startsWith("51") || cardNumber.startsWith("52") ||
			         cardNumber.startsWith("53") || cardNumber.startsWith("54") || cardNumber.startsWith("55"))
			     {
			         return "MasterCard";
			     }
			     // American Express
			     else if (cardNumber.startsWith("34") || cardNumber.startsWith("37"))
			     {
			         return "American Express";
			     }
			     // Unknown
			     else
			     {
			         return "Unknown";
			     }
			     }
    @FXML
    void saveall(ActionEvent event) throws IOException {
    	long input =Long.parseLong(numcarte.getText());
		String inputcvv= ccv.getText();
		
	     if(!validitychk(input)) {
		 infoBox("Please enter correct card number ", null, "Failed");
	     }else
	   if(!isValidCVVNumber(inputcvv)) {
	     infoBox("For CVVnumber ONLY digits are allowed", null, "Failed"); 
	     
	   }else {
		   infoBox("Informations registered Sucessfully", null, "Sucess");
		     int cvv= Integer.parseInt(ccv.getText());
		     String num =numcarte.getText();
			 String local = localisation.getText(); 
			 String dateexp = date.getText();
			 String typecar=type.getValue();
			 try {
					
					 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakerybd","root","");
				     PreparedStatement  st=conn.prepareStatement("INSERT INTO paiement (Numcart,typcart,ccv,lacalisation,dateExp) values(?,?,?,?,?)");
				
					st.setInt(3,cvv);
					st.setString(5,dateexp);
					st.setString(4,local);
					st.setString(1,num);
					st.setString(2,typecar);
					st.executeUpdate();
					conn.close();
					
					 Alert alert = new Alert(AlertType.CONFIRMATION);
					  Optional<ButtonType> result = alert.showAndWait();
						if (result.isPresent() && result.get() == ButtonType.OK) {
							root = FXMLLoader.load(getClass().getResource("orders.fxml"));
				        	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				        	  scene = new Scene(root);
				        	  stage.setScene(scene);
				        	  stage.show();
			     	
						}} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
 
	     }
	     
	     
	   }
	     
	

    @FXML
    void typecarte(KeyEvent event) {
    	 String cardType = GetCardType(numcarte.getText());
         type.setValue(cardType);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> List = FXCollections.observableArrayList("Visa","MasterCard","Unknown","American Express");
        type.setItems(List);
		
	}

}

