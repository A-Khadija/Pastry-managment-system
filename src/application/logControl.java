
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


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
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
public class logControl  implements Initializable { 

	 @FXML
	    private ImageView open;
	    @FXML
	    private ImageView close;
	    @FXML
	    private TextField txtpassword;
	    @FXML
	    private PasswordField password;

	    @FXML
	    private TextField username;


	    
	    @FXML
	    void connecte(ActionEvent event) {

	    }
	    @FXML
	    void cancel(ActionEvent event) {
username.clear();
password.clear();
txtpassword.clear();
	    }
	    @FXML
	    void showpassword(KeyEvent event) {
	      passwordvariable=txtpassword.getText();
	      txtpassword.setText(passwordvariable);
	    }
	    @FXML
	    void hidepassword(KeyEvent event) {
	       passwordvariable=password.getText();
	       txtpassword.setText(passwordvariable);
	    }
	    @FXML
	    void openeyes(MouseEvent event) {
	        txtpassword.setVisible(false);
	        open.setVisible(false);
	        close.setVisible(true);
	        password.setVisible(true);
	    }
	    @FXML
	    void closeedeyes(MouseEvent event) {
	      txtpassword.setVisible(true);
	      open.setVisible(true);
	      close.setVisible(false);
	      password.setVisible(false);
	    }


	
	@FXML private Button btnSignin;
	@FXML private Button cancel;
	 String passwordvariable;
	 public void initialize(){
	        txtpassword.setVisible(false);
	        open.setVisible(false);
	    }
	@FXML
	private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 
	    
	public void login(ActionEvent event ) throws SQLException, IOException {
		
		 Window owner = btnSignin.getScene().getWindow();

	        System.out.println(username.getText());
	        System.out.println(password.getText());
	        
	        if (username.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter your user id");
	            return;
	        }
	        if (txtpassword.getText().isEmpty()) {
	            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
	                "Please enter a password");
	            return;
	        }
	        
	        String txt = username.getText();
	        String pass = txtpassword.getText();
	        
	        
	        JdbcDao jdbcDao = new JdbcDao();
	        boolean flag = jdbcDao.validate(txt, pass);

	        if (!flag) {
	            infoBox("Please enter correct name and Password", null, "Failed");
	        } else {
	        	//infoBox("succufly ", null, "Failed");
	        	 root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
	        	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	        	  scene = new Scene(root);
	        	  stage.setScene(scene);
	        	  stage.show();

	           
	        }
	}
		private void infoBox(String infoMessage, String headerText, String title) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setContentText(infoMessage);
	        alert.setTitle(title);
	        alert.setHeaderText(headerText);
	        alert.showAndWait();
	  
		
	}
		
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtpassword.setVisible(false);
        open.setVisible(false);
	}

}
