package munk.erik;

import java.util.Objects;

public record Affrontement(Integer gauche, Integer droite) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Affrontement that)) return false;
        return (Objects.equals(gauche, that.gauche) && Objects.equals(droite, that.droite))
                || (Objects.equals(gauche, that.droite) && Objects.equals(droite, that.gauche));
    }

    @Override
    public int hashCode() {
        return Objects.hash(gauche, droite);
    }

    @Override
    public String toString() {
        return gauche + " vs " + droite;
    }


}
