package sk.uniza.fri.items.revive;

import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Revive je schopný oživiť pokémona ak nie je schopný boja
 *
 * @author Filip Šefčík
 */
public abstract class AbstractRevive extends AbstractItem {

    /**
     * Konštruktor je rovnaký ako predka
     * @param nazovPredmetu
     * @param popisPredmetu
     */
    public AbstractRevive(String nazovPredmetu, String popisPredmetu) {
        super(nazovPredmetu, popisPredmetu);
    }

    /**
     * @param pokemon, na ktorého sa použije item
     * @return true/false ak sa Revive použil/nepoužil
     */
    @Override
    public abstract boolean pouziPredmet(Pokemon pokemon);

    /**
     * Vypíšu sa informácie o predmete
     */
    @Override
    public void vypisSa() {
        System.out.println(super.getNazovPredmetu() + ": " + super.getPopisPredmetu());
    }
}
