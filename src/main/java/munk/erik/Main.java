package munk.erik;

import munk.erik.ihm.Affichage;
import munk.erik.impl.CalculRencontreSansDoublon;

public class Main {

    public static void main(String[] args) {
        new Affichage(new CalculRencontreSansDoublon());
    }

}