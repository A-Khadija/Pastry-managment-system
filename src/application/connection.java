package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.product;




public class connection {
	
	
	
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/bakerybd";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
   
    private static Connection connection ;
    
    
    public static Connection getConnect (){
    try {
        connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    } catch (SQLException ex) {
        Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        return  connection;
    }
    public static ObservableList<product> getDatausers(){
        Connection conn = getConnect();
        ObservableList<product> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new product(Integer.parseInt(rs.getString("IDproduit")), rs.getString("name"), rs.getString("description"), rs.getString("categorie"), Integer.parseInt(rs.getString("numberpiec")),rs.getString("status"),rs.getString("price")));               
            }
        } catch (Exception e) {
        }
        return list;
    }
    public static void closeConnection()
    {       
        if(connection != null)
        	connection = null;
    }
}