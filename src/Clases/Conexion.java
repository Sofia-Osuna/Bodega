/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.*;

/**
 *
 * @author sofiaosuna
 */
public class Conexion {
    public Connection conn;
    
    public Conexion(){
        
    try{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bodega", "root", "");
        System.out.println("Conectado");
        
    }catch (Exception e) {
    System.out.println("Error al conectar");
    }
    }
    
    
    
    //metodo para checar las contraseña del usuario
    public static Usuarios validarCredenciales(String usuario, String contraseña) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        Usuarios usuarioos = null;
        
        try {
            Conexion conexion = new Conexion();
            conn = conexion.conn;
            if (conn != null) {
                // Consulta SQL para validar usuario y contraseña
                String sql = "SELECT u.id_usuario, u.nombre, u.id_tipo_usuario, t.tipo, u.clave FROM usuario u INNER JOIN tipo_usuario t ON u.id_tipo_usuario = t.id_tipo_usuario WHERE u.nombre = ? AND u.clave = ? AND u.estatus = 'A'";
                
                pst = conn.prepareStatement(sql);
                pst.setString(1, usuario);
                pst.setString(2, contraseña);
                
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    // Crear objeto con información del usuario
                    usuarioos = new Usuarios(
                        rs.getInt("id_usuario"),
                        rs.getInt("id_tipo_usuario"),    
                        rs.getString("nombre"),
                        rs.getString("clave")
                        
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error al validar credenciales: " + e.getMessage());
            System.out.println("Error de conexión a la base de datos");
                 
                
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return usuarioos;
    }
    
}


