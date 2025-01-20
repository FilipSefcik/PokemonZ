package sk.uniza.fri.items.pokeball;

/**
 * 28. 3. 2022 - 12:58
 *
 * Greatball je pevne stanovený typ Pokéballu, funguje rovnako ako každý iný bežný Pokéball, len má iný catchRate
 *
 * @author Filip Šefčík
 */
public class Greatball extends AbstractPokeball {

    public Greatball() {
        super("Greatball", "vylepšená verzia pokéballu", 70);
    }
}
