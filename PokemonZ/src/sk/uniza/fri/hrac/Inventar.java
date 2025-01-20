package sk.uniza.fri.hrac;

import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.items.pokeball.*;
import sk.uniza.fri.items.potion.*;
import sk.uniza.fri.items.revive.*;
import sk.uniza.fri.items.tm.*;
import sk.uniza.fri.game.essentials.Prikazy;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 28. 3. 2022 - 12:58
 *
 * Trieda inventár spracováva predmety, ktoré hráč vlastní
 *
 * @author Filip Šefčík
 */
public class Inventar implements Serializable {

    private ArrayList<Pokeball> pokebally;
    private ArrayList<Greatball> greatbally;
    private ArrayList<Ultraball> ultrabally;
    private ArrayList<Netball> netbally;
    private ArrayList<Healball> healbally;
    private ArrayList<Evolveball> evolvebally;

    private ArrayList<Potion> potiony;
    private ArrayList<PowerPotion> powerPotiony;

    private ArrayList<Revive> revivy;
    private ArrayList<SuperRevive> superRevivy;
    private ArrayList<MegaRevive> megaRevivy;

    private ArrayList<TM> tmka;

    private Prikazy prikazy;
    private Prikazy prikazyPriVybere;
    private String tab;

    /**
     * Konštruktor inicializuje ArrayListy prdmetov a nastaví ďalšie atribúty na prednastavené hodnoty
     */
    public Inventar() {
        this.pokebally = new ArrayList<>();
        this.greatbally = new ArrayList<>();
        this.ultrabally = new ArrayList<>();
        this.netbally = new ArrayList<>();
        this.healbally = new ArrayList<>();
        this.evolvebally = new ArrayList<>();
        this.potiony = new ArrayList<>();
        this.powerPotiony = new ArrayList<>();
        this.revivy = new ArrayList<>();
        this.superRevivy = new ArrayList<>();
        this.megaRevivy = new ArrayList<>();
        this.tmka = new ArrayList<>();
        this.prikazy = new Prikazy("Pokeball", "Potion", "Revive", "TM", "pomoc", "inventar", "spat");
        this.prikazyPriVybere = null;
        this.tab = "";
    }

    /**
     * Zapne tabulátor
     */
    public void setTab() {
        this.tab = "\t";
    }

    /**
     * Vypne tabulátor
     */
    public void unsetTab() {
        this.tab = "";
    }

    /**
     * Metódy skupiny Vloz fungujú nasledovne:
     * Zistí do akej skupiny predmetov nový predmet patrí a pridá ho tam
     * @param item
     * @return true/false ak sa predmet úspešne/neúspešne pridal do inventára
     */

    public boolean vlozDoInventara(AbstractItem item) {
        if (item instanceof AbstractPokeball) {
            return this.vlozPokeball(item);
        } else if (item instanceof AbstractPotion) {
            return this.vlozPotion(item);
        } else if (item instanceof AbstractRevive) {
            return this.vlozRevive(item);
        } else if (item instanceof TM) {
            return this.vlozTM(item);
        }
        return false;
    }

    private boolean vlozTM(AbstractItem item) {
        return this.tmka.add((TM)item);
    }

    private boolean vlozRevive(AbstractItem item) {
        if (item instanceof Revive) {
            return this.revivy.add((Revive)item);
        } else if (item instanceof SuperRevive) {
            return this.superRevivy.add((SuperRevive)item);
        } else if (item instanceof MegaRevive) {
            return this.megaRevivy.add((MegaRevive)item);
        }
        return false;
    }

    private boolean vlozPotion(AbstractItem item) {
        if (item instanceof Potion) {
            return this.potiony.add((Potion)item);
        } else if (item instanceof PowerPotion) {
            return this.powerPotiony.add((PowerPotion)item);
        }
        return false;
    }

