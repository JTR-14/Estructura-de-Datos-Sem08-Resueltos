/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio03;

import Pila.*;

public class XMLValidator {

    private Pila<String> pila;

    public XMLValidator() {
        this.pila = new Pila<>();
    }

    public boolean validar(String xml) {
        pila.removeAll();

        String nombreTag = "";
        int estado = 0;

        for (int i = 0; i < xml.length(); i++) {

            char c = xml.charAt(i);

            switch (estado) {

                case 0:
                    if (c == '<') {
                        if (i + 1 < xml.length() && xml.charAt(i + 1) == '/') {
                            estado = 2;
                            i++;
                        } else {
                            estado = 1;
                        }
                        nombreTag = "";
                    }
                    break;

                case 1:
                    if (c == '>') {
                        if (nombreTag.isEmpty()) {
                            return false;
                        }
                        pila.push(nombreTag);
                        estado = 0;
                    } else {
                        nombreTag += c;
                    }
                    break;

                case 2:
                    if (c == '>') {
                        if (nombreTag.isEmpty()) 
                            return false;
                        
                        if (pila.isEmpty()) 
                            return false;
                        
                        String tagApertura = pila.pop();
                        String tagCierre = nombreTag;

                        if (!tagApertura.equals(tagCierre)) {
                            return false;
                        }
                        estado = 0;
                    } else {
                        nombreTag += c;
                    }
                    break;
            }
        }

        return estado == 0 && pila.isEmpty();
    }
}
