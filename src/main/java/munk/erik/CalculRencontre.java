package munk.erik;

import java.util.List;
import java.util.Map;

public interface CalculRencontre {

    List<Affrontement> determinerVersus(Integer nombreEquipe);

    Map<Integer, List<Affrontement>> determinerRotations(Integer nombreEpreuve, List<Affrontement> affrontements);

}
