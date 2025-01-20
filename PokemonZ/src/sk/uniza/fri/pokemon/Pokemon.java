package sk.uniza.fri.pokemon;

import sk.uniza.fri.game.essentials.Komunikator;

import java.io.Serializable;
import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pokemon je trieda opisujúca každého jedného konkrétneho pokémona v hre.
 * Udržuje informácie o jeho mene, druhu, leveli, útokoch, či patrí hráčovi atď.
 *
 * @author Filip Šefčík
 */
public class Pokemon implements Serializable {

    private String prezyvka;
    private DruhPokemona druhPokemona;
    private Utok[] utoky;
    private double[] stats;
    private int level;
    private double aktualneExp;
    private double levelUpExp;
    private double aktualneHp;
    private int indexNajstarsiehoUtoku;
    private boolean jeDivoky;
    private boolean jeHracov;

    /**
     * Pre vytvorenie pokémona potrebujeme iba vedieť akého je druhu.
     * Všetky špecifické informácie sa zo začiatku získjú od druhu pokémona, zvyšné atribúty sa nastavia na defaultné hodnoty.
     * @param druhPokemona
     */
    public Pokemon(DruhPokemona druhPokemona) {
        this.druhPokemona = druhPokemona;
        this.prezyvka = this.druhPokemona.getNazovDruhu();
        this.stats = new double[this.druhPokemona.getBaseStats().length];

        for (int i = 0; i < this.stats.length; i++) {
            this.stats[i] = this.druhPokemona.getBaseStats()[i];
        }

        this.druhPokemona.setDalsiaEvolucia();
        this.utoky = new Utok[4];
        this.utoky[0] = new Utok(this.druhPokemona.getUtokNaLeveli(0));
        this.levelUpExp = this.zratajStaty();
        this.aktualneHp = this.stats[0];
        this.level = 0;
        this.aktualneExp = 0;
        this.indexNajstarsiehoUtoku = 0;
        this.jeDivoky = true;
        this.jeHracov = false;
    }

    /**
     * @return prezývka pokémona
     */
    public String getPrezyvka() {
        return this.prezyvka;
    }

    /**
     * @return druh pokémona
     */
    public DruhPokemona getDruhPokemona() {
        return this.druhPokemona;
    }

    /**
     * @return level pokémona
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * @return obranný stat pokémona
     */
    private double getObrana() {
        return this.stats[2];
    }

    /**
     * @return true/false ak pokémon je/nie je divoký
     */
    public boolean jeDivoky() {
        return this.jeDivoky;
    }

    /**
     * @return true/false ak pokémon patrí/nepatrí hráčovi
     */
    public boolean jeHracov() {
        return this.jeHracov;
    }

    /**
     * Nastaví atribút jeDivoky na false
     */
    public void setNieJeDivoky() {
        this.jeDivoky = false;
    }

    /**
     * @return true/false ak pokémonove aktuálne hp sú/ nie sú väčšie ako 0
     */
    public boolean jeShopnyBoja() {
        return this.aktualneHp > 0;
    }

    /**
     * Nastaví atribút jeDivoky na false a atribút jeHracov na true
     */
    public void chytilSa() {
        this.jeDivoky = false;
        this.jeHracov = true;
    }

    /**
     * Metóda nastaví prezývku pokémona na hodnotu zadanú ako parameter
     * @param prezyvka
     */
    public void setPrezyvka(String prezyvka) {
        this.prezyvka = prezyvka;
    }

    /**
     * @return true/false ak level pokémona je/nie je dostatočne veľký na to, aby sa mohol vyvinúť
     * a zároveň či druh pokémona má/nemá ďalšiu evolúciu
     */
    public boolean mozeSaEvolvnut() {
        return this.level >= this.druhPokemona.getEvolveLevel() && this.druhPokemona.getDalsiaEvolucia() != null;
    }

    /**
     * @return percentuálnu hodnotu aktuálnych hp
     */
    public double vyratajPercentaHp() {
        return this.aktualneHp / this.stats[0] * 100;
    }

    /**
     * @return súčet všetkých pokémonových statov
     */
    public double zratajStaty() {
        double sucet = 0;
        for (double stat : this.stats) {
            sucet += stat;
        }
        return sucet;
    }

