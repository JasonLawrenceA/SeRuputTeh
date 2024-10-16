package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;

import connect.Connect;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Cart;
import models.Product;
import models.TransactionDetail;
import models.TransactionHeader;
import models.User;


public class Main extends Application{
	
	//Database
	Connect connect = Connect.getInstance();
	
	Vector<Product> productlistfull = new Vector<>();
	Vector<String> productlist = new Vector<>();
	
	Vector<Cart> productlistcart = new Vector<>();
	Vector<String> productlistcartname = new Vector<>();
		
	TableView<User> usertable = new TableView<>();;
	Vector<User> userlist = new Vector<>();
	
	String useridselected;
	String usernameselected;
	String productselected;
	int productselectedprice;
	int totalpriceproduct;
	
	Vector<TransactionHeader> transactionlist = new Vector<>();
	
	TransactionHeader transactionidselected;
	
	Vector<String> productlistcartph = new Vector<>();
	
	String useridincrement;
	String productidincrement;
	String transactionidincrement;
	
	// Login Scene
	Scene scene;
	BorderPane root = new BorderPane();
	GridPane gp = new GridPane();
	ScrollPane sp = new ScrollPane();
	VBox vblogin = new VBox(); 
	HBox hblogin = new HBox();
	
	Label logintitle = new Label("Login");
	Label usernamelbl = new Label("Username : ");
	Label passwordlbl = new Label("Password : ");
	Label accountstatuslbl = new Label("Don't have an account yet? ");
	Label registerherelbl = new Label("Register Here!");
	
	
	Button loginbtn = new Button("Login");

	
	TextField usernametf = new TextField();
	PasswordField passwordpf = new PasswordField();
	
	
	// Registration Scene
	Scene registrationscene;
	BorderPane registrationbp = new BorderPane();
	GridPane registrationgp = new GridPane();
	FlowPane registrationfp = new FlowPane();
	FlowPane registrationfp1 = new FlowPane();
	ScrollPane registrationsp = new ScrollPane();
	
	
	Label registrationtitle = new Label("Registration");
	Label usernamelblreg = new Label("Username : ");
	Label passwordlblreg = new Label("Password : ");
	Label confirmpasslblreg = new Label("Confirm Password : ");
	Label phonenumberlbl = new Label("Phone number : ");
	Label Addresslbl = new Label("Address : ");
	Label Genderlbl = new Label("Gender : ");
	Label haveanaccountlbl = new Label("Have an account? ");
	Label loginherelbl = new Label("Login Here!");
	
	
	TextField usernametextfieldreg = new TextField();
	PasswordField passwordfieldreg = new PasswordField();
	PasswordField confirmpasspf = new PasswordField();
	TextField phonenumbertextfieldreg = new TextField();
	TextArea addressaf = new TextArea();
	
	RadioButton malerb = new RadioButton("Male");
	RadioButton femalerb = new RadioButton("Female");
	ToggleGroup gendertg = new ToggleGroup();
	CheckBox iagreebox = new CheckBox("I agree to all terms and condition");
	Button registerbtn = new Button("Register");

	
	
	
	//Navigation
	//Customer
	MenuBar menuBarcustomer = new MenuBar();
	Menu homemenu = new Menu("Home");
	Menu cartmenu = new Menu("Cart");
	Menu accountcustomermenu = new Menu("Account");
	MenuItem homepagemenu = new MenuItem("HomePage");
	MenuItem mycartmenu = new MenuItem("My Cart");
	MenuItem logoutmenu = new MenuItem("Log Out");
	MenuItem purchasehistorymenu = new MenuItem("Purchase History");
	//Admin
	MenuBar menubaradmin = new MenuBar();
	Menu homemenuadmin = new Menu("Home");
	Menu manageproductsmenu = new Menu("Manage Products");
	Menu accountmenuadmin = new Menu ("Account");
	MenuItem homepagemenuadmin = new MenuItem("HomePage");
	MenuItem manageproductsitemmenu = new MenuItem("Manage products");
	MenuItem logoutmenuadmin = new MenuItem("Log Out");
	
	
	// Home Scene
	Scene homecustomerscene;
	Scene homeadminscene;
	BorderPane homebp = new BorderPane();
	BorderPane homebpadmin = new BorderPane();
	GridPane homegp = new GridPane();
	ScrollPane homesp = new ScrollPane();
	VBox vbhome = new VBox();
	GridPane homegp1 = new GridPane();
	GridPane homegpproducts = new GridPane();

	
	Label hometitle = new Label("SeRuput Teh");
	Label welcomelbl = new Label("Welcome, ");
	Label selectproductviewlbl = new Label("Select a product to view");
	
	ListView<String> listviewproduct = new ListView<>();
	
	Label producttitle = new Label();
	Label productdesc = new Label();
	Label productprice = new Label();
	Label productquantitylbl = new Label();
	Spinner<Integer> quantityspinner = new Spinner<>(1, 99, 1);
	int currentvalueproduct = quantityspinner.getValue();
	Label totalpricelbl = new Label();
	HBox hboxhomeproduct = new HBox();
	Button addtocartbtn = new Button("Add To Cart");
	
	
	
	//Cart Scene
	Scene cartscene;
	BorderPane cartbp = new BorderPane();
	GridPane cartgp = new GridPane();
	ScrollPane cartsp = new ScrollPane();
	VBox vbcart = new VBox();
	VBox vbcart2 = new VBox();
	
	Label carttitle = new Label("(Name) Cart");
	Label cartwelcomelbl = new Label("Welcome, ");
	Label selectproductcartlbl = new Label("Select a product to add and remove");
	
	ListView<Cart> listviewproductcart = new ListView<>();
	
