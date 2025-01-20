package sk.uniza.fri.hrac;

import sk.uniza.fri.interactive.npc.ZoznamHlasok;
import sk.uniza.fri.game.essentials.Komunikator;
import sk.uniza.fri.items.AbstractItem;
import sk.uniza.fri.items.pokeball.AbstractPokeball;
import sk.uniza.fri.items.potion.AbstractPotion;
import sk.uniza.fri.items.revive.AbstractRevive;
import sk.uniza.fri.items.revive.MegaRevive;
import sk.uniza.fri.world.location.Lokacia;
import sk.uniza.fri.pokemon.DruhUtoku;
import sk.uniza.fri.interactive.budovy.pokecentrum.PC;
import sk.uniza.fri.pokemon.Pokemon;
import sk.uniza.fri.game.essentials.Prikazy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * Trieda Hrac identifikuje postavu, za ktorú hráč hraje a ovláda akcie, ktoré vykoná.
 *
 * @author Filip Šefčík
 */
public class Hrac implements Serializable {

    private String meno;
    private int pokeCash;
    private Inventar inventar;
    private ArrayList<Pokemon> party;
    private final int VELKOST_PARTY = 6;
    private Lokacia aktualnaLokacia;
    private Lokacia respawnLokacia;
    private boolean hracJeVBoji;
    private ArrayList<Odznaky> odznaky;
    private Prikazy prikazyVBoji;
    private Prikazy prikazySPartou;
    private Pokedex hracovPokedex;
    private Pokemon nahradnyPokemon;

    /**
     * Konštruktor vytvorí hráča, inicializuje atribúty a nastaví ich na defaultné hodnoty
     * @param zaciatocnaLokacia, v ktorej hráč začína hrať
     */
    public Hrac(Lokacia zaciatocnaLokacia) {
        this.meno = "Zed";
        this.pokeCash = 200;
        this.inventar = new Inventar();
        this.party = new ArrayList<>();
        this.hracJeVBoji = false;
        this.setAktualnaLokacia(zaciatocnaLokacia);
        this.setRespawnLokacia(zaciatocnaLokacia);
        this.odznaky = new ArrayList<>();
        this.prikazyVBoji = new Prikazy("utok", "pokemon", "item", "utiect");
        this.prikazySPartou = new Prikazy("info", "vymen", "pomoc", "spat");
        this.hracovPokedex = new Pokedex();
        this.nahradnyPokemon = null;
    }

    /**
     * @return hráčovo meno
     */
    public String getMeno() {
        return meno;
    }

    /**
     * @return maximálny počet pokémonov, ktorých môže mať hráč pri sebe
     */
    public int getVELKOST_PARTY() {
        return this.VELKOST_PARTY;
    }

    /**
     * @return hráčov Pokédex
     */
    public Pokedex getHracovPokedex() {
        return this.hracovPokedex;
    }

    /**
     * @return množstvo peňazí na hráčovom konte
     */
    public int getPokeCash() {
        return this.pokeCash;
    }

    /**
     * @return lokácia v ktorej sa hráč nachádza
     */
    public Lokacia getAktualnaLokacia() {
        return this.aktualnaLokacia;
    }

    /**
     * @return počet pokémonov, ktorých má hráč pri sebe
     */
    public int getPocetPokemonovVParte() {
        return this.party.size();
    }

    /**
     * Nastaví meno hráčovi
     * @param menoHraca, ktoré chce hráč mať
     */
    public void setMeno(String menoHraca) {
        this.meno = menoHraca;
    }

    /**
     * Nastaví hráčovi lokáciu v ktorej sa nachádza
     * @param lokacia, do ktorej sa háč presunul
     */
    public void setAktualnaLokacia(Lokacia lokacia) {
        this.aktualnaLokacia = lokacia;
    }

    /**
     * Nastaví hráčovi lokáciu, do ktorej sa hráč presunie ak jeho pokémoni nebudú schopní boja
     * @param respawnLokacia, miesto kde hráč naposledy navštívil Pokécentrum a vyliečil si v ňom pokémonov
     */
    public void setRespawnLokacia(Lokacia respawnLokacia) {
        this.respawnLokacia = respawnLokacia;
    }