    /**
     * Zvýši aktuálne exp o stanovenú hodnotu, iba ak jeho level je menší než 50
     * @param ziskaneExp
     * @return ak sa pokémonovi po pridaní exp zvýšil/nezvýšil level
     */
    public boolean pridajExp(double ziskaneExp) {
        if (this.level < 50) {
            this.aktualneExp += ziskaneExp;
            if (this.aktualneExp >= this.levelUpExp) {
                this.aktualneExp -= this.levelUpExp;
                return true;
            }
        }
        return false;
    }

    /**
     * Zvýši level pokémona o stanovenú hodnotu.
     * @param hodnota o ktorú sa zvýši level
     * @param chcemeEvolve true/false ak sa má/nemá pokémon pri levelovaní vyvinúť
     */
    public void zvysLevelOHodnotu(int hodnota, boolean chcemeEvolve) {
        for (int i = 0; i < hodnota; i++) {
            this.levelUp();
            if (chcemeEvolve && this.mozeSaEvolvnut()) {
                this.evolvniSa();
            }
        }
    }

    /**
     * Zvýši sa level pokémona, spolu s jeho statmi.
     * Ak novo dosiahnutý level má hodnotu, na ktorej sa môže pokémon naučiť útok, tak ho naučí.
     */
    public void levelUp() {
        if (this.level < 50) {
            Random generator = new Random();
            double originalHp = this.stats[0];
            for (int i = 0; i < this.stats.length; i++) {
                this.stats[i] += generator.nextInt(3);
            }
            this.pridajHp(this.stats[0] - originalHp);
            this.levelUpExp = this.zratajStaty();
            this.level++;
            if (this.druhPokemona.nauciSaUtok(this.level)) {
                this.naucNovyUtok();
            }
        }
    }

    /**
     * Naučí pokémona nový útok. Ak je pokémon hráčov, spustí sa na to špecifickú metódu.
     * Ak nie, naučí pokémona útok automaticky.
     */
    private void naucNovyUtok() {
        Utok novyUtok = new Utok(this.druhPokemona.getUtokNaLeveli(this.level));
        if (this.jeHracov && !this.jeDivoky) {
            this.naucUtokHracom(novyUtok);
        } else {
            this.naucUtokAutomaticky(novyUtok);
        }
    }

    /**
     * Metóda informuje hráča o tom, že sa jeho pokémon chce naučiť nový útok.
     * Ak sa rozhodne hráč, že sa pokémon naučí nový útok, tak sa ho naučí automaticky
     * alebo bude musieť hráč vybrať, na ktorý útok má pokémon zabudnúť aby sa naučil nový útok.
     * @param novyUtok
     * @return
     */
    public boolean naucUtokHracom(Utok novyUtok) {
        System.out.println(this.prezyvka + " sa chce naučiť útok " + novyUtok.getNazovUtoku() + ".");
        System.out.println(novyUtok.dajPopisUtoku());

        if (Komunikator.vyberAnoAleboNie("Chceš aby sa ho naučil?")) {
            if (this.pocetNaucenychUtokov() == 4) {
                System.out.println(this.prezyvka + " sa chce naučiť tento útok, ale pozná už priveľa útokov.");
                System.out.println("Ktorý z nich chceš odstrániť aby sa ho naučil?");
                int indexUtoku = this.ziskajIndexUtoku();
                Utok staryUtok = this.utoky[indexUtoku];
                this.utoky[indexUtoku] = novyUtok;
                System.out.println("3.. 2.. 1.. poof! " + this.prezyvka + " zabudol útok " + staryUtok.getNazovUtoku() + ". Ale za to sa naučil " + novyUtok.getNazovUtoku() + " !");
            } else {
                this.naucUtokAutomaticky(novyUtok);
                System.out.println("Super, " + this.prezyvka + " sa naučil útok " + novyUtok.getNazovUtoku() + "!");
            }
            return true;
        } else {
            System.out.println(this.prezyvka + " sa rozhodol tento útok nenaučiť.");
            return false;
        }
    }

    /**
     * Naučí pokémna útok automaticky, v prípade ak pokémon pozná priveľa útokov, tak sa mu odstráni najstarší naučený útok.
     * @param novyUtok
     */
    public void naucUtokAutomaticky(Utok novyUtok) {
        if (this.pocetNaucenychUtokov() == 4) {
            this.utoky[this.indexNajstarsiehoUtoku] = novyUtok;
            this.posunIndexUtoku();
        } else {
            for (int i = 0; i < this.utoky.length; i++) {
                if (this.utoky[i] == null) {
                    this.utoky[i] = novyUtok;
                    return;
                }
            }
        }
    }

