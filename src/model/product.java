package model;



public class product {
	
int id;
String des;
String cat;
String nom;
int nbpc;
String price;
String status;
public product() {
	super();
}
public product(int id, String nom, String des, String cat , int nbpc , String price, String status) {
		
		this.id = id;
		this.des = des;
		this.cat = cat;
		this.nom = nom;
		this.nbpc =nbpc ;
		this.price = price;
		this.status = status;
	}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDes() {
	return des;
}
public void setDes(String des) {
	this.des = des;
}
public String getCat() {
	return cat;
}
public void setCat(String cat) {
	this.cat = cat;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public int getNbpc() {
	return nbpc;
}
public void setNbpc(int nbpc) {
	this.nbpc = nbpc;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
}