	Label totalcartlbl = new Label("Total : ");
	Label orderinfolbl = new Label("Order Information : ");
	Label usernamecartlbl = new Label("Username : ");
	Label phonenumbercartlbl = new Label("Phone Number : ");
	Label addresscartlbl = new Label("Address : ");
	
	Button makepurchasebtn = new Button("Make Purchase");
	
	
	VBox vbcart3 = new VBox();
	HBox hbcart = new HBox();
	HBox hbcart1 = new HBox();
	Label producttitlecart = new Label("Product title");
	Label productdesccart = new Label("product desc");
	Label productpricecart = new Label("product price");
	Label productquantitycart = new Label("Quantity: ");
	Label totalpriceproductcart = new Label("Total: ");
	
	Spinner<Integer> quantityspinnercart = new Spinner<>(-99, 99, 1);
	
	Button updatecartbtn = new Button("Update Cart");
	Button removefromcartbtn = new Button("Remove From Cart");
	
	Cart cartselectionname;
	String cartselectionidproduct;
	
	
	//Purchase History Scene
	Scene purchasehistoryscene;
	BorderPane purchasehistorybp = new BorderPane();
	GridPane purchasehistorygp = new GridPane();
	ScrollPane purchasehistorysp = new ScrollPane();
	VBox vbpurchasehistory = new VBox();
	VBox vbpurchasehistory1 = new VBox();
	VBox vbpurchasehistory2 = new VBox();
	VBox vbpurchasehistory3 = new VBox();
	
	Label purchasehistorytitle = new Label();
	Label nohistorylbl = new Label("There's No History");
	Label considerlbl = new Label("Consider Purchasing Our Products");
	Label selecttransactionlbl = new Label("Select a Transaction to view Details");
	Label transactionidlbl = new Label("transactionid");
	Label usernamephlbl = new Label("username");
	Label phonenumberphlbl = new Label("phonenumber");
	Label addressphlbl = new Label("address");
	Label totalpricephlbl = new Label("totalprice");
	
	ListView<TransactionHeader> listviewpurchasehistory = new ListView<>();
	
	ListView<String> listviewcartph = new ListView<>();
	
	
	//Manage Products Scene
	Scene manageproductsscene;
	BorderPane manageproductsbp = new BorderPane();
	GridPane manageproductsgp = new GridPane();
	ScrollPane manageproductssp = new ScrollPane();
	VBox manageproductsvb = new VBox();
	VBox manageproductsvb1 = new VBox();
	VBox manageproductsvb2 = new VBox();
	VBox manageproductsvb3 = new VBox();
	VBox manageproductsvb4 = new VBox();
	HBox manageproductshb = new HBox();
	HBox manageproductshb1= new HBox();
	
	Label manageproductstitle = new Label("Manage Products");
	
	ListView<String> listviewmanageproduct = new ListView<>();
	
	Label welcomemanageproductslbl = new Label();
	Label selectproductupdatelbl = new Label("Select a Product to Update");
	
	Label producttitlemanagelbl = new Label();
	Label productdescmanagelbl = new Label();
	Label productpricemanagelbl = new Label();
	
	
	Label inputproductnamelbl = new Label("Input product name");
	Label inputproductpricelbl = new Label("Input product price");
	Label inputproductdeslbl = new Label("Input product description...");
	
	TextField productnametf = new TextField();
	TextField productpricetf = new TextField();
	TextArea productdesta = new TextArea();
	
	Button addproductbtn = new Button("Add Product");
	Button backbtn = new Button("Back");
	Button addproductbtn1 = new Button("Add Product");
	
	Button updateproductbtn = new Button("Update Product");
	Button removeproductbtn = new Button("Remove Product");
	
	int manageproductclickstatus = 1;
	int manageproductclickstatus1 = 1;
	
	Label updateproductlbl = new Label("Update Product");
	TextField updateproducttf = new TextField();
	Button updateproduct1btn = new Button("Update Product");
	Button backupdateproductbtn = new Button("Back");
	
	Label removeconfirmationlbl = new Label("Are you sure, youwant to remove this product?");
	
