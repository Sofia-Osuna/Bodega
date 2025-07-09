/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author sofiaosuna
 */
public class TipoUsuario {
    int id_tipo_usuario;
    String tipo;

    public TipoUsuario(int id_tipo_usuario, String tipo) {
        this.id_tipo_usuario = id_tipo_usuario;
        this.tipo = tipo;
    }

    public int getId_tipo_usuario() {
        return id_tipo_usuario;
    }

    public String getTipo() {
        return tipo;
    }
    
    @Override
    public String toString() {
        return tipo.toString();  
    }
    
    //La verdad no recuerdo si los setter se necesitan en este caso, pero no creo que interfieran
    //por lo que los agregue, luego vemos que pasa uwu

    public void setId_tipo_usuario(int id_tipo_usuario) {
        this.id_tipo_usuario = id_tipo_usuario;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    //Ah pues creo que si se ocupan xdxdxd, pues ahi estan
    
}
