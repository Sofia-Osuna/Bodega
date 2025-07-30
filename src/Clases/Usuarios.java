/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Clases.Conexion;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.Connection;

/**
 *
 * @author sofiaosuna
 */
public class Usuarios{
    public int id_usuario, id_tipo_usuario, cp;
    public String nombre, ap, am, calle,  numero, telefono, clave, estatus;
//constructor con el id_usuario para combobox y asi
    public Usuarios(int id_usuario, int id_tipo_usuario, String nombre, String ap, String am, String calle, int cp, String numero, String telefono, String clave) {
        this.id_usuario = id_usuario;
        this.id_tipo_usuario = id_tipo_usuario;
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
        this.calle = calle;
        this.cp = cp;
        this.numero = numero;
        this.telefono = telefono;
        this.clave = clave;
        this.estatus = estatus;
    }
//constructor sin id_usuario para registrar nuevos usuarios
    public Usuarios(int id_tipo_usuario, String nombre, String ap, String am, String calle, int cp, String numero, String telefono, String clave) {
        this.id_tipo_usuario = id_tipo_usuario;
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
        this.calle = calle;
        this.cp = cp;
        this.numero = numero;
        this.telefono = telefono;
        this.clave = clave;
       // this.estatus = estatus;
    }
    
    


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Usuarios(int id_usuario, int id_tipo_usuario, int cp, String nombre, String ap, String am, String calle, String numero, String telefono, String clave, String estatus) {
        this.id_usuario = id_usuario;
        this.id_tipo_usuario = id_tipo_usuario;
        this.cp = cp;
        this.nombre = nombre;
        this.ap = ap;
        this.am = am;
        this.calle = calle;
        this.numero = numero;
        this.telefono = telefono;
        this.clave = clave;
        this.estatus = estatus;
    }
        

    
public boolean guardar(){
    try{
    Conexion conexion = new Conexion ();
    Connection conn = conexion.conn;
    
    String sql = "INSERT INTO usuario(nombre, ap, am, calle, cp, numero, telefono, clave, id_tipo_usuario, estatus) VALUES (?,?,?,?,?,?,?,?,?,'A')";
    PreparedStatement ps = conn.prepareStatement(sql);
    ps.setString(1, nombre);
    ps.setString(2, ap);
    ps.setString(3, am);
    ps.setString(4, calle);
    ps.setInt(5, cp);
    ps.setString(6, numero);
    ps.setString(7, telefono);
    ps.setString(8, clave);
    ps.setInt(9, id_tipo_usuario);
    ps.executeUpdate();
    
    
return true;
}catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error al guardar"+e.getMessage());
    return false;
}  
}

@Override
    public String toString() {
    return nombre.toString(); 
}
    
    
    
    //UwU constructor para el metodo de validacion de credenciales para lo de inicio de sesión... nomas pa eso

    public Usuarios(int id_usuario, int id_tipo_usuario, String nombre, String clave) {
        this.id_usuario = id_usuario;
        this.id_tipo_usuario = id_tipo_usuario;
        this.nombre = nombre;
        this.clave = clave;
    }
    public boolean actualizar(){
        try{
    
        Conexion conexion = new Conexion ();
        Connection conn = conexion.conn;
        
        String sql = "UPDATE usuario SET nombre=?, ap=?, am=?, calle=?, cp=?, numero=?, telefono=?, clave=?, id_tipo_usuario=?, estatus='A' WHERE id_usuario=?";              
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.setString(2, ap);
        ps.setString(3, am);
        ps.setString(4, calle);
        ps.setInt(5, cp);
        ps.setString(6, numero);
        ps.setString(7, telefono);
        ps.setString(8, clave);
        ps.setInt(9, id_tipo_usuario);
      
        ps.setInt(10, id_usuario);
        
        
        ps.executeUpdate();
        return true;
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error al actualizar"+e.getMessage());
        return false;
       }
    } 
    
}
