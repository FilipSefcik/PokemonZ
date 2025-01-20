package sk.uniza.fri.items.pokeball;

/**
 * 28. 3. 2022 - 12:58
 *
 * Ultraball je pevne stanovený typ Pokéballu, funguje rovnako ako každý iný bežný Pokéball, len má iný catchRate
 *
 * @author Filip Šefčík
 */
public class Ultraball extends AbstractPokeball {

    public Ultraball() {
        super("Ultraball", "najnovší typ pokéballu, s ním máš najväčšiu šancu chytiť pokémona", 80);
    }
}
