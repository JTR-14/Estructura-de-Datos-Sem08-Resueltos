/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InfijaPosfija_Propuestos;

import Pila.*;

public class EvaluadorAvanzado {

    private Pila<Character> pilaOperadores;
    private Pila<Double> pilaOperandos;

    public EvaluadorAvanzado() {
        this.pilaOperadores = new Pila<>();
        this.pilaOperandos = new Pila<>();
    }

    public String convertirAPostfija(String expresionInfija) {

        pilaOperadores.removeAll();
        String postfix = "";

        int i = 0;

        while (i < expresionInfija.length()) {

            char c = expresionInfija.charAt(i);

            if (Character.isDigit(c)) {

                String operando = "";
                operando += c;

                while (i + 1 < expresionInfija.length()
                        && (Character.isDigit(expresionInfija.charAt(i + 1))
                        || expresionInfija.charAt(i + 1) == '.')) {
                    i++;
                    operando += expresionInfija.charAt(i);
                }

                postfix += operando + " ";

            } else if (c == '(' || c == '[' || c == '{') {
                pilaOperadores.push(c);

            } else if (c == ')' || c == ']' || c == '}') {
                char pareja = obtenerPareja(c);

                while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != pareja) {
                    postfix += pilaOperadores.pop() + " ";
                }

                if (pilaOperadores.isEmpty()) {
                    throw new RuntimeException("Expresión mal formada: falta '" + pareja + "'");
                }

                pilaOperadores.pop();

            } else if (esOperador(c)) {

                while (!pilaOperadores.isEmpty() && !esApertura(pilaOperadores.peek())
                        && ((getPrecedencia(pilaOperadores.peek()) > getPrecedencia(c))
                        || (getPrecedencia(pilaOperadores.peek()) == getPrecedencia(c) && c != '^'))) {
                    postfix += pilaOperadores.pop() + " ";
                }
                pilaOperadores.push(c);

            } else if (Character.isWhitespace(c)) {

            } else {
                throw new IllegalArgumentException("Carácter inválido en la expresión: " + c);
            }

            i++;

        }

        while (!pilaOperadores.isEmpty()) {
            char op = pilaOperadores.pop();
            if (esApertura(op)) {
                throw new RuntimeException("Expresión mal formada: sobra '" + op + "'");
            }
            postfix += op + " ";
        }

        return postfix.trim();
    }

    public double evaluarPostfija(String expresionPostfija) {

        pilaOperandos.removeAll();

        String[] tokens = expresionPostfija.split("\\s+");

        for (String token : tokens) {

            if (esOperador(token.charAt(0)) && token.length() == 1) {
                try {
                    double b = pilaOperandos.pop();
                    double a = pilaOperandos.pop();

                    double resultado = realizarOperacion(a, b, token.charAt(0));

                    pilaOperandos.push(resultado);

                } catch (Exception e) {
                    throw new RuntimeException("Expresión postfija mal formada: Faltan operandos para '" + token + "'");
                }

            } else {
                try {
                    double numero = Double.parseDouble(token);
                    pilaOperandos.push(numero);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Token inválido en postfija: '" + token + "'");
                }
            }
        }

        double resultadoFinal = pilaOperandos.pop();

        if (!pilaOperandos.isEmpty()) {
            throw new RuntimeException("Expresión postfija mal formada: Sobran operandos");
        }

        return resultadoFinal;
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
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean esApertura(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private char obtenerPareja(char c) {
        switch (c) {
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return '\0';
        }
    }

    private double realizarOperacion(double a, double b, char operador) {
        switch (operador) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Error: División por cero");
                }
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }
}
