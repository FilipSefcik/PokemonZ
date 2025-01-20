package sk.uniza.fri.interactive.npc;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.game.essentials.Komunikator;
import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.items.pokeball.Pokeball;
import sk.uniza.fri.items.potion.Potion;
import sk.uniza.fri.items.potion.TypPotionov;
import sk.uniza.fri.items.revive.MegaRevive;
import sk.uniza.fri.items.revive.Revive;
import sk.uniza.fri.items.revive.SuperRevive;
import sk.uniza.fri.items.tm.TM;
import sk.uniza.fri.pokemon.DruhPokemona;
import sk.uniza.fri.pokemon.DruhUtoku;
import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Profesor je špeciálne Npc, ktoré na začiatku novej hry daruje hráčovi prvého pokémona a predstaví ho do hry.
 * Neskôr v priebehu hry kontroluje, koľko má hráč pokémonov v Pokédexe, ak hráč prekoná stanovenú hranicu pokémonov v Pokédexe,
 * Profesor daruje hráčovi nejaký užitočný predmet. Preto je Profesor potomkom triedy StedreNpc.
 *
 * @author Filip Šefčík
 */
public class Profesor extends StedreNpc {

    private Pokemon[] starteri = new Pokemon[] {new Pokemon(DruhPokemona.BULBASAUR), new Pokemon(DruhPokemona.CHARMANDER), new Pokemon(DruhPokemona.SQUIRTLE)};
    private AbstractItem[] darceky = new AbstractItem[] {new Revive(), new SuperRevive(), new MegaRevive(), new TM("TM55", DruhUtoku.FUTURE_SIGHT), new TM("TM38", DruhUtoku.HYPER_BEAM), null};
    private int hranica;


    /**
     * V koštruktore sa vyvolá koštruktor predka a stanoví sa hranica, spolu so špeciálnymi hláškami.
     */
    public Profesor() {
        super("ProfesorSmrek", new Revive());
        this.hranica = 10;
        super.setDarovaciaHlaska("Výborne, podarilo sa ti pozbierať " + this.hranica + " z 50 pokémonov! Toto ti pomôže, v zbieraní ďalších informácií.");
        super.setDefaultHlaska("Som veľmi vďačný za to, že si pozbieral všetky tieto informácie. Nesmierne si pomohol pokroku!");
        for (Pokemon pokemon : this.starteri) {
            pokemon.setNieJeDivoky();
            pokemon.zvysLevelOHodnotu(5, false);
        }
    }

    /**
     * V rozhovore s profesorom sa kontroluje, koľko má hráč pokémonov, ak nemá žiadneho, daruje mi ho.
     * Ak už má, tak skontroluje stav hráčovho Pokédexu.
     * @param hrac
     * @param cesta
     * @return
     */
    @Override
    public boolean rozhovor(Hrac hrac, String... cesta) {
        if (hrac.getPocetPokemonovVParte() < 1) {
            this.nastavMenoHracovi(hrac);
            this.darujStartera(hrac);
            this.vysvetliPokedex();
            if (!Rival.getInstance().rozhovor(hrac, hrac.getAktualnaLokacia().getNazovLokacie(), "intro", "subojSRivalom")) {
               Rival.getInstance().pripravSaNaOdvetu();
            }
            System.out.println("Bol to napínavý zápas, určite sa dostanete na vrchol bez problémov.");
            this.dajZaciatocnePredmety(hrac);
        } else {
            System.out.println("Ahoj " + hrac.getMeno() + ", ako je na tom tvoj Pokédex?");
            if (hrac.getHracovPokedex().getPocetPokemonovVPokedexe() >= this.hranica) {
                super.rozhovor(hrac);
                super.setDarcek(this.darceky[this.hranica/10]);
                this.hranica += 10;
                super.setDarovaciaHlaska("Výborne, podarilo sa ti pozbierať " + this.hranica + " z 50 pokémonov!");
            }
            if (!hrac.getHracovPokedex().maPlnyPokedex()) {
                System.out.println("Zatiaľ máš v Pokédexe " + hrac.getHracovPokedex().getPocetPokemonovVPokedexe() + " z 50 pokémonov, ešte ti ich zopár chýba");
                System.out.println("Vďaka za ukážku priebežnej práce.");
            }
        }
        return true;
    }

    /**
     * Daruje hráčovi základné predmety, ktoré sa mu zjednodušia začiatok hry
     * @param hrac
     */
    private void dajZaciatocnePredmety(Hrac hrac) {
        System.out.println("Pre začiatok vám ešte darujem zopár predmetov, ktoré sa vám zídu.");
        for (int i = 0; i < 5; i++) {
            hrac.vlozDoInventara(new Pokeball());
            hrac.vlozDoInventara(new Potion("Potion", TypPotionov.getInstance().getPotiony().get("Potion")));
        }
        System.out.println(super.getMeno() + " Vám daroval 5 Pokéballov a 5 Potionov.");
        System.out.println("Pomocou Pokéballov môžeš chytať divokých pokémonov, čím unavenejší je, tým väčšia šanca, že ho chytíte.");
        System.out.println("Pomocou Potionov môžete pridať energiu vaším pokémonom.");
    }

