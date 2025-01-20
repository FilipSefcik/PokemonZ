package sk.uniza.fri.items.pokeball;

/**
 * 28. 3. 2022 - 12:58
 *
 * Pokéball je pevne základný typ Pokéballu, má pevne stanovený názov, popis aj catchRate
 *
 * @author Filip Šefčík
 */
public class Pokeball extends AbstractPokeball {

    public Pokeball() {
        super("Pokéball", "s týmto predmetom môžeš chytať divokých pokémonov", 60);
    }

}