    /**
     * Posunie index najstaršieho útoku o 1, ak je index rovnako veľký ako počet možných útokov, tak sa začne znova rátať od 0;
     */
    private void posunIndexUtoku() {
        if ((this.utoky.length - 1) == this.indexNajstarsiehoUtoku) {
            this.indexNajstarsiehoUtoku = 0;
        } else {
            this.indexNajstarsiehoUtoku++;
        }
    }

    /**
     * @return počet útokov, ktoré je pokémon momentálne naučený
     */
    private int pocetNaucenychUtokov() {
        int pocetNaucenychUtokov = 0;
        for (Utok utok : this.utoky) {
            if (utok != null) {
                pocetNaucenychUtokov++;
            }
        }
        return pocetNaucenychUtokov;
    }

    /**
     * Vyvinie pokémona na jeho ďališu evolúciu, v prípade ak ju má.
     */
    public void evolvniSa() {
        if (this.druhPokemona.getDalsiaEvolucia() != null) {
            double[] bonusStaty = this.dajBonusStaty();
            String staraPrezyvka = this.prezyvka;
            if (this.prezyvka.equals(this.druhPokemona.getNazovDruhu())) {
                this.setPrezyvka(this.druhPokemona.getDalsiaEvolucia().getNazovDruhu());
            }
            this.druhPokemona = this.druhPokemona.getDalsiaEvolucia();
            this.druhPokemona.setDalsiaEvolucia();
            this.nastavBonusStaty(bonusStaty);
            if (this.jeHracov) {
                System.out.println("Čo sa to deje?...");
                System.out.println(staraPrezyvka + " sa vyvíja!");
                System.out.println("Wow! Z " + staraPrezyvka + " sa vyvinul " + this.druhPokemona.getNazovDruhu() + "!");
            }
        } else {
            System.out.println("Pokemon nemá ďalšiu evolúciu.");
        }
    }

    /**
     * Vyráta sa, o koľko sa jednotlivé staty zvýšili a tento bonus sa uloží do pola, ktoré sa return-uje.
     * @return pole s bonus statmi
     */
    private double[] dajBonusStaty() {
        double[] bonusStats = new double[this.stats.length];
        for (int i = 0; i < bonusStats.length; i++) {
            bonusStats[i] = this.stats[i] - this.druhPokemona.getBaseStats()[i];
        }
        return bonusStats;
    }

    /**
     * Bonus staty dané parametrom sa prirátajú k base statom druhu a nastavia sa ako aktuálne staty pokémona.
     * @param bonusStaty
     */
    private void nastavBonusStaty(double[] bonusStaty) {
        double rozdielHp = this.stats[0] - this.aktualneHp;
        for (int i = 0; i < bonusStaty.length; i++) {
            this.stats[i] = bonusStaty[i] + this.druhPokemona.getBaseStats()[i];
        }
        this.aktualneHp = this.stats[0] - rozdielHp;
    }

    /**
     * Aktuálne hp pokémona sa znížia o stanovanú hodnotu.
     * @param damage
     */
    public void odoberHp(double damage) {
        this.aktualneHp -= damage;
        if (this.aktualneHp < 0) {
            this.aktualneHp = 0;
            System.out.println(this.prezyvka + " už nie je schopný boja!");
        }
    }

    /**
     * Aktuálne hp sa zvýšia o stanovenú hodnotu.
     * @param heal
     */
    public void pridajHp(double heal) {
        this.aktualneHp += heal;
        if (this.aktualneHp > this.stats[0]) {
            this.aktualneHp = this.stats[0];
        }
    }

    /**
     * Aktuálne hp sa zvýšia o percentuálnu hodnotu maximálnych hp
     * @param percentoPridania
     */
    public void pridajPercentualneHp(int percentoPridania) {
        double heal = this.stats[0] * percentoPridania / 100;
        this.pridajHp(heal);
    }

