package sk.uniza.fri.interactive.budovy.pokecentrum;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.game.essentials.Komunikator;
import sk.uniza.fri.game.essentials.Prikazy;
import sk.uniza.fri.pokemon.Pokemon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 28. 3. 2022 - 12:58
 *
 * PC je "neobmedzene" veľké úložisko pokémonov, kde si hráč môže vložiť svojich pokémonov alebo si ich vybrať
 *
 * @author Filip Šefčík
 */
public class PC implements Serializable {
    
    private static PC instancia;
    private ArrayList<Pokemon> pokemoni;
    private Prikazy prikazy;

    /**
     * PC je singleton, preto má metódu getInstance()
     * @return inštanciu triedy PC
     */
    public static PC getInstance() {
        if (instancia == null) {
            instancia = new PC();
        }
        return instancia;
    }

    /**
     * V bezparametrickom konštruktore sa vytvorí ArrayList pokémonov a skupina príkzov používaných pre prácu s PC
     */
    private PC() {
        this.pokemoni = new ArrayList<>();
        this.prikazy = new Prikazy("info", "vloz", "vyber", "pomoc", "vypnut");
    }

    /**
     * Táto metóda sa používa pri načítaní hry zo súboru, načíta sa inštancia v súbore do inštancie
     * @param instance
     */
    public static void setInstance(PC instance) {
        instancia = instance;
    }

    /**
     * Oživí pokémona a pridá ho do zoznamu pokémonov
     * @param novyPokemon
     */
    public void pridajPokemona(Pokemon novyPokemon) {
        novyPokemon.kompletOzivPokemona();
        this.pokemoni.add(novyPokemon);
    }

    /**
     * PC ponúkne hráčovi príkazy na prácu s PC a pýta vstup od neho
     * @param hrac
     * @param nazovLokacie
     */
    public void pracujSPC(Hrac hrac, String nazovLokacie) {
        boolean spravnostVstupu;
        this.prikazy.vypisPrikazy();
        do {
            String[] input = this.prikazy.nacitajInput(hrac.getAktualnaLokacia().getNazovLokacie(), nazovLokacie, "PC");
            spravnostVstupu = this.spracujInput(hrac, input);
        } while (spravnostVstupu);
    }

    /**
     * PC spracuje vstup, ktorý hráč zadal
     * @param hrac
     * @param input
     * @return
     */
    private boolean spracujInput(Hrac hrac, String[] input) {
        switch (this.prikazy.getCisloPrikazu(input[0])) {
            case 1:
                this.zistiInfo();
                return true;
            case 2:
                this.vlozPokemona(hrac);
                return true;
            case 3:
                this.vyberPokemona(hrac);
                return true;
            case 4:
                this.prikazy.vypisPrikazy();
                return true;
            case 5:
                System.out.println("PC sa vypína.");
                return false;
            default:
                System.out.println("Neznámy príkaz.");
                return true;
        }
    }

    /**
     * Hráč si môže vybrať pokémona z PC a vložiť si ho do party, iba ak má v parte miesto a ak nie je PC prázdne
     * @param hrac
     */
    private void vyberPokemona(Hrac hrac) {
        if (!this.pokemoni.isEmpty()) {
            if (hrac.getPocetPokemonovVParte() < hrac.getVELKOST_PARTY()) {
                int indexPokemona = this.ziskajIndexPokemona();
                Pokemon pokemon = this.pokemoni.get(indexPokemona);
                hrac.vlozDoParty(pokemon);
                this.pokemoni.remove(indexPokemona);
            } else {
                System.out.println("V parte máte príliš veľa pokémonov.");
            }
        } else {
            System.out.println("V PC sa nenachádza žiaden pokémon.");
        }
    }

    /**
     * Vloží pokémona z hráčovej party do PC, ak má v parte viac ako jedného pokémona
     * @param hrac
     */
    private void vlozPokemona(Hrac hrac) {
        if (hrac.getPocetPokemonovVParte() > 1) {
            int indexPokemona = hrac.ziskajIndexPokemona();
            Pokemon pokemon = hrac.dajPokemona(indexPokemona);
            this.pridajPokemona(pokemon);
            hrac.odstranPokemona(indexPokemona);
            System.out.println(pokemon.getPrezyvka() + " sa úspešne vložil do PC.");
        } else if (hrac.getPocetPokemonovVParte() == 1) {
            System.out.println("Pri sebe musíte mať vždy minimálne jedného pokémona!");
        } else {
            System.out.println("Nemáte žiadnych pokémonov.");
        }
    }

    /**
     * Vypíšu sa podrobné informácie o vybranom pokémonovi
     */
    private void zistiInfo() {
        if (!this.pokemoni.isEmpty()) {
            int indexPokemona = this.ziskajIndexPokemona();
            this.pokemoni.get(indexPokemona).vypisSaKompletne();
        } else {
            System.out.println("V PC nemáte žiadnych pokémonov.");
        }

    }

    /**
     * Vyvolá sa metóda ziskajIndex() triedy Komunikator
     * @return index pokémona v PC
     */
    private int ziskajIndexPokemona() {
        String otazka = "Vyberte index pokémona.";
        String chyba = "Nesprávny vstup";
        String zlyIndex = "Musíte zadať číslo od 1 do " + this.pokemoni.size();
        return Komunikator.ziskajIndex(this.pokemoni.size(), otazka, chyba, zlyIndex, true, true, this);
    }

    /**
     * Vypíše všetkých pokémonov v PC
     */
    public void vypisPokemonov() {
        if (!this.pokemoni.isEmpty()) {
            for (int i = 0; i < this.pokemoni.size(); i++) {
                System.out.print((i + 1) + " ");
                this.pokemoni.get(i).vypisZakladneInfo();
            }
        } else {
            System.out.println("V PC nemáte žiadnych pokémonov.");
        }
    }
}
