/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio02;

import Pila.*;
import javax.swing.table.DefaultTableModel;

public class CallStack {

    Pila<CallFrame> pila = new Pila<>();

    public void call(CallFrame valor) {
        pila.push(valor);
    }

    public CallFrame retorno() {
        return pila.pop();
    }

    public void printStack(DefaultTableModel modelo) {
        int n = pila.contar();
        String titulos[] = {"METODOS", "PARAMETROS", "TIEMPO ESTIMADO"};
        Object datos[][] = new Object[n][3];
        Nodo<CallFrame> nodo = pila.peek();

        for (int i = 0; i < n; i++) {
            CallFrame pila = nodo.getInfo();
            datos[i][0] = pila.getMetodo();
            datos[i][1] = pila.getParametros();
            datos[i][2] = pila.getTiempoEstimado();
            nodo = nodo.getSgte();
        }
        modelo.setDataVector(datos, titulos);
    }
}
