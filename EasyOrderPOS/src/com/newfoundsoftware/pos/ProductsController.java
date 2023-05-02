/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newfoundsoftware.pos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Flori Vula
 */
public class ProductsController implements Initializable {

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Products> tableProducts;
    @FXML
    private TableColumn<Products, Integer> colD;
    private TableColumn<Products, String> colName;
    @FXML
    private ImageView ivProduct;
    @FXML
    private TextField etDescription;
    @FXML
    private TextField etPrice;
    @FXML
    private ComboBox<String> cbCategories;
    @FXML
    private Button btnSave1;
    @FXML
    private ComboBox<String> cbWeight;
    @FXML
    private ComboBox<String> cbStatus;
    @FXML
    private Button btnSave;
    
    JdbcDao jdbc;
    @FXML
    private TableColumn<Products, String> colDescription;
    @FXML
    private TableColumn<Products, String> colPrice;
    @FXML
    private TableColumn<Products, String> colCategory;
    @FXML
    private TableColumn<Products, String> colStatus;
    
    Scene fxmlFile;
    Parent root;
    Stage window;
    
    
    File file;
    
    @FXML
    private Button btnBrowse;
    @FXML
    private TextField etId; //eshte nderruar nga edId ne etId
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jdbc = new JdbcDao();
        showProducts();
        populateCategories();
        
        cbStatus.getItems().add("AVAILABLE");
        cbStatus.getItems().add("UN-AVAILABLE");
        
        cbWeight.getItems().add("YES");
        cbWeight.getItems().add("NO");
    }    

    @FXML
    private void editEntry(ActionEvent event) {
    }

    @FXML
    private void deleteEntry(ActionEvent event) {
    }

    @FXML
    private void saveTable(ActionEvent event) {
    }
    
    public void showProducts(){
        ObservableList<Products> list = getProductList();
        //gjenerimi i value te tabeles/tavolines
        colD.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<Products, String>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Products, String>("price"));
        colCategory.setCellValueFactory(new PropertyValueFactory<Products, String>("category"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Products, String>("status"));
        tableProducts.setItems(list);
    }

    private void insertRecord(){
        /*String name = tfTableName.getText();
        if(!name.isEmpty()){
            String query = "INSERT INTO `tbltables` (name) VALUES('" + name + "')";
            executeQuery(query);
            showProducts();
            
        }
        */
    }
    
    private ObservableList<Products> getProductList() {
        ObservableList<Products> productList = FXCollections.observableArrayList();
        //lidhja e databazes per tavolinat
        Connection conn = jdbc.getConnection();
        //selektimi i tabeles
        String query = "SELECT * FROM products";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Products products;
            while(rs.next()){
                products = new Products(rs.getInt("id"), rs.getString("description"), rs.getString("price"), rs.getString("category"), rs.getBlob("image"), rs.getString("status"));
                productList.add(products);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return productList;
    }

    private void executeQuery(String query){
        Connection conn = jdbc.getConnection();
        Statement st;
        System.out.println(query);
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            System.out.println("error while inserting record.");
            ex.printStackTrace();
        }
    }
    
    private void openModalWindow(String resource, String title) throws IOException{
        root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        //window.initStyle(StageStyle.UNDECORATED);
        window.setTitle(title);
        window.showAndWait();
    }

    @FXML
    private void actionAddCategory(ActionEvent event) {
        try{
            openModalWindow("Category.fxml", "Manage Categories");
        }catch(Exception ex){
            
        }
    }
    //mbush combo-box e kategorive me kategori
    private void populateCategories() {
        Connection conn = jdbc.getConnection();
        Statement st;
        //System.out.println(query);
        ObservableList<String> list = FXCollections.observableArrayList();
        try{
            //st = conn.createStatement();
            //st.executeUpdate(query);
            
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM categories");
            while(rs.next()){
                list.add(rs.getString("name"));
                //cbCategories.add(rs.getString("name"));
            }
        }catch(Exception ex){
            System.out.println("error while inserting record.");
            ex.printStackTrace();
        }
        
        cbCategories.setItems(null); //e bojim empty combo-boxin para se mi shtu listen
        cbCategories.setItems(list);
    }

    @FXML
    private void handleBrowseImage(ActionEvent event){
        try{
            FileChooser fc = new FileChooser();
            //filters
            FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)","*.JPG");
            FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)","*.PNG");
            
            fc.getExtensionFilters().addAll(ext1, ext2);
            
            file = fc.showOpenDialog(DashboardController.getPrimaryStage()); //getPrimaryStage() method gjendet ne DashboardController.java
            BufferedImage bf;
            bf = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bf, null);
            ivProduct.setImage(image);
        }catch(Exception ex){
            System.out.println(""+ex.getMessage());
        }
    }
    
}