	Button removeproductbtn1 = new Button("Remove Product");
	Button backremoveproductbtn = new Button("Back");
	
	

	
	public void setelements() {
		// Login Scene
		root.setCenter(hblogin);
		gp.add(logintitle, 0, 0);
		gp.add(usernamelbl, 0, 1);
		gp.add(passwordlbl, 0, 2);
		gp.add(accountstatuslbl, 1, 3);
		gp.add(registerherelbl, 2, 3);
		gp.add(loginbtn, 1, 4);
		
		gp.add(usernametf, 1, 1);
		gp.add(passwordpf, 1, 2);
		
		
		// Registration Scene
		registrationsp.setContent(registrationgp);
		registrationbp.setCenter(registrationsp);
		
		registrationfp.getChildren().addAll(malerb, femalerb);
		registrationfp1.getChildren().addAll(haveanaccountlbl, loginherelbl);
		
		malerb.setToggleGroup(gendertg);
		femalerb.setToggleGroup(gendertg);
		
		registrationgp.add(registrationtitle, 0, 0);
		registrationgp.add(usernamelblreg, 0, 1);
		registrationgp.add(passwordlblreg, 0, 2);
		registrationgp.add(confirmpasslblreg, 0, 3);
		registrationgp.add(phonenumberlbl, 0, 4);
		registrationgp.add(Addresslbl, 0, 5);
		registrationgp.add(Genderlbl, 0, 6);
		
		registrationgp.add(usernametextfieldreg, 1, 1);
		registrationgp.add(passwordfieldreg, 1, 2);
		registrationgp.add(confirmpasspf, 1, 3);
		registrationgp.add(phonenumbertextfieldreg, 1, 4);
		registrationgp.add(addressaf, 1, 5);
		registrationgp.add(registrationfp, 1, 6);
		registrationgp.add(iagreebox, 1, 7);
		registrationgp.add(registrationfp1, 1, 8);
		registrationgp.add(registerbtn, 1, 9);
		
		// Nagivation
		//Customer
		menuBarcustomer.getMenus().addAll(homemenu, cartmenu, accountcustomermenu);
		homemenu.getItems().add(homepagemenu);
		cartmenu.getItems().add(mycartmenu);
		accountcustomermenu.getItems().addAll(purchasehistorymenu, logoutmenu);
		//Admin
		menubaradmin.getMenus().addAll(homemenuadmin, manageproductsmenu, accountmenuadmin);
		homemenuadmin.getItems().add(homepagemenuadmin);
		manageproductsmenu.getItems().add(manageproductsitemmenu);
		accountmenuadmin.getItems().addAll(logoutmenuadmin);	
		
		// Cart Scene
		cartsp.setContent(cartgp);
		cartbp.setCenter(cartsp);
		cartgp.add(carttitle, 0, 0);
		cartgp.add(listviewproductcart, 0, 1);
		vbcart.getChildren().addAll(cartwelcomelbl, selectproductcartlbl);
		cartgp.add(vbcart, 1, 1);
		vbcart2.getChildren().addAll(totalcartlbl, orderinfolbl, usernamecartlbl, phonenumbercartlbl, addresscartlbl, makepurchasebtn);
		cartgp.add(vbcart2, 0, 2);
		
		
		vbcart3.getChildren().addAll(producttitlecart, productdesccart, productpricecart, hbcart, hbcart1);
		hbcart.getChildren().addAll(productquantitycart, quantityspinnercart, totalpriceproductcart);
		hbcart1.getChildren().addAll(updatecartbtn, removefromcartbtn);
		
		
		
		// Manage Products Scene
		manageproductssp.setContent(manageproductsgp);
		manageproductsbp.setCenter(manageproductssp);
		manageproductsgp.add(manageproductstitle, 0, 0);
		manageproductsgp.add(listviewmanageproduct, 0, 1);
		manageproductsgp.add(manageproductsvb, 1, 1);
		manageproductsvb.getChildren().addAll(manageproductsvb1, manageproductsvb2, manageproductshb, manageproductshb1);
		manageproductsvb1.getChildren().addAll(welcomemanageproductslbl, selectproductupdatelbl);
		manageproductshb.getChildren().addAll(addproductbtn);
		manageproductsvb3.getChildren().addAll(updateproductlbl, updateproducttf);
		manageproductsvb4.getChildren().addAll(removeconfirmationlbl);
		
		
		// Purchase History Scene
		purchasehistorysp.setContent(purchasehistorygp);
		purchasehistorybp.setCenter(purchasehistorysp);
		purchasehistorygp.add(purchasehistorytitle, 0, 0);
		purchasehistorygp.add(listviewpurchasehistory, 0, 1);
		vbpurchasehistory1.getChildren().addAll(nohistorylbl, considerlbl);
		vbpurchasehistory.getChildren().addAll(vbpurchasehistory1);
		purchasehistorygp.add(vbpurchasehistory, 1, 1);
		
		vbpurchasehistory2.getChildren().addAll(transactionidlbl, usernamephlbl, phonenumberphlbl, addressphlbl, totalpricephlbl);
		vbpurchasehistory3.getChildren().add(listviewcartph);
				
		
	}

	public void setposition() {
		// Login Scene
		gp.setPadding(new Insets(20));
		gp.setVgap(10);
		gp.setHgap(5);

		vblogin.getChildren().add(gp);
		vblogin.setAlignment(Pos.CENTER);

		
		hblogin.getChildren().add(gp);
		hblogin.setAlignment(Pos.CENTER);
		hblogin.setPadding(new Insets(185, 0, 185, 0));
		
		
		logintitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		gp.setColumnSpan(logintitle, 2);
		
		// Registration Scene
		registrationgp.setPadding(new Insets(20));
		registrationgp.setVgap(10);
		registrationgp.setHgap(10);
		
		registrationfp.setHgap(15);
		registrationfp1.setHgap(5);
		
		registrationtitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		registrationgp.setColumnSpan(registrationtitle, 2);
		
		// Home Scene
		homegp.setPadding(new Insets(20));
		homegp.setVgap(10);
		homegp.setHgap(10);
		hometitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		gp.setColumnSpan(hometitle, 10);
		
		homegpproducts.setVgap(10);
		homegpproducts.setVgap(10);
		homegpproducts.setHgap(10);
		productdesc.setWrapText(true);
		productdesc.setTextAlignment(TextAlignment.JUSTIFY);
		productdesc.setMaxWidth(500);
		
		listviewproduct.setMaxHeight(300);
		
		hboxhomeproduct.setSpacing(5);
		
		//Cart Scene
		cartgp.setPadding(new Insets(20));
		cartgp.setVgap(10);
		cartgp.setHgap(10);
		carttitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		cartgp.setColumnSpan(carttitle, 10);
		
		vbcart.setSpacing(10);
		vbcart2.setSpacing(10);
		vbcart3.setSpacing(10);
		hbcart.setSpacing(5);
		hbcart1.setSpacing(5);
		
		listviewproductcart.setMaxHeight(300);
			
		homegp1.setVgap(10);
		
		orderinfolbl.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		
		productdesccart.setWrapText(true);
		productdesccart.setTextAlignment(TextAlignment.JUSTIFY);
		productdesccart.setMaxWidth(500);
		
		//Manage Products Scene
		manageproductsgp.setPadding(new Insets(20));
		manageproductsgp.setVgap(10);
		manageproductsgp.setHgap(10);
		manageproductstitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		manageproductsgp.setColumnSpan(manageproductstitle, 10);
		
		manageproductsvb.setSpacing(10);
		manageproductsvb1.setSpacing(10);
		manageproductsvb2.setSpacing(10);
		manageproductshb.setSpacing(10);
		manageproductshb1.setSpacing(10);
		productdescmanagelbl.setWrapText(true);
		productdescmanagelbl.setTextAlignment(TextAlignment.JUSTIFY);
		productdescmanagelbl.setMaxWidth(500);
		
		
		
		//Purchase History Scene
		purchasehistorygp.setPadding(new Insets(20));
		purchasehistorygp.setVgap(10);
		purchasehistorygp.setHgap(10);
		purchasehistorytitle.setFont(Font.font("Arial", FontWeight.BOLD, 40));
		purchasehistorygp.setColumnSpan(purchasehistorytitle, 2);
		
		vbpurchasehistory.setSpacing(10);
		vbpurchasehistory1.setSpacing(10);
		vbpurchasehistory2.setSpacing(10);
		
		listviewpurchasehistory.setMaxHeight(800);
		listviewcartph.setMaxHeight(300);
		

		
	}
	
