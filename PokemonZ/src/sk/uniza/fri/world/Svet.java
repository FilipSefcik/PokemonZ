package sk.uniza.fri.world;

import sk.uniza.fri.hrac.Odznaky;
import sk.uniza.fri.interactive.budovy.Obchod;
import sk.uniza.fri.interactive.budovy.Stadion;
import sk.uniza.fri.interactive.budovy.pokecentrum.Pokecentrum;
import sk.uniza.fri.interactive.budovy.wild.Divocina;
import sk.uniza.fri.interactive.npc.Npc;
import sk.uniza.fri.interactive.npc.StedreNpc;
import sk.uniza.fri.interactive.npc.Trener;
import sk.uniza.fri.items.pokeball.*;
import sk.uniza.fri.items.potion.Potion;
import sk.uniza.fri.items.potion.PowerPotion;
import sk.uniza.fri.items.potion.TypPotionov;
import sk.uniza.fri.items.revive.MegaRevive;
import sk.uniza.fri.items.revive.Revive;
import sk.uniza.fri.items.revive.SuperRevive;
import sk.uniza.fri.items.tm.TM;
import sk.uniza.fri.pokemon.DruhPokemona;
import sk.uniza.fri.pokemon.DruhUtoku;
import sk.uniza.fri.pokemon.Pokemon;
import sk.uniza.fri.world.location.Lokacia;
import sk.uniza.fri.world.location.priechody.Priechod;
import sk.uniza.fri.world.location.priechody.PriechodSODznakom;
import sk.uniza.fri.world.location.priechody.PriechodSRivalom;
import sk.uniza.fri.world.location.priechody.PriechodSUtokom;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 28. 3. 2022 - 12:58
 *
 * Svet je trieda, v ktorej sa nastavia všetky detaily sveta a obsahuje všetky lokácie, ktoré sa vytvorili.
 * Trieda Mapa z cvičení predmetu Informatika 2 slúžila ako predloha pre túto triedu
 *
 * @author Filip Šefčík
 */
public class Svet implements Serializable {

    private Lokacia zaciatocnaLokacia;
    private ArrayList<Lokacia> lokacie;

