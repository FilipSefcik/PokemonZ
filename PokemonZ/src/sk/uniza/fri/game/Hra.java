package sk.uniza.fri.game;

import sk.uniza.fri.game.essentials.SaveSystem;
import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.budovy.AbstractBudova;
import sk.uniza.fri.interactive.npc.Npc;
import sk.uniza.fri.interactive.npc.Profesor;
import sk.uniza.fri.interactive.npc.Rival;
import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.world.location.Lokacia;
import sk.uniza.fri.interactive.budovy.pokecentrum.PC;
import sk.uniza.fri.game.essentials.Prikazy;
import sk.uniza.fri.world.Svet;

import java.io.*;

/**
 * 28. 3. 2022 - 12:58
 *
 * Trieda Hra má nastarosti samotný priebeh hry. Obsauje základné príkazy pre pohyb po mape, výpis informácií alebo ukončenie hry.
 * Trieda bola inšpirovaná triedou Hra z cvičení predmetu Informatika 2
 *
 * @author Filip Šefčík
 */
public class Hra implements Serializable {
    private Hrac hrac;
    private Svet mapa;
    private Prikazy prikazy;

    /**
     * Bezparametrický konštruktor triedy Hra
     * Inicializujú sa v ňom všetky atribúty
     * Vytvorí sa hráč, mapa a príkazy, ktoré hráč môže zadávať
     * Nakoniec sa hráčovi ešte nastaví štartovacia a respawn lokácia
     */
    public Hra() {
        this.prikazy = new Prikazy("chod", "vstup", "oslov", "inventar", "parta", "zbierka", "pomoc", "uloz", "nacitaj", "koniec");
        this.mapa = new Svet();
        this.hrac = new Hrac(this.mapa.getZaciatocnaLokacia());
    }

    /**
     * Metóda spúšťa hru, pýta vstupy od hráča až pokiaľ sa ju hráč nerozhodne ukončiť.
     */
    public void spustiHru() {
        this.prikazy.vypisPrikazy();
        boolean hraSa;

        do {
            String[] input = this.prikazy.nacitajInput(this.hrac.getAktualnaLokacia().getNazovLokacie());
            hraSa = this.spracujInput(input);
        } while (hraSa);
        System.out.println("Vďaka za hranie PokemonZ.");
    }

    /**
     * Spracuje input vložený ako parameter, ktorý bol získaný metódou nacitajInput()
     * Podľa inputu sa vyvolá metóda prislúchajúca inputu
     * @param input od hráča v tvare {prikaz, parameter}
     * @return true/false ak zadaný príkaz neukončuje/ukončuje hru
     */
    private boolean spracujInput(String[] input) {
        switch (this.prikazy.getCisloPrikazu(input[0])) {
            case 1:
                this.presunDoLokacie(input[1]);
                return true;
            case 2:
                this.vstupDoBudovy(input[1]);
                return true;
            case 3:
                this.oslovNpC(input[1]);
                return true;
            case 4:
                this.pouziPredmet();
                return true;
            case 5:
                this.hrac.pracujSPartou();
                return true;
            case 6:
                this.hrac.ukazZbierku();
                return true;
            case 7:
                this.hrac.getAktualnaLokacia().vypisOkolie();
                this.vypisNapovedu();
                return true;
            case 8:
                this.ulozHru();
                return true;
            case 9:
                this.nacitajHru();
                return true;
            case 10:
                return false;
            default:
                System.out.println("Neplatný vstup, zadaj znovu");
                return true;
        }
    }

    /**
     * Hráč touto metódu spustí rozhovor s Npc
     * @param menoNpc
     */
    private void oslovNpC(String menoNpc) {
        Npc npc = this.hrac.getAktualnaLokacia().getNpc(menoNpc);
        if (npc != null) {
            npc.rozhovor(this.hrac, this.hrac.getAktualnaLokacia().getNazovLokacie());
        } else {
            System.out.println("Taka osoba sa tu nenachadza");
        }
    }

    /**
     * Hráč použije predmet, ktorý si vyberie
     */
    private void pouziPredmet() {
        AbstractItem predmet = this.hrac.dajPredemt();

        if (predmet != null) {
            this.hrac.pouziPredmet(predmet, null, null);
        }
    }

