package sk.uniza.fri.items.tm;

import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.pokemon.DruhUtoku;
import sk.uniza.fri.pokemon.Pokemon;
import sk.uniza.fri.pokemon.Utok;

/**
 * 28. 3. 2022 - 12:58
 *
 * TM dokáže naučiť pokémona nový útok
 *
 * @author Filip Šefčík
 */
public class TM extends AbstractItem {

    private Utok utok;

    /**
     * V parametrickom konštruktore sa zadáva názov TM a druh útoku, ktorý bude tento predmet učiť
     * @param nazovPredmetu
     * @param druhUtoku
     */
    public TM(String nazovPredmetu, DruhUtoku druhUtoku) {
        super(nazovPredmetu, druhUtoku.getNazovUtoku());
        this.utok = new Utok(druhUtoku);
    }

    /**
     * Tm vyvolá metódu pokémona aby sa naučil útok, ktorý Tm ponúka
     * @param pokemon, na ktorého sa použije item
     * @return true/false ak sa pokémon útok naučil, nenaučil
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        return pokemon.naucUtokHracom(this.utok);
    }

    /**
     * Vypíše informáciu o TM v tvare:
     * TM02: pomocou tohto TM sa pokémon dokáže naučiť útok Karate chop
     */
    @Override
    public void vypisSa() {
        System.out.println(super.getNazovPredmetu() + ": pomocou tohto TM sa pokémon dokáže naučiť útok " + super.getPopisPredmetu());
    }
}
