package efs.task.collections.entity;

import java.util.List;

public class Town {
    private String townName;
    private List<String> startingHeroClasses;

    public Town(String townName, List<String> startingHeroesClass) {
        this.townName = townName;
        startingHeroClasses = startingHeroesClass;
    }

    public String getTownName() {
        return townName;
    }

    public List<String> getStartingHeroClasses() {
        return startingHeroClasses;
    }

    //TODO implementacja metody equal porównująca obiekty Town na podstawie tylko townName.
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Town)) {
            return false;
        }
        Town town = (Town) o;
        boolean townNameEquals = (this.getTownName() == null && town.getTownName() == null) || (this.getTownName() != null && this.getTownName().equals(town.getTownName()));
        return townNameEquals;
    }

    //TODO implementacja metody equal biorąca pod uwagę tylko townName.
    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + (townName == null ? 0 : townName.hashCode());
        return result;
    }
    @Override
    public String toString() {
        return "Miasto :" + townName;
    }

    @Override
    public String toString() {
        return "Miasto :" + townName;
    }
}
