/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio03_Propuestos;

import Pila.*;

public class ConvertidorBase {

    public String convertidor(int base, int entero) {
        String digitos = "0123456789ABCDEF";
        if (entero == 0) {
            return "0";
        }

        Pila<Character> pila = new Pila<>();
        int numero = entero;
        while (numero > 0) {
            int residuo = numero % base;
            pila.push(digitos.charAt(residuo));
            numero /= base;
        }
        String resultado = "";
        while (!pila.isEmpty()) {
            resultado += pila.pop();
        }
        return resultado;
    }
}
