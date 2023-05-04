/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.newfoundsoftware.pos;
import java.sql.Blob;

/**
 *
 * @author Flori Vula
 */
public class Products {
    private int id;
    private String barcode;
    private String description;
    private String price;
    private String category;
    private Blob image;
    private String weight;
    private String status;

    public Products(int id, String barcode,String description, String price, String category, Blob image, String weight,String status) {
        this.id = id;
        this.barcode = barcode;
        this.description = description;
        this.price = price;
        this.category = category;
        this.image = image;
        this.weight = weight;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    
    public String getBarcode(){
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Blob getImage() {
        return image;
    }
    
    public String getWeight(){
        return weight;
    }

    public String getStatus() {
        return status;
    }
    
    
}