    /**
     * Hráč "vstúpi" do budovy, do ktorej sa rozhodne vojsť
     * @param nazovBudovy
     */
    private void vstupDoBudovy(String nazovBudovy) {
        if (nazovBudovy == null) {
            System.out.println("Kam chceš vstúpiť?");
            return;
        } else if (this.hrac.getAktualnaLokacia().getLokacia(this.hrac, nazovBudovy) != null) {
            System.out.println("Zadal si názov lokácie, do lokácie sa dá CHODIŤ a do špecifického miesta sa dá VSTÚPIŤ.");
            System.out.println("Ak chceš ísť do lokácie zadaj príkaz \"CHOD\"");
            return;
        }

        AbstractBudova budova = this.hrac.getAktualnaLokacia().getBudova(nazovBudovy);

        if (budova == null) {
            System.out.println("Neviem kde také miesto vidíš, ale žiadna tam nie je...");
        } else {
            budova.vstupDoBudovy(this.hrac);
        }
    }

    /**
     * Hráč sa presunie do lokácie, v skutočnosti sa mu zmení aktuálna lokácia
     * @param nazovLokacie, ktorý bol zadaný k príkazu ako input hráčom a určuje kam sa hráč presunie
     */
    private void presunDoLokacie(String nazovLokacie) {
        if (nazovLokacie == null) {
            System.out.println("A kam by si chcel ísť?");
            return;
        } else if (this.hrac.getAktualnaLokacia().getBudova(nazovLokacie) != null) {
            System.out.println("Zadal si názov miesta, do lokácie sa dá CHODIŤ a do špecifického miesta sa dá VSTÚPIŤ.");
            System.out.println("Ak chceš vstúpiť do miesta zadaj príkaz \"VSTUP\"");
            return;
        }

        Lokacia dalsiaLokacia = this.hrac.getAktualnaLokacia().getLokacia(this.hrac, nazovLokacie);

        if (dalsiaLokacia == null) {
            System.out.println("Tam sa nedá ísť.");
        } else {
            this.hrac.setAktualnaLokacia(dalsiaLokacia);
            this.hrac.getAktualnaLokacia().vypisOkolie();
        }
    }

    /**
     * Na obrazovku sa vypíšu všetky príkazy
     */
    private void vypisNapovedu() {
        this.prikazy.vypisPrikazy();
    }

    /**
     * Metóda vytvorí nového profesora, s ktorým sa spustí rozhovor. Tam si hráč zadá svoje meno a vyberie si svojho starter pokémona.
     * Nakoniec sa profesor pridá do aktuálnej lokácie hráča.
     */
    public void spustiIntro() {
        Profesor profesor = new Profesor();
        profesor.rozhovor(this.hrac);
        this.hrac.getAktualnaLokacia().pridajNpc(profesor);
        this.hrac.getAktualnaLokacia().vypisOkolie();
    }


    /**
     * Vracia výsledok metódy, ktorá sa vyvoláva v save systéme
     * @return
     */
    public boolean maHracUlozenuHru() {
        return SaveSystem.getInstance().maUlozenuHru();
    }

    /**
     * Pomocou tejto metódy môže hráč načítať hru, ktorú ma uloženú
     */
    public void nacitajHru() {
        if (this.maHracUlozenuHru()) {
            File subor = SaveSystem.getInstance().vyberSuborPriNacitani();
            try (ObjectInputStream nacitavac = new ObjectInputStream(new FileInputStream(subor))) {
                this.hrac = (Hrac) nacitavac.readObject();
                this.mapa = (Svet) nacitavac.readObject();
                Rival.setInstance((Rival)nacitavac.readObject());
                PC.setInstance((PC)nacitavac.readObject());
                this.hrac.getAktualnaLokacia().vypisOkolie();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Pri načítavaní nastala chyba.");
            }
        } else {
            System.out.println("Hru si si ešte neuložil, nemáš čo načítať.");
        }
    }

    /**
     * Metóda ukladá hru do súboru vybraného hráčom
     */
    private void ulozHru() {
        File subor = SaveSystem.getInstance().vyberSuborPriUkladani();
        try (ObjectOutputStream ukladac = new ObjectOutputStream(new FileOutputStream(subor))) {
            ukladac.writeObject(this.hrac);
            ukladac.writeObject(this.mapa);
            ukladac.writeObject(Rival.getInstance());
            ukladac.writeObject(PC.getInstance());
        } catch (IOException e) {
            System.out.println("Pri ukladaní nastala chyba.");
        }

    }
}
