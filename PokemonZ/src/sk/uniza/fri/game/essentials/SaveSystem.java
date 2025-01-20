package sk.uniza.fri.game.essentials;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * 28. 3. 2022 - 12:58
 *
 * SaveSystem má na starosti prácu so súbormi, kontroluje či sú prázdne. Zisťuje kedy boli naposledy uložené.
 *
 * @author Filip Šefčík
 */
public class SaveSystem {

    private File[] subory;
    private static SaveSystem instance;

    /**
     * @return inštancia triedy SaveSystem
     */
    public static SaveSystem getInstance() {
        if (instance == null) {
            instance = new SaveSystem();
        }
        return instance;
    }

    /**
     * V bezparametrickom konštruktore sa vytvorí File pole, v ktorom sú súbory zo save priečinku
     */
    private SaveSystem() {
        this.subory = new File[6];
        for (int i = 0; i < this.subory.length; i++) {
            this.subory[i] = new File("saves/slot" + (i + 1) + ".pkmnz");
        }
    }

    /**
     * Vyvolá sa metóda ziskajIndex() v triede Komunikator
     * @return index súboru zadaný hráčom
     */
    public int ziskajIndexSuboru() {
        String otazka = "Vyber index jedného zo slotov.";
        String chyba = "Musíš zadat jeho číslo";
        String zlyIndex = "Musíš zadať číslo od 1 do " + this.subory.length;
        return Komunikator.ziskajIndex(this.subory.length, otazka, chyba, zlyIndex, true, true, this);
    }

    /**
     * @return vybraný súbor, z ktorého sa načíta nová hra, v prípade ak nie je prázdny
     */
    public File vyberSuborPriNacitani() {
        int indexSuboru = this.ziskajIndexSuboru();
        File subor = this.subory[indexSuboru];

        while (subor.length() == 0) {
            System.out.println("Tento slot je prázdny, zadaj iný.");
            indexSuboru = this.ziskajIndexSuboru();
            subor = this.subory[indexSuboru];
        }

        return subor;
    }

    /**
     * Metóda vyberá súbor, do ktorého sa hra uloží, v prípade že súbor nie je prázdny, tak sa uistíme, či chceme prepísať daný súbor.
     * Ak nie, vyberie sa iný.
     * @return vybraný súbor
     */
    public File vyberSuborPriUkladani() {
        int indexSuboru = this.ziskajIndexSuboru();
        File subor = this.subory[indexSuboru];

        if (subor.length() > 0) {
            if (!Komunikator.vyberAnoAleboNie("V tomto slote sa už niečo nachádza, chcete ho prepísať?")) {
                return this.vyberSuborPriUkladani();
            }
        }
        return subor;
    }

    /**
     * @return true/false ak je/nie je niečo v jednom zo súborov zapísané
     */
    public boolean maUlozenuHru() {
        for (File file : this.subory) {
            if (file.length() > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metóda vypíše všetky súbory a ak nie je súbor prázdny, vypíše sa aj čas kedy bol súbor uložený
     */
    public void vypisSubory() {
        for (int i = 0; i < this.subory.length; i++) {
            System.out.print((i + 1) + " slot" + (i + 1) + " ");
            if (this.subory[i].length() == 0) {
                System.out.println("prázdny");
            } else {
                SimpleDateFormat datum = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                System.out.println(datum.format(this.subory[i].lastModified()));
            }
        }
    }

}