    private boolean vlozPokeball(AbstractItem item) {
        if (item instanceof Pokeball) {
            return this.pokebally.add((Pokeball)item);
        } else if (item instanceof Greatball) {
            return this.greatbally.add((Greatball)item);
        } else if (item instanceof Ultraball) {
            return this.ultrabally.add((Ultraball)item);
        } else if (item instanceof Netball) {
            return this.netbally.add((Netball)item);
        } else if (item instanceof Healball) {
            return this.healbally.add((Healball)item);
        } else if (item instanceof Evolveball) {
            return this.evolvebally.add((Evolveball)item);
        }
        return false;
    }

    /**
     * Pomocou tejto metódy si hráč vyberá predmet z inventára
     * @return predmet ktorý si hráč vybral
     */
    public AbstractItem vyberItem() {
        this.vypisInventar();
        System.out.println("Vyberte kategóriu predmetu.");
        this.prikazy.vypisPrikazy();
        boolean itemSaNevybral;
        String kategoria = "";
        do {
            String[] input = this.prikazy.nacitajInput("inventar");
            itemSaNevybral = this.spracujInput(input);
            if (!itemSaNevybral) {
                kategoria = input[0];
            }
        } while (itemSaNevybral);

        switch (kategoria) {
            case "Pokeball":
                return this.vyberPokeball();
            case "Potion":
                return this.vyberPotion();
            case "Revive":
                return this.vyberRevive();
            case "TM":
                return this.vyberTM();
            default:
                return null;
            }

    }

    /**
     * Spracuje sa input podľa vstupu
     * @param vstup
     * @return true/false ak hráč zadal nesprávny/správny vstup
     */
    private boolean spracujInput(String[] vstup) {
        switch (this.prikazy.getCisloPrikazu(vstup[0])) {
            case 1:
                if (!this.maInventarPokeball()) {
                    System.out.println("Nemáš žiadne Pokébally!");
                    return true;
                } else {
                    return false;
                }
            case 2:
                if (!this.maInvetnarPotion()) {
                    System.out.println("Nemáš žiadne Potiony!");
                    return true;
                } else {
                    return false;
                }
            case 3:
                if (!this.maInventarRevive()) {
                    System.out.println("Nemáš žiadne Revivy!");
                    return true;
                } else {
                    return false;
                }
            case 4:
                if (!this.maInventarTM()) {
                    System.out.println("Nemáš žiadne TMka!");
                    return true;
                } else {
                    return false;
                }
            case 5:
                this.prikazy.vypisPrikazy();
                return true;
            case 6:
                this.vypisInventar();
                return true;
            case 7:
                return false;
            default:
                System.out.println("Neznámy vstup, zadajte znovu.");
                return true;
        }
    }

    /**
     * Metódy typu Vyber fungujú nasledovne:
     * Nastavia sa príkazy podľa kategórie predmetu a následne sa vyberie
     * @return vybraný predmet
     */
    private AbstractItem vyberTM() {
        this.nastavPrikazyPreTMka();
        this.vypisTMka();
        String poziadavka = "Zadajte názov TM";
        String chyba = "Také TM sa v inventári nenachádza";
        String nazovTM = this.vyberNazovPredmetu(poziadavka, "TM", chyba);
        return this.dajTM(nazovTM);
    }

    private void nastavPrikazyPreTMka() {
        this.prikazyPriVybere = new Prikazy();
        for (TM tm : this.tmka) {
            this.prikazyPriVybere.pridajPrikaz(tm.getNazovPredmetu());
        }
    }

    private AbstractRevive vyberRevive() {
        this.nastavPrikazyPreRevivy();
        this.vypisRevivy();
        String poziadavka = "Zadaj začiatočné písmeno Revivu, ktorý si chceš vybrať.";
        String chyba = "Je potrebné zadať iba začiatočné písmeno Revivu, ktorý máš v inventári";
        String typRevivu = this.vyberNazovPredmetu(poziadavka, "Revive", chyba);
        return this.dajRevive(typRevivu);
    }

