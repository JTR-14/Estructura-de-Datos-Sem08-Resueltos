/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio04;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


public class DynamicStack<T> {

    private final int tamanio = 10;
    private int tope;
    private int cantidadRedimensiones;
    private T array[];

    public DynamicStack() {
        this.tope = -1;
        this.cantidadRedimensiones = 0;
        array = (T[]) new Object[tamanio];
    }

    public boolean limiteArray() {
        return tope == array.length - 1;
    }

    public void redimensionar() {
        JOptionPane.showMessageDialog(null, "El array se ha redimensionado","INFORMACION",1);
        cantidadRedimensiones++;
        int nuevoTamanio = array.length * 2;
        T arrayNuevo[] = (T[]) new Object[nuevoTamanio];
        for (int i = 0; i < array.length; i++) {
            arrayNuevo[i] = array[i];
        }
        this.array = arrayNuevo;
    }

    public void push(T valor) {
        if (limiteArray()) {
            this.redimensionar();
        }
        tope++;
        array[tope] = valor;
    }
    public void mostrar(DefaultListModel modelo){
        modelo.removeAllElements();
        for(int i=tope ; i>=0 ; i--){
            modelo.addElement(array[i]);
        }
    }
    public int getCantidadRedimensiones() {
        return cantidadRedimensiones;
    }
    public int tamanioArray(){
        int tamanio = 10;
        for(int i=0;i<cantidadRedimensiones;i++)
            tamanio = tamanio * 2;
        return tamanio;
    }

}
