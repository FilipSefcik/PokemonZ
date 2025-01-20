package sk.uniza.fri.items.potion;

import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pomocou Potionu môže hráč obnoviť buď životy alebo počet použití útokov
 *
 * @author Filip Šefčík
 */
public abstract class AbstractPotion extends AbstractItem {

    private int liecivaSila;

    /**
     * Potion sa od obyčajného predmetu líši tým, že má svoju liečivú silu, ktorá sa v konštrukotre zadáva ako parameter
     * @param nazovPredmetu
     * @param popisPredmetu
     * @param liecivaSila koľko daný Potion obnoví hp alebo počet použití
     */
    public AbstractPotion(String nazovPredmetu, String popisPredmetu, int liecivaSila) {
        super(nazovPredmetu, popisPredmetu);
        this.liecivaSila = liecivaSila;
    }

    /**
     * @return koľko daný Potion obnoví hp alebo počet použití
     */
    public int getLiecivaSila() {
        return this.liecivaSila;
    }

    /**
     * Pri Potione sa rozlišuje či pridáva životy alebo počet použití,
     * preto musia túto metódu spracovať potomkovia
     * @param pokemon, na ktorého sa použije item
     * @return
     */
    @Override
    public abstract boolean pouziPredmet(Pokemon pokemon);

    /**
     * Vypíšu sa informácie o Potione
     */
    @Override
    public void vypisSa() {
        System.out.print(super.getNazovPredmetu() + ": " + super.getPopisPredmetu() + ", sila: " + this.getLiecivaSila());
    }
}
