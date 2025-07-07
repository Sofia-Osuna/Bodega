/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author sofiaosuna
 */
public class Categoria {
       int id_categoria;
        String nombre_categoria, estatus;

    public Categoria(int id, String nombre, String estatus) {
        this.id_categoria = id;
        this.nombre_categoria = nombre;
        this.estatus = estatus;
    }

    public int getId() {
        return id_categoria;
    }

    public String getNombre() {
        return nombre_categoria;
    }

    public String getEstatus() {
        return estatus;
    }

    @Override
    public String toString() {
        return nombre_categoria.toString();  
    }

    public Categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public Categoria(int id_categoria, String nombre_categoria) {
        this.id_categoria = id_categoria;
        this.nombre_categoria = nombre_categoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }
    
    
     public boolean guardar (){
        try{
            Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;

           
            String sql = "INSERT INTO categoria (nombre_categoria,estatus ) VALUES (?,'A')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1, nombre_categoria);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar"+e.getMessage());
            return false;
        }
    }  
 
}
    
    
    

