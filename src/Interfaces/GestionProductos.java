/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package Interfaces;

import Clases.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Clases.Producto;
import Clases.Categoria;
import Interfaces.GestionDeUsuario;
import java.util.ArrayList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;



//todo bien aqui, no mover porfas
/**
 *
 * @author pedro
 */
public class GestionProductos extends javax.swing.JFrame {
    public JPopupMenu menu;
    
    //estas dos variables son tambien lo para el boton de buscar
    
    
    
    /** Creates new form GestionProductos */
    public GestionProductos() {
        initComponents();
        mostrarProductos();
    }
    
    public void mostrarProductos(){
        DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Stock");
    modelo.addColumn("Precio");
    modelo.addColumn("Categoria");
    modelo.addColumn("Acciones");
    
    try {
        Conexion conexion = new Conexion();
        Connection con = conexion.conn;
        
        String sql = "SELECT p.*, c.nombre_categoria as nombre_categoria FROM producto p INNER JOIN categoria c ON p.id_categoria=c.id_categoria WHERE p.estatus='A'";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet datos = ps.executeQuery();
        //Array aqui
         ArrayList<Producto> gestionProducto = new ArrayList<>();
        
        while(datos.next()){
            int id_producto = datos.getInt("id_producto");
            String nombre_producto = datos.getString("nombre_producto");
            int stock = datos.getInt("stock");
            int precio = datos.getInt("precio");
            int id_categoria = datos.getInt("id_categoria");
            String nombre_categoria = datos.getString("nombre_categoria");
            String estatus = datos.getString("estatus");
            
           
            
            //En los parentesis se tiene que acomodar los nombre de las variables en el orden que viene en el constructor
            
            Categoria categoria = new Categoria(id_categoria, nombre_categoria, estatus);
            Producto producto = new Producto(id_producto, stock, precio, id_categoria, nombre_producto);
            
            modelo.addRow(new Object[]{
            
                producto.getId_producto(),
                producto.getNombre_producto(),
                producto.getStock(),
                producto.getPrecio(),
                categoria.getNombre(),
                "Editar"
                
                });
             gestionProducto.add(producto);
        }
        tabla_productos.setModel(modelo);
            
        
       menu = new JPopupMenu();
       JMenuItem itemEditar = new JMenuItem("Editar");
       JMenuItem itemEliminar = new JMenuItem("Eliminar");
       
       menu.add(itemEditar);
       menu.add(itemEliminar);
       
       tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mousePressed(java.awt.event.MouseEvent evt){
           if (evt.isPopupTrigger() || evt.getButton()== java.awt.event.MouseEvent.BUTTON3){
           int fila = tabla_productos.rowAtPoint(evt.getPoint());
           
           if(fila>=0){
               tabla_productos.setRowSelectionInterval(fila,fila);
               menu.show(tabla_productos,    evt.getX(), evt.getY());
           }
           }
       }
       });
       
       //editar 
      itemEditar.addActionListener(e ->{
       int fila  = tabla_productos.getSelectedRow();
       if (fila >= 0){    
          Producto u = gestionProducto.get(fila);
           new EditarProducto(u).setVisible(true);
                   
       }
       });
      
