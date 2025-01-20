package sk.uniza.fri.interactive.budovy.wild;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.budovy.AbstractBudova;
import sk.uniza.fri.interactive.npc.Trener;
import sk.uniza.fri.pokemon.DruhPokemona;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Potomok budovy, v tejto "budove" môže hráč natrafiť na divokých pokémonov alebo bojovať s trénermi
 *
 * @author Filip Šefčík
 */
public class Divocina extends AbstractBudova {

    private Spawner spawner;
    private ArrayList<Trener> treneri;

    /**
     * Konštrukor inicializuje atribúty a nastaví príkazy, ktoré sa môžu zadávať v tejto "budove"
     * @param nazovBudovy
     */
    public Divocina(String nazovBudovy) {
        super(nazovBudovy, "chytat", "trenovat", "pomoc", "odist");
        this.treneri = new ArrayList<>();
        this.spawner = null;
    }

    /**
     * Metóda pridáva nového trénera so špecifickými pokémonmi
     * @param trener nový tréner
     * @param levelyPokemonov levely tréneroých pokémonov
     * @param pokemoni trénerovi pokémoni
     */
    public void pridajTrenera(Trener trener, int[] levelyPokemonov, Pokemon... pokemoni) {
        trener.nastavPartu(levelyPokemonov, pokemoni);
        this.treneri.add(trener);
    }

    /**
     * Vytvorí spawner, ktorý bude generovať divokých pokémonov
     * Všetky parametre sú parametrami konštruktora Spawner
     * @param minLevel
     * @param maxLevel
     * @param spawnRate
     * @param pokemoni
     */
    public void nastavSpawner(int minLevel, int maxLevel, int[] spawnRate, DruhPokemona... pokemoni) {
        this.spawner = new Spawner(minLevel, maxLevel, spawnRate, pokemoni);
    }


    @Override
    public void odchodZBudovy() {
        System.out.println("Potom ako si zažil a videl krásy, čo ti mohlo miesto " + super.getNazovBudovy() + "poskytnúť, si sa rozhodol vrátiť späť.");

    }

    @Override
    public void uvitaciaHlaska() {
        System.out.println("Rozhodol si sa preskúmať " + super.getNazovBudovy() + ", uvidíme na koho alebo na čo tu natrafíš.");
    }

    @Override
    public boolean spracujInput(Hrac hrac, String[] vstup) {
        switch (super.getCisloPrikazu(vstup[0])) {
            case 1:
                return this.subojSRandomPokemonom(hrac);
            case 2:
                return this.subojSRandomTrenerom(hrac);
            case 3:
                super.vypisPrikazy();
                return true;
            case 4:
                this.odchodZBudovy();
                return false;
            default:
                System.out.println("Neplatný vstup");
                return true;
        }
    }

    /**
     * Spusti sa suboj s nahodnym pokemonom vygenerovanym spawnerom
     * @param hrac
     * @return true/false ak hráč neprehral/prehral
     */
    private boolean subojSRandomPokemonom(Hrac hrac) {
        if (this.spawner != null) {
            Pokemon divokyPokemon = this.spawner.vygenerujPokemona();
            if (divokyPokemon != null) {
                divokyPokemon.kompletOzivPokemona();
                System.out.println("Natrafil si na divokého pokémona! Je to " + divokyPokemon.getPrezyvka() + "!");
                if (!hrac.zacniSuboj(divokyPokemon, hrac.getAktualnaLokacia().getNazovLokacie(), super.getNazovBudovy(), "subojSPokemonom")) {
                    hrac.hracPrehral(true);
                    return false;
                }
            } else {
                System.out.println("Hľadal si hľadal, ale žiadneho pokémona si nenašiel.");
            }
        } else {
            System.out.println("Na mieste " + super.getNazovBudovy() + " sa nenachádzajú žiadny pokémoni.");
        }
        return true;
    }

    /**
     * Spustí sa súboj s náhodným trénerom
     * @param hrac
     * @return true/false ak hráč neprehral/prehral
     */
    private boolean subojSRandomTrenerom(Hrac hrac) {
        if (!this.treneri.isEmpty()) {
            if (this.mozeSaBojovatSTrenermi()) {
                Random generator = new Random();
                Trener oponent = this.treneri.get(generator.nextInt(this.treneri.size()));
                while (oponent.getPocetZapasov() == 0) {
                    oponent = this.treneri.get(generator.nextInt(this.treneri.size()));
                }
                return oponent.rozhovor(hrac, hrac.getAktualnaLokacia().getNazovLokacie(), super.getNazovBudovy(), "subojSTrenerom");
            } else {
                System.out.println("Na tomto mieste si už porazil všetkých trénerov, nájdi silnejších trénerov na inom mieste.");
            }
        } else {
            System.out.println("Nenachádzajú sa tu žiadny tréneri.");
        }
        return true;
    }

    /**
     * @return true/false ak aspoň jeden tréner može/nemôže bojovať proti trénerovi
     */
    private boolean mozeSaBojovatSTrenermi() {
        for (Trener trener : this.treneri) {
            if (trener.getPocetZapasov() > 0) {
                return true;
            }
        }
        return false;
    }
}
