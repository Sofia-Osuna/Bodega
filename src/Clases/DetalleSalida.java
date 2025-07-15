/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


//Al final si se va a necesitar una clase para guardar los detalles de las salidas, que es nomás los productos y la cantidad de cada uno
/**
 *
 * @author sofiaosuna
 */
public class DetalleSalida {
    int id_detalle_salida, id_salida, id_producto, cantidad;
    String estatus;
    int stockDisponible; //agregue una variable extra para ver lo de las validaciones xdxd
    //este constructo no tiene estatus, para editar supongo
    public DetalleSalida(int id_detalle_salida, int id_salida, int id_producto, int cantidad) {
        this.id_detalle_salida = id_detalle_salida;
        this.id_salida = id_salida;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }
    //este constructor si tiene el estatus..... Por si acaso

    public DetalleSalida(int id_detalle_salida, int id_salida, int id_producto, int cantidad, String estatus) {
        this.id_detalle_salida = id_detalle_salida;
        this.id_salida = id_salida;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.estatus = estatus;
    }
    
    //Ahora un constructor sin el id_detalle_salida para registrar nuevos registros xdxdxdxd

    public DetalleSalida(int id_salida, int id_producto, int cantidad) {
        this.id_salida = id_salida;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public int getId_detalle_salida() {
        return id_detalle_salida;
    }

    public void setId_detalle_salida(int id_detalle_salida) {
        this.id_detalle_salida = id_detalle_salida;
    }

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    //intento de validacion para ver si hay suficiente producto
    public boolean validarStock() {
        return this.cantidad <= this.stockDisponible;
    }

    //guardar el detalle??
      public boolean guardar (){
        try{
            Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;

           
            String sql = "INSERT INTO detalle_salida (id_salida, id_producto, cantidad, estatus) VALUES (?,?,?,'A')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_salida);
            ps.setInt(2, id_producto);
            ps.setInt(3, cantidad);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar"+e.getMessage());
            return false;
        }
    }
    
}