    /**
     * Každému útoku sa pridá počet použití v percentuálnej hodnote
     * @param percentoPridania
     */
    public void pridajPercentualnePouzitieVsetkymUtokom(int percentoPridania) {
        for (Utok utok : this.utoky) {
            if (utok != null) {
                utok.pridajPercentualne(percentoPridania);
            }
        }
    }

    /**
     * Metóda nastaví hp aj počet použití všetkým útokom na 100 %
     */
    public void kompletOzivPokemona() {
        this.pridajPercentualneHp(100);
        this.pridajPercentualnePouzitieVsetkymUtokom(100);
    }

    /**
     * Metóda vyráta celkový damage útoku, ktorý sa použil na target pokémona.
     * Do úvahy sa berie typ oboch pokémonov a útoku, staty oboch pokémonov ale taktiež aj náhoda.
     * @param target pokémon, na ktorého sa zaútočilo
     * @param pouzityUtok útok, ktorý tento pokémon používa
     * @return vyrátaná hodnota celkového poškodenia
     */
    private double vyratajDamage(Pokemon target, Utok pouzityUtok) {
        Random random = new Random();

        double stab = this.vyratajSTAB(pouzityUtok.getTypUtoku());
        double efektivita = this.vyratajEfektivitu(pouzityUtok.getTypUtoku(), target);

        if (efektivita == 2 || efektivita == 4) {
            System.out.println("Útok bol super efektívny!");
        } else if (efektivita == 0.5 || efektivita == 0.25) {
            System.out.println("Útok nebol veľmi efektívny.");
        } else if (efektivita == 0) {
            System.out.println("Útok nemal efekt...");
            return 0;
        }

        double damage = (((2 * (double) this.level / 5 + 2) * pouzityUtok.getSilaUtoku() * this.stats[1] / target.getObrana()) / 10 + 2) * efektivita * stab * random.nextDouble(0.85, 1);
        return damage;
    }

    /**
     * Vyráta sa, podľa kompatibility stanoveného typu a typu pokémona, či sa typy rovnajú.
     * @param typUtoku
     * @return hodnota 1.5/1 ak pokémon a typ sú/nie sú rovnaké typy.
     */
    private double vyratajSTAB(Typ typUtoku) {
        for (Typ typPokemona : this.druhPokemona.getTypPokemona()) {
            if (typPokemona.getNazovTypu().equals(typUtoku.getNazovTypu())) {
                return 1.5;
            }
        }
        return 1;
    }

    /**
     * Vyráta sa efektivita typu útoku voči stanovenému pokémonovi.
     * @param typUtoku
     * @param target
     * @return hodnota záležiaca od toho, akú efektivitu mal typ útoku na typ pokémona
     */
    private double vyratajEfektivitu(Typ typUtoku, Pokemon target) {
        double efektivita = 1;
        for (Typ typPokemona : target.getDruhPokemona().getTypPokemona()) {
            if (typUtoku.nemaEffectProti(typPokemona)) {
                return 0;
            } else if (typUtoku.jeSuperEffectiveProti(typPokemona)) {
                efektivita *= 2;
            } else if (typUtoku.jeNotVeryEffectiveProti(typPokemona)) {
                efektivita *= 0.5;
            }
        }
        return efektivita;
    }

    /**
     * @return náhodný útok, ktorý sa dá stále použiť
     */
    public Utok dajRandomUtok() {
        Random random = new Random();
        Utok utok;
        do {
            utok = this.utoky[random.nextInt(this.utoky.length)];
        } while (utok == null || utok.getPocetPouziti() == 0);
        return utok;
    }

