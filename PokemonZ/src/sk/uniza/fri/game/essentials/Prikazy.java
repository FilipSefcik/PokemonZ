package sk.uniza.fri.game.essentials;

import java.io.Serializable;
import java.util.Scanner;

/**
 * 28. 3. 2022 - 12:58
 *
 * Trieda Prikazy pracuje s príkazmy, obsahuje zoznam príkazov a spracováva ich.
 * Trieda je kombináciou tried Parser, Prikaz a NazvyPrikazov z cvičení predmetu Informatika 2.
 *
 * @author Filip Šefčík
 */
public class Prikazy implements Serializable {

    private String[] prikazy;

    /**
     * Do konštruktora sa zadávajú všetky rôzne príkazy, ktoré sa môžu zadať, a načítajú sa do atribútu prikazy.
     * @param prikazy
     */
    public Prikazy(String... prikazy) {
        this.prikazy = prikazy;
    }

    /**
     * Metóda si pýta vstup od hráča, ideálne by to mal byť príkaz
     * @param cesta, slúži na zjednodušenie orientácie hráčovi
     * @return spracovaný input zadaný hráčom
     */
    public String[] nacitajInput(String... cesta) {
        Scanner skener = new Scanner(System.in);
        for (int i = 0; i < cesta.length; i++) {
            System.out.print(cesta[i] + ">");
            if (i == (cesta.length - 1)) {
                System.out.print(" ");
            }
        }

        String vstup = skener.nextLine();

        String prikaz = null;
        String parameter = null;

        Scanner tokenizer = new Scanner(vstup);
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();
            }
        }
        String[] input = {prikaz, parameter};

        return input;
    }

    /**
     * Metóda zisťuje na akom indexe sa zadaný príkaz nachádza.
     * @param prikaz
     * @return index príkazu zvýšený o 1, ak sa príkaz nenachádza v zozname vracia -1
     */
    public int getCisloPrikazu(String prikaz) {
        for (int i = 0; i < this.prikazy.length; i++) {
            if (this.prikazy[i].equals(prikaz)) {
                return (i + 1);
            }
        }
        return -1;
    }

    /**
     * Metóda pridá slovo v parametri ako nový príkaz
     * @param novyPrikaz
     */
    public void pridajPrikaz(String novyPrikaz) {
        String[] starePrikazy = this.prikazy;
        this.prikazy = new String[this.prikazy.length + 1];
        for (int i = 0; i < starePrikazy.length; i++) {
            this.prikazy[i] = starePrikazy[i];
        }
        this.prikazy[this.prikazy.length - 1] = novyPrikaz;

    }

    /**
     * Premenuje príkaz na danom indexe na dané slovo zadané parametrom
     * @param indexPrikazu
     * @param novyNazov
     */
    public void premenujPrikaz(int indexPrikazu, String novyNazov) {
        this.prikazy[indexPrikazu] = novyNazov;
    }


    /**
     * Vypíšu sa všetky príkazy, ktoré sa môžu zadať
     */
    public void vypisPrikazy() {
        System.out.print("Možnosti: ");
        for (String prikaz : this.prikazy) {
            System.out.print(prikaz + " ");
        }
        System.out.println();
    }
}
