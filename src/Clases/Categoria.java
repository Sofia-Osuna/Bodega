/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sofiaosuna
 */
public class Categoria {
       int id_categoria;
        String nombre_categoria, estatus;

    public Categoria(int id, String nombre, String estatus) {
        this.id_categoria = id;
        this.nombre_categoria = nombre;
        this.estatus = estatus;
    }

    public int getId() {
        return id_categoria;
    }

    public String getNombre() {
        return nombre_categoria;
    }

    public String getEstatus() {
        return estatus;
    }

    @Override
    public String toString() {
        return nombre_categoria.toString();  
    }
    
    
    
    
}
