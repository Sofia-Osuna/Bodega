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
public class Proveedor{
       int id_proveedor;
        String nombre_proveedor,telefono,estatus;
        
public Proveedor(int id, String nombre,String telefono, String estatus){
    this.id_proveedor= id;
    this.nombre_proveedor= nombre;
    this.telefono= telefono;
    this.estatus= estatus;
}

    public int getId_proveedor() {
        return id_proveedor;
    }

    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEstatus() {
        return estatus;
    }

    @Override
    public String toString() {
        return nombre_proveedor.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }
    
    public String getNombre__proveedor(){
        return nombre_proveedor;
    }
    
    public Proveedor(int id_proveedor, String nombre_proveedor){
        this.id_proveedor = id_proveedor;
        this.nombre_proveedor = nombre_proveedor;
    }

    public Proveedor(String nombre_proveedor, String telefono) {
        this.nombre_proveedor = nombre_proveedor;
        this.telefono = telefono;
    }

    public Proveedor(String nombre_proveedor, String telefono, String estatus) {
        this.nombre_proveedor = nombre_proveedor;
        this.telefono = telefono;
        this.estatus = estatus;
    }
    
    
    
    
    public boolean guardar (){
        try{
            Conexion conexion = new Conexion();
            Connection conn = conexion.conn;
            
            String sql ="INSERT INTO proveedor (nombre_proveedor,telefono, estatus)VALUES(?,?,'A')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre_proveedor);
            ps.setString(2,telefono);
            ps.executeUpdate();
            return true;
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al guardar"+e.getMessage());
            return false;
        }
        }
    public boolean actualizar (){
        try{
            Conexion conexion = new Conexion();
            Connection conn = conexion.conn;
            
            String sql ="UPDATE proveedor SET nombre_proveedor=?, telefono=? WHERE id_proveedor=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre_proveedor);
             ps.setString(2,telefono);
            ps.setInt(3,id_proveedor);
            ps.executeUpdate();
            return true;
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error al guardar"+e.getMessage());
            return false;
        }
        }
    }