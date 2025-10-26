/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio01_Propuestos;

import Pila.*;

/**
 *
 * @author USER
 */
public class Parentesis {

    public boolean parametrosBalanceados(String expresion) {
        Pila<Character> pila = new Pila<>();
        int n = expresion.length();

        for (int i = 0; i < n; i++) {
            char x = expresion.charAt(i);

            if (x == '(') {
                pila.push(x);
            } else if (x == ')') {
                if (pila.isEmpty()) {
                    return false;
                } else {
                    pila.pop();
                }
            }
        }

        return pila.isEmpty();
    }
}