       //eliminar
      itemEliminar.addActionListener(e -> {
       int fila = tabla_productos.getSelectedRow();
       if(fila >= 0){
        //Producto u = GestionProductos.get(fila);
        Producto u = gestionProducto.get(fila);
        int respuesta = JOptionPane.showConfirmDialog(null, "¿estás seguro de eliminar este producto?","Si", JOptionPane.YES_NO_OPTION);
           if(respuesta == JOptionPane.YES_OPTION){
           try{
               //no se porque el conn me lo pide como con.... checar eso
           PreparedStatement ps2 = con.prepareStatement("UPDATE producto SET estatus='B' WHERE id_producto=?");
           ps2.setInt(1, u.getId_producto());
           //ps2.setInt(1, u.getId());
           ps2.executeUpdate();
           mostrarProductos();
          }catch(Exception e2){
          JOptionPane.showMessageDialog(null,"Error al guardar"+e2.getMessage());
          
          
          }
           
           }
       
       
              }
       });
      
        }
     catch (Exception e){
         JOptionPane.showMessageDialog(null, "Error al cargar los datos"+e.getMessage());

     }
    
    }
    
    
        public void buscarProductos(){
        DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Stock");
    modelo.addColumn("Precio");
    modelo.addColumn("Categoria");
    modelo.addColumn("Acciones");
    
    try {
        Conexion conexion = new Conexion();
        Connection con = conexion.conn;
        String nombre_buscar = txtbuscar.getText().trim();
        
        String sql = "SELECT p.*, c.nombre_categoria as nombre_categoria FROM producto p INNER JOIN categoria c ON p.id_categoria=c.id_categoria WHERE p.estatus='A' AND p.nombre_producto=?";
        PreparedStatement ps = con.prepareStatement(sql);
          ps.setString( 1, nombre_buscar);
        ResultSet datos = ps.executeQuery();
      
        
        //Array aqui
         ArrayList<Producto> gestionProducto = new ArrayList<>();
        
        while(datos.next()){
            int id_producto = datos.getInt("id_producto");
            String nombre_producto = datos.getString("nombre_producto");
            int stock = datos.getInt("stock");
            int precio = datos.getInt("precio");
            int id_categoria = datos.getInt("id_categoria");
            String nombre_categoria = datos.getString("nombre_categoria");
            String estatus = datos.getString("estatus");
            
           
            
            //En los parentesis se tiene que acomodar los nombre de las variables en el orden que viene en el constructor
            
            Categoria categoria = new Categoria(id_categoria, nombre_categoria, estatus);
            Producto producto = new Producto(id_producto, stock, precio, id_categoria, nombre_producto);
            
            modelo.addRow(new Object[]{
            
                producto.getId_producto(),
                producto.getNombre_producto(),
                producto.getStock(),
                producto.getPrecio(),
                categoria.getNombre(),
                "Editar"
                
                });
             gestionProducto.add(producto);
        }
        tabla_productos.setModel(modelo);
            
        
       menu = new JPopupMenu();
       JMenuItem itemEditar = new JMenuItem("Editar");
       JMenuItem itemEliminar = new JMenuItem("Eliminar");
       
       menu.add(itemEditar);
       menu.add(itemEliminar);
       
       tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
           public void mousePressed(java.awt.event.MouseEvent evt){
           if (evt.isPopupTrigger() || evt.getButton()== java.awt.event.MouseEvent.BUTTON3){
           int fila = tabla_productos.rowAtPoint(evt.getPoint());
           
           if(fila>=0){
               tabla_productos.setRowSelectionInterval(fila,fila);
               menu.show(tabla_productos,    evt.getX(), evt.getY());
           }
           }
       }
       });
       
       //editar 
      itemEditar.addActionListener(e ->{
       int fila  = tabla_productos.getSelectedRow();
       if (fila >= 0){    
          Producto u = gestionProducto.get(fila);
           new EditarProducto(u).setVisible(true);
                   
       }
       });
      
       //eliminar
      itemEliminar.addActionListener(e -> {
       int fila = tabla_productos.getSelectedRow();
       if(fila >= 0){
        //Producto u = GestionProductos.get(fila);
        Producto u = gestionProducto.get(fila);
        int respuesta = JOptionPane.showConfirmDialog(null, "¿estás seguro de eliminar este producto?","Si", JOptionPane.YES_NO_OPTION);
           if(respuesta == JOptionPane.YES_OPTION){
           try{
               //no se porque el conn me lo pide como con.... checar eso
           PreparedStatement ps2 = con.prepareStatement("UPDATE producto SET estatus='B' WHERE id_producto=?");
           ps2.setInt(1, u.getId_producto());
           //ps2.setInt(1, u.getId());
           ps2.executeUpdate();
           mostrarProductos();
          }catch(Exception e2){
          JOptionPane.showMessageDialog(null,"Error al guardar"+e2.getMessage());
          
          
          }
           
           }
       
       
              }
       });
      
        }
     catch (Exception e){
         JOptionPane.showMessageDialog(null, "Error al cargar los datos"+e.getMessage());

     }
    
    }
    
    
        
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        botonproducto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        botoncategoria = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        botonbuscar = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(42, 138, 127));

        jPanel3.setBackground(new java.awt.Color(25, 39, 52));

        botonproducto.setBackground(new java.awt.Color(25, 39, 52));
        botonproducto.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        botonproducto.setForeground(new java.awt.Color(255, 255, 255));
        botonproducto.setText("Producto");
        botonproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonproductoActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(25, 39, 52));
        jButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton2.setText("Usuarios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(25, 39, 52));
        jButton3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton3.setText("Menu principal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(25, 39, 52));
        jButton4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton4.setText("Proveedores");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        botoncategoria.setBackground(new java.awt.Color(25, 39, 52));
        botoncategoria.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        botoncategoria.setForeground(new java.awt.Color(255, 255, 255));
        botoncategoria.setText("Categorias");
        botoncategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoncategoriaActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(25, 39, 52));
        jButton6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton6.setText("Reporte");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(botonproducto)
                .addGap(40, 40, 40)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(botoncategoria)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonproducto)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(botoncategoria)
                    .addComponent(jButton6))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestion de productos");

        jButton7.setBackground(new java.awt.Color(25, 39, 52));
        jButton7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton7.setText("Salir");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(173, 173, 173)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(28, 28, 28))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButton7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Stock", "Precio", "Categoria", "Acciones"
            }
        ));
        jScrollPane1.setViewportView(tabla_productos);

        txtbuscar.setBackground(new java.awt.Color(204, 204, 204));
        txtbuscar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtbuscar.setForeground(new java.awt.Color(102, 102, 102));
        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });

        botonbuscar.setBackground(new java.awt.Color(25, 39, 52));
        botonbuscar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        botonbuscar.setForeground(new java.awt.Color(255, 255, 255));
        botonbuscar.setText("Buscar");
        botonbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonbuscarActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(42, 138, 127));
        jButton9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Agregar Producto");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44)
                            .addComponent(botonbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addGap(28, 28, 28))
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

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void botoncategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoncategoriaActionPerformed
         //este codigo es para que los botones funcionen
        GestionCategoria gestionc = new GestionCategoria();
        //Indicamos que se hace visible
        gestionc.setVisible(true);
        //cerramos esta ventana
        dispose();
        //Fin del codigo para abrir nuevas ventanas
        // TODO add your handling code here:
    }//GEN-LAST:event_botoncategoriaActionPerformed

    private void botonproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonproductoActionPerformed
        // TODO add your handling code here:
        //este codigo es para que los botones funcionen
        GestionProductos gestionp = new GestionProductos();
        //Indicamos que se hace visible
        gestionp.setVisible(true);
        //cerramos esta ventana
        dispose();
        //Fin del codigo para abrir nuevas ventanas

    }//GEN-LAST:event_botonproductoActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        
        FormularioProducto prod = new FormularioProducto();
        prod.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
GestionDeUsuario gesusuario = new GestionDeUsuario();
    gesusuario.setVisible(true);
    dispose();                                           
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
       dispose();     }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
Proveedores prove = new Proveedores();
       prove.setVisible(true);
       dispose();    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 ReporteDiario re = new ReporteDiario();
        re.setVisible(true);
        dispose();    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
Inicio_de_sesion ini = new Inicio_de_sesion();
        ini.setVisible(true);
        dispose();     }//GEN-LAST:event_jButton7ActionPerformed

    private void botonbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonbuscarActionPerformed
        // TODO add your handling code here:
        
      
        
    }//GEN-LAST:event_botonbuscarActionPerformed

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txtbuscarKeyTyped
    
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
            java.util.logging.Logger.getLogger(GestionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonbuscar;
    private javax.swing.JButton botoncategoria;
    private javax.swing.JButton botonproducto;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_productos;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables

}