	public void seteventhandler(Stage stage) {
		loginbtn.setOnAction(e -> {
			if(usernametf.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Name error");
				alert.setContentText("Name field must be filled!");
				alert.showAndWait();

				
			}else if(passwordpf.getText().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Password error");
				alert.setContentText("Password field must be filled!");
				alert.showAndWait();	
					
		}else{
			
			
		}
			
			});
		
		
		
		addtocartbtn.setOnAction(e -> {
			Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
			confirmationAlert.setHeaderText("Add to Cart Successfull");
			confirmationAlert.setContentText("Added to Cart");
			confirmationAlert.showAndWait();
			
			cartdatahandle(e);
			
			productselected = null;
	
		});
			
	}
	

	public void getdata() {
		
		productlistfull.clear();
		productlist.clear();
		
		String query = "SELECT * FROM product";
		
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String productid = connect.rs.getString("productID"); 
				String productname = connect.rs.getString("product_name");
				Integer productprice = connect.rs.getInt("product_price");
				String productdesc = connect.rs.getString("product_des");
				
				productlistfull.add(new Product(productid, productname, productprice, productdesc));
				
				productlist.add(connect.rs.getString("product_name"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void refreshlistview() {
		getdata();
		ObservableList<String> regObs = FXCollections.observableArrayList(productlist);
		listviewproduct.setItems(regObs);
		listviewmanageproduct.setItems(regObs);
				
	}
	
	
	public void setonaction(Stage stage) {
	
		registerbtn.setOnAction(e -> {
			
				if(usernametextfieldreg.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Username must be filled");
					
			}else if(passwordfieldreg.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Password must be filled!");
				}
			else if(confirmpasspf.getText().isEmpty()){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Confirm Password must be filled!");
				}
			else if(phonenumbertextfieldreg.getText().isEmpty()){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Phone number must be filled!");
				}
			else if(addressaf.getText().isEmpty()){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Address must be filled!");
				}
			else if(usernametextfieldreg.getText().length() < 5 || usernametextfieldreg.getText().length() > 20){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Username must be 5-20 characters!");
				}
			else if(passwordfieldreg.getText().length() < 5){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("password must be at least 5 characters!");
				}
			else if(!confirmpasspf.getText().equals(passwordfieldreg.getText())){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Confirm password must be the same password!");
				}
			else if(!phonenumbertextfieldreg.getText().startsWith("+62")){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("Failed to Register");
					alert.setContentText("Phone number must start with +62");
				}else {
				stage.setScene(scene);
				stage.setTitle("Login");
				userdatahandle(e);
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText("Register Success");
				alert.setContentText("Register Successfully!");
				}	
			
		});
		
		
		homepagemenu.setOnAction(e -> {
			stage.setScene(homecustomerscene);
			stage.setTitle("Home");
			homebp.setTop(null);
			homebp.setTop(menuBarcustomer);
		});
		
		mycartmenu.setOnAction(e -> {
			stage.setScene(cartscene);
			stage.setTitle("My Cart");
			homebp.setTop(null);
			cartbp.setTop(menuBarcustomer);
			vbcart.getChildren().clear();
			vbcart.getChildren().addAll(cartwelcomelbl, selectproductcartlbl);
		});
		
		purchasehistorymenu.setOnAction(e -> {
			stage.setScene(purchasehistoryscene);
			stage.setTitle("Purchase History");
			homebp.setTop(null);
			purchasehistorybp.setTop(menuBarcustomer);
		});
		
		logoutmenu.setOnAction(e -> {
			stage.setScene(scene);
			stage.setTitle("Login");
			homebp.setTop(null);

			useridselected = null;
			productselected = null;
			productselectedprice = 0;
			totalpriceproduct = 0;
			
			homebp.setTop(null);
			homebp.setCenter(null);

		});
		
		homepagemenuadmin.setOnAction(e -> {
			stage.setScene(homeadminscene);
			stage.setTitle("Home");
			homebpadmin.setTop(null);
			homebpadmin.setTop(menubaradmin);
			
		});
		
		manageproductsitemmenu.setOnAction(e -> {
			stage.setScene(manageproductsscene);
			stage.setTitle("Manage Products");
			homebpadmin.setTop(null);
			manageproductsbp.setTop(menubaradmin);
			
		});
		
		logoutmenuadmin.setOnAction(e -> {
			stage.setScene(scene);
			stage.setTitle("Login");
			homebpadmin.setTop(null);
			manageproductsbp.setTop(null);
			useridselected = null;
			productselected = null;
			productselectedprice = 0;
			totalpriceproduct = 0;
			homebpadmin.setCenter(null);
		});		
		
		addproductbtn.setOnAction(e -> {
			manageproductsvb2.getChildren().addAll(inputproductnamelbl, productnametf, inputproductpricelbl, productpricetf, inputproductdeslbl, productdesta);
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().addAll(addproductbtn1, backbtn);
			manageproductshb1.getChildren().clear();
			manageproductclickstatus = 2;
		});
		
		backbtn.setOnAction(e -> {
			manageproductsvb2.getChildren().clear();
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().addAll(addproductbtn);
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
			manageproductclickstatus = 1;
		});
		
		addproductbtn1.setOnAction(e -> {
			productdatahandle(e);
		});
		
		updateproductbtn.setOnAction(e -> {
			
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().addAll(manageproductsvb3);
			
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(updateproduct1btn, backupdateproductbtn);
			
			manageproductclickstatus = 4;
			manageproductclickstatus1 = 2;
		
		});
		
		backupdateproductbtn.setOnAction(e -> {
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().add(addproductbtn);
			
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
			
			manageproductclickstatus1 = 1;
		});
		
		removeproductbtn.setOnAction(e -> {
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().add(manageproductsvb4);
			
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(removeproductbtn1, backremoveproductbtn);
			manageproductclickstatus = 4;
			manageproductclickstatus1 = 4;
			
		});
		
		backremoveproductbtn.setOnAction(e -> {
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().add(addproductbtn);
			
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
			manageproductclickstatus1 = 1;
		});
		
		updateproduct1btn.setOnAction(e -> {
			String productprice = updateproducttf.getText();
			
			String query = "UPDATE product SET product_price = '"+productprice+"' WHERE product_name = '"+productselected+"'";
			connect.execUpdate(query);
			refreshlistview();
			
			manageproductsvb1.getChildren().clear();
			manageproductsvb1.getChildren().addAll(welcomemanageproductslbl, selectproductupdatelbl);
			
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().add(addproductbtn);
			
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
			
			manageproductclickstatus1 = 1;
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Update Success");
			alert.setContentText("Product Price has been successfully updated!");
		});
		
		removeproductbtn1.setOnAction(e -> {
			String query = "DELETE FROM product WHERE product_name = '"+productselected+"'";
			connect.execUpdate(query);
			refreshlistview();
			
			manageproductsvb1.getChildren().clear();
			manageproductsvb1.getChildren().addAll(welcomemanageproductslbl, selectproductupdatelbl);
			
			manageproductshb.getChildren().clear();
			manageproductshb.getChildren().add(addproductbtn);
			
			manageproductshb1.getChildren().clear();
			manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
			manageproductclickstatus1 = 1;
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Remove Success");
			alert.setContentText("Product has been successfully removed!");
		});
		
		makepurchasebtn.setOnAction(e -> {
			
			transactionheaderhandle(e);
			gettransactionid();
			transactiondetailhandle(e);
			clearlistviewcart();
			refreshlistviewpurchasehistory();
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Purchase Success!");
			alert.setContentText("Successfully Purchased!");
			
			vbcart.getChildren().clear();
			vbcart.getChildren().addAll(cartwelcomelbl, selectproductcartlbl);
			
			vbpurchasehistory1.getChildren().clear();
			vbpurchasehistory1.getChildren().add(selecttransactionlbl);
			
		});
		
		updatecartbtn.setOnAction(e -> {
			int getquantityupdate = quantityspinnercart.getValue();
			String query = "UPDATE cart SET quantity = quantity + "+getquantityupdate+" WHERE userID = '"+useridselected+"' AND productID = '"+cartselectionidproduct+"'";
			connect.execUpdate(query);
			vbcart.getChildren().clear();
			vbcart.getChildren().addAll(cartwelcomelbl, selectproductcartlbl);
			refreshlistviewcart();
			
		});
		
		removefromcartbtn.setOnAction(e -> {
			String query = "DELETE FROM cart WHERE userID = '"+useridselected+"' AND productID = '"+cartselectionidproduct+"'";
			connect.execUpdate(query);
			vbcart.getChildren().clear(); 
			vbcart.getChildren().addAll(cartwelcomelbl, selectproductcartlbl);
			refreshlistviewcart();
		});
		
		
	}
	


	

	public void setitemevent(Stage stage) {
		

		registerherelbl.setOnMouseClicked(e -> {
			stage.setScene(registrationscene);
			stage.setTitle("Register");	
		});
		
		loginherelbl.setOnMouseClicked(e -> {
			stage.setScene(scene);
			stage.setTitle("Login");
		});
		
		
		listviewproduct.setOnMouseClicked(e -> {
			String nameselected = listviewproduct.getSelectionModel().getSelectedItem();
			vbhome.getChildren().clear();
			vbhome.getChildren().add(homegpproducts);
			
			productselected = nameselected;
			

			String query = "SELECT * FROM product WHERE product_name = '"+nameselected+"'";
			
			connect.rs = connect.execQuery(query);
			
				try {
					while(connect.rs.next()) {
						producttitle.setText(connect.rs.getString("product_name"));
						productdesc.setText(connect.rs.getString("product_des"));
						productprice.setText("Price Rp. " + connect.rs.getString("product_price"));
						totalpricelbl.setText("Total: Rp. " + connect.rs.getInt("product_price"));
						
						
						int onepriceproduct = connect.rs.getInt("product_price");
						
						totalpricelbl.setText("Total: Rp. " + onepriceproduct * currentvalueproduct);
						
						quantityspinner.valueProperty().addListener(new ChangeListener<Integer>() {

							@Override
							public void changed(ObservableValue<? extends Integer> observable, Integer oldValue,
									Integer newValue) {
								
								currentvalueproduct = quantityspinner.getValue();
								
								productselectedprice = onepriceproduct;

								totalpricelbl.setText("Total: Rp. " + onepriceproduct*currentvalueproduct);
								
								totalpriceproduct = currentvalueproduct*onepriceproduct;
							}
							
						});
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		});
			
		loginbtn.setOnMouseClicked(e -> {
			String usernamelogin = usernametf.getText();
			String passwordlogin = passwordpf.getText();
			
			String query = "SELECT * FROM user WHERE username = '"+usernamelogin+"'";
			connect.rs = connect.execQuery(query);
			
			try {
				while(connect.rs.next()) {
					
					if(passwordlogin.equals(connect.rs.getString("password"))) {
						
						if(connect.rs.getString("role").equals("Admin")) {
							stage.setScene(homeadminscene);
							stage.setTitle("Home");
														
							homesp.setContent(homegp);
							homebpadmin.setCenter(homesp);
							homebpadmin.setTop(menubaradmin);
							
							homegp.getChildren().clear();
							homegp1.getChildren().clear();
							vbhome.getChildren().clear();
							homegpproducts.getChildren().clear();
									
							homegp.add(hometitle, 0, 0);
							homegp.add(listviewproduct, 0, 1);
							homegp.add(vbhome, 1, 1);

							homegp1.add(welcomelbl, 0, 0);
							homegp1.add(selectproductviewlbl, 0, 1);
							vbhome.getChildren().addAll(homegp1);
							vbhome.setAlignment(Pos.TOP_LEFT);
							
							homegpproducts.add(producttitle, 0, 0);
							homegpproducts.add(productdesc, 0, 1);
							homegpproducts.add(productprice, 0, 2);
							
						}else{
						
						Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
						confirmationAlert.setHeaderText("Login Success!");
						confirmationAlert.setContentText("Login Successful");
						confirmationAlert.showAndWait();
						
						stage.setScene(homecustomerscene);
						stage.setTitle("Home");
						
						homesp.setContent(homegp);
						homebp.setCenter(homesp);
						homebp.setTop(menuBarcustomer);
						
						hboxhomeproduct.getChildren().clear();
						homegp.getChildren().clear();
						homegp1.getChildren().clear();
						vbhome.getChildren().clear();
						homegpproducts.getChildren().clear();
						
						homegp.add(hometitle, 0, 0);
						homegp.add(listviewproduct, 0, 1);
						homegp.add(vbhome, 1, 1);

						homegp1.add(welcomelbl, 0, 0);
						homegp1.add(selectproductviewlbl, 0, 1);
						vbhome.getChildren().addAll(homegp1);
						vbhome.setAlignment(Pos.TOP_LEFT);
						
						homegpproducts.add(producttitle, 0, 0);
						homegpproducts.add(productdesc, 0, 1);
						homegpproducts.add(productprice, 0, 2);
						hboxhomeproduct.getChildren().addAll(productquantitylbl, quantityspinner, totalpricelbl);
						homegpproducts.add(hboxhomeproduct, 0, 3);
						homegpproducts.add(addtocartbtn, 0, 4);

						}
						
						
						welcomelbl.setText("Welcome, " + usernamelogin);
						
						useridselected = connect.rs.getString("userID");
						usernameselected = connect.rs.getString("username");
						
						carttitle.setText(""+usernamelogin+"'s Cart");
						cartwelcomelbl.setText("Welcome, "+usernamelogin+"");
						
						purchasehistorytitle.setText(""+usernamelogin+"'s Purchase History");
						
						welcomemanageproductslbl.setText("Welcome, "+usernamelogin+"");
						
						usernamecartlbl.setText("Username: " + connect.rs.getString("username"));
						phonenumbercartlbl.setText("Phone Number: " + connect.rs.getString("phone_num"));
						addresscartlbl.setText("Address: " + connect.rs.getString("address"));
						
						usernamephlbl.setText("Username: " + connect.rs.getString("username"));
						phonenumberphlbl.setText("Phone Number: " + connect.rs.getString("phone_num"));
						addressphlbl.setText("Address: " + connect.rs.getString("address"));
						totalpricephlbl.setText("Total Price: ");
						
						refreshlistview();
						refreshlistviewcart();
						refreshlistviewpurchasehistory();
						refreshusertable();
				
					}else if(!passwordlogin.equals(connect.rs.getString("password"))) {
						Alert erroralert = new Alert(AlertType.ERROR);
						erroralert.setHeaderText("Login Error");
						erroralert.setContentText("Username or Password incorrect!");
					}
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		});
			
		
			listviewmanageproduct.setOnMouseClicked(e -> {
				
				String nameselected = listviewmanageproduct.getSelectionModel().getSelectedItem();
				manageproductsvb1.getChildren().clear();
				manageproductsvb1.getChildren().addAll(producttitlemanagelbl, productdescmanagelbl, productpricemanagelbl);
				
				productselected = nameselected;
				
				String query = "SELECT * FROM product WHERE product_name = '"+nameselected+"'";
				
				connect.rs = connect.execQuery(query);
				
					try {
						while(connect.rs.next()) {
							producttitlemanagelbl.setText(connect.rs.getString("product_name"));
							productdescmanagelbl.setText(connect.rs.getString("product_des"));
							productpricemanagelbl.setText("Price Rp. " + connect.rs.getString("product_price"));
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					manageproductshb1.getChildren().clear();
					manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
					
					
					if(manageproductclickstatus == 1) {
						manageproductclickstatus1 = 1;
						manageproductshb1.getChildren().clear();
						manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
						
					}else if(manageproductclickstatus == 2) {
						manageproductshb1.getChildren().clear();
						manageproductclickstatus1 = 3;
						
					}
					
					if(manageproductclickstatus1 == 1) {
						manageproductshb1.getChildren().clear();
						manageproductshb1.getChildren().addAll(updateproductbtn, removeproductbtn);
						
					}else if(manageproductclickstatus1 == 2) {
						manageproductshb1.getChildren().clear();
						manageproductshb1.getChildren().addAll(updateproduct1btn, backupdateproductbtn);
						
						
					} else if(manageproductclickstatus1 == 3) {
						manageproductshb1.getChildren().clear();
						
					} else if(manageproductclickstatus1 == 4) {
						manageproductshb1.getChildren().clear();
						manageproductshb1.getChildren().addAll(removeproductbtn1, backremoveproductbtn);
					}
							
			});
			
			listviewproductcart.setOnMouseClicked(e -> {
				
				Cart cartselection = listviewproductcart.getSelectionModel().getSelectedItem();
				
				cartselectionname = cartselection;
				
				vbhome.getChildren().clear();
				vbhome.getChildren().add(homegpproducts);
				
				vbcart.getChildren().clear();
				vbcart.getChildren().add(vbcart3);
				
				String query = "SELECT * FROM cart JOIN product ON cart.productID = product.productID WHERE product_name = '"+cartselection+"' AND userID = '"+useridselected+"'";
				
				connect.rs = connect.execQuery(query);
				
				try {
					while(connect.rs.next()){
						
						producttitlecart.setText(connect.rs.getString("product_name"));
						productdesccart.setText(connect.rs.getString("product_des"));
						
						productpricecart.setText("Price: Rp. " + connect.rs.getString("product_price"));
						productquantitycart.setText("Quantity: ");
						quantityspinnercart.getValueFactory();
						
						cartselectionidproduct = connect.rs.getString("productID");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			});
			
			listviewpurchasehistory.setOnMouseClicked(e -> {
								
				TransactionHeader transactionselection = listviewpurchasehistory.getSelectionModel().getSelectedItem();
				
				
				vbpurchasehistory.getChildren().clear();
				vbpurchasehistory.getChildren().addAll(vbpurchasehistory2, vbpurchasehistory3);
				
				transactionidselected = transactionselection;
				
				refreshlistviewcartph();
				
				String query = "SELECT * FROM transaction_detail td JOIN transaction_header th ON td.transactionID = th.transactionID WHERE td.transactionID = '"+transactionselection+"' AND th.userID = '"+useridselected+"'";
				connect.rs = connect.execQuery(query);
				
				try {
					while(connect.rs.next()) {
						
						transactionidlbl.setText("Transaction ID: "+ connect.rs.getString("transactionID"));
														
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
								
			});	
		}
	
	
	public void getuserdata() {
		userlist.clear();
		
		String query = "SELECT * FROM user";
		
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String userid = connect.rs.getString("userID");
				String username = connect.rs.getString("username");
				String password = connect.rs.getString("password");
				String phonenumber = connect.rs.getString("phone_num");
				String address = connect.rs.getString("address");
				String gender = connect.rs.getString("gender");
				
				userlist.add(new User(userid, username, password, address, phonenumber, gender));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void refreshusertable() {
		getuserdata();
		ObservableList<User> regObs = FXCollections.observableArrayList(userlist);
		usertable.setItems(regObs);
	}
	
	public void adduserdata(String userid, String username, String password,  String address, String phonenumber, String gender) {
		String query = "INSERT INTO user VALUES ('"+userid+"', '"+username+"', '"+password+"', 'User', '"+address+"', '"+phonenumber+"', '"+gender+"')";
		connect.execUpdate(query);
	}
	
	public void userdatahandle(ActionEvent e) {
		if(e.getSource() == registerbtn) {
			
			iduserautoincrement();
			
			String userid = useridincrement;
			String username = usernametextfieldreg.getText();
			String password = passwordfieldreg.getText();
			String address = addressaf.getText();
			String phonenumber = phonenumbertextfieldreg.getText();
			String gender = null;	
			if(gendertg.getSelectedToggle().equals(malerb)) {
					gender = "Male";
				}
			else if(gendertg.getSelectedToggle().equals(femalerb)) {
				gender = "Female";
			}
			adduserdata(userid, username, password, address, phonenumber, gender);
			refreshusertable();
			
		}
			
	}
	
	public void iduserautoincrement() {
		String query = "SELECT userID FROM user ORDER BY userID DESC LIMIT 1";
		connect.rs = connect.execQuery(query);
		
		ResultSet rs = connect.rs;
		
		try {
			while(rs.next()) {
				String userid = rs.getString("userID");
				int useridlength = userid.length();
				String idletter = userid.substring(0,2);
				String idlatestno = userid.substring(2, useridlength);
				int i = Integer.parseInt(idlatestno);
				i++;
				
				String idno = Integer.toString(i);
				String idincremented= idletter + "00" + idno;
				
				useridincrement = idincremented; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cartdatahandle(ActionEvent e) {
		String nameselected = productselected;
		
		String query = "SELECT * FROM product WHERE product_name = '"+nameselected+"'";
		
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				
				if(e.getSource() == addtocartbtn) {

					String productid = connect.rs.getString("productID");
					String userid = useridselected;
					int quantity = currentvalueproduct;
					
					addcartdata(productid, userid, quantity);
					refreshlistviewcart();
			}
				
				
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		}
	
	
	public void addcartdata(String productid, String userid, int quantity) {
		
		String query = "INSERT INTO cart VALUES ('"+productid+"', '"+userid+"', '"+quantity+"')";
		connect.execUpdate(query);
	}
	

	public void getcartdata() {
		
		productlistcart.clear();
		productlistcartname.clear();
				
		String query = "SELECT * FROM cart JOIN product ON cart.productID = product.productID  WHERE userID = '"+useridselected+"' ";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String productid = connect.rs.getString("productID");
				String userid = connect.rs.getString("userID");
				int quantity = connect.rs.getInt("quantity");
				String productname = connect.rs.getString("product_name");
				int totalpriceproductcart = connect.rs.getInt("product_price") * quantity;
				
	
				String cartdisplay = ""+quantity+"x "+productname+" (Rp."+totalpriceproductcart+")";

				
				productlistcart.add(new Cart(productid, productname, userid, quantity, totalpriceproductcart));
				productlistcartname.add(cartdisplay);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	

	public void refreshlistviewcart() {
		getcartdata();
		ObservableList<Cart> regObs = FXCollections.observableArrayList(productlistcart);
		listviewproductcart.setItems(regObs);
		
	}
	
	public void clearlistviewcart() {
		String query = "DELETE FROM cart WHERE userID = '"+useridselected+"'";
		connect.execUpdate(query);
		refreshlistviewcart();
		
	}
	
	
	public void productdatahandle(ActionEvent e) {
		if(e.getSource() == addproductbtn1) {
			
			productidautoincrement();
			
			String productid = productidincrement;
			String productname = productnametf.getText();
			String productprice = productpricetf.getText();
			String productdesc = productdesta.getText();
			
			
			addproductdata(productid, productname, productprice, productdesc);
			refreshlistview();
					
		}
	}
	
	public void productidautoincrement() {
		String query = "SELECT productID FROM product ORDER BY productID DESC LIMIT 1";
		connect.rs = connect.execQuery(query);
		
		ResultSet rs = connect.rs;
		
		try {
			while(rs.next()) {
				String userid = rs.getString("productID");
				int useridlength = userid.length();
				String idletter = userid.substring(0,2);
				String idlatestno = userid.substring(2, useridlength);
				int i = Integer.parseInt(idlatestno);
				i++;
				
				String idno = Integer.toString(i);
				String idincremented= idletter + "00" + idno;
				
				productidincrement = idincremented; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addproductdata(String productid, String productname, String productprice, String productdesc){
		String query = "INSERT INTO product VALUES('"+productid+"', '"+productname+"', '"+productprice+"', '"+productdesc+"')";
		connect.execUpdate(query);
	}
	
	public void transactionheaderhandle(ActionEvent e){
		if (e.getSource() == makepurchasebtn) {
			
			transactionidautoincrement();
			
			String transactionid = transactionidincrement;
			String userid = useridselected;
			
			addtransactionheaderdata(transactionid, userid);			
		}
	}
	
	String transactionidadded;
	
	public void addtransactionheaderdata(String transactionid, String userid) {
		String query = "INSERT INTO transaction_header VALUES('"+transactionid+"', '"+userid+"')";
		connect.execUpdate(query);
		
	}
	
	public void transactionidautoincrement() {
		String query = "SELECT transactionID FROM transaction_header ORDER BY transactionID DESC LIMIT 1";
		connect.rs = connect.execQuery(query);
		
		ResultSet rs = connect.rs;
		
		try {
			while(rs.next()) {
				String userid = rs.getString("transactionID");
				int useridlength = userid.length();
				String idletter = userid.substring(0,2);
				String idlatestno = userid.substring(2, useridlength);
				int i = Integer.parseInt(idlatestno);
				i++;
				
				String idno = Integer.toString(i);
				String idincremented= idletter + "00" + idno;
				
				transactionidincrement = idincremented; 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void gettransactionid() {
		String query = "SELECT * FROM transaction_header WHERE userID = '"+useridselected+"'";
		connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				
				transactionidadded = connect.rs.getString("transactionID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void transactiondetailhandle(ActionEvent e) {
		if(e.getSource() == makepurchasebtn) {
			String query = "INSERT INTO transaction_detail (transactionID, productID, quantity)"
					+ " SELECT '"+transactionidadded+"', productID, quantity FROM cart"
					+ " WHERE userID = '"+useridselected+"'";
			
			connect.execUpdate(query);
			
		}
	}
	
	
	
	public void getpurchasehistorydata() {
		transactionlist.clear();
		String query = "SELECT * FROM transaction_header WHERE userID = '"+useridselected+"' ";

		connect.rs = connect.execQuery(query);

		try {
		    while (connect.rs.next()) {
		        String transactionid = connect.rs.getString("transactionID");
		        String userid = connect.rs.getString("userID");
		        
		        
		        transactionlist.add(new TransactionHeader(transactionid, userid));
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		
	}
	

	
	public void refreshlistviewpurchasehistory() {
		getpurchasehistorydata();
		ObservableList<TransactionHeader> regObs = FXCollections.observableArrayList(transactionlist);
		listviewpurchasehistory.setItems(regObs);
		
	}
	
	
	public void getcartdataph() {
		productlistcartph.clear();
		
		String query = "SELECT * FROM transaction_detail JOIN product ON transaction_detail.productID = product.productID  WHERE transactionID = '"+transactionidselected+"' ";
		connect.rs = connect.execQuery(query);
		
		try {
			while(connect.rs.next()) {
				String productid = connect.rs.getString("productID");
				String userid = connect.rs.getString("transactionID");
				int quantity = connect.rs.getInt("quantity");
				String productname = connect.rs.getString("product_name");
				int totalpriceproductcart = connect.rs.getInt("product_price") * quantity;
				
				String cartphdisplay = ""+quantity+"x "+productname+" (Rp."+totalpriceproductcart+")";
				productlistcartph.add(cartphdisplay);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	public void refreshlistviewcartph() {
		getcartdataph();
		ObservableList<String> regObs = FXCollections.observableArrayList(productlistcartph);
		listviewcartph.setItems(regObs);
		
	}
		

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setelements();
		setposition();
			
		setitemevent(primaryStage);
		

		setonaction(primaryStage);
		seteventhandler(primaryStage);

		scene = new Scene(root, 800, 600);
		registrationscene = new Scene(registrationbp, 800, 600);
		homecustomerscene = new Scene(homebp, 800, 600);
		homeadminscene = new Scene(homebpadmin, 800, 600);
		cartscene = new Scene(cartbp, 800, 600);
		purchasehistoryscene = new Scene(purchasehistorybp, 800, 600);
		manageproductsscene = new Scene(manageproductsbp, 800, 600);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
