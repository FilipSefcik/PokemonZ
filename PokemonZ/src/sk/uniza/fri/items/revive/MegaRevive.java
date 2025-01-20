package sk.uniza.fri.items.revive;

import sk.uniza.fri.pokemon.Pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * MegaRevive kompletne vylieči každého pokémona v hráčovej parte a zároveň aj nastaví na všetky útoky počet použití na max
 *
 * @author Filip Šefčík
 */
public class MegaRevive extends AbstractRevive {

    /**
     * Konštruktor už obsahuje predvolené hodnoty pre vytvorenie predka
     * Spolu s upozornením o použití tohto predmetu
     */
    public MegaRevive() {
        super("MegaRevive", "vzácny a extrémne účinný predmet, kompletne vylieči každého pokémona v parte. \n\tUpozornenie: Funguje bez ohľadu na to, v akom stave máš partu!");
    }

    /**
     * Vylieči a naplní silou každého pokémona v hráčovej parte, ale nekontroluje v akom stave je
     * Preto ak bude hráč neopatrný, tak môže tento predmet použiť úplne zbytočne
     * Vyriešenie toho, aby sa vyliečil každý pokémon je v triede Hrac
     * tam sa to vyvolá v cykle v prípade ak sa použije tento predmet
     * @param pokemon, na ktorého sa použije item
     * @return true pretože sa tento predmet vždy použije
     */
    @Override
    public boolean pouziPredmet(Pokemon pokemon) {
        if (pokemon.vyratajPercentaHp() < 100 || !pokemon.maPlnyPocetPouziti()) {
            System.out.println(pokemon.getPrezyvka() + " sa vyliečil a je plný sily!");
        } else {
            System.out.println(pokemon.getPrezyvka() + " to veľmi nepotreboval, dúfam že zvyšku tímu tento predmet pomohol");
        }
        pokemon.kompletOzivPokemona();
        return true;
    }
}
