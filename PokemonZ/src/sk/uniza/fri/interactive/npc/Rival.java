package sk.uniza.fri.interactive.npc;

import sk.uniza.fri.pokemon.DruhPokemona;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.ArrayList;

/**
 * 28. 3. 2022 - 12:58
 *
 * Rival je špecifický typ trénera, na ktorého môže hráč náhodne natrafiť počas jeho hry, pri každom strete rivala a hráča nastane k boju.
 *
 * @author Filip Šefčík
 */
public class Rival extends Trener {

    private static Rival instance;
    private ArrayList<Pokemon> rivalovaParta;
    private Pokemon[] rivaloviPokemoni = new Pokemon[] {new Pokemon(DruhPokemona.PIDGEY), new Pokemon(DruhPokemona.GEODUDE), new Pokemon(DruhPokemona.ABRA)};
    private int pocetPrehier;

    /**
     * Skrz toho, že Rival je singleton, tak používa metódu getInstance()
     * @return inštanciu triedy Rival
     */
    public static Rival getInstance() {
        if (instance == null) {
            instance = new Rival();
        }
        return instance;
    }

    /**
     * V konštruktore sa nastavia rivalovi špecifické hlášky a inicializujú atribúty.
     */
    private Rival() {
        super("Zed", 4);
        super.setDefaultHlaska("Si dobrý protivník, ale ja ťa porazím, budem ďalej trénovať!");
        super.setPrveStretnutieHlaska("Konečne sme sa dočkali, poďme začať naše dobrodružstvo súbojom!");
        super.setRematchHlaska("Teraz určite vyhrám, trénoval som najviac ako sa dá!");
        super.setVitaznaHlaska("Nič si z toho nerob, o víťazovi bolo rozhodnuté už dávno.");
        super.setPorazenaHlaska("To nie je možné! Nabudúce budem silnejší, len uvidíš!");
        this.pocetPrehier = -1;
        this.rivalovaParta = new ArrayList<>();
        for (Pokemon pokemon : this.rivaloviPokemoni) {
            pokemon.setNieJeDivoky();
        }
    }

    /**
     * Táto metóda sa používa pri načítaní hry zo súboru, načíta sa inštancia v súbore do inštancie
     * @param instance
     */
    public static void setInstance(Rival instance) {
        Rival.instance = instance;
    }

    /**
     * @return počet zápasov, ktoré rival prehral proti hráčovi
     */
    public int getPocetPrehier() {
        return this.pocetPrehier;
    }

    /**
     * Pridá sa počet prehier, pridá sa rivalovi nový pokémon a vyvolá sa tá istá metóda v predkovi
     */
    @Override
    public void pripravSaNaOdvetu() {
        this.pocetPrehier++;
        this.pridajRivaloviPokemona(this.rivaloviPokemoni[this.pocetPrehier]);
        super.pripravSaNaOdvetu();
    }

    /**
     * Rivalovi sa pridá ďalší pokémon z poľa pokémonov, ktoré má preddefinované
     * @param pokemon
     */
    public void pridajRivaloviPokemona(Pokemon pokemon) {
        if (!this.rivalovaParta.isEmpty()) {
            pokemon.zvysLevelOHodnotu(this.rivalovaParta.get(0).getLevel(), true);
        }
        this.rivalovaParta.add(pokemon);
        super.setTrenerovaParta(this.rivalovaParta);
    }
}