    /**
     * V bezparametrickom konštrukore sa vytvorí celý svet od úplného začiatku, od každého pokémona cez každú budovu až do každej lokácie.
     */
    public Svet() {
        this.lokacie = new ArrayList<>();

        Lokacia zdiar = new Lokacia("Zdiar", "horské mesto s vikingskou minulosťou");
        Lokacia domov = new Lokacia("domov", "tu sa to všetko začína");
        Lokacia dedina = new Lokacia("dedina", "malá dedinka na konci lesa");
        Lokacia mesto = new Lokacia("mesto", "jedno z malebných miest tohto regióna");
        Lokacia gabcikovo = new Lokacia("Gabcikovo", "mesto prežívajúce hlavne z bohatých zdrojov vody");
        Lokacia banickaDedina = new Lokacia("banickaDedina", "dedina plná pracovitých baníkov uprostred hory");
        Lokacia sopka = new Lokacia("sopka", "malé mesto nachádzajúce sa pod neaktívnou sopkou.");
        Lokacia ghostTown = new Lokacia("ghostTown", "opustené mesto, ktoré teraz slúži ako turistická atrakcia");
        Lokacia karateCity = new Lokacia("karateCity", "mesto plné silných a ambicióznych ľudí");

        domov.pridajNpc(new StedreNpc("suseda", new Greatball()));
        domov.pridajNpc(new Npc("sused"));
        Divocina les = new Divocina("les");
        les.nastavSpawner(2, 5, new int[] {15, 15, 10, 10, 1}, DruhPokemona.WEEDLE, DruhPokemona.ODDISH, DruhPokemona.PIDGEY, DruhPokemona.PIKACHU, DruhPokemona.BULBASAUR);
        les.pridajTrenera(new Trener("Joe", 3), new int[] {2, 2}, new Pokemon(DruhPokemona.WEEDLE), new Pokemon(DruhPokemona.ODDISH));
        domov.postavBudovu(les);

        dedina.pridajNpc(new Npc("pekar"));
        dedina.pridajNpc(new Npc("student"));
        Divocina rybnik = new Divocina("rybnik");
        rybnik.nastavSpawner(5, 8, new int[] {20, 20, 10, 1}, DruhPokemona.PSYDUCK, DruhPokemona.SHELLDER, DruhPokemona.JIGGLYPUFF, DruhPokemona.SQUIRTLE);
        rybnik.pridajTrenera(new Trener("Rybar Joe", 3), new int[] {5, 6}, new Pokemon(DruhPokemona.PSYDUCK), new Pokemon(DruhPokemona.SHELLDER));
        rybnik.pridajTrenera(new Trener("Plavec Kristof", 3), new int[] {3, 4, 5}, new Pokemon(DruhPokemona.PIDGEY), new Pokemon(DruhPokemona.PSYDUCK), new Pokemon(DruhPokemona.SQUIRTLE));
        dedina.postavBudovu(rybnik);

        mesto.pridajNpc(new StedreNpc("postar", new Revive()));
        mesto.pridajNpc(new Npc("zmrzlinar"));
        Stadion startStadion = new Stadion(mesto, 3, Odznaky.START_BADGE);
        startStadion.pridajTrenera(new Trener("Morty", 3), false, new int[] {3, 5}, new Pokemon(DruhPokemona.WEEDLE), new Pokemon(DruhPokemona.PIDGEY));
        startStadion.pridajTrenera(new Trener("Rick", 3), true, new int[] {6, 6, 6}, new Pokemon(DruhPokemona.PIDGEY), new Pokemon(DruhPokemona.JIGGLYPUFF), new Pokemon(DruhPokemona.PIKACHU));
        mesto.postavBudovu(startStadion);
        mesto.postavBudovu(new Pokecentrum(mesto));
        Obchod mestskyObchod = new Obchod("pokeJednota");
        mestskyObchod.nastavTovar(new Pokeball(), 10);
        mestskyObchod.nastavTovar(new Greatball(), 20);
        mestskyObchod.nastavTovar(new Potion("Potion", TypPotionov.getInstance().getPotiony().get("Potion")), 15);
        mestskyObchod.nastavTovar(new Potion("SuperPotion", TypPotionov.getInstance().getPotiony().get("SuperPotion")), 20);
        mesto.postavBudovu(mestskyObchod);

        Npc banik = new Npc("banik");
        banik.setDefaultHlaska("Na to aby sa človek dostal do vedľajšieho mesta potrebuje \njeden z jeho pokémonov vedieť Surf, preto ho predávajú v obchode ako TM22.");
        banickaDedina.pridajNpc(banik);
        banickaDedina.pridajNpc(new StedreNpc("jaskyniar", new Ultraball()));
        banickaDedina.pridajNpc(new Npc("geolog"));
        Obchod banickyObchod = new Obchod("ObchodUBanika");
        banickyObchod.nastavTovar(new Pokeball(), 15);
        banickyObchod.nastavTovar(new Greatball(), 20);
        banickyObchod.nastavTovar(new Ultraball(), 30);
        banickyObchod.nastavTovar(new MegaRevive(), 200);
        banickyObchod.nastavTovar(new TM("TM03", DruhUtoku.KARATE_CHOP), 100);
        banickyObchod.nastavTovar(new TM("TM22", DruhUtoku.SURF), 100);
        banickaDedina.postavBudovu(banickyObchod);
        Divocina jaskyna = new Divocina("jaskyna");
        jaskyna.nastavSpawner(10, 15, new int[] {40, 40, 19, 1}, DruhPokemona.GEODUDE, DruhPokemona.SHELLDER, DruhPokemona.MACHOP, DruhPokemona.ARTICUNO);
        jaskyna.pridajTrenera(new Trener("Jano", 4), new int[] {10, 14}, new Pokemon(DruhPokemona.DRATINI), new Pokemon(DruhPokemona.GRAVELER));
        jaskyna.pridajTrenera(new Trener("archeolog", 3), new int[] {11, 10}, new Pokemon(DruhPokemona.KAKUNA), new Pokemon(DruhPokemona.MACHOP));
        jaskyna.pridajTrenera(new Trener("turista", 4), new int[] {10, 8, 10}, new Pokemon(DruhPokemona.MACHOP), new Pokemon(DruhPokemona.GEODUDE), new Pokemon(DruhPokemona.JIGGLYPUFF));
        banickaDedina.postavBudovu(jaskyna);

        gabcikovo.pridajNpc(new Npc("Jergus"));
        gabcikovo.pridajNpc(new Npc("biznisman"));
        gabcikovo.pridajNpc(new StedreNpc("dochodca", new Netball()));
        gabcikovo.postavBudovu(new Pokecentrum(gabcikovo));
        Obchod gabcikMarket = new Obchod("GabcikMarket");
        gabcikMarket.nastavTovar(new Netball(), 20);
        gabcikMarket.nastavTovar(new Evolveball(), 20);
        gabcikMarket.nastavTovar(new PowerPotion("Energizer", TypPotionov.getInstance().getPowerPotiony().get("Energizer")), 25);
        gabcikMarket.nastavTovar(new Potion("SuperPotion", TypPotionov.getInstance().getPotiony().get("SuperPotion")), 30);
        gabcikMarket.nastavTovar(new TM("TM25", DruhUtoku.THUNDERBOLT), 500);
        gabcikMarket.nastavTovar(new TM("TM40", DruhUtoku.HYDRO_PUMP), 1000);
        gabcikovo.postavBudovu(gabcikMarket);
        Stadion gabStadion = new Stadion(gabcikovo, 3, Odznaky.WATERFALL_BADGE);
        gabStadion.pridajTrenera(new Trener("plavcik", 3), false, new int[] {17, 18, 17}, new Pokemon(DruhPokemona.PSYDUCK), new Pokemon(DruhPokemona.WARTORTLE), new Pokemon(DruhPokemona.SHELLDER));
        gabStadion.pridajTrenera(new Trener("potapac", 3), false, new int[] {15, 14}, new Pokemon(DruhPokemona.SQUIRTLE), new Pokemon(DruhPokemona.SHELLDER));
        gabStadion.pridajTrenera(new Trener("Misty", 3), true, new int[] {17, 18, 19}, new Pokemon(DruhPokemona.GOLDUCK), new Pokemon(DruhPokemona.WARTORTLE), new Pokemon(DruhPokemona.CLOYSTER));
        gabcikovo.postavBudovu(gabStadion);
        Divocina elektraren = new Divocina("vodnaElektraren");
        elektraren.nastavSpawner(17, 22, new int[] {40, 40, 19, 1}, DruhPokemona.MAGNEMITE, DruhPokemona.PIKACHU, DruhPokemona.WARTORTLE, DruhPokemona.ZAPDOS);
        elektraren.pridajTrenera(new Trener("veduciElektrarne", 3), new int[] {16, 15, 11, 14}, new Pokemon(DruhPokemona.MAGNEMITE), new Pokemon(DruhPokemona.PIKACHU), new Pokemon(DruhPokemona.PIDGEOTTO), new Pokemon(DruhPokemona.SHELLDER));
        gabcikovo.postavBudovu(elektraren);

        sopka.pridajNpc(new StedreNpc("doktor", new SuperRevive()));
        sopka.pridajNpc(new Npc("dieta"));
        sopka.pridajNpc(new StedreNpc("sprievodca", new Ultraball()));
        Divocina krater = new Divocina("krater");
        krater.nastavSpawner(20, 26, new int[] {40, 40, 19, 1}, DruhPokemona.PONYTA, DruhPokemona.JIGGLYPUFF, DruhPokemona.CHARMANDER, DruhPokemona.MOLTRES);
        krater.pridajTrenera(new Trener("vedec", 3), new int[] {20, 16, 15}, new Pokemon(DruhPokemona.CHARMELEON), new Pokemon(DruhPokemona.PIDGEOTTO), new Pokemon(DruhPokemona.GRAVELER));
        krater.pridajTrenera(new Trener("dovolenkar", 4), new int[] {16, 23, 20}, new Pokemon(DruhPokemona.GASTLY), new Pokemon(DruhPokemona.RAPIDASH), new Pokemon(DruhPokemona.IVYSAUR));
        krater.pridajTrenera(new Trener("ohnoChrlic", 3), new int[] {15, 12, 10, 11}, new Pokemon(DruhPokemona.JIGGLYPUFF), new Pokemon(DruhPokemona.MACHOP), new Pokemon(DruhPokemona.SQUIRTLE), new Pokemon(DruhPokemona.ABRA));
        sopka.postavBudovu(krater);

        ghostTown.pridajNpc(new StedreNpc("Andrej", new Revive()));
        Divocina opustenaBudova = new Divocina("opustenaBudova");
        opustenaBudova.nastavSpawner(26, 30, new int[] {50, 10, 20, 1}, DruhPokemona.GASTLY, DruhPokemona.HAUNTER, DruhPokemona.ABRA, DruhPokemona.MEWTWO);
        opustenaBudova.pridajTrenera(new Trener("ghostBuster", 2), new int[] {26, 27}, new Pokemon(DruhPokemona.GASTLY), new Pokemon(DruhPokemona.KADABRA));
        Obchod spookyShop = new Obchod("SpookyShop");
        spookyShop.nastavTovar(new Healball(), 30);
        spookyShop.nastavTovar(new Ultraball(), 30);
        spookyShop.nastavTovar(new Potion("HyperPotion", TypPotionov.getInstance().getPotiony().get("HyperPotion")), 50);
        spookyShop.nastavTovar(new PowerPotion("RedTauros", TypPotionov.getInstance().getPowerPotiony().get("RedTauros")), 50);
        spookyShop.nastavTovar(new TM("TM27", DruhUtoku.SHADOW_BALL), 500);
        ghostTown.postavBudovu(spookyShop);

        karateCity.pridajNpc(new Npc("bezec"));
        karateCity.pridajNpc(new Npc("silak"));
        Trener kulturista = new Trener("kulturista", 2);
        kulturista.nastavPartu(new int[] {30, 33}, new Pokemon[] {new Pokemon(DruhPokemona.MACHOKE), new Pokemon(DruhPokemona.PIDGEOT)});
        karateCity.pridajNpc(kulturista);
        karateCity.pridajNpc(new StedreNpc("coach", new PowerPotion("GokuEnergy", TypPotionov.getInstance().getPowerPotiony().get("GokuEnergy"))));
        karateCity.postavBudovu(new Pokecentrum(karateCity));
        Obchod muscleShop = new Obchod("SilnyObchod");
        muscleShop.nastavTovar(new PowerPotion("GokuEnergy", TypPotionov.getInstance().getPowerPotiony().get("GokuEnergy")), 100);
        muscleShop.nastavTovar(new Revive(), 50);
        muscleShop.nastavTovar(new SuperRevive(), 100);
        muscleShop.nastavTovar(new MegaRevive(), 500);
        muscleShop.nastavTovar(new TM("TM21", DruhUtoku.CROSS_CHOP), 500);
        muscleShop.nastavTovar(new TM("TM35", DruhUtoku.DAZZLING_GLEAM), 500);
        karateCity.postavBudovu(muscleShop);
        Stadion gymGym = new Stadion(karateCity, 2, Odznaky.MUSCLE_BADGE);
        gymGym.pridajTrenera(new Trener("Rock", 2), false, new int[] {30, 29}, new Pokemon(DruhPokemona.MACHOP), new Pokemon(DruhPokemona.MACHOKE));
        gymGym.pridajTrenera(new Trener("JohnCena", 2), false, new int[] {28, 24, 26}, new Pokemon(DruhPokemona.MACHOP), new Pokemon(DruhPokemona.CHARMELEON), new Pokemon(DruhPokemona.RAICHU));
        gymGym.pridajTrenera(new Trener("Attila", 2), true, new int[] {30, 31, 28}, new Pokemon(DruhPokemona.MACHAMP), new Pokemon(DruhPokemona.PSYDUCK), new Pokemon(DruhPokemona.MACHOKE));
        karateCity.postavBudovu(gymGym);

        zdiar.pridajNpc(new StedreNpc("Stikut", new TM("TM11", DruhUtoku.DRAGON_BREATH)));
        zdiar.pridajNpc(new Npc("horolezec"));
        zdiar.pridajNpc(new Npc("skialpinista"));
        zdiar.postavBudovu(new Pokecentrum(zdiar));
        Divocina draciaHora = new Divocina("draciaHora");
        draciaHora.nastavSpawner(33, 35, new int[] {30, 25, 15, 1}, DruhPokemona.DRATINI, DruhPokemona.PIDGEOTTO, DruhPokemona.DRAGONAIR, DruhPokemona.DRAGONITE);
        draciaHora.pridajTrenera(new Trener("prieskumnik", 2), new int[] {30, 31}, new Pokemon(DruhPokemona.DRATINI), new Pokemon(DruhPokemona.CHARMANDER));
        draciaHora.pridajTrenera(new Trener("pozorovatelDrakov", 2), new int[] {29, 33}, new Pokemon(DruhPokemona.DRATINI), new Pokemon(DruhPokemona.IVYSAUR));
        zdiar.postavBudovu(draciaHora);
        Stadion dragonLair = new Stadion(zdiar, 2, Odznaky.DRAGON_BADGE);
        dragonLair.pridajTrenera(new Trener("Lance", 2), false, new int[] {35, 33}, new Pokemon(DruhPokemona.DRAGONITE), new Pokemon(DruhPokemona.DRAGONAIR));
        dragonLair.pridajTrenera(new Trener("Cynthia", 2), false, new int[] {29, 33}, new Pokemon(DruhPokemona.DRATINI), new Pokemon(DruhPokemona.CHARMELEON));
        dragonLair.pridajTrenera(new Trener("Boris", 2), true, new int[] {33, 34, 36, 35}, new Pokemon(DruhPokemona.DRAGONAIR), new Pokemon(DruhPokemona.DRAGONITE), new Pokemon(DruhPokemona.CHARIZARD), new Pokemon(DruhPokemona.VENUSAUR));
        zdiar.postavBudovu(dragonLair);

        domov.nastavPriechod("dedina", new Priechod(dedina));
        domov.nastavPriechod("Zdiar", new PriechodSODznakom(zdiar, Odznaky.DRAGON_BADGE));

        dedina.nastavPriechod("domov", new Priechod(domov));
        dedina.nastavPriechod("mesto", new Priechod(mesto));
        dedina.nastavPriechod("Gabcikovo", new PriechodSODznakom(gabcikovo, Odznaky.START_BADGE));

        mesto.nastavPriechod("dedina", new Priechod(dedina));
        mesto.nastavPriechod("banickaDedina", new PriechodSRivalom(banickaDedina, 0));

        banickaDedina.nastavPriechod("mesto", new PriechodSRivalom(mesto, 0));
        banickaDedina.nastavPriechod("Gabcikovo", new PriechodSUtokom(gabcikovo, DruhUtoku.SURF, "Cez toto miesto prúdi divoká rieka. Samotný šlovek sa \ncez ňu nemôže prebrodiť. S pokémonom by to možno šlo."));
        banickaDedina.nastavPriechod("sopka", new Priechod(sopka));

        gabcikovo.nastavPriechod("dedina", new Priechod(dedina));
        gabcikovo.nastavPriechod("banickaDedina", new PriechodSUtokom(banickaDedina, DruhUtoku.SURF, "Cez toto miesto prúdi divoká rieka. Samotný šlovek sa \ncez ňu nemôže prebrodiť. S pokémonom by to možno šlo."));
        gabcikovo.nastavPriechod("sopka", new PriechodSRivalom(sopka, 1));

        sopka.nastavPriechod("banickaDedina", new Priechod(banickaDedina));
        sopka.nastavPriechod("Gabcikovo", new PriechodSRivalom(gabcikovo, 1));
        sopka.nastavPriechod("ghostTown", new Priechod(ghostTown));
        sopka.nastavPriechod("karateCity", new PriechodSODznakom(karateCity, Odznaky.WATERFALL_BADGE));

        ghostTown.nastavPriechod("sopka", new Priechod(sopka));
        ghostTown.nastavPriechod("Zdiar", new PriechodSUtokom(zdiar, DruhUtoku.CROSS_CHOP, "Horský priechod blokuje obrovská skala, je potrebné ju rozbiť."));

        karateCity.nastavPriechod("sopka", new Priechod(sopka));
        karateCity.nastavPriechod("Zdiar", new PriechodSODznakom(zdiar, Odznaky.MUSCLE_BADGE));

        zdiar.nastavPriechod("domov", new PriechodSRivalom(domov, 2));
        zdiar.nastavPriechod("ghostTown", new PriechodSUtokom(ghostTown, DruhUtoku.CROSS_CHOP, "Horský priechod blokuje obrovská skala, je potrebné ju rozbiť."));
        zdiar.nastavPriechod("karateCity", new Priechod(karateCity));

        this.zaciatocnaLokacia = domov;
        this.lokacie.add(domov);
        this.lokacie.add(dedina);
        this.lokacie.add(mesto);
        this.lokacie.add(banickaDedina);
        this.lokacie.add(gabcikovo);
        this.lokacie.add(sopka);
        this.lokacie.add(ghostTown);
        this.lokacie.add(karateCity);
        this.lokacie.add(zdiar);
    }

    /**
     * @return lokácia, v ktorej hráč začína svoju hru
     */
    public Lokacia getZaciatocnaLokacia() {
        return this.zaciatocnaLokacia;
    }
}
