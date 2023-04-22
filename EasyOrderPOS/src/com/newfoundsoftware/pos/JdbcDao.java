/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.newfoundsoftware.pos;

/**
 *
 * @author Flori Vula
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class JdbcDao {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/posjavafx?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?"; //query per mi kqyr a ekziston useri ne database (selekton te gjithe userat ne databazse)
    
    public boolean validate(String username, String password){
        //hapi 1: connection
        try{
            
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            //hapi 2: krijimi i nje statement me perdorimin e objektit per konektim
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            
            System.out.println(preparedStatement);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            
        }catch(SQLException e){
            //per printim te sql exception
            printSQLException(e);
        }
        return false;
    }
    
    //klase per te bere lidhjen e databazes
    public Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            return connection;
        }catch(SQLException ex){
            printSQLException(ex);
        }
        return null;
    }
    
    public static void printSQLException(SQLException ex){
        //per mi pas debugging ma t'leht gjate vazhdimit te projektit
        for(Throwable e: ex){
            if(ex instanceof SQLException){
                e.printStackTrace(System.err);
                System.out.println("SQLState: " + ((SQLException)e).getSQLState());
                System.out.println("Error Code: " + ((SQLException)e).getErrorCode());
                System.out.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.out.println("Cause: " + t);
                    e = t.getCause();
                }
            }
        }
    }
}
