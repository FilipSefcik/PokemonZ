package sk.uniza.fri.pokemon;


import java.io.Serializable;
import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Trieda Utok špecifický útok, ktorý patrí pokémonovi
 *
 * @author Filip Šefčík
 */
public class Utok implements Serializable {

    private DruhUtoku druhUtoku;
    private int pocetPouziti;

    /**
     * V konštruktore sa ako parameter zadáva akého druhu útoku bude tento útok.
     * Taktiež sa uloží informácia o maximálnom počte použití do atribútu pocetPouziti
     * @param druhUtoku
     */
    public Utok(DruhUtoku druhUtoku) {
        this.druhUtoku = druhUtoku;
        this.pocetPouziti = this.druhUtoku.getMaxPocetPouziti();
    }

    /**
     * @return druh útoku
     */
    public DruhUtoku getDruhUtoku() {
        return this.druhUtoku;
    }

    /**
     * @return aktuálny počet zostávajúcich použití útoku
     */
    public int getPocetPouziti() {
        return this.pocetPouziti;
    }

    /**
     * @return maximálny počet použití útoku
     */
    public int getMaxPocetPouziti() {
        return this.druhUtoku.getMaxPocetPouziti();
    }

    /**
     * @return typ útoku
     */
    public Typ getTypUtoku() {
        return this.druhUtoku.getTypUtoku();
    }

    /**
     * @return názov útoku
     */
    public String getNazovUtoku() {
        return this.druhUtoku.getNazovUtoku();
    }

    /**
     * @return sila útoku
     */
    public double getSilaUtoku() {
        return this.druhUtoku.getSilaUtoku();
    }

    /**
     * @return presnosť útoku
     */
    public int getPresnostUtoku() {
        return this.druhUtoku.getPresnostUtoku();
    }

    /**
     * @return popis útoku
     */
    public String dajPopisUtoku() {
        return this.druhUtoku.dajPopis();
    }

    /**
     * @return vráti String v tvare "pocetPouziti/maxPocetPouziti"
     */
    public String vypisPouzitie() {
        return this.pocetPouziti + "/" + this.getMaxPocetPouziti();
    }

    /**
     * @return true/false ak sa aktuálny počet použití rovná/nerovná tomu maximálnemu počtu použití
     */
    public boolean maPlnyPocetPouziti() {
        return this.pocetPouziti == this.getMaxPocetPouziti();
    }

    /**
     * Zníží počet použití o 1
     */
    public void znizPocetPouziti() {
        this.pocetPouziti--;
    }

    /**
     * Zvýši počet použití o zadanú hodnotu
     * @param bonusPocetPouziti
     */
    public void pridajPocetPouziti(int bonusPocetPouziti) {
        this.pocetPouziti += bonusPocetPouziti;
        if (this.pocetPouziti > this.getMaxPocetPouziti()) {
            this.pocetPouziti = this.getMaxPocetPouziti();
        }
    }

    /**
     * Pridá počet použití o dané percento z maximálneho počtu
     * @param percentoPridania
     */
    public void pridajPercentualne(int percentoPridania) {
        int bonusPocetPouziti = this.getMaxPocetPouziti() * percentoPridania / 100;
        this.pridajPocetPouziti(bonusPocetPouziti);
    }

    /**
     * Použije sa útok, zníži sa mu počet použití a vyráta sa pomocou náhody, či sa útok trafil/netrafil
     * @param pokemon
     * @return true/false ak sa útok trafil/netrafil
     */
    public boolean pouziUtok(Pokemon pokemon) {
        Random generator = new Random();
        System.out.println(pokemon.getPrezyvka() + " pouzil " + this.getNazovUtoku() + "!");
        this.znizPocetPouziti();
        if (generator.nextInt(100) > this.getPresnostUtoku()) {
            System.out.println(pokemon.getPrezyvka() + " sa netrafil!");
            return false;
        }
        return true;
    }
}
