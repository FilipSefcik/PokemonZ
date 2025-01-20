package sk.uniza.fri.interactive.npc;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.items.AbstractItem;

/**
 * 28. 3. 2022 - 12:58
 *
 * Npc, ktoré pri oslovení daruje hráčovi svoj predmet, ktorý má pri sebe
 *
 * @author Filip Šefčík
 */
public class StedreNpc extends Npc {

    private AbstractItem darcek;
    private String darovaciaHlaska;

    /**
     * V konštruktore sa vytvorí predok a nastaví darček a darovacie hláška z parametrov
     * @param meno
     * @param darcek
     */
    public StedreNpc(String meno, AbstractItem darcek) {
        super(meno);
        this.darovaciaHlaska = ZoznamHlasok.getInstance().dajRandomDarovaciuHlasku();
        this.darcek = darcek;
    }

    /**
     * Setter pre atribút darcek
     * @param darcek
     */
    public void setDarcek(AbstractItem darcek) {
        this.darcek = darcek;
    }

    /**
     * Setter pre atribút darovaciaHlaska
     * @param darovaciaHlaska
     */
    public void setDarovaciaHlaska(String darovaciaHlaska) {
        this.darovaciaHlaska = darovaciaHlaska;
    }

    /**
     * Ak toto Npc má pri sebe svoj predmet, daruje ho hráčovi, ak už ho daroval, bude omielať svoju defaultnú hlášku
     * @param hrac
     * @param cesta
     * @return
     */
    @Override
    public boolean rozhovor(Hrac hrac, String... cesta) {
        if (this.darcek != null) {
            System.out.println(this.darovaciaHlaska);
            if (hrac.vlozDoInventara(this.darcek)) {
                System.out.println("Získal si " + this.darcek.getNazovPredmetu());
                this.darcek = null;
                return true;
            } else {
                return false;
            }
        } else {
            return super.rozhovor(hrac);
        }
    }
}
