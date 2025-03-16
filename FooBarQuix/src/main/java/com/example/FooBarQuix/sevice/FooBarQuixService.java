package com.example.FooBarQuix.sevice;

import org.springframework.stereotype.Service;

@Service
public class FooBarQuixService {

    public String transformerNombre(int number) {
        if (number < 0 || number > 100) {
            return "Nombre hors de l'intervalle [0, 100]";
        }

        StringBuilder resultat = new StringBuilder();

        // Vérification des divisibilités
        if (number % 3 == 0) {
            resultat.append("FOO");
        }
        if (number % 5 == 0) {
            resultat.append("BAR");
        }

        // Vérification de la présence des chiffres (de gauche à droite)
        String nombreStr = String.valueOf(number);
        for (char ch : nombreStr.toCharArray()) {
            if (ch == '3') {
                resultat.append("FOO");
            } else if (ch == '5') {
                resultat.append("BAR");
            } else if (ch == '7') {
                resultat.append("QUIX");
            }
        }

        return resultat.length() > 0 ? resultat.toString() : nombreStr;
    }
}
