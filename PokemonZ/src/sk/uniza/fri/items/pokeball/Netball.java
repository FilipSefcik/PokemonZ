package sk.uniza.fri.items.pokeball;

import sk.uniza.fri.pokemon.Pokemon;
import sk.uniza.fri.pokemon.Typ;

/**
 * 28. 3. 2022 - 12:58
 *
 * Netball je špeciálny typ Pokéballu, ktorý má vyššiu šancu chytenia na pokémonov typu Water alebo Bug
 *
 * @author Filip Šefčík
 */
public class Netball extends AbstractPokeball {

    public Netball() {
        super("Netball", "tento Pokéball má vyššiu úspešnosť na Water a Bug pokémonov", 60);
    }

    @Override
    public int vyratajCelkovuSancu(Pokemon pokemon) {
        int bonus = 0;
        for (Typ typ : pokemon.getDruhPokemona().getTypPokemona()) {
            if (typ.getNazovTypu().equals(Typ.WATER.getNazovTypu()) || typ.getNazovTypu().equals(Typ.BUG.getNazovTypu())) {
                bonus += 10;
            }
        }
        return super.vyratajCelkovuSancu(pokemon) + bonus;
    }
}
