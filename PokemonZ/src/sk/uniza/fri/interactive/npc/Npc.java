package sk.uniza.fri.interactive.npc;

import sk.uniza.fri.hrac.Hrac;

import java.io.Serializable;

/**
 * 28. 3. 2022 - 12:58
 *
 * Jednoduché Npc ktoré po oslovení povie svoju hlášku
 *
 * @author fifos
 */
public class Npc implements Serializable {

    private String meno;
    private String defaultHlaska;

    /**
     * Npc sa v konštruktore nastaví jeho meno a hláška, ktorú bude vravieť
     * @param meno
     */
    public Npc(String meno) {
        this.meno = meno;
        this.defaultHlaska = ZoznamHlasok.getInstance().dajRandomDefaultHlasku();
    }

    /**
     * Nastaví defaultnú hlášku, na danú hodnotu
     * @param defaultHlaska
     */
    public void setDefaultHlaska(String defaultHlaska) {
        this.defaultHlaska = defaultHlaska;
    }

    /**
     * @return meno Npc
     */
    public String getMeno() {
        return this.meno;
    }

    /**
     * Npc povie svoju hlášku, potomkovia tejto triedy už majú k tomu pridané svoje špecifické postupy
     * @param hrac
     * @param cesta
     * @return
     */
    public boolean rozhovor(Hrac hrac, String... cesta) {
        System.out.println(this.defaultHlaska);
        return true;
    }
}
