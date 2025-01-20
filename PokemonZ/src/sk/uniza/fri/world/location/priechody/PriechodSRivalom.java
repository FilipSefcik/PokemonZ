package sk.uniza.fri.world.location.priechody;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.npc.Rival;
import sk.uniza.fri.world.location.Lokacia;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pri priechode s rivalom je podmienka pre získanie lokácie je porazenie rivala.
 *
 * @author Filip Šefčík
 */
public class PriechodSRivalom extends Priechod {

    private int subojVPoradi;

    /**
     * V konštruktore sa k lokácii zadáva ako parameter číslo, ktoré indikuje, ktorý súboj s rivalom v poradí to bude
     * @param lokacia
     * @param subojVPoradi
     */
    public PriechodSRivalom(Lokacia lokacia, int subojVPoradi) {
        super(lokacia);
        this.subojVPoradi = subojVPoradi;
    }

    /**
     * Ak hráč už porazil rivala alebo ak ešte nie je čas na ich súboj v tomto mieste, tak hráč môže prejsť ďalej.
     * V inom prípade musí hráč poraziť svojho rivala.
     * @param hrac
     * @return
     */
    @Override
    public Lokacia getLokacia(Hrac hrac) {
        if (Rival.getInstance().getPocetPrehier() == this.subojVPoradi) {
            if (Rival.getInstance().rozhovor(hrac, hrac.getAktualnaLokacia().getNazovLokacie(), "subojSRivalom")) {
                return super.getLokacia(hrac);
            } else {
                return null;
            }
        } else {
            return super.getLokacia(hrac);
        }

    }
}
