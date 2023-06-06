package efs.task.collections.game;

import efs.task.collections.data.DataProvider;
import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameLobby {

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby() {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList() {
        return playableTownsWithHeroesList;
    }

    //TODO Dodać miasta i odpowiadających im bohaterów z DLC gry do mapy dostępnych
    // miast - playableTownsWithHeroesList, tylko jeżeli jeszcze się na niej nie znajdują.
    public void enableDLC() {
        List<Town> towns_dlc = dataProvider.getDLCTownsList();
        List<Hero> heroes_dlc = List.copyOf(dataProvider.getDLCHeroesSet());

        for (Town town : towns_dlc) {
            if (!playableTownsWithHeroesList.containsKey(town)) {
                playableTownsWithHeroesList.put(town, heroes_dlc);
            }
        }
    }


    //TODO Usunąć miasta i odpowiadających im bohaterów z DLC gry z mapy dostępnych
    // miast - playableTownsWithHeroesList.
    public void disableDLC() {
        List<Town> towns_dlc = dataProvider.getDLCTownsList();

        for (Town town : towns_dlc) {
            if (playableTownsWithHeroesList.containsKey(town)) {
                playableTownsWithHeroesList.remove(town);
            }
        }
    }

    // TODO Sprawdza czy mapa playableCharactersByTown zawiera dane miasto.
    //  Jeśli tak zwróć listę bohaterów z tego miasta.
    //  Jeśli nie rzuć wyjątek NoSuchElementException z wiadomością NO_SUCH_TOWN + town.getName()
    public List<Hero> getHeroesFromTown(Town town) {
        if (playableTownsWithHeroesList.containsKey(town)) {
            return playableTownsWithHeroesList.get(town);
        }
        else {
            throw new NoSuchElementException(NO_SUCH_TOWN + town.getTownName());
        }
    }

    // TODO Metoda powinna zwracać mapę miast w kolejności alfabetycznej z odpowiadającymi im bohaterami.
    //  Każde z miast charakteryzuje się dwoma klasami bohaterów dostępnymi dla tego miasta - Town.startingHeroClass.
    //  Mapa ma zawierać pare klucz-wartość gdzie klucz: miasto, wartość: lista bohaterów;
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> towns_left, Set<Hero> heroes_left) {
        Map<Town, List<Hero>> map = new TreeMap<>(Comparator.comparing(Town::getTownName));

        for(var town : towns_left) {
            List<Hero> list_of_heros = new ArrayList<>();

            for(var hero : heroes_left) {
                if(town.getStartingHeroClasses().contains(hero.getHeroClass())) {
                    list_of_heros.add(hero);
                }
            }

            map.put(town, list_of_heros);
        }


        return map;
    }

    //TODO metoda zwraca wybranego bohatera na podstawie miasta z którego pochodzi i imienia.
    // Jeżeli istnieje usuwa go z listy dostępnych bohaterów w danym mieście i zwraca bohatera.
    // Jeżeli nie ma go na liście dostępnych bohaterów rzuca NoSuchElementException z wiadomością HERO_NOT_FOUND + name
    public Hero selectHeroByName(Town town_of_hero, String name_of_hero) {
        for (var hero : playableTownsWithHeroesList.get(town_of_hero)) {
            if (hero.getName().equals(name_of_hero)) {
                playableTownsWithHeroesList.get(town_of_hero).remove(hero);
                return hero;
            }
        }
        throw new NoSuchElementException(HERO_NOT_FOUND + name_of_hero);
    }
}