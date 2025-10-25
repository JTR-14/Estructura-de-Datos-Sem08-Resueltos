/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio01;

import Pila.*;


public class ExpresionConverter {

    private Pila<Character> pilaOperadores;

    public ExpresionConverter() {
        this.pilaOperadores = new Pila<>();
    }

    public String convertir(String expresionInfija) {

        pilaOperadores.removeAll();
        String postfix = "";

        int i = 0;

        while (i < expresionInfija.length()) {

            char c = expresionInfija.charAt(i);

            if ((c >= 'A' && c <= 'Z')
                    || (c >= 'a' && c <= 'z')
                    || (c >= '0' && c <= '9')) {
                String operando = "";
                operando += c;

                while (i + 1 < expresionInfija.length()) {
                    char sgteCaracter = expresionInfija.charAt(i + 1);

                    if ((sgteCaracter >= 'A' && sgteCaracter <= 'Z')
                            || (sgteCaracter >= 'a' && sgteCaracter <= 'z')
                            || (sgteCaracter >= '0' && sgteCaracter <= '9')) {
                        i++;
                        operando += sgteCaracter;
                    } else {
                        break;
                    }
                }

                postfix += operando + " ";

            } else if (c == '(') {
                pilaOperadores.push(c);

            } else if (c == ')') {
                while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != '(') {
                    postfix += pilaOperadores.pop() + " ";
                }
                if (pilaOperadores.isEmpty()) {
                    throw new RuntimeException("Expresión mal formada: falta '('");
                }
                pilaOperadores.pop();

            } else if (esOperador(c)) {

                while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != '('
                        && ((getPrecedencia(pilaOperadores.peek()) > getPrecedencia(c))
                        || (getPrecedencia(pilaOperadores.peek()) == getPrecedencia(c) && c != '^'))) {
                    postfix += pilaOperadores.pop() + " ";
                }
                pilaOperadores.push(c);

            } else if (c == ' ' || c == '\t') {
            } else {
                throw new IllegalArgumentException("Carácter inválido en la expresión: " + c);
            }
            i++;
        }

        while (!pilaOperadores.isEmpty()) {
            char op = pilaOperadores.pop();
            if (op == '(') {
                throw new RuntimeException("Expresión mal formada: sobra '('");
            }
            postfix += op + " ";
        }

        return postfix.trim();
    }

    private int getPrecedencia(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    private boolean esOperador(char c) {
        return c == '+' || c == '-'
                || c == '*' || c == '/'
                || c == '^';
    }
}