    /**
     * @param odznak, ktorý chceme zistiť či hráč má
     * @return true/false ak hráč už vlastní/nevlastní zadaný odznak
     */
    public boolean ziskalOdznak(Odznaky odznak) {
        return this.odznaky.contains(odznak);
    }

    /**
     * @return true/false ak hráčov inventár nie je/je prázdny
     */
    public boolean maHracItem() {
        return this.inventar.obsahujeNiecoInventar();
    }

    /**
     * @param item, ktorý sa má vložiť
     * @return true/false ak sa predmet úspešne/neúspešne vložil do inventára
     */
    public boolean vlozDoInventara(AbstractItem item) {
        return this.inventar.vlozDoInventara(item);
    }

    /**
     * Vyvolá sa metóda ziskajIndex() triedy Komunikator
     * @return index pokémona z party
     */
    public int ziskajIndexPokemona() {
        String otazka = "Zadaj index pokémona, ktorého chceš vybrať.";
        String chyba = "Musíš zadať index pokémona, čiže číslo.";
        String zlyIndex = "Neplatný index pokémona";
        return Komunikator.ziskajIndex(this.party.size(), otazka, chyba, zlyIndex, true, true,this);
    }

    /**
     * @param indexPokemona, ktorého chceme získať
     * @return pokémon so zadaným indexom v parte, ktorého má hráč pri sebe
     */
    public Pokemon dajPokemona(int indexPokemona) {
        return this.party.get(indexPokemona);
    }

    /**
     * Vloží pokémona do party
     * @param pokemon, ktorého chceme pridať
     */
    public void vlozDoParty(Pokemon pokemon) {
        if (this.party.add(pokemon)) {
            System.out.println(pokemon.getPrezyvka() + " sa úspešne pridal do party.");
        } else {
            System.out.println("Pri vkladaní pokémona do party nastalo k problému.");
        }
    }

    /**
     * Odstráni pokémona z party
     * @param indexPokemona, ktorý sa má odstrániť
     */
    public void odstranPokemona(int indexPokemona) {
        this.party.remove(indexPokemona);
    }

    /**
     * Pridá novo získaného pokémona do party. Ak je plná, pridá sa do PC
     * @param novyPokemon, ktorého hráč získal alebo chytil
     */
    public void pridajPokemona(Pokemon novyPokemon) {
        novyPokemon.chytilSa();
        this.hracovPokedex.vlozDoPokedexu(novyPokemon);
        this.nastavPokemonoviPrezyvku(novyPokemon);
        if (this.party.size() < this.VELKOST_PARTY) {
            this.vlozDoParty(novyPokemon);
        } else {
            System.out.println("Parta je plná, " + novyPokemon.getPrezyvka() + " sa pridá automaticky do PC.");
            PC.getInstance().pridajPokemona(novyPokemon);
        }
    }

    /**
     * Hráč nastaví pokémonovi prezývku, ak chce
     * @param novyPokemon pokémon, ktorému sa nastaví prezývka
     */
    private void nastavPokemonoviPrezyvku(Pokemon novyPokemon) {
        if (Komunikator.vyberAnoAleboNie("Bude mať " + novyPokemon.getPrezyvka() + " novú prezývku?")) {
            String prezyvkaPokemona;
            do {
                prezyvkaPokemona = Komunikator.zadajSlovo("Nastav novú prezývku: ", "Zadal si neplatnú prezývku, zadaj znovu");
            } while (!Komunikator.vyberAnoAleboNie("Pokémon " + novyPokemon.getPrezyvka() + " bude mať prezývku " + prezyvkaPokemona + " súhlasíš?"));
            novyPokemon.setPrezyvka(prezyvkaPokemona);
        }
    }

