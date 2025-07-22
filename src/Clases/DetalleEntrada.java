/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.*;
/**
 *
 * @author braya
 */
public class DetalleEntrada {
    int id_detalle_entrada,id_entrada,id_producto,cantidad;
       String estatus;
    int stockDisponible;

    public DetalleEntrada(int id_detalle_entrada, int id_entrada, int id_producto, int cantidad, String estatus) {
        this.id_detalle_entrada = id_detalle_entrada;
        this.id_entrada = id_entrada;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.estatus = estatus;
    }

    public DetalleEntrada(int id_entrada, int id_producto, int cantidad) {
        this.id_entrada = id_entrada;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
    }

    public DetalleEntrada(int id_entrada) {
        this.id_entrada = id_entrada;
    }

    public int getId_detalle_entrada() {
        return id_detalle_entrada;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public int getId_producto() {
        return id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }
    
     public boolean guardar() {
        Connection conn = null;
        try {
            Conexion conexion = new Conexion();
            conn = conexion.conn;
            
            // Verificar que tenemos un id_salida válido.....tal vez quito esto
            if (this.id_entrada <= 0) {
                JOptionPane.showMessageDialog(null, "Error: ID de Entrada no válido");
                return false;
            }
            
            // Verificar stock disponible
          
            // Insertar el detalle de salida - SQL CORREGIDO
            String sql = "INSERT INTO detalle_entrada (id_entrada, id_producto, cantidad, estatus) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, this.id_entrada);
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
       private void actualizarStock () {
        Connection conn = null;
        try {
            Conexion conexion = new Conexion();
            conn = conexion.conn;
            
            String sql = "UPDATE producto SET stock = stock + ? WHERE id_producto = ?";
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
    


