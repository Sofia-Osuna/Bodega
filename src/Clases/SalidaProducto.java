/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author sofiaosuna
 */
public class SalidaProducto {
   int id_salida, id_usuario_op, id_usuario_so;
   String fecha_salida, hora_salida, estatus;
   //localTime horaSalida;
    
  //constructor completo 

    public SalidaProducto(int id_salida,  int id_usuario_op, int id_usuario_so, String fecha_salida, String hora_salida) {
        this.id_salida = id_salida;
        
        this.id_usuario_op = id_usuario_op;
        this.id_usuario_so = id_usuario_so;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        //this.estatus = estatus;
    }
    
    

    //este constructor es solo para mostrar los datos en la gestion de salidas
    public SalidaProducto(int id_salida, String fecha_salida, String hora_salida) {
        this.id_salida = id_salida;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
    }
    
    
    
    

    public int getId_salida() {
        return id_salida;
    }

    public void setId_salida(int id_salida) {
        this.id_salida = id_salida;
    }

    
    public int getId_usuario_op() {
        return id_usuario_op;
    }

    public void setId_usuario_op(int id_usuario_op) {
        this.id_usuario_op = id_usuario_op;
    }

    public int getId_usuario_so() {
        return id_usuario_so;
    }

    public void setId_usuario_so(int id_usuario_so) {
        this.id_usuario_so = id_usuario_so;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    /*
     public boolean guardar (){
        try{
            Conexion conexion = new Conexion ();
            Connection conn = conexion.conn;

           
            String sql = "INSERT INTO salida (fecha_salida, hora_salida, id_usuario_operador, id_usuario_solicitante, estatus) VALUES (?,NOW(),?,?,'A')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fecha_salida);
            ps.setInt(2, id_usuario_op);
             ps.setInt(3, id_usuario_so);
            ps.executeUpdate();
            return true;
            
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al guardar"+e.getMessage());
            return false;
        }
    } 

     */
     //constructor para nuevos registrossss que incluyo lo de la hora automaticamente
      public SalidaProducto(int id_usuario_operador, int id_usuario_solicitante, String fecha_salida) {
        this.id_usuario_op = id_usuario_operador;
        this.id_usuario_so = id_usuario_solicitante;
        this.fecha_salida = fecha_salida;
        this.estatus = "A"; 
        
        // Generar hora automáticamente
        LocalTime horaActual = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.hora_salida = horaActual.format(formatter);
    }
    
    // Método para guardar y devolver el ID generado
    public int guardar() {
        try {
            Conexion conexion = new Conexion();
            Connection conn = conexion.conn;
            
            String sql = "INSERT INTO salida (fecha_salida, hora_salida, id_usuario_operador, id_usuario_solicitante, estatus) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, fecha_salida);
            ps.setString(2, hora_salida);
            ps.setInt(3, id_usuario_op);
            ps.setInt(4, id_usuario_so);
            ps.setString(5, estatus);
            
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Obtener el ID generado
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.id_salida = rs.getInt(1);
                    rs.close();
                    ps.close();
                    conn.close();
                    return this.id_salida; // Devolver el ID generado
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