    private void nastavPrikazyPreRevivy() {
        this.prikazyPriVybere = new Prikazy();
        if (!this.revivy.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.revivy.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.superRevivy.isEmpty()){
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.revivy.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.megaRevivy.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.revivy.get(0).getNazovPredmetu().charAt(0)));
        }
    }

    private AbstractPotion vyberPotion() {
        this.nastavPrikazyPrePotiony();
        this.vypisPotiony();
        String poziadavka = "Napíšte názov potionu, ktorý chcete vybrať.";
        String chyba = "Zlý názov, zadajte znova.";
        String typPotionu = this.vyberNazovPredmetu(poziadavka, "Potion", chyba);
        return this.dajPotion(typPotionu);
    }

    private void nastavPrikazyPrePotiony() {
        this.prikazyPriVybere = new Prikazy();
        for (Potion potion : this.potiony) {
            this.prikazyPriVybere.pridajPrikaz(potion.getNazovPredmetu());
        }
        for (PowerPotion powerPotion : this.powerPotiony) {
            this.prikazyPriVybere.pridajPrikaz(powerPotion.getNazovPredmetu());
        }
    }

    private AbstractPokeball vyberPokeball() {
        this.nastavPrikazyPrePokebally();
        this.vypisPokebally();
        String poziadavka = "Vyberte Pokéball podľa jeho začiatočného písmena.";
        String chyba = "Zadajte začiatočné písmeno názvu Pokéballu, ktoré máš v inventári.";
        String typPokeballu = this.vyberNazovPredmetu(poziadavka, "Pokeball", chyba);
        return this.dajPokeball(typPokeballu);
    }

    private void nastavPrikazyPrePokebally() {
        this.prikazyPriVybere = new Prikazy();
        if (!this.pokebally.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.pokebally.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.greatbally.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.greatbally.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.ultrabally.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.ultrabally.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.netbally.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.netbally.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.healbally.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.healbally.get(0).getNazovPredmetu().charAt(0)));
        }
        if (!this.evolvebally.isEmpty()) {
            this.prikazyPriVybere.pridajPrikaz(String.valueOf(this.evolvebally.get(0).getNazovPredmetu().charAt(0)));
        }
    }

    /**
     * Vyberá sa názov predmetu ktorý sa vráti hráčovi
     * @param poziadavka ktorá sa udelí hráčovi
     * @param cesta pre zjednodušenie orientácie
     * @param chyba sa vypíše ak hráč zadá neplatný vstup
     * @return názov vybraného predmetu
     */
    public String vyberNazovPredmetu(String poziadavka, String cesta, String chyba) {
        System.out.println(poziadavka);
        boolean predmetSaNevybral;
        String nazovPredmetu = "";
        do {
            String[] input = this.prikazyPriVybere.nacitajInput("inventar", cesta);
            if (this.prikazyPriVybere.getCisloPrikazu(input[0]) > 0) {
                nazovPredmetu = input[0];
                predmetSaNevybral = false;
            } else {
                System.out.println(chyba);
                predmetSaNevybral = true;
            }
        } while (predmetSaNevybral);
        return nazovPredmetu;
    }


    /**
     * Metóda daj pracuje nasledovne:
     * Vráti predmet ktorý sa doteraz vyberal pomocou metód na výber predmetu
     * @return vybraný predmet
     */

    public AbstractPokeball dajPokeball(String typPokeballu) {
        if (!this.pokebally.isEmpty() && typPokeballu.equals(String.valueOf(this.pokebally.get(0).getNazovPredmetu().charAt(0)))) {
            return this.pokebally.get(0);
        } else if (!this.greatbally.isEmpty() && typPokeballu.equals(String.valueOf(this.greatbally.get(0).getNazovPredmetu().charAt(0)))) {
            return this.greatbally.get(0);
        } else if (!this.ultrabally.isEmpty() && typPokeballu.equals(String.valueOf(this.ultrabally.get(0).getNazovPredmetu().charAt(0)))) {
            return this.ultrabally.get(0);
        } else if (!this.netbally.isEmpty() && typPokeballu.equals(String.valueOf(this.netbally.get(0).getNazovPredmetu().charAt(0)))) {
            return this.netbally.get(0);
        } else if (!this.healbally.isEmpty() && typPokeballu.equals(String.valueOf(this.healbally.get(0).getNazovPredmetu().charAt(0)))) {
            return this.healbally.get(0);
        } else if (!this.evolvebally.isEmpty() && typPokeballu.equals(String.valueOf(this.evolvebally.get(0).getNazovPredmetu().charAt(0)))) {
            return this.evolvebally.get(0);
        }
        return null;
    }

    public AbstractPotion dajPotion(String nazovPotionu) {
        for (Potion potion : this.potiony) {
            if (potion.getNazovPredmetu().equals(nazovPotionu)) {
                return potion;
            }
        }

        for (PowerPotion powerPotion : this.powerPotiony) {
            if (powerPotion.getNazovPredmetu().equals(nazovPotionu)) {
                return powerPotion;
            }
        }

        return null;
    }

    public AbstractRevive dajRevive(String typRevivu) {
        if (!this.revivy.isEmpty() && typRevivu.equals(String.valueOf(this.revivy.get(0).getNazovPredmetu().charAt(0)))) {
            return this.revivy.get(0);
        } else if (!this.superRevivy.isEmpty() && typRevivu.equals(String.valueOf(this.superRevivy.get(0).getNazovPredmetu().charAt(0)))) {
            return this.superRevivy.get(0);
        } else if (!this.megaRevivy.isEmpty() && typRevivu.equals(String.valueOf(this.megaRevivy.get(0).getNazovPredmetu().charAt(0)))) {
            return this.megaRevivy.get(0);
        }
        return null;
    }

    public TM dajTM(String nazovTM) {
        for (TM tm : this.tmka) {
            if (tm.getNazovPredmetu().equals(nazovTM)) {
                return tm;
            }
        }
        return null;
    }

    /**
     * Metódy typu Odstran fungujú nasledovne:
     * Odstránia predmet zadaný z parametra keď sa zistí do akej kategórie patrí
     * @return true/false ak sa predmet podarilo/nepodarilo odstrániť
     */

    public boolean odstranItem(AbstractItem item) {
        if (item instanceof AbstractPokeball) {
            return this.odstranPokeball(item);
        } else if (item instanceof AbstractPotion) {
            return this.odstranPotion(item);
        } else if (item instanceof AbstractRevive) {
            return this.odstranRevive(item);
        } else if (item instanceof TM) {
            return this.odstranTM(item);
        }

        System.out.println("Taký item nemáš v inventári.");
        return false;

    }

    private boolean odstranTM(AbstractItem item) {
        for (int i = 0; i < this.tmka.size(); i++) {
            if (this.tmka.get(i).getNazovPredmetu().equals(item.getNazovPredmetu())) {
                this.tmka.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean odstranRevive(AbstractItem item) {
        if (item instanceof Revive) {
            this.revivy.remove(0);
            return true;
        } else if (item instanceof SuperRevive) {
            this.superRevivy.remove(0);
            return true;
        } else if (item instanceof MegaRevive) {
            this.megaRevivy.remove(0);
            return true;
        }
        return false;
    }

    private boolean odstranPotion(AbstractItem item) {
        if (item instanceof Potion) {
            for (int i = 0; i < this.potiony.size(); i++) {
                if (this.potiony.get(i).getNazovPredmetu().equals(item.getNazovPredmetu())) {
                    this.potiony.remove(i);
                    return true;
                }
            }
        } else if (item instanceof PowerPotion) {
            for (int i = 0; i < this.powerPotiony.size(); i++) {
                if (this.powerPotiony.get(i).getNazovPredmetu().equals(item.getNazovPredmetu())) {
                    this.powerPotiony.remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean odstranPokeball(AbstractItem item) {
        if (item instanceof  Pokeball) {
            this.pokebally.remove(0);
            return true;
        } else if (item instanceof  Greatball) {
            this.greatbally.remove(0);
            return true;
        } else if (item instanceof  Ultraball) {
            this.ultrabally.remove(0);
            return true;
        } else if (item instanceof Netball) {
            this.netbally.remove(0);
            return true;
        } else if (item instanceof Healball) {
            this.healbally.remove(0);
            return true;
        } else if (item instanceof Evolveball) {
            this.evolvebally.remove(0);
            return true;
        }
        return false;
    }

    /**
     * Matódy DajPocet zisťujú koľkokrát sa daný predmet určený parametrom nachádza v inventári
     * @return početnosť predmetu
     */

    public int dajPocetPokeballov(AbstractItem predmet) {
        if (predmet instanceof Pokeball) {
            return this.pokebally.size();
        } else if (predmet instanceof  Greatball) {
            return this.greatbally.size();
        } else if (predmet instanceof Ultraball) {
            return this.ultrabally.size();
        } else if (predmet instanceof Netball) {
            return this.netbally.size();
        } else if (predmet instanceof Healball) {
            return this.healbally.size();
        } else if (predmet instanceof Evolveball) {
            return this.evolvebally.size();
        }
        return 0;
    }

    public int dajPocetRevivov(AbstractItem predmet) {
        if (predmet instanceof Revive) {
            return this.revivy.size();
        } else if (predmet instanceof SuperRevive) {
            return this.superRevivy.size();
        } else if (predmet instanceof MegaRevive) {
            return this.megaRevivy.size();
        }
        return 0;
    }

    public int dajPocetPotionov(AbstractItem predmet) {
        if (predmet instanceof Potion) {
            return this.zistiPocetPotionov(predmet.getNazovPredmetu());
        } else if (predmet instanceof PowerPotion) {
            return this.zistiPocetPowerPotionov(predmet.getNazovPredmetu());
        }
        return 0;
    }

    public int zistiPocetPotionov(String nazovPotionu) {
        int pocet = 0;
        for (Potion potion : this.potiony) {
            if (potion.getNazovPredmetu().equals(nazovPotionu)) {
                pocet++;
            }
        }
        return pocet;
    }

    public int zistiPocetPowerPotionov(String nazovPotionu) {
        int pocet = 0;
        for (PowerPotion potion : this.powerPotiony) {
            if (potion.getNazovPredmetu().equals(nazovPotionu)) {
                pocet++;
            }
        }
        return pocet;
    }

    /**
     * Metódy Vypis vypisujú jednotlivé predmety, ich počet a popis
     */

    public void vypisPokebally() {
        if (!this.pokebally.isEmpty()) {
            System.out.print(this.tab + this.pokebally.size() + "x ");
            this.pokebally.get(0).vypisSa();
        }
        if (!this.greatbally.isEmpty()) {
            System.out.print(this.tab + this.greatbally.size() + "x ");
            this.greatbally.get(0).vypisSa();
        }
        if (!this.ultrabally.isEmpty()) {
            System.out.print(this.tab + this.ultrabally.size() + "x ");
            this.ultrabally.get(0).vypisSa();
        }
        if (!this.netbally.isEmpty()) {
            System.out.print(this.tab + this.netbally.size() + "x ");
            this.netbally.get(0).vypisSa();
        }
        if (!this.healbally.isEmpty()) {
            System.out.print(this.tab + this.healbally.size() + "x ");
            this.healbally.get(0).vypisSa();
        }
        if (!this.evolvebally.isEmpty()) {
            System.out.print(this.tab + this.evolvebally.size() + "x ");
            this.evolvebally.get(0).vypisSa();
        }
    }

    public void vypisPotiony() {
        if (!this.potiony.isEmpty()) {
            HashMap<String, Integer> typyPotionov = TypPotionov.getInstance().getPotiony();
            for (String nazovPotionu : typyPotionov.keySet()) {
                int pocetnostPotionu = this.zistiPocetPotionov(nazovPotionu);
                if (pocetnostPotionu > 0) {
                    System.out.print(this.tab + pocetnostPotionu + "x ");
                    this.dajPotion(nazovPotionu).vypisSa();
                }
            }
        }

        if (!this.powerPotiony.isEmpty()) {
            HashMap<String, Integer> typyPowerPotionov = TypPotionov.getInstance().getPowerPotiony();
            for (String nazovPowerPotionu : typyPowerPotionov.keySet()) {
                int pocetnostPotionu = this.zistiPocetPowerPotionov(nazovPowerPotionu);
                if (pocetnostPotionu > 0) {
                    System.out.print(this.tab + pocetnostPotionu + "x ");
                    this.dajPotion(nazovPowerPotionu).vypisSa();
                }
            }
        }
    }

    public void vypisRevivy() {
        if (!this.revivy.isEmpty()) {
            System.out.print(this.tab + this.revivy.size() + "x ");
            this.revivy.get(0).vypisSa();
        }
        if (!this.superRevivy.isEmpty()) {
            System.out.print(this.tab + this.superRevivy.size() + "x ");
            this.superRevivy.get(0).vypisSa();
        }
        if (!this.megaRevivy.isEmpty()) {
            System.out.println(this.tab + this.megaRevivy.size() + "x ");
            this.megaRevivy.get(0).vypisSa();
        }
    }

    public void vypisTMka() {
        for (TM tm : this.tmka) {
            System.out.print(this.tab);
            tm.vypisSa();
        }
    }

    /**
     * Vypíše úplne celý inventár
     */
    public void vypisInventar() {
        if (this.obsahujeNiecoInventar()) {
            this.setTab();
            System.out.println("Pokébally:");
            if (this.maInventarPokeball()) {
                this.vypisPokebally();
            } else {
                System.out.println(this.tab + "Nemáš žiadne Pokébally!!");
            }
            System.out.println("Potiony:");
            if (this.maInvetnarPotion()) {
                this.vypisPotiony();
            } else {
                System.out.println(this.tab + "Nemáš žiadne Potiony!");
            }
            System.out.println("Revivy:");
            if (this.maInventarRevive()) {
                this.vypisRevivy();
            } else {
                System.out.println(this.tab + "Nemáš žiadne Revivy.");
            }
            System.out.println("TM:");
            if (this.maInventarTM()) {
                this.vypisTMka();
            } else {
                System.out.println(this.tab + "Nemáš žiadne TMka.");
            }
            this.unsetTab();
        }
    }

    /**
     * Nasledovné metódy MaInventar alebo Obsahuje zisťujú ktoré kategórie predmetov sa nachádzajú v ňom
     * @return true/false ak sa predmety nachádzajú/nenachádzajú v inventári alebo či inventár nie je/je prázdny
     */

    public boolean obsahujeNiecoInventar() {
        return this.maInventarPokeball() || this.maInvetnarPotion() || this.maInventarRevive() || this.maInventarTM();
    }

    public boolean maInventarTM() {
        return !this.tmka.isEmpty();
    }

    public boolean maInventarRevive() {
        return !this.revivy.isEmpty() || !this.superRevivy.isEmpty() || !this.megaRevivy.isEmpty();
    }

    public boolean maInvetnarPotion() {
        return !this.potiony.isEmpty() || !this.powerPotiony.isEmpty();
    }

    public boolean maInventarPokeball() {
        return !this.pokebally.isEmpty() || !this.greatbally.isEmpty() || !this.ultrabally.isEmpty() || !this.netbally.isEmpty() || !this.healbally.isEmpty() || !this.evolvebally.isEmpty();
    }
}