    /**
     * Metóda prejde každého pokémona v parte, ak aspoň jeden pokémon pozná útok zadaný parametrom, vráti true
     * @param druhUtoku, útok ktorý sa kontroluje
     * @return true/false ak aspoň jeden pokémon z party má/nemá daný útok v movesete
     */
    public boolean poznaPartaUtok(DruhUtoku druhUtoku) {
        for (Pokemon pokemon : this.party) {
            if (pokemon.poznaUtok(druhUtoku)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metóda inicializuje súboj medzi hráčom a protívnikom
     * @param oponent divoký pokémon alebo trénerov pokémon, proti ktorému sa bojuje
     * @param cesta indikátor toho, kde a s kým súboj prebieha
     * @return true/false ak hráč vyhral/nevyhral alebo true ak sa hráč rozhodol utiecť z boja alebo sa mu podarilo chytiť pokémona
     */
    public boolean zacniSuboj(Pokemon oponent, String... cesta) {

        this.hracJeVBoji = true;
        boolean spravnostVstupu;
        Pokemon hracovPokemon = this.dajSchopnehoPokemona();
        this.nahradnyPokemon = hracovPokemon;
        if (hracovPokemon != null) {
            int pocetKol = 1;
            do {

                if (!hracovPokemon.jeShopnyBoja()) {
                    hracovPokemon = this.dajSchopnehoPokemona();
                    if (hracovPokemon != null) {
                        System.out.println("Ďalši na rade je " + hracovPokemon.getPrezyvka());
                    }
                }

                System.out.println();
                System.out.println("Čislo kola " + pocetKol);
                oponent.vypisSaVBoji();
                hracovPokemon.vypisSaVBoji();
                System.out.println();
                do {
                    this.prikazyVBoji.vypisPrikazy();
                    String[] input = this.prikazyVBoji.nacitajInput(cesta);
                    spravnostVstupu = this.spracujInput(oponent, hracovPokemon, input);

                    if (input[0] != null) {
                        if (input[0].equals("pokemon")) {
                            hracovPokemon = this.nahradnyPokemon;
                        }
                    }
                } while (spravnostVstupu);


                if (!this.hracJeVBoji || oponent.jeHracov()) {
                    return true;
                }

                pocetKol++;
            } while (this.getPocetSchopnychPokemonov() > 0 && oponent.jeShopnyBoja());

            this.hracJeVBoji = false;

            if (this.getPocetSchopnychPokemonov() > 0 && hracovPokemon.getLevel() < 50) {
                double ziskaneExp = oponent.zratajStaty() * oponent.getLevel() / 100 * pocetKol;
                System.out.printf("%s získal %.2f exp.\n", hracovPokemon.getPrezyvka(), ziskaneExp);
                if (hracovPokemon.pridajExp(ziskaneExp)) {
                    System.out.println(hracovPokemon.getPrezyvka() + " sa zvýšil level na " + hracovPokemon.getLevel() + "!");
                    hracovPokemon.levelUp();
                    if (hracovPokemon.mozeSaEvolvnut()) {
                        hracovPokemon.evolvniSa();
                        this.hracovPokedex.vlozDoPokedexu(hracovPokemon);
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Nemal si pri sebe žiadnych pokémonov schopných boja.");
            System.out.println("Protivník ťa zaskočil, nemal si šancu reagovať.");
            return false;
        }

    }

    /**
     * Spracuváva input vložený hráčom počas boja
     * @param oponent
     * @param hracovPokemon
     * @param input
     * @return true/false podla toho, ak bol input neúspešne/úspešne spracovaný
     */
    private boolean spracujInput(Pokemon oponent, Pokemon hracovPokemon, String[] input) {
        switch (this.prikazyVBoji.getCisloPrikazu(input[0])) {
            case 1:
                this.zautocteNaSeba(oponent, hracovPokemon);
                return false;
            case 2:
                if (this.vystriedajPokemonov(hracovPokemon)) {
                    oponent.zautocNaProtivnika(this.nahradnyPokemon);
                }
                return false;
            case 3:
                AbstractItem predmet = this.dajPredemt();
                if (predmet != null) {
                    this.pouziPredmet(predmet, oponent, hracovPokemon);
                    return false;
                }
                return true;
            case 4:
                this.utecZBoja(oponent);
                return false;
            default:
                System.out.println("Nesprávny vstup, zadaj znova.");
                return true;
        }
    }

    /**
     * Hráč si vyberie a použije vybraný predmet, metóda je prispôsobená pre prípad ak je hhráč v boji
     * @param oponent na ktorého sa použije Pokéball alebo zaútočí na hráčovho pokémona
     * @param hracovPokemon na ktorého oponent zaútočí, ak hráč použije predmet
     */
    public void pouziPredmet(AbstractItem predmet, Pokemon oponent, Pokemon hracovPokemon) {

        if (predmet instanceof AbstractPokeball) {
            if (oponent != null && this.hracJeVBoji) {
                System.out.println(this.meno + " použil " + predmet.getNazovPredmetu() + " !");
                if (predmet.pouziPredmet(oponent)) {
                    this.pridajPokemona(oponent);
                    this.odstranItem(predmet);
                    return;
                }
                this.odstranItem(predmet);
            } else {
                System.out.println("Pokébally môžeš použiť iba v boji!");
            }
        } else if (predmet instanceof MegaRevive) {
            for (Pokemon pokemon : this.party) {
                predmet.pouziPredmet(pokemon);
            }
            this.odstranItem(predmet);
        } else {
            Pokemon pokemon = this.party.get(this.ziskajIndexPokemona());
            if (pokemon != null) {
                if (predmet.pouziPredmet(pokemon)) {
                    this.odstranItem(predmet);
                }
            }
        }

        if (oponent != null && hracovPokemon != null) {
            oponent.zautocNaProtivnika(hracovPokemon);
        }
    }

    /**
     * Hráč utečie z boja iba v prípade, ak bojuje proti divokému pokémonovi
     * @param oponent
     */
    private void utecZBoja(Pokemon oponent) {
        if (oponent.jeDivoky()) {
            System.out.println(this.meno + " sa dal na útek.");
            this.hracJeVBoji = false;
        } else {
            System.out.println("Z boja proti trénerovi sa nedá utiecť.");
        }
    }

    /**
     * Hráč pošle do boja iného pokémona z party, ak môže, následne naňho zaútočí oponent
     * @param hracovPokemon
     * @return true/false ak sa pokemoni uspesne/neuspesne vysriedali
     */
    private boolean vystriedajPokemonov(Pokemon hracovPokemon) {
        if (this.getPocetSchopnychPokemonov() > 1) {
            do {
                int indexNahradnika = this.ziskajIndexPokemona();
                Pokemon nahradnik = this.party.get(indexNahradnika);
                if (nahradnik.equals(hracovPokemon)) {
                    System.out.println("Tento pokémon sa práve nachádza v boji, zadaj iného pokémona.");
                } else {
                    if (nahradnik.jeShopnyBoja()) {
                        System.out.print(hracovPokemon.getPrezyvka() + "! Vráť sa späť! ");
                        this.nahradnyPokemon = nahradnik;
                        System.out.println(ZoznamHlasok.getInstance().dajRandomBojovuHlasku() + " " + nahradnik.getPrezyvka());
                        return true;
                    } else {
                        System.out.println(nahradnik.getPrezyvka() + " nie je schopný boja, vyber iného.");
                    }
                }
            } while (true);
        } else {
            if (this.party.size() > 1) {
                System.out.println("Nikto iný nie je schopný boja. " + hracovPokemon.getPrezyvka() + " je tvoj posledný pokémon.");
            } else {
                System.out.println("Iného pokémona nemáš. " + hracovPokemon.getPrezyvka() + " je tvoj jediný pokémon.");
            }
        }
        return false;
    }

    /**
     * Oponent a hráčov pokémon na seba zaútočia v náhodnom poradí
     * @param oponent
     * @param hracovPokemon
     */
    private void zautocteNaSeba(Pokemon oponent, Pokemon hracovPokemon) {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            this.zautocteVPoradi(hracovPokemon, oponent);
        } else {
            this.zautocteVPoradi(oponent, hracovPokemon);
        }
    }

    /**
     * Pokémoni zadaní ako parametre na seba zaútočia prvý pokémon zaútočí a ak to druhý prežije, tak zaútočí aj on
     * @param prvy, pokémon čo zaútočí ako prvý
     * @param druhy, pokémon čo zaútočí ako druhý
     */
    public void zautocteVPoradi(Pokemon prvy, Pokemon druhy) {
        prvy.zautocNaProtivnika(druhy);
        if (druhy.jeShopnyBoja()) {
            druhy.zautocNaProtivnika(prvy);
        }
    }

    /**
     * Vypíše sa ak hráčovi pokémoni nie sú schopní boja, stratí peniaze a vráti sa na respawn lokáciu
     * @param protiDivokemuPokemonovi true/false ak hráč prehral proti divokému/trénerovmu pokémonovi
     */
    public void hracPrehral(boolean protiDivokemuPokemonovi) {
        int strata = this.pokeCash / 10;
        this.odoberPokecash(strata);
        if (protiDivokemuPokemonovi) {
            System.out.println("V tomto napínavom boji si prehral a musel si od tohto divokého pokémona utiecť");
            System.out.println("Pri tom ako si utekal ti vypadlo " + strata + " ₽.");
        } else {
            System.out.println("Po tejto napínavej bitke si odišiel so zdvihnutou hlavou.");
            System.out.println("Podľa trénerských pravidiel si odovzdal protivníkovi " + strata + " ₽.");
        }
        System.out.println("Vrátil si sa do lokácie " + this.respawnLokacia.getNazovLokacie());
        this.setAktualnaLokacia(this.respawnLokacia);
        this.vyliecPokemonov();
        this.aktualnaLokacia.vypisOkolie();
    }

    /**
     * Metóda kompletne vylieči všetkých hráčových pokémonov
     */
    public void vyliecPokemonov() {
        for (Pokemon pokemon : this.party) {
            pokemon.kompletOzivPokemona();
        }
    }

    /**
     * Hráčovi sa odrátajú peniaze
     * @param strata
     */
    public void odoberPokecash(int strata) {
        this.pokeCash -= strata;
    }

    /**
     * Hráčovi sa pridajú peniaze
     * @param profit
     */
    public void pridajPokecash(int profit) {
        this.pokeCash += profit;
    }

    /**
     * @return počet pokémonov v parte, ktorí sú schopný boja
     */
    public int getPocetSchopnychPokemonov() {
        int pocetSchopnychPokemonov = 0;
        for (Pokemon pokemon : this.party) {
            if (pokemon.jeShopnyBoja()) {
                pocetSchopnychPokemonov++;
            }
        }
        return pocetSchopnychPokemonov;
    }

    /**
     * @return prvý pokémon v parte, ktorý je schopný boja
     */
    private Pokemon dajSchopnehoPokemona() {
        for (Pokemon pokemon : this.party) {
            if (pokemon.jeShopnyBoja()) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * @param odznak, ktorý sa dá hráčovi
     */
    public void dajHracoviOdznak(Odznaky odznak) {
        this.odznaky.add(odznak);
    }

    /**
     * @return predmet, krotý si hráč vybral
     */
    public AbstractItem dajPredemt() {
        if (this.maHracItem()) {
            return this.inventar.vyberItem();
        } else {
            System.out.println("Máš prázdny inventár!");
            return null;
        }
    }

    /**
     * Odstráni predmet z inventára
     * @param item, ktorý sa má odstrániť
     * @return true/false ak sa predmet odstránil/neodstránil
     */
    public boolean odstranItem(AbstractItem item) {
        return this.inventar.odstranItem(item);
    }

    /**
     * Vráti koľkokrát sa daný predmet nachádza v inventári, platí iba pre predmety ktorých môže mať hráč viac
     * @param predmet, ktorého počet sa má zistiť
     * @return početnosť predmetu
     */
    public int getMnozstvoPredmetu(AbstractItem predmet) {
        if (predmet instanceof AbstractPokeball) {
            return this.inventar.dajPocetPokeballov(predmet);
        } else if (predmet instanceof AbstractPotion) {
            return this.inventar.dajPocetPotionov(predmet);
        } else if (predmet instanceof AbstractRevive) {
            return this.inventar.dajPocetRevivov(predmet);
        }
        System.out.println("Tento typ predmetu sa neskladuje v kusoch ale jednotlivo.");
        return 0;
    }

    /**
     * Metóda vymení miesta v parte pre dvoch pokémonov vybraných hráčom
     */
    public void vymenPokemonovVParte() {
        if (this.party.size() > 1) {
            int indexPrvehoPokemona = this.ziskajIndexPokemona();
            int indexDruhehoPokemona = this.ziskajIndexPokemona();
            while (indexPrvehoPokemona == indexDruhehoPokemona) {
                System.out.println("Zadal si index toho istého pokémona, zadaj znova.");
                indexDruhehoPokemona = this.ziskajIndexPokemona();
            }

            Collections.swap(this.party, indexPrvehoPokemona, indexDruhehoPokemona);
            this.vypisPartu();
        } else {
            System.out.println("Méš nedostatočný počet pokémonov v parte.");
        }
    }

    /**
     * Pomcou tejto metódy hráč spravuje partu
     */
    public void pracujSPartou() {
        if (!this.party.isEmpty()) {
            this.vypisPartu();
            boolean spravnyVstup;
            this.prikazySPartou.vypisPrikazy();
            do {
                String[] input = this.prikazySPartou.nacitajInput(this.getAktualnaLokacia().getNazovLokacie(), "parta");
                spravnyVstup = this.spracujInputSPartou(input);
            } while (spravnyVstup);
        } else {
            System.out.println("V parte nemáš žiadnych pokémonov.");
        }
    }

    /**
     * Spracúvava inputy, ktoré boli zadané hráčom pri práci s partou
     * @param input, ktorý sa má spracovať
     * @return true/false ak hráč bude/nebude ďalej pracovať s partou
     */
    private boolean spracujInputSPartou(String[] input) {
        switch (this.prikazySPartou.getCisloPrikazu(input[0])) {
            case 1:
                this.zistiInfoOPokemonovi();
                return true;
            case 2:
                this.vymenPokemonovVParte();
                return true;
            case 3:
                this.prikazySPartou.vypisPrikazy();
                return true;
            case 4:
                return false;
            default:
                System.out.println("Nesprávny vstup.");
                return true;
        }
    }

    /**
     * Vypíšu sa podrobné informácie o konkrétnom pokémonovi z party
     */
    private void zistiInfoOPokemonovi() {
        int indexPokemona = this.ziskajIndexPokemona();
        this.party.get(indexPokemona).vypisSaKompletne();
    }

    /**
     * Vypíše sa Pokédex spolu so zoznamom odznakov a peňaženkou
     */
    public void ukazZbierku() {
        System.out.println("Pokédex: ");
        this.hracovPokedex.vypisPokedex();
        System.out.println("Odznaky: ");
        this.vypisOdznaky();
        System.out.println("Peňaženka: " + this.pokeCash + " ₽");
    }

    /**
     * Vypíše hráčovi partu
     */
    public void vypisPartu() {
        if (this.party.isEmpty()) {
            System.out.println("Nemáš v parte žiadnych pokémonov.");
        } else {
            for (int i = 0; i < this.party.size(); i++) {
                System.out.print((i + 1) + " - ");
                this.party.get(i).vypisZakladneInfo();
            }
        }
    }

    /**
     * Vypíše hráčovi inventár
     */
    public void vypisInventar() {
        if (this.maHracItem()) {
            this.inventar.vypisInventar();
        } else {
            System.out.println("Máš prázdny inventár.");
        }
    }

    /**
     * Vypíšu sa hráčove odznaky, ktoré získal
     */
    public void vypisOdznaky() {
        if (!this.odznaky.isEmpty()) {
            for (Odznaky odznak : this.odznaky) {
                System.out.println(odznak.getNazovOdznaku() + ": získaný v meste " + odznak.getLokaciaZiskania());
            }
        } else {
            System.out.println("Nezískal si žiadne odznaky!!!");
        }
    }
}
