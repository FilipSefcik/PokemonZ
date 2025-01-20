package sk.uniza.fri.items.pokeball;

import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Evolveball je typ Pokéballu, ktorý má vyššiu šancu chytenia, keď sa použije na vyvinutých pokémonov
 *
 * @author Filip Šefčík
 */
public class Evolveball extends AbstractPokeball {

    public Evolveball() {
        super("Evolveball", "tento Pokéball má väčšiu úspešnosť na vyvinutých pokémonov", 60);
    }

    @Override
    public int vyratajCelkovuSancu(Pokemon pokemon) {
        int bonus = 0;

        if (pokemon.getDruhPokemona().getStadiumEvolucie().equals("druhé")) {
            bonus += 10;
        } else if (pokemon.getDruhPokemona().getStadiumEvolucie().equals("finálne")) {
            bonus += 20;
        }

        return super.vyratajCelkovuSancu(pokemon) + bonus;
    }
}
