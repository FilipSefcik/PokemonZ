package sk.uniza.fri.game.essentials;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.budovy.Obchod;
import sk.uniza.fri.interactive.npc.Profesor;
import sk.uniza.fri.interactive.budovy.pokecentrum.PC;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pomocou tejto triedy sa vyvolávajú metódy spojené s komunikáciou s hráčom, konkrétne týkajúce sa získavania informácií
 *
 * @author Filip Šefčík
 */
public class Komunikator {

    /**
     * Metóda pýta vstup, konkrétne číslo, od hráča až pokiaľ nezadá ten správny
     * @param maxHranica
     * @param pytajucaHlaska
     * @param chybovaHlaska
     * @param indexovaHlaska
     * @param pracaSPolom
     * @param vyvolatMetodu
     * @param objekt
     * @return index zadaný hráočm
     */
    public static int ziskajIndex(int maxHranica, String pytajucaHlaska, String chybovaHlaska, String indexovaHlaska, boolean pracaSPolom, boolean vyvolatMetodu, Object objekt) {
        Scanner skener = new Scanner(System.in);
        System.out.println(pytajucaHlaska);
        if (vyvolatMetodu) {
            vyvolajMetodu(objekt);
        }
        if (!pracaSPolom) {
            maxHranica++;
            System.out.print("ziskavaniePoctu> ");
        } else {
            System.out.print("ziskavanieIndexu> ");
        }

        try {
            int index = skener.nextInt();

            if (pracaSPolom) {
                index--;
            }

            if (index >= 0 && index < maxHranica) {
                return index;
            } else {
                System.out.println(indexovaHlaska);
            }

        } catch (InputMismatchException e) {
            System.out.println(chybovaHlaska);
        }
        return ziskajIndex(maxHranica, pytajucaHlaska, chybovaHlaska, indexovaHlaska, pracaSPolom, false, objekt);
    }

    /**
     * Vyvolá sa metóda výpisu, podľa toho o inštanciu akej triedy sa jedná, aby sa užívateľovi zjednodušilo hľadanie indexu
     * @param objekt ktorého hláška sa má vypisovať
     */
    private static void vyvolajMetodu(Object objekt) {
        if (objekt instanceof Hrac) {
            ((Hrac)objekt).vypisPartu();
        } else if (objekt instanceof Pokemon) {
            ((Pokemon)objekt).vypisUtoky();
        } else if (objekt instanceof Obchod) {
            ((Obchod) objekt).vypisTovar();
        } else if (objekt instanceof PC) {
            ((PC)objekt).vypisPokemonov();
        } else if (objekt instanceof Profesor) {
            ((Profesor)objekt).vypisStarterov();
        } else if (objekt instanceof SaveSystem) {
            ((SaveSystem)objekt).vypisSubory();
        }
    }

    /**
     * Hráč má zadať áno alebo nie, aby odpovedal na danú otázku, väčšinou týkajúcej sa potvrdenia informácie
     * @param otvorenaOtazka
     * @return true/false ak hráč zadá kladnú/zápornú odpoveď
     */
    public static boolean vyberAnoAleboNie(String otvorenaOtazka) {
        Scanner skener = new Scanner(System.in);
        System.out.println(otvorenaOtazka + " [ano/nie]");
        System.out.print("AnoAleboNie> ");
        String odpoved = skener.nextLine();

        while (odpoved.isBlank() || (!odpoved.equals("ano") && !odpoved.equals("nie"))) {
            System.out.println("Zadajte ano alebo nie");
            System.out.print("AnoAleboNie> ");
            odpoved = skener.nextLine();
        }

        return odpoved.equals("ano");
    }

    /**
     * Metóda zisťuje od hráča slovo, ktoré chce zadať, používa sa napráklad pri zadávaní prezývky pokémonovi
     * @param hlaska
     * @param chyba
     * @return slovo, ktoré hráč zadal
     */
    public static String zadajSlovo(String hlaska, String chyba) {
        Scanner skener = new Scanner(System.in);
        System.out.println(hlaska);
        System.out.print("zadajSlovo> ");
        String slovo = skener.nextLine();

        while (slovo.isBlank()) {
            System.out.println(chyba);
            System.out.print("zadajSlovo> ");
            slovo = skener.nextLine();
        }

        return slovo;
    }


}
