/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.newfoundsoftware.pos;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Flori Vula
 */
public class DashboardController implements Initializable {

    @FXML
    private Label lblUsername;
    @FXML
    private Button btnManageTable;

    Scene fxmlFile;
    Parent root;
    Stage window;

    private static Stage pStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setUsername(String username) {
        lblUsername.setText(username);
    }

    private void setPrimaryStage(Stage pStage) {
        DashboardController.pStage = pStage;
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    @FXML
    private void manageTable(ActionEvent event) {
        try {
            openModalWindow("Tables.fxml", "Manage Tables");
        } catch (Exception ex) {

        }
    }

    private void openModalWindow(String resource, String title) throws IOException {
        root = FXMLLoader.load(getClass().getResource(resource));
        fxmlFile = new Scene(root);
        window = new Stage();
        window.setScene(fxmlFile);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setAlwaysOnTop(true);
        window.setIconified(false);
        //window.initStyle(StageStyle.UNDECORATED);
        window.setTitle(title);
        setPrimaryStage(window);
        window.showAndWait();
    }

    @FXML
    private void actionManageProduct(ActionEvent event) {
        try {
            openModalWindow("Products.fxml", "Manage Products");
        } catch (Exception ex) {

        }
    }

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        System.out.println("Key pressed: " + event.getCode());
        switch (event.getCode()) {
            case F1:
                System.out.println("New Order");
                break;
            case F2:
                System.out.println("Payment");
                break;
            case F3:
                System.out.println("Cancel Order");
                break;
            case F4:
                System.out.println("Manage Products");
                try {
                    openModalWindow("Products.fxml", "Manage Products");
                } catch (Exception ex) {
                }
                break;
            case F5: 
                System.out.println("Manage Table");
                try {
                    openModalWindow("Tables.fxml", "Manage Tables");
                } catch (Exception ex) {
                }
                break;
            case F6:
                System.out.println("Sales Report");
                break;
            case F7:
                System.out.println("Logout");
                break;
        }
    }
}
