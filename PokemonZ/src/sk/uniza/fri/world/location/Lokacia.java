package sk.uniza.fri.world.location;


import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.budovy.AbstractBudova;
import sk.uniza.fri.interactive.npc.Npc;
import sk.uniza.fri.world.location.priechody.Priechod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 28. 3. 2022 - 12:58
 *
 * Lokacia predstavuje špecifické miesto, kde sa môže hráč presunúť, obsahuje budovy, npc a ponúka presun do iných lokácii
 * Trieda Lokacia bola prebratá z cvičení predmetu Informatika 2
 *
 * @author Filip Šefčík
 */
public class Lokacia implements Serializable {
    private String nazovLokacie;
    private String popisLokacie;
    private HashMap<String, Priechod> okoliteLokacie;
    private ArrayList<Npc> npc;
    private ArrayList<AbstractBudova> budovy;

    /**
     * V konštruktore sa inicializujú atribúty a do parametrov sa zadáva názov a popis lokácie
     * @param nazovLokacie
     * @param popisLokacie
     */
    public Lokacia(String nazovLokacie, String popisLokacie) {
        this.nazovLokacie = nazovLokacie;
        this.popisLokacie = popisLokacie;
        this.okoliteLokacie = new HashMap<>();
        this.npc = new ArrayList<>();
        this.budovy = new ArrayList<>();
    }

    /**
     * @return názov lokácie
     */
    public String getNazovLokacie() {
        return this.nazovLokacie;
    }

    /**
     * @return popis lokácie spolu s názvom
     */
    public String getPopisLokacie() {
        return this.nazovLokacie + " - " + this.popisLokacie;
    }

    /**
     * Vráti lokáciu ktorej názov bol zadaný ako parameter
     * @param hrac
     * @param nazovLokacie
     * @return
     */
    public Lokacia getLokacia(Hrac hrac, String nazovLokacie) {
        Priechod priechod = this.okoliteLokacie.get(nazovLokacie);
        if (priechod != null) {
            return priechod.getLokacia(hrac);
        }
        System.out.println("K danému miestu z tohto miesta žiadna cesta nevedie.");
        return null;
    }

    /**
     * Vráti npc, ktorého meno sa zadal cez parameter
     * @param menoNpc
     * @return
     */
    public Npc getNpc(String menoNpc) {
        for (Npc npcecko : this.npc) {
            if (npcecko.getMeno().equals(menoNpc)) {
                return npcecko;
            }
        }
        return null;
    }

    /**
     * Vráti budovu, ktorej názov sa zadal ako parameter
     * @param nazovBudovy
     * @return
     */
    public AbstractBudova getBudova(String nazovBudovy) {
        for (AbstractBudova budova : this.budovy) {
            if (budova.getNazovBudovy().equals(nazovBudovy)) {
                return budova;
            }
        }
        return null;
    }

    /**
     * Pridá npc do zoznamu npc
     * @param npcecko
     */
    public void pridajNpc(Npc npcecko) {
        this.npc.add(npcecko);
    }

    /**
     * Pridá budovu do zoznamu budov
     * @param budova
     */
    public void postavBudovu(AbstractBudova budova) {
        this.budovy.add(budova);
    }

    /**
     * Prepojí novú lokáciu s touto lokáciou
     * @param nazov
     * @param priechod
     */
    public void nastavPriechod(String nazov, Priechod priechod) {
        this.okoliteLokacie.put(nazov, priechod);
    }

    /**
     * Vypíše čo všetko môže hráč v lokácii robiť, isť do inej lokácie, vstúpiť do budovy, oslvoviť osobu
     */
    public void vypisOkolie() {
        System.out.println("Nachádzaš sa v " + this.getPopisLokacie());
        System.out.print("Ďalej sa môžeš vydať smerom: ");

        for (String lokacia : this.okoliteLokacie.keySet()) {
            System.out.print(lokacia + " ");
        }
        System.out.println();

        if (!this.budovy.isEmpty()) {
            System.out.print("Dostupné miesta: ");
            for (AbstractBudova budova : this.budovy) {
                System.out.print(budova.getNazovBudovy() + " ");
            }
            System.out.println();
        }

        if (!this.npc.isEmpty()) {
            System.out.print("Alebo oslov týchto ľudí: ");
            for (Npc npcecko : this.npc) {
                System.out.print(npcecko.getMeno() + " ");
            }
            System.out.println();
        }
    }
}
