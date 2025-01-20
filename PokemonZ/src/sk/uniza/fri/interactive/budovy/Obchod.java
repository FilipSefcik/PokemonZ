package sk.uniza.fri.interactive.budovy;

import sk.uniza.fri.game.essentials.Komunikator;
import sk.uniza.fri.game.essentials.Prikazy;
import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.items.potion.AbstractPotion;
import sk.uniza.fri.items.tm.TM;

import java.util.ArrayList;

/**
 * 28. 3. 2022 - 12:58
 *
 * V inštanciách triedy Obchod bude môcť hráč nakupovať nové predmety alebo predať svoje predmety
 *
 * @author Filip Šefčík
 */
public class Obchod extends AbstractBudova {

    private ArrayList<AbstractItem> tovar;
    private ArrayList<Integer> cenyTovaru;
    private Prikazy predajTovaru;
    private Prikazy kupaTovaru;

    /**
     * V konštruktore sa inicializujú atribúty a nastavia sa príkazy čo sa v tejto budove používajú
     * @param nazovBudovy
     */
    public Obchod(String nazovBudovy) {
        super(nazovBudovy, "nakupit", "predat", "pomoc", "odist");
        this.tovar = new ArrayList<>();
        this.cenyTovaru = new ArrayList<>();
        this.predajTovaru = new Prikazy("kupit", "ponuka", "pomoc", "naspat");
        this.kupaTovaru = new Prikazy("predat", "inventar", "pomoc", "naspat");
    }

    @Override
    public void odchodZBudovy() {
        System.out.println("Vďaka za nakupovanie u nás! Príďte zas!");
        System.out.println("Prevádzka " + super.getNazovBudovy() + " je pre Vás vždy otvorená!");
    }

    @Override
    public void uvitaciaHlaska() {
        System.out.println("Rozhodol si sa ísť nakupovať do obchodu s názvom " + super.getNazovBudovy() + ".");
        System.out.println("Hádam tu nájdeš to, čo hľadáš.");
    }

    @Override
    public boolean spracujInput(Hrac hrac, String[] vstup) {
        switch (super.getCisloPrikazu(vstup[0])) {
            case 1:
                this.predajHracovi(hrac);
                return true;
            case 2:
                this.kupOdHraca(hrac);
                return true;
            case 3:
                super.vypisPrikazy();
                return true;
            case 4:
                this.odchodZBudovy();
                return false;
            default:
                System.out.println("Zle som Vás rozumel, zopakujete to prosím?");
                return true;
        }
    }


    /**
     * Pridá sa predmet a jeho cena do inventára Obchodu
     * @param item ktorý bude Obchod ponúkať
     * @param cena za ktorú sa bude tovar predávať
     */
    public void nastavTovar(AbstractItem item, int cena) {
        this.tovar.add(item);
        this.cenyTovaru.add(cena);
    }

    /**
     * Metóda si pýta vstupy od hráča ktoré sa používajú pri nákupe od hráča
     * @param hrac
     */
    private void kupOdHraca(Hrac hrac) {
        if (hrac.maHracItem()) {
            this.kupaTovaru.vypisPrikazy();
            boolean hracDopredal;
            do {
                String[] input = this.kupaTovaru.nacitajInput(hrac.getAktualnaLokacia().getNazovLokacie(), super.getNazovBudovy(), "predaj");
                hracDopredal = this.spracujInputPriKupe(hrac, input);
            } while (hracDopredal);
        } else {
            System.out.println("Nemáte mi čo predať, Váš inventár je prázdny.");
        }
    }

    /**
     * Spracuje sa vstup čo zadal hráč
     * @param hrac
     * @param vstup
     * @return true/false ak hráč bude/nebude pokračovať v predaji jeho predmetov Obchodu
     */
    private boolean spracujInputPriKupe(Hrac hrac, String[] vstup) {
        switch (this.kupaTovaru.getCisloPrikazu(vstup[0])) {
            case 1:
                this.kupTovarOdHraca(hrac);
                return true;
            case 2:
                hrac.vypisInventar();
                return true;
            case 3:
                this.kupaTovaru.vypisPrikazy();
                return true;
            case 4:
                return false;
            default:
                System.out.println("Zle som Vás rozumel, zopakujete to prosím?");
                return true;
        }
    }

