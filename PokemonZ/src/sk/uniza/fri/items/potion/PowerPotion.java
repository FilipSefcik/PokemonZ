package sk.uniza.fri.items.potion;

import sk.uniza.fri.pokemon.Pokemon;
import sk.uniza.fri.pokemon.Utok;

/**
 * 28. 3. 2022 - 12:58
 *
 * Tento typ Potionu pridáva počet použití útokom pokémona
 *
 * @author Filip Šefčík
 */
public class PowerPotion extends AbstractPotion {

    /**
     * V parametrickom konštruktore sa zadáva názov potionu a jeho liečivá sila
     * @param nazovPredmetu
     * @param liecivaSila
     */
    public PowerPotion(String nazovPredmetu, int liecivaSila) {
        super(nazovPredmetu, "toto je Power Potion, pridáva počet použití vybranému útoku", liecivaSila);
    }

    /**
     * Počet použití sa pridá iba tomu útoku, ktorý existuje a nemá plný počet použití
     * @param pokemon, na ktorého sa použije item
     * @return true/false ak sa predmet použil/nepoužil
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (!pokemon.maPlnyPocetPouziti()) {
            Utok utok;
            boolean neplatnyUtok = true;
            do {
                utok = pokemon.vyberUtok(pokemon.ziskajIndexUtoku());
                if (utok == null) {
                    System.out.println("Zadal si index neexistujúceho útoku.");
                } else if (utok.maPlnyPocetPouziti()) {
                    System.out.println(utok.getNazovUtoku() + " má svoj počet použití na max, vyber iný útok");
                } else {
                    neplatnyUtok = false;
                }
            } while (neplatnyUtok);

            System.out.println("Útoku " + utok.getNazovUtoku() + " sa pridalo " + super.getLiecivaSila() + " počet použití");
            utok.pridajPocetPouziti(super.getLiecivaSila());
            return true;
        } else {
            System.out.println(pokemon.getPrezyvka() + " má všetky útoky na max, nepotrebuješ použiť " + super.getNazovPredmetu());
            return false;
        }
    }

    /**
     * Výpis vyzerá nasledovne:
     * Energizer: toto je Power Potion, pridáva počet použití vybranému útoku, sila: 5 počet použití
     */
    @Override
    public void vypisSa() {
        super.vypisSa();
        System.out.println(" počet použití");
    }
}
