package sk.uniza.fri.items.pokeball;

import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Ak je pokémon chytený Healballom, úplne sa vylieči
 *
 * @author Filip Šefčík
 */
public class Healball extends AbstractPokeball {

    public Healball() {
        super("Healball", "tento Pokéball vylieči pokémona, ktorý sa doňho chytí", 60);
    }

    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (super.pouziPredmet(pokemon)) {
            pokemon.kompletOzivPokemona();
            return true;
        }
        return false;
    }
}
