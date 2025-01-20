package sk.uniza.fri.items;

import sk.uniza.fri.pokemon.Pokemon;

import java.io.Serializable;

/**
 * 28. 3. 2022 - 12:58
 *
 * Predmet, ktorý môže hráč použiť na rôzne účely
 *
 * @author Filip Šefčík
 */
public abstract class AbstractItem implements Serializable {

    private String nazovPredmetu;
    private String popisPredmetu;

    /**
     * Parametrický konštruktor potrebuje dva String-y, prvý je názov predmetu
     * a druhý je popis samotného predmetu
     * @param nazovPredmetu
     * @param popisPredmetu
     */
    public AbstractItem(String nazovPredmetu, String popisPredmetu) {
        this.nazovPredmetu = nazovPredmetu;
        this.popisPredmetu = popisPredmetu;
    }

    /**
     * @return názov predmetu
     */
    public String getNazovPredmetu() {
        return this.nazovPredmetu;
    }

    /**
     * @return popis predmetu
     */
    public String getPopisPredmetu() {
        return this.popisPredmetu;
    }

    /**
     * Predmet sa dá použiť iba na pokémonov, hráč z nich nič nemá, preto parametrom je pokémon
     * @param pokemon, na ktorého sa použije item
     * @return true/false ak sa predmet úspešne/neúspešne použil
     */
    public abstract boolean pouziPredmet(Pokemon pokemon);

    /**
     * Informácie o predmete sa vypíšu
     */
    public abstract void vypisSa();
}