    /**
     * Tu prebieha samotný proces kúpy predmetu od hráča, ktorý nám chce predať
     * Hráč si vyberie predmet, potom počet kusova potom sa ich Obchod od neho odkúpy za 80 % ceny
     * Obchod nekúpy od hráča predmety, ktoré neponúka samotný Obchod
     * @param hrac
     */
    private void kupTovarOdHraca(Hrac hrac) {
        System.out.println("Čo by ste chceli predať?");
        AbstractItem predmet = hrac.dajPredemt();
        if (predmet != null) {
            if (this.maObchodDanyTovar(predmet)) {
                int maxPocetKusov = hrac.getMnozstvoPredmetu(predmet);
                if (maxPocetKusov > 0) {
                    System.out.println("Koľko kusov chcete predať?");
                    int pocetKusov = this.ziskajPocetKusov(maxPocetKusov, false);
                    if (pocetKusov > 0) {
                        int cenaPredmetu = this.zistiKupnuCenuPredmetu(predmet);
                        int cenaPredaja = cenaPredmetu * pocetKusov;
                        System.out.println("Predmet: " + predmet.getNazovPredmetu() + ", Cena za 1 kus: " + cenaPredmetu + " ₽");
                        for (int i = 0; i < pocetKusov; i++) {
                            if (!hrac.odstranItem(predmet)) {
                                System.out.println("Nastal problém pri predaji predmetu " + predmet.getNazovPredmetu() + ". Predaj bol zrušený.");
                                return;
                            }
                            hrac.pridajPokecash(cenaPredmetu);
                        }
                        System.out.println("Úspešne ste predali " + pocetKusov + "-krát " + predmet.getNazovPredmetu() + ".");
                        System.out.println("Cena predaja: " + cenaPredaja + " ₽ Stav peňaženky: " + hrac.getPokeCash() + " ₽");
                    } else {
                        System.out.println("Nabudúce sa nebojte predať toho viac, radi to od Vás kúpime.");
                    }
                }
            } else {
                System.out.println("Ospravedlňujem sa, ale " + predmet.getNazovPredmetu() + " nie som schopní od Vás kúpiť.");
            }
        } else {
            System.out.println("Vyzerá to tak, že nastal problém pri výbere predmetu z inventára, skúste to znova.");
        }
    }

    /**
     * Tu sa vyráta koľko Obchod zaplatí hráčovi za 1 kus predmetu
     * @param predmet
     * @return
     */
    private int zistiKupnuCenuPredmetu(AbstractItem predmet) {
        for (int i = 0; i < this.tovar.size(); i++) {
            if (this.tovar.get(i).getNazovPredmetu().equals(predmet.getNazovPredmetu())) {
                return (int)(this.cenyTovaru.get(i) * 0.8);
            }
        }
        return 0;
    }

