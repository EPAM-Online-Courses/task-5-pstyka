package efs.task.collections.data;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.List;
import java.util.Set;

public class DataProvider {

    public static final String DATA_SEPARATOR = ",";

    // TODO Utwórz listę miast na podstawie tablicy Data.baseTownsArray.
    //  Tablica zawiera String zawierający nazwę miasta oraz dwie klasy bohatera związane z tym miastem oddzielone przecinkami.
    //  Korzystając z funkcji split() oraz stałej DATA_SEPARATOR utwórz listę obiektów klasy efs.task.collections.entities.Town.
    //  Funkcja zwraca listę obiektów typu Town ze wszystkimi dziewięcioma podstawowymi miastami.
    public List<Town> getTownsList() {
        List<Town> towns = new ArrayList<>();
        for(var town : Data.baseTownsArray) {
            var name_of_town = town.split(DATA_SEPARATOR)[0].trim();
            var class_one = town.split(DATA_SEPARATOR)[1].trim();
            var class_two = town.split(DATA_SEPARATOR)[2].trim();
            List<String> list_of_classes = List.of(firstClass, secondClass);

            towns.add(new Town(name_of_town, list_of_classes));
        }
        return towns;
    }

    //TODO Analogicznie do getTownsList utwórz listę miast na podstawie tablicy Data.DLCTownsArray
    public List<Town> getDLCTownsList() {

            List<Town> towns = new ArrayList<>();

            for(var town : Data.dlcTownsArray) {
                var name_of_town = town.split(DATA_SEPARATOR)[0].trim();
                var class_one = town.split(DATA_SEPARATOR)[1].trim();
                var class_two = town.split(DATA_SEPARATOR)[2].trim();
                List<String> list_of_classes = List.of(firstClass, secondClass);

                towns.add(new Town(name_of_town, list_of_classes));
            }
            return towns;
    }

    //TODO Na podstawie tablicy Data.baseCharactersArray utworzyć listę bohaterów dostępnych w grze.
    // Tablica Data.baseCharactersArray zawiera oddzielone przecinkiem imie bohatera, klasę bohatera.
    // Korzystając z funkcji split() oraz DATA_SEPARATOR utwórz listę unikalnych obiektów efs.task.collections.entities.Hero.
    // UWAGA w Data.baseCharactersArray niektórzy bohaterowie powtarzają się, do porównania bohaterów używamy zarówno imie jak i jego klasę;
    public Set<Hero> getHeroesSet() {

        Set<Hero> heroesSet = new HashSet<>();
        for (String data_of_hero : Data.baseCharactersArray) {
            String[] data_of_hero_arr = heroData.split(DATA_SEPARATOR);
            if (heroDataArray.length == 2) {
                String name_of_hero = heroDataArray[0].trim();
                String class_of_hero = heroDataArray[1].trim();
                Hero hero = new Hero(name_of_hero, class_of_hero);
                heroesSet.add(hero);
            }
        }
        return heroesSet;
    }

    //TODO Analogicznie do getHeroesSet utwórz listę bohaterów na podstawie tablicy Data.DLCCharactersArray
    public Set<Hero> getDLCHeroesSet() {
        public Set<Hero> getDLCHeroesSet() {
            Set<Hero> heroes = new HashSet<>();
            for(var hero : Data.dlcCharactersArray) {
                var name_of_hero = hero.split(DATA_SEPARATOR)[0].trim();
                var class_of_hero = hero.split(DATA_SEPARATOR)[1].trim();

                if(heroes.contains(new Hero(name_of_hero, class_of_hero))) {
                    continue;
                }
                heroes.add(new Hero(name_of_hero, class_of_hero));
            }
            return heroes;
        }
}
