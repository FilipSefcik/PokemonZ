package sk.uniza.fri.interactive.budovy.wild;

import sk.uniza.fri.pokemon.DruhPokemona;
import sk.uniza.fri.pokemon.Pokemon;

import java.io.Serializable;
import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Spawner vytvára randomných pokémonv z množstva, ktoré má určené s levelom, ktorého rozmedzie má tiež stanovené
 *
 * @author Filip Šefčík
 */
public class Spawner implements Serializable {

    private DruhPokemona[] pokemoni;
    private int[] spawnRate;
    private int vzorka;
    private int minLevel;
    private int maxLevel;

    /**
     * V konštruktore sa inicializujú atribúty na hodnoty stanovené parametrami
     * @param minLevel minimálny level, ktorý pokémon môže nadobudnúť
     * @param maxLevel maximálny level, ktorý pokémon môže nadobudnúť
     * @param spawnRate šanca na to, že sa z celkového množstva pokémonov spawne ten jeden
     * @param pokemoni druhy pokémonov, ktoré sa môžu spawnovať
     */
    public Spawner(int minLevel, int maxLevel, int[] spawnRate, DruhPokemona... pokemoni) {
        this.pokemoni = pokemoni;
        this.spawnRate = spawnRate;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel + 1;
        this.vzorka = this.getVelkostVzorky();
    }

    /**
     * Vyráta sa celková vzorka, z ktorej majú pokémoni šancu sa spawnúť.
     * Napríklad ak by sme zadali dvoch pokémonov so šancami 10 a 10 neznamená to že obaja majú 10% šancu sa spawnúť
     * Znamená to to, že sa z celkovej vzroky spawne 10 takých a 10 takých pokémonov, čiže šanca je 50 na 50
     * V skratke sa spraví kumulatívna hodnota všetkých šancí na spawn
     * @return celková veľkosť vzorky
     */
    private int getVelkostVzorky() {
        int velkostVzorky = 0;
        for (int spawnRateJednotlivca : this.spawnRate) {
            velkostVzorky += spawnRateJednotlivca;
        }
        return velkostVzorky;
    }

    /**
     * Vygeneruje náhodného pokémona a nastaví mu náhodný level
     * @return vygenerovaný pokémon
     */
    public Pokemon vygenerujPokemona() {
        Random generator = new Random();
        int randHodnota = generator.nextInt(this.vzorka);
        int spawnRateJednotlivca = 0;
        for (int i = 0; i < this.pokemoni.length; i++) {
            spawnRateJednotlivca += this.spawnRate[i];
            if (randHodnota < spawnRateJednotlivca && this.pokemoni[i] != null) {
                int level = generator.nextInt(this.minLevel, this.maxLevel);
                Pokemon vygenerovanyPokemon = new Pokemon(this.pokemoni[i]);
                vygenerovanyPokemon.zvysLevelOHodnotu(level, false);
                return vygenerovanyPokemon;
            }
        }
        return null;
    }
}
