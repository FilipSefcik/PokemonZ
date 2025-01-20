package sk.uniza.fri.world.location.priechody;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.world.location.Lokacia;

import java.io.Serializable;

/**
 * 28. 3. 2022 - 12:58
 *
 * Trieda Priechod uchováva lokáciu, do ktorej sa môže hráč presunúť
 * Základný nápad triedy Priechod bol inšpirovaný od triedy Dvere z cvičení predmetu Informatika 2
 *
 * @author Filip Šefčík
 */
public class Priechod implements Serializable {
    private Lokacia lokacia;

    /**
     * V konštruktore sa ako parameter zadáva lokácia, ktorú tento priechod prepája
     * @param lokacia
     */
    public Priechod(Lokacia lokacia) {
        this.lokacia = lokacia;
    }

    /**
     * @param hrac
     * @return lokácia
     */
    public Lokacia getLokacia(Hrac hrac) {
        return this.lokacia;
    }
}
