package sk.uniza.fri.interactive.budovy;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.game.essentials.Prikazy;

import java.io.Serializable;

/**
 * 28. 3. 2022 - 12:58
 *
 * AbstractBudova slúži ako predloha pre ďalšie budovy
 *
 * @author Filip Šefčík
 */
public abstract class AbstractBudova implements Serializable {

    private Prikazy prikazy;
    private String nazovBudovy;

    /**
     * Vytvorí sa budova o ktorej vieme, ako sa volá a aké príkazy sa budú môcť v nej zadávať
     * @param nazovBudovy
     * @param prikazy
     */
    public AbstractBudova(String nazovBudovy, String... prikazy) {
        this.prikazy = new Prikazy(prikazy);
        this.nazovBudovy = nazovBudovy;
    }

    /**
     * @return názov budovy
     */
    public String getNazovBudovy() {
        return this.nazovBudovy;
    }

    /**
     * Metóda si pýta vstup od hráča a následne bude konať podľa toho ako sa vstup spracuje
     * @param hrac
     */
    public void vstupDoBudovy(Hrac hrac) {
        this.uvitaciaHlaska();
        boolean hracZostaneVBudove;
        this.prikazy.vypisPrikazy();
        do {
            String[] input = this.prikazy.nacitajInput(hrac.getAktualnaLokacia().getNazovLokacie(), this.nazovBudovy);
            hracZostaneVBudove = this.spracujInput(hrac, input);
        } while (hracZostaneVBudove);
    }

    /**
     * Vypíšu sa príkazy ktoré, môže hráč zadať
     */
    public void vypisPrikazy() {
        this.prikazy.vypisPrikazy();
    }

    /**
     * @param prikaz, ktorého číslo sa má zistiť
     * @return číslo príkazu, ktorý hráč zadal
     */
    public int getCisloPrikazu(String prikaz) {
        return this.prikazy.getCisloPrikazu(prikaz);
    }

    /**
     * Metóda, ktorá sa koná pri dobrovoľnom odchode z budovy
     */
    public abstract void odchodZBudovy();

    /**
     * Vykoná sa pri vstupe do budovy
     */
    public abstract void uvitaciaHlaska();

    /**
     * Spracováva vstup od hráča
     * @param hrac
     * @param vstup
     * @return true/false ak hráč naďalej ostane/neostane v budove
     */
    public abstract boolean spracujInput(Hrac hrac, String[] vstup);

    /**
     * Premenuje príkaz na zadanom indexe na zadanú hodnotu
     * @param indexPrikazu, ktorý sa má zmeniť
     * @param novyNazov, ktorý bude príkaz mať
     */
    public void premenujPrikaz(int indexPrikazu, String novyNazov) {
        this.prikazy.premenujPrikaz(indexPrikazu, novyNazov);
    }
}
