/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newfoundsoftware.pos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Alert;
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
import javafx.stage.Window;
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
    
    
    File file; //fotoja e produktit
    
    @FXML
    private Button btnBrowse;
    @FXML
    private TextField etId; //eshte nderruar nga edId ne etId
    @FXML
    private TextField etBarcode;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        jdbc = new JdbcDao();
        
        addListenerForTable();
        
        showProducts();
        populateCategories();
        
        cbStatus.getItems().add("AVAILABLE");
        cbStatus.getItems().add("UN-AVAILABLE");
        
        cbWeight.getItems().add("YES");
        cbWeight.getItems().add("NO");
    }    

    @FXML
    private void editEntry(ActionEvent event) {
        Connection conn = jdbc.getConnection();
        Products product = tableProducts.getSelectionModel().getSelectedItem();
        String query = "UPDATE products SET barcode=?, description=?, price=?, category=?, weight=?, status=? WHERE id=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, etBarcode.getText());
            statement.setString(2, etDescription.getText());
            statement.setString(3, etPrice.getText());
            statement.setString(4, cbCategories.getSelectionModel().getSelectedItem());
            statement.setString(5, cbWeight.getSelectionModel().getSelectedItem());
            statement.setString(6, cbStatus.getSelectionModel().getSelectedItem());
            statement.setInt(7, product.getId());
            statement.executeUpdate();
            Window owner = (Stage) etDescription.getScene().getWindow();
            showAlert(Alert.AlertType.INFORMATION, owner, "Product updated successfully!", "Product Updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void deleteEntry(ActionEvent event) {
        Connection conn = jdbc.getConnection();
        try{
            Products product = tableProducts.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM products WHERE id = '" + product.getId() + "'"; //query per delete
            executeQuery(query);
            showProducts();
            Window owner = (Stage) etDescription.getScene().getWindow();
            showAlert(Alert.AlertType.INFORMATION, owner, "Product deleted successfully!", "Product Deleted");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
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
                products = new Products(rs.getInt("id"), rs.getString("barcode"),rs.getString("description"), rs.getString("price"), rs.getString("category"), rs.getBlob("image"), rs.getString("weight"),rs.getString("status"));
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
    
    private void addListenerForTable(){
        tableProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null){
                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                
                //tfCategoryName.setText(newSelection.getName()); //newSelection kthen tables object qe i kemi kriju en tables.java
                etId.setText("" + newSelection.getId());
                etBarcode.setText(newSelection.getBarcode());
                etDescription.setText(newSelection.getDescription());
                etPrice.setText(newSelection.getPrice());
                
                //per combo-box
                cbCategories.getSelectionModel().select(newSelection.getCategory());
                cbWeight.getSelectionModel().select(newSelection.getWeight());
                cbStatus.getSelectionModel().select(newSelection.getStatus());
                //cbWeight.getSelectionModel().select(newSelection.getWeight());
            }else{
                etBarcode.setText("");
                etDescription.setText("");
                etPrice.setText("");
                cbCategories.getSelectionModel().selectFirst(); //ressetimi i combo-box
                cbStatus.getSelectionModel().selectFirst();
                
                btnSave.setDisable(false);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        });
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
    
    @FXML
    private void saveProduct(ActionEvent event) {
        Connection conn = jdbc.getConnection();
        try{
            String barcode = etBarcode.getText();
            String description = etDescription.getText();
            String price = etPrice.getText();
            String category = cbCategories.getSelectionModel().getSelectedItem(); //ruajme vleren e zgjedhur nga combobox ne nje String "category"
            String isweight = cbWeight.getSelectionModel().getSelectedItem();
            String status = cbStatus.getSelectionModel().getSelectedItem();
            Window owner = (Stage) etDescription.getScene().getWindow();
            // kontrolli se a i jane dhene te gjitha vlerat e duhura per ruajtjen e nje produkti
            if(barcode.isEmpty() || description.isEmpty() || price.isEmpty() || category.isEmpty() || isweight.isEmpty() || status.isEmpty()){
                showAlert(Alert.AlertType.ERROR, owner, "Please fill the form correctly!", "Form Error");
            }else{
                //kontrolli nese eshte zgjedhur image
                if(file == null){
                    showAlert(Alert.AlertType.ERROR, owner, "Please select image!", "Form Error!");
                }else{
                    FileInputStream fin = new FileInputStream(file);
                    int len = (int) file.length();
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO products(barcode, description, price, category, weight, image, status) VALUES(?,?,?,?,?,?,?)");
                    ps.setString(1, barcode);
                    ps.setString(2, description);
                    ps.setString(3, price);
                    ps.setString(4, category);
                    ps.setString(5, isweight);
                    ps.setBinaryStream(6, fin, len); //image
                    ps.setString(7, status);
                    
                    //results
                    int res = ps.executeUpdate();
                    if(res > 0){
                        showAlert(Alert.AlertType.INFORMATION, owner, "Products saved successfully", "Success!");
                        
                        //fshierja e te dhenave te shkruara ne fusha
                        etBarcode.clear();
                        etDescription.clear();
                        etPrice.clear();
                        
                        cbCategories.valueProperty().set(null);
                        cbWeight.valueProperty().set(null);
                        cbStatus.valueProperty().set(null);
                        
                        ivProduct.setImage(null);
                        file = null;
                        
                        showProducts();
                    }else{
                        showAlert(Alert.AlertType.ERROR, owner, "There were errors while processing", "Form Error!");
                    }
                }
            }
            
        }catch(Exception ex){
            System.out.println("" + ex.getMessage());
        }
    }
    
    public static void showAlert(Alert.AlertType alertType, Window owner, String message, String title){
        //kodi per gjenerimin e alert window sa here qe njera prej fushave nuk mbushet
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(owner);
        alert.showAndWait();
    }
    
}
