/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;


import Clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import Clases.Usuarios;
import static java.awt.SystemColor.menu;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author braya
 */
public class GestionDeUsuario extends javax.swing.JFrame {
public JPopupMenu menu;
    /**
     * Creates new form GestionDeUsuario
     */
    public GestionDeUsuario() {
        initComponents();
        mostrarUsuarios();
        buscarUsuarios();
        this.setLocationRelativeTo(null);
    }
    public void mostrarUsuarios() {
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Id_Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ap");
        modelo.addColumn("Am");
        modelo.addColumn("Calle");
        modelo.addColumn("Cp");
        modelo.addColumn("Numero");
        modelo.addColumn("Telefono");
        modelo.addColumn("Clave");
        modelo.addColumn("Estatus");
        
        try {
           Conexion conexion = new Conexion();
        Connection conn = conexion.conn;
        //texto
        
        String sql = "SELECT * FROM usuario WHERE estatus='A'  ";
        PreparedStatement ps = conn.prepareStatement(sql);
        //parametro
        ResultSet datos = ps.executeQuery();
        ArrayList<Usuarios> GestionDeUsuario = new ArrayList<>();
        
        while(datos.next()){
            int id_usuario = datos.getInt("id_usuario");
            String nombre = datos.getString("nombre");
            String ap = datos.getString("ap");
            String am = datos.getString("am");
            String calle = datos.getString("calle");
            int cp = datos.getInt("cp");
            String numero = datos.getString("numero");
            String telefono = datos.getString("telefono");
            String clave = datos.getString("clave");
            int id_tipo_usuario = datos.getInt("id_tipo_usuario");
            String estatus = datos.getString("estatus");
            
            Usuarios usuario = new Usuarios(id_usuario, id_tipo_usuario, cp,  nombre, ap, am, calle, numero, telefono,clave,estatus);
           
            modelo.addRow(new Object[]{
            usuario.getId_usuario(),
            usuario.getNombre(),
            usuario.getAp(),
            usuario.getAm(),
            usuario.getCalle(),
            usuario.getCp(),
            usuario.getNumero(),
            usuario.getTelefono(),
            usuario.getClave(),
            usuario.getEstatus(),
            "Editar"
            });
            
            GestionDeUsuario.add(usuario);
        }
        tabla_usuarios.setModel(modelo);
        menu = new JPopupMenu();
        JMenuItem itemEditar = new JMenuItem("Editar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        
        menu.add(itemEditar);
        menu.add(itemEliminar);
        
        tabla_usuarios.addMouseListener(new java.awt.event.MouseAdapter(){
        public void mousePressed(java.awt.event.MouseEvent evt) {
        if (evt.isPopupTrigger() || evt.getButton() == java.awt.event.MouseEvent.BUTTON3){
            int fila = tabla_usuarios.rowAtPoint(evt.getPoint());
            
            if (fila>=0){
                tabla_usuarios.setRowSelectionInterval(fila,fila);
                menu.show(tabla_usuarios, evt.getX(), evt.getY());
            }
        }
            }
        public void mouseReleased(java.awt.event.MouseEvent evt){
            mousePressed(evt);
        }
        });
        //Editar 
        itemEditar.addActionListener(e ->{
        int fila = tabla_usuarios.getSelectedRow();
        if (fila >= 0){
            Usuarios u = GestionDeUsuario.get(fila);
            new EditarUsuario(u).setVisible(true);
        }
        });
        //Eliminar 
        itemEliminar.addActionListener(e ->{
            int fila = tabla_usuarios.getSelectedRow();
            if(fila >=0){
                Usuarios u = GestionDeUsuario.get(fila);
                int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar al usuario?", "Si", JOptionPane.YES_NO_OPTION);
                if(respuesta == JOptionPane.YES_OPTION){
                    try{
                    PreparedStatement ps2 = conn.prepareStatement("UPDATE usuario SET estatus='B' WHERE id_usuario=?");
                    ps2.setInt(1, u.getId_usuario());
                    ps2.executeUpdate();
                    mostrarUsuarios();
                     }catch(Exception e2){
                      JOptionPane.showMessageDialog(null, "Error al guardar"+e2.getMessage());
                     }
                }
            }
        });
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,"Error al cargar los datos"
                     +e.getMessage());      
 }
    }
    
