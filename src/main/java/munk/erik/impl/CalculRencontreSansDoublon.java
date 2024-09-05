package munk.erik.impl;

import munk.erik.Affrontement;
import munk.erik.CalculRencontre;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculRencontreSansDoublon implements CalculRencontre {

    @Override
    public List<Affrontement> determinerVersus(Integer nombreEquipe) {
        List<Affrontement> affrontements = new ArrayList<>();
        List<Integer> equipes = IntStream.rangeClosed(1, nombreEquipe).boxed().toList();
        Iterator<Integer> it = equipes.iterator();
        int j = 1;
        while (it.hasNext()) {
            Integer equipe = it.next();
            for (int i = j; i <= equipes.size(); i++) {
                if (equipe != i) {
                    affrontements.add(new Affrontement(equipe, i));
                }
            }
            j++;
        }
        return affrontements.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Map<Integer, List<Affrontement>> determinerRotations(Integer nombreEpreuve, List<Affrontement> affrontements) {
        int affrontementParEpreuve = affrontements.size() / nombreEpreuve;
        int restant = affrontements.size() % nombreEpreuve;
        HashMap<Integer, List<Affrontement>> res = new HashMap<>();

        for (int i = 1; i <= nombreEpreuve; i++) {
            int extra = i <= restant ? 1 : 0;
            for (int j = 0; j < affrontementParEpreuve + extra; j++) {
                List<Affrontement> subSet = res.get(i);
                subSet = subSet == null ? new ArrayList<>() : subSet;
                if (subSet.size() <= affrontementParEpreuve + extra) {
                    subSet.add(affrontements.removeFirst());
                    res.put(i, subSet);
                }
            }
        }

        return res;
    }
}
