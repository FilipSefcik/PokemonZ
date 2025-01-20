package sk.uniza.fri.items.pokeball;

import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pomocou predmetu Pokéball môže hráč chytiť nového pokémona
 *
 * @author Filip Šefčík
 */
public abstract class AbstractPokeball extends AbstractItem {

    private int catchRate;

    /**
     * Ako parameter naviac k predkovému konštruktoru má konštruktor Pokéballov
     * pridaný parameter catchRate, ktorý určuje, koľko percentnú úspešosť má daný Pokéball
     * @param nazovPredmetu
     * @param popisPredmetu
     * @param catchRate
     */
    public AbstractPokeball(String nazovPredmetu, String popisPredmetu, int catchRate) {
        super(nazovPredmetu, popisPredmetu);
        this.catchRate = catchRate;
    }

    /**
     * Pokéball sa použije iba na pokémona, ktorý je divoký, hráč nemôže chytiť pokémona, ktorý patrí niekomu inému
     * Pri použití Pokéballu sa pomocou náhody určí, či bol hod úspešný alebo nie
     * @param pokemon, na ktorého sa použije Pokéball
     * @return true/false ak sa pokémon chytil/nechytil
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (pokemon.jeDivoky()) {
            Random generator = new Random();
            int sancaChytenia = this.vyratajCelkovuSancu(pokemon);
            if (sancaChytenia > generator.nextInt(100)) {
                System.out.println("Výborne! Podarilo sa ti chytiť " + pokemon.getPrezyvka() + "!");
                return true;
            } else {
                System.out.println("Skoro! " + pokemon.getPrezyvka() + " vyskočil z " + super.getNazovPredmetu() + "-u");
            }
        } else {
            System.out.println("Druhým ľuďom sa nemôžu kradnúť pokémoni!!");
        }
        return false;
    }

    /**
     * Celková šanca závisí aj od stavu samotného pokémona
     * Čím menej hp má pokémon, tým väčšia šanca, že sa chytí
     * @param pokemon
     * @return celková šanca, akú má Pokéball na úspešné chytenie
     */
    public int vyratajCelkovuSancu(Pokemon pokemon) {
        int bonusSanca = 10 - ((int)pokemon.vyratajPercentaHp() / 10);
        return this.catchRate + bonusSanca;
    }

    /**
     * Vypíše sa pokébal v tvare napr.:
     * Pokéball: s týmto predmetom môžeš chytať divokých pokémonov úspešnosť: 60 %
     */
    @Override
    public void vypisSa() {
        System.out.println(super.getNazovPredmetu() + ": " + super.getPopisPredmetu() + " úspešnosť: " + this.catchRate + " %");
    }

}