    public void buscarUsuarios(){
    DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Id_Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ap");
        modelo.addColumn("Am");
        modelo.addColumn("Calle");
        modelo.addColumn("Cp");
        modelo.addColumn("Numero");
        modelo.addColumn("Telefono");
        modelo.addColumn("Clave");
        modelo.addColumn("Estatus");
        
        try{
        Conexion conexion = new Conexion();
        Connection conn = conexion.conn;  
        
        String sql = "SELECT * FROM usuario WHERE estatus='A' AND nombre = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet datos = ps.executeQuery();
        
        ArrayList<Usuarios> GestionDeUsuario = new ArrayList<>();
        
        while(datos.next());{
         int id_usuario = datos.getInt("id_usuario");
            String nombre = datos.getString("nombre");
            String ap = datos.getString("ap");
            String am = datos.getString("am");
            String calle = datos.getString("calle");
            int cp = datos.getInt("cp");
            String numero = datos.getString("numero");
            String telefono = datos.getString("telefono");
            String clave = datos.getString("clave");
            int id_tipo_usuario = datos.getInt("id_tipo_usuario");
            String estatus = datos.getString("estatus"); 
            
            Usuarios usuario = new Usuarios(id_usuario, id_tipo_usuario, nombre,  ap, am, calle, cp, numero, telefono, clave);
            
            modelo.addRow(new Object[]{
                
            usuario.getId_usuario(),
            usuario.getNombre(),
            usuario.getAp(),
            usuario.getAm(),
            usuario.getCalle(),
            usuario.getCp(),
            usuario.getNumero(),
            usuario.getTelefono(),
            usuario.getClave(),
            usuario.getEstatus(),
            "Editar"
                
            });
            
            GestionDeUsuario.add(usuario);
        }
        tabla_usuarios.setModel(modelo);
        menu = new JPopupMenu();
        JMenuItem itemEditar = new JMenuItem("Editar");
        JMenuItem itemEliminar = new JMenuItem("Eliminar");
        
        menu.add(itemEditar);
        menu.add(itemEliminar);
        
        tabla_usuarios.addMouseListener(new java.awt.event.MouseAdapter(){
        public void mousePressed(java.awt.event.MouseEvent evt) {
        if (evt.isPopupTrigger()|| evt.getButton() == java.awt.event.MouseEvent.BUTTON3){
         int fila = tabla_usuarios.rowAtPoint(evt.getPoint());
         
         if (fila>=0){
          tabla_usuarios.setRowSelectionInterval(fila,fila);
           }
         }    
        } 
        public void mouseReleased(java.awt.event.MouseEvent evt){
           mousePressed(evt);
        }
        });
        
        //Editar
        itemEditar.addActionListener(e ->{
        int fila = tabla_usuarios.getSelectedRow();
        if (fila >= 0){
        Usuarios u = GestionDeUsuario.get(fila);
        new EditarUsuario(u).setVisible(true);   
        }
        });
        
        //Eliminar
        itemEliminar.addActionListener(e ->{
            int fila = tabla_usuarios.getSelectedRow();
            if (fila >=0){
            Usuarios u = GestionDeUsuario.get(fila);
                int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar al usuario?", "Si", JOptionPane.YES_NO_OPTION);  
                if(respuesta == JOptionPane.YES_OPTION){
                    try{
                     PreparedStatement ps2 = conn.prepareStatement("UPDATE usuario SET estatus='B' WHERE id_usuario=?");
                   ps2.setInt(1, u.getId_usuario());
                    ps2.executeUpdate();
                    mostrarUsuarios();
                    }catch(Exception e2){
                      JOptionPane.showMessageDialog(null, "Error al guardar"+e2.getMessage());  
                    }
                }
            }
        });
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al cargar los datos"
                     +e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_usuarios = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(42, 138, 127));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Gestion de usuarios");

        jPanel2.setBackground(new java.awt.Color(25, 39, 52));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jButton4.setBackground(new java.awt.Color(25, 39, 52));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setText("Reporte Diario");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(25, 39, 52));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton6.setText("Categorias");
        jButton6.setBorderPainted(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(25, 39, 52));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton5.setText("Proveedores");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(25, 39, 52));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Menu principal");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(25, 39, 52));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Usuario");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(25, 39, 52));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Producto");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButton1)
                .addGap(30, 30, 30)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton7.setBackground(new java.awt.Color(25, 39, 52));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton7.setText("Salir");
        jButton7.setBorderPainted(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(21, 21, 21))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabla_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Usuario", "Nombre", "Ap", "Am", "Cp", "Calle", "Numero", "Telefono", "Clave", "Estatus"
            }
        ));
        jScrollPane1.setViewportView(tabla_usuarios);

        jButton8.setBackground(new java.awt.Color(42, 138, 127));
        jButton8.setText("registrar usuario");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 ReporteDiario re = new ReporteDiario();
        re.setVisible(true);
        dispose();    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
Inicio_de_sesion ini = new Inicio_de_sesion();
        ini.setVisible(true);
        dispose();    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
GestionCategoria gestcat = new GestionCategoria();
        gestcat.setVisible (true);
        dispose();      }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
 HistorialDeSalidas hismovi = new HistorialDeSalidas();
       hismovi.setVisible(true);
       dispose();    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
       dispose();    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
GestionDeUsuario gesusuario = new GestionDeUsuario();
    gesusuario.setVisible(true);
    dispose();     }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
GestionProductos gesproducto = new GestionProductos();
        
        gesproducto.setVisible(true);
     
        dispose();    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
 CrearUsuario cu= new CrearUsuario();
    cu.setVisible(true);
    dispose();   

    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionDeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionDeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionDeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionDeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionDeUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_usuarios;
    // End of variables declaration//GEN-END:variables
}
