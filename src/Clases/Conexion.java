/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.*;

/**
 *
 * @author sofiaosuna
 */
public class Conexion {
    public Connection conn;
    
    public Conexion(){
        
    try{
        
        Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bodega", "root", "");
        System.out.println("Conectado");
        
    }catch (Exception e) {
    System.out.println("Error al conectar");
    }
    }
}


