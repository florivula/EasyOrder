package com.newfoundsoftware.pos;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Flori Vula
 */
public class Login extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            Parent parentRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
            //e bojim setup primary stage:
            primaryStage.setTitle("Login");
            primaryStage.setResizable(false); //nuk lejon ndrrimin e madhesise
            primaryStage.setIconified(false);
            primaryStage.setScene(new Scene(parentRoot));
            primaryStage.show();
        }catch(Exception ex){
            //log per me tregu nese dishka shkon gabim
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(0); //nese ka error mbyllet aplikacioni
        }
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
}
