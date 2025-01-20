package sk.uniza.fri.world.location.priechody;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.hrac.Odznaky;
import sk.uniza.fri.world.location.Lokacia;

/**
 * 28. 3. 2022 - 12:58
 *
 * Priechod s odznakom je špeciálny priechod, ktorý vráti svoju lokáciu iba v prípade, ak hráč získal špecifický odznak
 *
 * @author Filip Šefčík
 */
public class PriechodSODznakom extends Priechod {

    private Odznaky odznak;

    /**
     * Ako parametre konštruktora sa zadáva lokácia a odznak, ktorý musí mať hráč pri sebe
     * @param lokacia
     * @param odznak
     */
    public PriechodSODznakom(Lokacia lokacia, Odznaky odznak) {
        super(lokacia);
        this.odznak = odznak;
    }

    /**
     * Ak hráč má pri sebe daný odznak, tak mu priechod vráti danú lokáciu, inak nie.
     * @param hrac
     * @return
     */
    @Override
    public Lokacia getLokacia(Hrac hrac) {
        if (hrac.ziskalOdznak(this.odznak)) {
            return super.getLokacia(hrac);
        } else {
            System.out.println(super.getLokacia(hrac).getNazovLokacie() + " je lokácia pre silných trénerov, podmienka vstupu je získať " + this.odznak.getNazovOdznaku());
            return null;
        }
    }
}
