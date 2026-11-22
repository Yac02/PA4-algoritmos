/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * PA4 - Actividad 1: Recursividad aplicada
 * @author Jack
 */
public class Actividad1 {

    static int calcularDistancia(String[] tramos, int[] distancias, int indice) {
        // Caso base y condicion de finalizacion
        if (indice == distancias.length) {
            return 0;
        }

        System.out.println("Tramo " + (indice + 1) + ": " + tramos[indice] + " (" + distancias[indice] + " m)");

        // Caso recursivo: suma el tramo actual + el resultado del resto de la ruta
        return distancias[indice] + calcularDistancia(tramos, distancias, indice + 1);
    }

    public static void main(String[] args) {
        String[] tramos = {
            "Entrada Principal -> Biblioteca",
            "Biblioteca -> Pabellon A",
            "Pabellon A -> Cafeteria",
            "Cafeteria -> Laboratorios"
        };
        int[] distancias = {120, 85, 60, 140};

        System.out.println("--- Analisis recursivo de ruta en el campus ---");
        int total = calcularDistancia(tramos, distancias, 0);
        System.out.println("Distancia total del recorrido: " + total + " metros");
    }
}