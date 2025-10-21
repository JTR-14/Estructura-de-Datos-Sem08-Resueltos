/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pila;

public class Pila<T> {
    protected Nodo<T> L;
    
    public void push(T valor){
        Nodo<T> nuevo = new Nodo<>(valor);
        if(L==null)
            L=nuevo;
        else{
            nuevo.setSgte(L);
            L = nuevo;
        }
    }
    public T pop(){
        T eliminado  = L.getInfo();
        L = L.getSgte();
        return eliminado;
    }
    public T peek(){
        return L.getInfo();
    }
    public boolean isEmpty(){
        return L==null;
    }
    public void removeAll(){
         while(L != null)
             L = L.getSgte();
    }
}