    /**
     * Profesor sa spýta hráča na meno, ktoré si hráč určí a následne sa mu nastaví
     * @param hrac
     */
    private void nastavMenoHracovi(Hrac hrac) {
        System.out.println("Vitaj vo svete Pokémonov, presnejšie v regióne Kanto.");
        System.out.println("V tomto svete hráš za osobu, ktorá práve dovršila veku, kedy sa stáva trénerom pokémonov.");
        System.out.println("Prepáč, ešte som sa nepredstavil. Som " + super.getMeno() + ".");
        String menoHraca;
        do {
            menoHraca = Komunikator.zadajSlovo("Ako sa voláš?", "Nezadal si nič, zadaj znovu.");
        } while (!Komunikator.vyberAnoAleboNie("Len aby som sa uistil. Tvoje meno je " + menoHraca + ", však?"));
        hrac.setMeno(menoHraca);
        System.out.println("Veľmi ma teší, " + hrac.getMeno() + ". Teraz keď už máme papierovačky za sebou, \nčas si vybrať svojho starter pokémona.");
    }

    /**
     * Profesor v tejto metóde uba vysvetľuje "oficiálny" cieľ hry.
     */
    private void vysvetliPokedex() {
        System.out.println("Teraz keď už máte svojich pokémonov, teraz vám vysvetlím čo robiť s Pokédexom, ktorý vám dali rodičia.");
        System.out.println("Pokédex je vynález, v ktorom zbierate informácie o všetkých druhoch pokémonov v tomto regióne, ktorých chytíte.");
        System.out.println("Práve sa vám pridali aj pokémoni, ktorých som vám práve dal.");
        System.out.println("Tieto informácie potom my v laboratoriu spracujeme a zisťujeme ďalšie zaujímavosti o pokémonoch!");
        System.out.println("Čiže ak sa vám podarí vyzbierať infromácie o všetkých pokémonoch v tomto regióne, tak mi príďte vaše Pokédexy ukázať.");
        System.out.println("Nebojte sa mi ukázať aj vašu priebežnú prácu.");
        System.out.println("Pre začiatok vám pomožem, máte vo vašich Pokédexoch 1/50 pokémonov. Prajem vám obom veľa šťastia!");
    }

    /**
     * Profesor ponúkne hráčovi jeho prvého pokémona, ktorého si hráč vyberie, k tomu si pokémona vyberie aj hráčov rival
     * @param hrac
     */
    private void darujStartera(Hrac hrac) {
        Rival rival = Rival.getInstance();
        System.out.println("Tejto udalosti sa zúčastnuješ ty a aj tvoj dlhoročný kamarát " + rival.getMeno() + ", ktorý je tvoj kamarát a rival v jednom.");
        System.out.println("Ahoj " + hrac.getMeno() + " a " + rival.getMeno() + "! Prišli ste sa stať už oficiálnymi trénermi pokémonov?");
        System.out.println("V tom prípade vám obom veľmi rád pomôžem. Ponúknem vám partnera, s ktorým môžete začat svoje dobrodružstvo!");

        int indexStaretra = this.ziskajIndexStartera();
        Pokemon hracovPokemon = this.starteri[indexStaretra];
        Pokemon rivalovPokemon = this.vyberRivalovhoPokemona(indexStaretra);
        hrac.pridajPokemona(hracovPokemon);
        System.out.println("Ak tvoj pokémon je " + hracovPokemon.getPrezyvka() + " tak môj pokémon je " + rivalovPokemon.getPrezyvka() + ".");
        rival.pridajRivaloviPokemona(rivalovPokemon);
    }

    /**
     * Rival si vždy vyberá takého pokémona, ktorý má typovú výhodu oproti hráčovmu.
     * V Profesorovom poli starter pokémonov to je pokémon s nasledujúcim indexom,
     * ak hráčov pokémon je posledný pokémon v poli, rivalov pokémon bude prvý v poli.
     * @param indexStaretra
     * @return
     */
    private Pokemon vyberRivalovhoPokemona(int indexStaretra) {
        if (indexStaretra + 1 == this.starteri.length) {
            return this.starteri[0];
        }
        return this.starteri[indexStaretra + 1];
    }

    /**
     * Vyvolá sa metóda ziskajIndex() v triede Komunikator prispôsobená pre špecifický účel
     * @return
     */
    private int ziskajIndexStartera() {
        String otazka = "Vyber index svojho startera";
        String chyba = "Musíš zadať číslo";
        String zlyIndex = "Musíš zadať číslo od 1 do " + this.starteri.length;
        return Komunikator.ziskajIndex(this.starteri.length, otazka, chyba, zlyIndex, true,true,this);
    }

    /**
     * Vypíšu sa sterter pokémoni, ktorí sú ponúkaní profesorom
     */
    public void vypisStarterov() {
        for (int i = 0; i < this.starteri.length; i++) {
            System.out.print((i + 1) + " ");
            this.starteri[i].vypisZakladneInfo();
        }
    }
}
