/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.time.LocalDate;
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

    public SalidaProducto(int id_salida,  int id_usuario_op, int id_usuario_so, String fecha_salida, String hora_salida, String estatus) {
        this.id_salida = id_salida;
        
        this.id_usuario_op = id_usuario_op;
        this.id_usuario_so = id_usuario_so;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.estatus = estatus;
    }
   
    //constructor si el id_salida para nuevos registros

    public SalidaProducto( int id_usuario_op, int id_usuario_so, String fecha_salida) {
       
        this.id_usuario_op = id_usuario_op;
        this.id_usuario_so = id_usuario_so;
        this.fecha_salida = fecha_salida;
        //this.hora_salida = hora_salida;
        //this.estatus = estatus;
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
}
