/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio02_Propuestos;
import Pila.*;

public class Palindrome {
    public boolean esPalindrome(String expresion){
        Pila<Character> pila = new Pila<>();
        int n = expresion.length();
        for(int i=0 ; i<n ; i++){
            char x = expresion.charAt(i);
            pila.push(x);
        }
        String invertido = "";
        while(!pila.isEmpty())
            invertido += pila.pop();
        return expresion.equals(invertido);
    }
    
}
