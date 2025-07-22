/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
/**
 *
 * @author sofiaosuna
 */

public class EntradaProducto {
        public int id_entrada, id_proveedor, id_usuario_operador;
        public String fecha_entrada , hora_entrada, estatus;
        
        public EntradaProducto(int id_proveedor, int id_usuario_operador, String fecha_entrada, String hora_entrada, String estatus) {
        this.id_proveedor = id_proveedor;
        this.id_usuario_operador = id_usuario_operador;
        this.fecha_entrada = fecha_entrada;
        this.hora_entrada = hora_entrada;
        this.estatus = estatus;
    }

    public int getId_entrada() {
        return id_entrada;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public int getId_usuario_operador() {
        return id_usuario_operador;
    }
  
    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public String getEstatus() {
        return estatus;
    }

    public EntradaProducto(int id_proveedor, int id_usuario_operador,String fecha_entrada, String hora_entrada) {
        this.id_proveedor = id_proveedor;
        this.id_usuario_operador = id_usuario_operador;
        this.fecha_entrada = fecha_entrada;
        this.hora_entrada = hora_entrada;
    }

    public EntradaProducto(int id_proveedor, int id_usuario_operador) {
        this.id_proveedor = id_proveedor;
        this.id_usuario_operador = id_usuario_operador;
    }

    

    
     
     
     
     
     
     
    
    
    /*
      public boolean guardar() {
             try{
       Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;
            String sql = "INSERT INTO entrada (id_proveedor,id_usuario_operador,fecha_entrada,hora_entrada,estatus ) VALUES (?,?,NOW(),NOW(),'A')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id_proveedor);
            ps.setInt(2,id_usuario_operador);
            ps.executeUpdate();
            return true;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar"+e.getMessage());
            return false;
        }
           
}       
*/
    
    public EntradaProducto(int id_usuario_operador, String fecha_salida) {
        this.id_usuario_operador = id_usuario_operador;
        this.fecha_entrada = fecha_salida;
        this.estatus = "A"; 
        
        // Generar hora automáticamente
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora_entrada = horaActual.format(formatter);
    }
    public int guardar() {
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conn;
            
            String sql = "INSERT INTO entrada (fecha_entrada, hora_entrada, id_usuario_operador, id_proveedor, estatus) VALUES (NOW(), NOW(), ?, ?, 'A')";
            PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, id_usuario_operador);
            ps.setInt(2, id_proveedor );
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id_entrada = rs.getInt(1);
                    rs.close();
                    ps.close();
                    conn.close();
                    return this.id_entrada; // Devolver el ID generado
                }
            }
            
            ps.close();
            conn.close();
            return -1; // Error
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar salida: " + e.getMessage());
            return -1;
        }
    }
    
        }