    /**
     * @param predmet
     * @return true/false ak Obchod ponúka/neponúka predmet zadaný parametrom
     */
    private boolean maObchodDanyTovar(AbstractItem predmet) {
        if (predmet instanceof TM) {
            return false;
        } else if (predmet instanceof AbstractPotion) {
            for (AbstractItem predmetVObchode : this.tovar) {
                if (predmetVObchode.getNazovPredmetu().equals(predmet.getNazovPredmetu())) {
                    return true;
                }
            }
        } else {
            for (AbstractItem tovar : this.tovar) {
                System.out.println(tovar.getClass());
                if (tovar.getClass().equals(predmet.getClass())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Metóda si pýta vstupy od hráča ktoré sa používajú pri predaji tovaru hráčovi
     * @param hrac
     */
    private void predajHracovi(Hrac hrac) {
        if (!this.tovar.isEmpty()) {
            this.vypisTovar();
            this.predajTovaru.vypisPrikazy();
            boolean hracDonakupoval;
            do {
                String[] input = this.predajTovaru.nacitajInput(hrac.getAktualnaLokacia().getNazovLokacie(), super.getNazovBudovy(), "nakup");
                hracDonakupoval = this.spracujInputPriPredaji(hrac, input);
            } while (hracDonakupoval);
        } else {
            System.out.println("Ospravedlňujem sa, momentálne sme vypredaní.");
        }
    }

    /**
     * Spracuje sa vstup čo zadal hráč
     * @param hrac
     * @param vstup
     * @return true/false ak hráč bude/nebude pokračovať v nákupe predmetov z Obchodu
     */
    private boolean spracujInputPriPredaji(Hrac hrac, String[] vstup) {
        switch (this.predajTovaru.getCisloPrikazu(vstup[0])) {
            case 1:
                this.predajTovarHracovi(hrac);
                return true;
            case 2:
                this.vypisTovar();
                return true;
            case 3:
                this.predajTovaru.vypisPrikazy();
                return true;
            case 4:
                return false;
            default:
                System.out.println("Zle som Vás rozumel, zopakujete to prosím?");
                return true;
        }
    }

    /**
     * Tu prebieha samotný predaj hráčovi
     * Hráčovi sa predá tovar, ktorého číslo zadal v metóde od Komunikator
     * Potom zadá koľko kusov chce kúpiť podľa toho, koľko si môže dovoliť
     * @param hrac
     */
    private void predajTovarHracovi(Hrac hrac) {

        int indexTovaru = this.ziskajIndexTovaru();
        AbstractItem predmet = this.tovar.get(indexTovaru);
        int cenaPredmetu = this.cenyTovaru.get(indexTovaru);
        int maxPocetKusov = hrac.getPokeCash() / cenaPredmetu;
        System.out.println("Peňaženka: " + hrac.getPokeCash() + " ₽ Cena predmetu: " + cenaPredmetu + " ₽");

        if (maxPocetKusov > 0) {
            int pocetKusov;
            if (predmet instanceof TM) {
                pocetKusov = 1;
            } else {
                System.out.println("Koľko kusov predmetu " + predmet.getNazovPredmetu() + " si chcete kúpiť?");
                pocetKusov = this.ziskajPocetKusov(maxPocetKusov, true);
            }
            if (pocetKusov > 0) {
                int cenaNakupu = cenaPredmetu * pocetKusov;
                for (int i = 0; i < pocetKusov; i++) {
                    if (!hrac.vlozDoInventara(predmet)) {
                        System.out.println("Nastal problém pri nákupe predmetu " + predmet.getNazovPredmetu() + ". Nákup sa zrušil");
                        return;
                    }
                    hrac.odoberPokecash(cenaPredmetu);
                    if (predmet instanceof TM) {
                        this.odstranTovarZPonuky(predmet);
                    }
                }
                System.out.println("Predmet " + predmet.getNazovPredmetu() + " bol úspešne pridaný do Vášho inventára " + pocetKusov + "-krát.");
                System.out.println("Bude to spolu " + cenaNakupu + " ₽");
                System.out.println("Stav penazenky po nakupe: " + hrac.getPokeCash() + " ₽");
            } else {
                System.out.println("Nula kusov? Nebojte sa nabudúce aj viac kúpit.");
            }
        } else {
            System.out.println("Nemáte dosť ₽ na zakúpenie tohto predmetu.");
        }

    }

    /**
     * Odstráni predmet z ponuky obchodu
     * Používa sa hlavne pri TM, aby sa dodržala jeho rarita
     * @param predmet
     */
    private void odstranTovarZPonuky(AbstractItem predmet) {
        this.cenyTovaru.remove(this.tovar.indexOf(predmet));
        this.tovar.remove(predmet);
    }

    /**
     * Pýta od hráča počet koľko kusov predmetu chce predať/kúpiť
     * @param maxPocetKusov maximálny počet predmetov čo môže hráč kúpiť/predať
     * @param vyvolatMetodu true/false ak sa má/nemá metóda vypisTovar vypísať na začiatku
     * @return počet kusov, čo zadal hráč
     */
    public int ziskajPocetKusov(int maxPocetKusov, boolean vyvolatMetodu) {
        String otazka = "Maximálny počet kusov: " + maxPocetKusov;
        String chyba = "Musíte zadať číslo!";
        String zlyIndex = "Číslo musíte zadať z rozsahu od 0 do " + maxPocetKusov;
        return Komunikator.ziskajIndex(maxPocetKusov, otazka, chyba, zlyIndex, false, vyvolatMetodu, this);
    }

    /**
     * @return index tovaru, ktorý si hráč chce kúpiť
     */
    public int ziskajIndexTovaru() {
        String otazka = "Zadajte index tovaru, ktorý si chcete kúpiť.";
        String chyba = "Musíte zadať číslo!";
        String zlyIndex = "Číslo musíte zadať z rozsahu od 1 do " + this.tovar.size();
        return Komunikator.ziskajIndex(this.tovar.size(), otazka, chyba, zlyIndex, true, true, this);
    }

    /**
     * Vypíše sa ponuka Obchodu aj s cenami
     */
    public void vypisTovar() {
        if (!this.tovar.isEmpty()) {
            System.out.println("Nech sa páči, naša dnešná ponuka.");
            for (int i = 0; i < this.tovar.size(); i++) {
                System.out.println((i + 1) + " " + this.tovar.get(i).getNazovPredmetu() + " cena: " + this.cenyTovaru.get(i) + " ₽");
            }
        } else {
            System.out.println("Momentálne sme vypredaní, ospravedlňujeme sa Vám.");
        }
    }
}
