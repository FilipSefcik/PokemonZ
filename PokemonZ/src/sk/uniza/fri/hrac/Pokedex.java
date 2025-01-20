package sk.uniza.fri.hrac;

import sk.uniza.fri.pokemon.DruhPokemona;
import sk.uniza.fri.pokemon.Pokemon;

import java.io.Serializable;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pokédex je zoznam všetkých druhov pokémonov, úlohou hráča je naplniť tento pokédex
 *
 * @author Filip Šefčík
 */
public class Pokedex implements Serializable {

    private DruhPokemona[] druhyPokemonov;

    /**
     * V bezparametrickom konštruktore sa iba vytvorí nové pole, ktoré zachováva druhy pokémonov
     */
    public Pokedex() {
        this.druhyPokemonov = new  DruhPokemona[50];
    }

    /**
     * Skontroluje, či sa druh daného pokémona nachádza v Pokédexe. Ak nie, pridá ho.
     * @param pokemon
     */
    public void vlozDoPokedexu(Pokemon pokemon) {
        DruhPokemona druhPokemona = pokemon.getDruhPokemona();
        int cisloVPokedexe = druhPokemona.getCisloVPokedexe() - 1;
        if (this.druhyPokemonov[cisloVPokedexe] == null) {
            this.druhyPokemonov[cisloVPokedexe] = druhPokemona;
            System.out.println(druhPokemona.getNazovDruhu() + " sa pridal do Pokédexu!");
        }
    }

    /**
     * Vypíše sa celý Pokédex, na miestach, kde je zaznamenaný druh pokémona sa vypíšu o druhu informácie
     */
    public void vypisPokedex() {
        System.out.println("Plnosť Pokédexu " + this.getPocetPokemonovVPokedexe() + "/" + this.druhyPokemonov.length);
        for (DruhPokemona druhPokemona : this.druhyPokemonov) {
            if (druhPokemona != null) {
                druhPokemona.vypisSa();
            } else {
                System.out.println("Prázdny slot.");
            }
        }
    }

    /**
     * @return true/false ak Pokédex je/nie je naplnený všetkými pokémonmi
     */
    public boolean maPlnyPokedex() {
        for (DruhPokemona druhPokemona : this.druhyPokemonov) {
            if (druhPokemona == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return počet zaznamenaných pokémonov v Pokédexe
     */
    public int getPocetPokemonovVPokedexe() {
        int pocet = 0;
        for (DruhPokemona druhPokemona : this.druhyPokemonov) {
            if (druhPokemona != null) {
                pocet++;
            }
        }
        return pocet;
    }
}
