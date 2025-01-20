package sk.uniza.fri.items.potion;

import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Tento druh Potionu pridáva životy
 *
 * @author Filip Šefčík
 */
public class Potion extends AbstractPotion {

    /**
     * V parametrickom konštruktore sa zadáva názov potionu a jeho liečivá sila
     * @param nazovPredmetu
     * @param liecivaSila
     */
    public Potion(String nazovPredmetu, int liecivaSila) {
        super(nazovPredmetu, "tento potion pridáva hp", liecivaSila);
    }

    /**
     * Potion vyliečí životy iba tomu pokémonovi, čo nemá plné hp
     * @param pokemon, na ktorého sa použije item
     * @return true/false či sa potion použil/nepoužil
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (pokemon.vyratajPercentaHp() < 100) {
            pokemon.pridajHp(super.getLiecivaSila());
            System.out.println(pokemon.getPrezyvka() + " sa vyliečilo " + super.getLiecivaSila() + " hp");
            return true;
        } else {
            System.out.println(pokemon.getPrezyvka() + " má plné hp! Viac sa už nedá vyliečiť.");
            return false;
        }
    }

    /**
     * Výpis vyzerá nasledovne:
     * Potion: tento potion pridáva hp, sila: 20 hp
     */
    @Override
    public void vypisSa() {
        super.vypisSa();
        System.out.println(" hp");
    }
}
