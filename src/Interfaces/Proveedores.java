/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;
import Clases.Conexion;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JOptionPane;
import Clases.Proveedor;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JPopupMenu;
import java.util.ArrayList;
import javax.swing.JMenuItem;


        


/**
 *
 * @author braya
 */
public class Proveedores extends javax.swing.JFrame {
    public JPopupMenu menu;

    /**
     * Creates new form Proveedores
     */
    public Proveedores() {
        initComponents();
        mostrarProveedor();
        this.setLocationRelativeTo(null);

        this.setTitle("Gestion de los proveedores");

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
        botonreportediario = new javax.swing.JButton();
        botoncategorias = new javax.swing.JButton();
        botonproveedores = new javax.swing.JButton();
        botonhistorialdemovimientos = new javax.swing.JButton();
        botonusuario = new javax.swing.JButton();
        botonproducto = new javax.swing.JButton();
        botonsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Proveedores = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(42, 138, 127));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Proveedores");

        jPanel2.setBackground(new java.awt.Color(25, 39, 52));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        botonreportediario.setBackground(new java.awt.Color(25, 39, 52));
        botonreportediario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonreportediario.setForeground(new java.awt.Color(255, 255, 255));
        botonreportediario.setText("Reporte diario");
        botonreportediario.setBorderPainted(false);
        botonreportediario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonreportediarioActionPerformed(evt);
            }
        });

        botoncategorias.setBackground(new java.awt.Color(25, 39, 52));
        botoncategorias.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botoncategorias.setForeground(new java.awt.Color(255, 255, 255));
        botoncategorias.setText("Categorias");
        botoncategorias.setBorderPainted(false);
        botoncategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoncategoriasActionPerformed(evt);
            }
        });

        botonproveedores.setBackground(new java.awt.Color(25, 39, 52));
        botonproveedores.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonproveedores.setForeground(new java.awt.Color(255, 255, 255));
        botonproveedores.setText("Proveedores");
        botonproveedores.setBorderPainted(false);
        botonproveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonproveedoresActionPerformed(evt);
            }
        });

        botonhistorialdemovimientos.setBackground(new java.awt.Color(25, 39, 52));
        botonhistorialdemovimientos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonhistorialdemovimientos.setForeground(new java.awt.Color(255, 255, 255));
        botonhistorialdemovimientos.setText("Menu principal");
        botonhistorialdemovimientos.setBorderPainted(false);
        botonhistorialdemovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonhistorialdemovimientosActionPerformed(evt);
            }
        });

        botonusuario.setBackground(new java.awt.Color(25, 39, 52));
        botonusuario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonusuario.setForeground(new java.awt.Color(255, 255, 255));
        botonusuario.setText("Usuario");
        botonusuario.setBorderPainted(false);
        botonusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonusuarioActionPerformed(evt);
            }
        });

        botonproducto.setBackground(new java.awt.Color(25, 39, 52));
        botonproducto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonproducto.setForeground(new java.awt.Color(255, 255, 255));
        botonproducto.setText("Producto");
        botonproducto.setBorderPainted(false);
        botonproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonproductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(botonproducto)
                .addGap(30, 30, 30)
                .addComponent(botonusuario)
                .addGap(18, 18, 18)
                .addComponent(botonproveedores)
                .addGap(18, 18, 18)
                .addComponent(botoncategorias)
                .addGap(18, 18, 18)
                .addComponent(botonreportediario)
                .addGap(18, 18, 18)
                .addComponent(botonhistorialdemovimientos)
                .addContainerGap(174, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonproducto)
                    .addComponent(botonusuario)
                    .addComponent(botonhistorialdemovimientos)
                    .addComponent(botonreportediario)
                    .addComponent(botonproveedores)
                    .addComponent(botoncategorias))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonsalir.setBackground(new java.awt.Color(25, 39, 52));
        botonsalir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        botonsalir.setForeground(new java.awt.Color(255, 255, 255));
        botonsalir.setText("Salir");
        botonsalir.setBorderPainted(false);
        botonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonsalirActionPerformed(evt);
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
                .addComponent(botonsalir)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(botonsalir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        Tabla_Proveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Teléfono"
            }
        ));
        jScrollPane1.setViewportView(Tabla_Proveedores);

        jButton1.setBackground(new java.awt.Color(42, 138, 127));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Nuevo proveedor");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1))
                .addGap(26, 26, 26))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
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
public void mostrarProveedor(){
     DefaultTableModel modelo = new DefaultTableModel();

     modelo.addColumn("Nombre");
     modelo.addColumn("Telefono");
     
     
   
       try{Conexion conexion = new Conexion();
       Connection conn = conexion.conn;
       String sql = "SELECT * FROM proveedor WHERE estatus='A'";
     PreparedStatement ps = conn.prepareStatement(sql);
       ResultSet datos = ps.executeQuery();
                 ArrayList<Proveedor>Proveedores= new ArrayList<>();
       
     while (datos.next()){
       String nombre = datos.getString("nombre_proveedor");
       String telefono = datos.getString("telefono");
       String estatus = datos.getString("estatus");

       Proveedor proveedor = new Proveedor( nombre,telefono,estatus);
       modelo.addRow(new Object[]{nombre,telefono});

      
     Proveedores.add(proveedor);
     
  }
           
      
       //Este pedazo lo pueden copiar y pegar nomas cambien las variables 
       Tabla_Proveedores.setModel(modelo);
        menu = new JPopupMenu();
       JMenuItem itemEditar = new JMenuItem("Editar");
       JMenuItem itemEliminar = new JMenuItem("Eliminar");
       
       menu.add(itemEditar);
       menu.add(itemEliminar);
       
      Tabla_Proveedores.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mousePressed(java.awt.event.MouseEvent evt){
           if (evt.isPopupTrigger() || evt.getButton()== java.awt.event.MouseEvent.BUTTON3){
           int fila = Tabla_Proveedores.rowAtPoint(evt.getPoint());
           
           if(fila>=0){
             Tabla_Proveedores.setRowSelectionInterval(fila,fila);
               menu.show(Tabla_Proveedores, evt.getX(), evt.getY());
           }
           }
       }
           });
     //editar 
      itemEditar.addActionListener(e ->{
       int fila  = Tabla_Proveedores.getSelectedRow();
       if (fila >= 0){    
          Proveedor u = Proveedores.get(fila);
           new EditarProveedores(u).setVisible(true);
                   
       }
       });
     //eliminar
      itemEliminar.addActionListener(e -> {
       int fila = Tabla_Proveedores.getSelectedRow();
       if(fila >= 0){
        Proveedor u = Proveedores.get(fila);
        int respuesta = JOptionPane.showConfirmDialog(null, "estás seguro de eliminar al usuario?","Si", JOptionPane.YES_NO_OPTION);
           if(respuesta == JOptionPane.YES_OPTION){
           try{
           PreparedStatement ps2 = conn.prepareStatement("UPDATE proveedor SET estatus='B' WHERE id_proveedor=?");
           ps2.setInt(1, u.getId_proveedor());
           
           ps2.executeUpdate();
           mostrarProveedor();
          }catch(Exception e2){
          JOptionPane.showMessageDialog(null,"Error al guardar"+e2.getMessage());
          
          
          }
           
           }
       
       
              }
       });
     
     
 }catch(Exception e){
             JOptionPane.showMessageDialog(null,"Error:"
                     +e.getMessage());      
 }
    }


    private void botonreportediarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonreportediarioActionPerformed
        ReporteDiario rep = new ReporteDiario();
        rep.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_botonreportediarioActionPerformed

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
        Inicio_de_sesion inicios = new Inicio_de_sesion();
        inicios.setVisible(true);
        dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_botonsalirActionPerformed

    private void botoncategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoncategoriasActionPerformed
        //este codigo es para que los botones funcionen
        GestionCategoria gestionc = new GestionCategoria();
        //Indicamos que se hace visible
        gestionc.setVisible(true);
        //cerramos esta ventana
        dispose();
        //Fin del codigo para abrir nuevas ventanas
        // TODO add your handling code here:
    }//GEN-LAST:event_botoncategoriasActionPerformed

    private void botonproveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonproveedoresActionPerformed
         Proveedores pro = new Proveedores();
        pro.setVisible(true);
        dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_botonproveedoresActionPerformed

    private void botonhistorialdemovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonhistorialdemovimientosActionPerformed
       MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_botonhistorialdemovimientosActionPerformed

    private void botonusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonusuarioActionPerformed
GestionDeUsuario gesusuario = new GestionDeUsuario();
    gesusuario.setVisible(true);
    dispose();       }//GEN-LAST:event_botonusuarioActionPerformed

    private void botonproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonproductoActionPerformed
         //este codigo es para que los botones funcionen
        GestionProductos gestionp = new GestionProductos();
        //Indicamos que se hace visible
        gestionp.setVisible(true);
        //cerramos esta ventana
        dispose();
        //Fin del codigo para abrir nuevas ventanas


        // TODO add your handling code here:
    }//GEN-LAST:event_botonproductoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       CrearProveedor crearProv = new CrearProveedor();
        crearProv.setVisible(true);
        dispose();
       
       
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_Proveedores;
    private javax.swing.JButton botoncategorias;
    private javax.swing.JButton botonhistorialdemovimientos;
    private javax.swing.JButton botonproducto;
    private javax.swing.JButton botonproveedores;
    private javax.swing.JButton botonreportediario;
    private javax.swing.JButton botonsalir;
    private javax.swing.JButton botonusuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
