package sk.uniza.fri.items.revive;

import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * SuperRevive má rovnakú funkciu ako Revive ale k tomu dokáže ešte zvýšiť počet použití na veštkých útokoch o 50 %
 *
 * @author fifos
 */
public class SuperRevive extends AbstractRevive {


    /**
     * Konštruktor už obsahuje predvolené hodnoty pre vytvorenie predka
     */
    public SuperRevive() {
        super("SuperRevive", "oživí pokémona na 100 % jeho hp a zvýši použitie všetkým útokom o 50%");
    }

    /**
     * SuperRevive sa použije iba na pokémona, ktorý nie je schopný boja
     * Bez ohľadu na to, ak jeho útoky majú plný počet použití
     * @param pokemon, na ktorého sa použije item
     * @return true/false ak sa SuperRevive úspešne/neúspešne použil
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (!pokemon.jeShopnyBoja()) {
            pokemon.pridajPercentualneHp(100);
            pokemon.pridajPercentualnePouzitieVsetkymUtokom(50);
            System.out.println(pokemon.getPrezyvka() + " je opäť schopný boja naplnený silou!");
            return true;
        } else {
            System.out.println(pokemon.getPrezyvka() + " je schopný boja, nedá sa " + super.getNazovPredmetu() + " naňho použiť.");
            return false;
        }
    }
}
