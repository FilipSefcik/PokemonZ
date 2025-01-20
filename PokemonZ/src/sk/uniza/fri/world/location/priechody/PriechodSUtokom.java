package sk.uniza.fri.world.location.priechody;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.world.location.Lokacia;
import sk.uniza.fri.pokemon.DruhUtoku;

/**
 * 28. 3. 2022 - 12:58
 *
 * Podmienka pre získanie lokácie pri priechode s útokom je, aby aspoň jeden pokémon v hráčovej parte vedel stanovaný útok.
 *
 * @author Filip Šefčík
 */
public class PriechodSUtokom extends Priechod {

    private DruhUtoku druhUtoku;
    private String nevyhovujucaHlaska;

    /**
     * Parametrický konštruktor požaduje okrem lokácie aj útok, ktorý je potrebný na prejdenie
     * a aj hlášku, ktorá sa vypíše v prípade ak hráč nevyhovie podmienke
     * @param lokacia
     * @param druhUtoku
     * @param nevyhovujucaHlaska
     */
    public PriechodSUtokom(Lokacia lokacia, DruhUtoku druhUtoku, String nevyhovujucaHlaska) {
        super(lokacia);
        this.druhUtoku = druhUtoku;
        this.nevyhovujucaHlaska = nevyhovujucaHlaska;
    }

    /**
     * V prípade ak jeden z hráčových pokémonov pozná stanovený útok, tak ho priechod pustí ďalej inak nie.
     * @param hrac
     * @return
     */
    @Override
    public Lokacia getLokacia(Hrac hrac) {
        if (hrac.poznaPartaUtok(this.druhUtoku)) {
            return super.getLokacia(hrac);
        } else {
            System.out.println(this.nevyhovujucaHlaska);
            System.out.println("Na prejdenie do lokácie " + super.getLokacia(hrac).getNazovLokacie() + " potrebuje jeden z tvojich pokémonov poznať " + this.druhUtoku.getNazovUtoku());
            return null;
        }
    }
}
