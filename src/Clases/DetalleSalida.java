/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;

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
    
    //este constructor solo contiene el id de la salida porque lo necesito para que me regrese id para los detalles

    public DetalleSalida(int id_salida) {
        this.id_salida = id_salida;
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
      public boolean guardar() {
        Connection conn = null;
        try {
            Conexion conexion = new Conexion();
            conn = conexion.conn;
            
            // Verificar que tenemos un id_salida válido.....tal vez quito esto
            if (this.id_salida <= 0) {
                JOptionPane.showMessageDialog(null, "Error: ID de salida no válido");
                return false;
            }
            
            // Verificar stock disponible
            if (!verificarStock()) {
                return false;
            }

            // Insertar el detalle de salida - SQL CORREGIDO
            String sql = "INSERT INTO detalle_salida (id_salida, id_producto, cantidad, estatus) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, this.id_salida);
            ps.setInt(2, this.id_producto);
            ps.setInt(3, this.cantidad);
            ps.setString(4, "A"); // Estatus activo
            
            int filasAfectadas = ps.executeUpdate();
            ps.close();
            
            if (filasAfectadas > 0) {
                // Si se guardó correctamente, actualizar el stock
                actualizarStock();
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar detalle: " + e.getMessage());
            return false;
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    
      
      
      // Método para verificar si hay suficiente stock
    private boolean verificarStock() {
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conn;
            
            String sql = "SELECT stock, nombre_producto FROM producto WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id_producto);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                int stockActual = rs.getInt("stock");
                String nombreProducto = rs.getString("nombre_producto");
                
                if (stockActual < cantidad) {
                    JOptionPane.showMessageDialog(null, 
                        "Stock insuficiente para el producto: " + nombreProducto + 
                        "\nStock disponible: " + stockActual + 
                        "\nCantidad solicitada: " + cantidad);
                    rs.close();
                    ps.close();
                    conn.close();
                    return false;
                }
            }
            
            rs.close();
            ps.close();
            conn.close();
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al verificar stock: " + e.getMessage());
            return false;
        }
    }
    
    // Método para actualizar el stock del producto, este es el que vas a ocupar tu brayan xd
 private void actualizarStock() {
        Connection conn = null;
        try {
            Conexion conexion = new Conexion();
            conn = conexion.conn;
            
            String sql = "UPDATE producto SET stock = stock - ? WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, this.cantidad);
            ps.setInt(2, this.id_producto);
            
            int filasAfectadas = ps.executeUpdate();
            ps.close();
            
            if (filasAfectadas > 0) {
                System.out.println("Stock actualizado correctamente para producto: " + this.id_producto);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar stock: " + e.getMessage());
        } 
    }
      
}
