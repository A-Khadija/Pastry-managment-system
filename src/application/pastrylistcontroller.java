package application;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.product;

public class pastrylistcontroller implements Initializable  {
	@FXML
    private TableView<product> producTable;
    @FXML
    private TableColumn<product, String> Descol;

    @FXML
    private TableColumn<product, Integer>IDcol;
    @FXML
    private Button home;
    @FXML
    private Button orders;
    @FXML
    private Button analytic;
    @FXML
    private TableColumn<product, String> catcol;

    @FXML
    private TableColumn<product, String> namcol;

    @FXML
    private TableColumn<product, Integer> nbcol;

    @FXML
    private TableColumn<product, String> prcol;
   

    @FXML
    private TableColumn<product, String> stcol;
    
    @FXML
    private TextField prodid;
    @FXML
    private TextField deletetext;
    
    int index = -1;
    
    @FXML
    void showid(MouseEvent event) {
    	index =producTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
        
            return;
        }
        deletetext.setText(IDcol.getCellData(index).toString());
    }
 
    Connection conn  ;
   public PreparedStatement st ;
    public ResultSet res;
   product product = null ;
   
   @FXML
 	private Stage stage;
 	 private Scene scene;
 	 private Parent root;
   
   ObservableList<product>  ProductList = FXCollections.observableArrayList();
   @FXML
   
public void loadDate() {
	producTable.getItems().clear();
       String sql="select IDproduit,name,description,categorie,numberpiec,price,status from produit";
       try {
		st=conn.prepareStatement(sql);
		 res = st.executeQuery();
         
         while (res.next()){
        	 
        	 ProductList.add(new product(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),res.getString(6),res.getString(7))) ;
        	 
        	 
         }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
        
        IDcol.setCellValueFactory(new PropertyValueFactory<product, Integer>("id"));
        namcol.setCellValueFactory(new PropertyValueFactory<product, String>("nom"));
        Descol.setCellValueFactory(new PropertyValueFactory<product, String>("des"));
        catcol.setCellValueFactory(new PropertyValueFactory<product, String>("cat"));
        nbcol.setCellValueFactory(new PropertyValueFactory<product, Integer>("nbpc"));
        stcol.setCellValueFactory(new PropertyValueFactory<product, String>("status"));
        prcol.setCellValueFactory(new PropertyValueFactory<product, String>("price"));
        producTable.setItems(ProductList);
}

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	conn = connection.getConnect();
	loadDate();
	
	//scene.getStylesheets().add("css/style.css");
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
void orders(ActionEvent event) throws IOException {
	Window owner =orders.getScene().getWindow();
	root = FXMLLoader.load(getClass().getResource("orders.fxml"));
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
void addvente(ActionEvent event) throws IOException {

	root = FXMLLoader.load(getClass().getResource("vente.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
}
@FXML
void searchdataa() {
	IDcol.setCellValueFactory(new PropertyValueFactory<product, Integer>("id"));
        namcol.setCellValueFactory(new PropertyValueFactory<product, String>("nom"));
        Descol.setCellValueFactory(new PropertyValueFactory<product, String>("des"));
        catcol.setCellValueFactory(new PropertyValueFactory<product, String>("cat"));
        nbcol.setCellValueFactory(new PropertyValueFactory<product, Integer>("nbpc"));
        stcol.setCellValueFactory(new PropertyValueFactory<product, String>("status"));
        prcol.setCellValueFactory(new PropertyValueFactory<product, String>("price"));
        ProductList=connection.getDatausers();
        producTable.setItems(ProductList);
    FilteredList<product> filteredData = new FilteredList<>(ProductList, b -> true);  
  
    prodid.textProperty().addListener((observable, oldValue, newValue) -> {
filteredData.setPredicate(prod -> {
if (newValue == null || newValue.isEmpty()) {
 return true;
}    
String lowerCaseFilter = newValue.toLowerCase();

if (prod.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
    return true; // Filter matches username
   } else if (prod.getDes().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    return true; // Filter matches password
   }else if (prod.getStatus().toLowerCase().indexOf(lowerCaseFilter) != -1) {
    return true; // Filter matches password
   }
   else if (String.valueOf(prod.getCat()).indexOf(lowerCaseFilter)!=-1)
        return true;// Filter matches email
                               
        else  
         return false; // Does not match.
  });
 }); 

SortedList<product> sortedData = new SortedList<>(filteredData);  
 sortedData.comparatorProperty().bind( producTable.comparatorProperty());  
 producTable.setItems(sortedData);      
   }


@FXML
void searchdata(ActionEvent event) {
	searchdataa();
}

private void infoBox(String infoMessage, String headerText, String title) {
	Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText(infoMessage);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.showAndWait();


}
@FXML
void delete(ActionEvent event) throws SQLException {
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakerybd","root","");
	    String sql = "delete from produit where IDproduit = ?";

	        try {
	        	PreparedStatement  pst = conn.prepareStatement(sql);
	            pst.setString(1,deletetext.getText());
	            pst.execute();
	          
	        } catch (Exception e) {
	           
	        }
	        infoBox("Pastry has been deleted sucessfully", null, "sucess");
}

}