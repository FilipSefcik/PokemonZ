package sk.uniza.fri.items.revive;

import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Revive dokáže oživiť pokémona na 50 % jeho hp
 *
 * @author Filip Šefčík
 */
public class Revive extends AbstractRevive {

    /**
     * Konštruktor už obsahuje predvolené hodnoty pre vytvorenie predka
     */
    public Revive() {
        super("Revive", "ožviví pokémona na 50 % hp");
    }

    /**
     * Revive sa nepoužije ak pokémon je stále schopný boja
     * @param pokemon, na ktorého sa použije item
     * @return true/false ak sa Revive úspešne/neúspešne použil
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (!pokemon.jeShopnyBoja()) {
            pokemon.pridajPercentualneHp(50);
            System.out.println(pokemon.getPrezyvka() + " je opäť schopný boja!");
            return true;
        } else {
            System.out.println(pokemon.getPrezyvka() + " je schopný boja, nedá sa " + super.getNazovPredmetu() + " naňho použiť.");
            return false;
        }
    }
}
