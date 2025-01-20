package sk.uniza.fri.interactive.npc;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Tréner je Npc, ktoré má svojich pokémonov a môže bojovať s hráčom
 *
 * @author Filip Šefčík
 */
public class Trener extends Npc {

    private ArrayList<Pokemon> trenerovaParta;
    private String prveStretnutieHlaska;
    private String rematchHlaska;
    private String vitaznaHlaska;
    private String porazenaHlaska;
    private boolean nebojovalProtiHracovi;
    private int pocetZapasov;

    /**
     * V konštruktore sa inicialitujú atribúty, vytvoria trénerove hlášky v rôznych situáciách,
     * jeho meno, či sa dá/nedá s ním bojovať v odvete
     * @param meno
     * @param pocetZapasov
     */
    public Trener(String meno, int pocetZapasov) {
        super(meno);
        this.prveStretnutieHlaska = ZoznamHlasok.getInstance().dajRandomPrvotnuHlasku();
        this.rematchHlaska = ZoznamHlasok.getInstance().dajRandomRematchHlasku();
        this.vitaznaHlaska = ZoznamHlasok.getInstance().dajRandomVitaznuHlasku();
        this.porazenaHlaska = ZoznamHlasok.getInstance().dajRandomPorazenuHlasku();
        this.trenerovaParta = new ArrayList<>();
        this.nebojovalProtiHracovi = true;
        this.pocetZapasov = pocetZapasov;
    }

    /**
     * Setter atribútu prveStretnutieHlaska
     * @param prveStretnutieHlaska
     */
    public void setPrveStretnutieHlaska(String prveStretnutieHlaska) {
        this.prveStretnutieHlaska = prveStretnutieHlaska;
    }

    /**
     * Setter atribútu rematchHlaska
     * @param rematchHlaska
     */
    public void setRematchHlaska(String rematchHlaska) {
        this.rematchHlaska = rematchHlaska;
    }

    /**
     * Setter atribútu vitaznaHlaska
     * @param vitaznaHlaska
     */
    public void setVitaznaHlaska(String vitaznaHlaska) {
        this.vitaznaHlaska = vitaznaHlaska;
    }

    /**
     * Setter atribútu porazenaHlaska
     * @param porazenaHlaska
     */
    public void setPorazenaHlaska(String porazenaHlaska) {
        this.porazenaHlaska = porazenaHlaska;
    }

    /**
     * Setter atribútu pocetZapasov
     * @param pocetZapasov
     */
    public void setPocetZapasov(int pocetZapasov) {
        this.pocetZapasov = pocetZapasov;
    }

    /**
     * @return počet zápasov, ktoré môže tréner odohrať s hráčom
     */
    public int getPocetZapasov() {
        return this.pocetZapasov;
    }

    /**
     * Nastaví trénerovu partu z parametra
     * @param trenerovaParta
     */
    public void setTrenerovaParta(ArrayList<Pokemon> trenerovaParta) {
        this.trenerovaParta = trenerovaParta;
    }

    /**
     * V rozhovore sa spustí súboj s hráčom, ak má tréner ešte zápasy k dispozícií
     * Ak už tréner bojoval proti hráčovi a nemá povolenú odvetu, tak bude rozprávať iba svoju predvolenú hlášku dookola
     * @param hrac
     * @param cesta
     * @return
     */
    @Override
    public boolean rozhovor(Hrac hrac, String... cesta) {
        if (this.pocetZapasov > 0) {
            if (this.nebojovalProtiHracovi) {
                System.out.println(this.prveStretnutieHlaska);
            } else {
                System.out.println(this.rematchHlaska);
            }

            if (this.zacniSubojSHracom(hrac, cesta)) {
                System.out.println(this.porazenaHlaska);
                System.out.println("Podarilo sa ti poraziť " + super.getMeno() + "!");
                int kompenzacia = this.vyratajKompenzaciu();
                hrac.pridajPokecash(kompenzacia);
                System.out.println("Po tomto súboji ti bolo podľa pravidiel protivníkom " + super.getMeno() + " odovzdaných " + kompenzacia + " ₽.");
                this.nebojovalProtiHracovi = false;
                if (this.pocetZapasov > 0) {
                    this.pripravSaNaOdvetu();
                }
                return true;
            } else {
                System.out.println(this.vitaznaHlaska);
                hrac.hracPrehral(false);
                return false;
            }
        } else {
            return super.rozhovor(hrac);
        }
    }

    /**
     * Metóda zniži počet povolených zápasov a zvýši levely všetkých trénerových pokémonov, nech je pri ďalšom strete s hráčom silnejší
     */
    public void pripravSaNaOdvetu() {
        Random generator = new Random();
        this.pocetZapasov--;
        for (Pokemon pokemon : this.trenerovaParta) {
            pokemon.zvysLevelOHodnotu(generator.nextInt(4, 8), true);
        }
    }

    /**
     * @return cena, ktorú musí tréner zaplatiť hráčovi za hráčovu výhru
     */
    public int vyratajKompenzaciu() {
        int kompenzacia = 0;
        for (Pokemon pokemon : this.trenerovaParta) {
            kompenzacia += pokemon.zratajStaty();
        }
        kompenzacia /= 10;
        return kompenzacia;
    }

    /**
     * Z parametrov sa vytvorí trénerovi parta
     * @param levelyPokemonov levely, ktoré budú mať pokémoni
     * @param pokemoni trénerovi pokémoni
     */
    public void nastavPartu(int[] levelyPokemonov, Pokemon[] pokemoni) {
        for (int i = 0; i < pokemoni.length; i++) {
            pokemoni[i].setNieJeDivoky();
            pokemoni[i].zvysLevelOHodnotu(levelyPokemonov[i], false);
            this.trenerovaParta.add(pokemoni[i]);
        }
    }

    /**
     * Spustí sa cyklus, v ktorom sa pre každého trénerovho pokémona spustí súboj s hráčom
     * @param hrac
     * @param cesta, ktorá slúži na identifikáciu stavu, v ktorom sa hráč nachádza
     * @return true/false ak hráč vyhral/prehral
     */
    public boolean zacniSubojSHracom(Hrac hrac, String... cesta) {
        this.vyliecPokemonov();
        System.out.println("Začína sa súboj medzi " + super.getMeno() + " a " + hrac.getMeno() + "!");
        for (Pokemon pokemon : this.trenerovaParta) {
            System.out.println(ZoznamHlasok.getInstance().dajRandomBojovuHlasku() + " " + pokemon.getPrezyvka());
            if (!hrac.zacniSuboj(pokemon, cesta)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metóda vylieči všetkých trénerových pokémonov.
     */
    public void vyliecPokemonov() {
        for (Pokemon pokemon : this.trenerovaParta) {
            pokemon.kompletOzivPokemona();
        }
    }
}