    /**
     * @return true/false ak aspoň jeden útok má/nemá aspoň nejaký počet použití
     */
    public boolean mozePouzitUtok() {
        for (Utok utok : this.utoky) {
            if (utok != null) {
                if (utok.getPocetPouziti() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Pokémon použije útok na protivníka.
     * Ak je hráčov, tak útok vyberie hráč, ak nie, použije sa náhodný útok.
     * @param protivnik
     */
    public void zautocNaProtivnika(Pokemon protivnik) {
        Utok vybranyUtok;
        if (this.mozePouzitUtok()) {
            if (this.jeHracov) {
                boolean neplatnyUtok = true;
                do {
                    vybranyUtok = this.utoky[this.ziskajIndexUtoku()];
                    if (vybranyUtok != null) {
                        if (vybranyUtok.getPocetPouziti() == 0) {
                            System.out.println("Na tento útok už " + this.prezyvka + " nemá silu.");
                        } else {
                            neplatnyUtok = false;
                        }
                    } else {
                        System.out.println("Vybral si prázdny slot!");
                    }
                } while (neplatnyUtok);
            } else {
                vybranyUtok = this.dajRandomUtok();
            }
        } else {
            System.out.println(this.prezyvka + " už nemá silu použiť žiaden útok.");
            vybranyUtok = new Utok(DruhUtoku.STRUGGLE);
            this.odoberHp(this.stats[0] / 4);
        }

        if (vybranyUtok.pouziUtok(this)) {
            double damage = this.vyratajDamage(protivnik, vybranyUtok);
            protivnik.odoberHp(damage);
        }

    }

    /**
     * Metóda vracia útok na stanovenom indexe.
     * @param indexUtoku
     * @return
     */
    public Utok vyberUtok(int indexUtoku) {
        return this.utoky[indexUtoku];
    }


    /**
     * Používa sa metóda ziskajIndex() triedy Komunikator
     * @return
     */
    public int ziskajIndexUtoku() {
        String otazka = "Zadaj index útoku.";
        String chyba = "Musíš zadať číslo prislúchajúce útoku.";
        String zlyIndex = "Inedx mimo rozsahu, zadaj znova";
        return Komunikator.ziskajIndex(this.utoky.length, otazka, chyba, zlyIndex, true, true, this);
    }

    /**
     * @return true/false ak všetky útoky majú/aspoň jeden nemá plný počet použití
     */
    public boolean maPlnyPocetPouziti() {
        for (Utok utok : this.utoky) {
            if (utok != null && !utok.maPlnyPocetPouziti()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param druhUtoku
     * @return true/false ak pokémon má/nemá daný útok medzi svojimi útokmi
     */
    public boolean poznaUtok(DruhUtoku druhUtoku) {
        for (Utok utok : this.utoky) {
            if (utok != null) {
                if (utok.getDruhUtoku().getNazovUtoku().equals(druhUtoku.getNazovUtoku())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Vypíšu sa všetky pokémonove útoky
     */
    public void vypisUtoky() {
        for (int i = 0; i < this.utoky.length; i++) {
            Utok utok = this.utoky[i];
            if (utok != null) {
                System.out.println((i + 1) + " " + utok.getNazovUtoku() + " TYP: " + utok.getTypUtoku() + " " +  utok.vypisPouzitie());
            } else {
                System.out.println((i + 1) + " Prázdny slot");
            }
        }
    }

    /**
     * Vypíšu sa informácie, ktoré sú potrebné vedieť počas boja
     * Level, hp a exp
     */
    public void vypisSaVBoji() {
        System.out.printf("%s LVL %d\n" +
                        "\tHP: %.2f/%.2f\n" +
                        "\tEXP: %.2f/%.2f\n",
                this.prezyvka, this.level, this.aktualneHp, this.stats[0], this.aktualneExp, this.levelUpExp);
    }

    /**
     * Vypíšu sa podrobné informácie o pokémonovi
     */
    public void vypisPodrobneInfo() {
        System.out.printf("%s: %s, LVL: %d, TYP: %s, HP: %.2f/%.2f, EXP: %.2f/%.2f\n", this.prezyvka, this.druhPokemona.getNazovDruhu(), this.level, this.druhPokemona.getNazovTypu(), this.aktualneHp, this.stats[0], this.aktualneExp, this.levelUpExp);
    }

    /**
     * Vypíšu sa základné informácie o pokémonovi
     */
    public void vypisZakladneInfo() {
        System.out.printf("%s: LVL: %d, TYP: %s, HP: %.2f/%.2f\n", this.prezyvka, this.level, this.druhPokemona.getNazovTypu(), this.aktualneHp, this.stats[0]);
    }

    /**
     * Vypíšu sa staty pokémona
     */
    private void vypisStaty() {
        System.out.printf("HP: %.0f, ATK: %.0f, DEF: %.0f\n", this.stats[0], this.stats[1], this.stats[2]);
    }

    /**
     * Vypíšu sa všetky informácie o pokémonovi, podrobné info, staty a aj útoky.
     */
    public void vypisSaKompletne() {
        this.vypisPodrobneInfo();
        this.vypisStaty();
        this.vypisUtoky();
    }
}
