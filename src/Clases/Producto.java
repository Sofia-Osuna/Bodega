/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Clases.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author sofiaosuna
 */
public class Producto {
    int id_producto, stock, precio, id_categoria;
    String nombre_producto;

    //Constructor sin ID
    public Producto(int stock, int precio, int id_categoria, String nombre_producto) {
        this.stock = stock;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
    }
    
    //Constructor con el Id del producto

    public Producto(int id_producto, int stock, int precio, int id_categoria, String nombre_producto) {
        this.id_producto = id_producto;
        this.stock = stock;
        this.precio = precio;
        this.id_categoria = id_categoria;
        this.nombre_producto = nombre_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getStock() {
        return stock;
    }

    public int getPrecio() {
        return precio;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
    
    public boolean guardar (){
        try{
            Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;

           
            String sql = "INSERT INTO producto (nombre_producto, stock, precio, id_categoria, estatus ) VALUES (?, ?, ?, ?, 'A')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre_producto);
            ps.setInt(2, stock);
            ps.setInt(3, precio);
             ps.setInt(4, id_categoria);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar"+e.getMessage());
            return false;
        }
    }  
  @Override
public String toString() {
    return nombre_producto + " (Stock: " + stock + ")";
}

  public boolean actualizar (){
        try{
            Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;

           
            String sql = "UPDATE producto SET nombre_producto=?, stock=?, precio=?, id_categoria=? WHERE id_producto=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1, nombre_producto);
            ps.setInt(2, stock);
            ps.setInt(3, precio);
            ps.setInt(4, id_categoria);
            ps.setInt(5, id_producto);

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al actualizar"+e.getMessage());
            return false;
        }
    }  
  /*
   public boolean Buscar (){
        try{
            Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;

           
            String sql = "SELECT p.*, c.nombre_categoria as nombre_categoria FROM producto p INNER JOIN categoria c ON p.id_categoria=c.id_categoria WHERE p.estatus='A'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1, nombre_producto);
            ps.setInt(2, stock);
            ps.setInt(3, precio);
            ps.setInt(4, id_categoria);
            ps.setInt(5, id_producto);

            ps.executeUpdate();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "No se encontro el producto o hubo un problema"+e.getMessage());
            return false;
        }
    }  
  
  */
}
