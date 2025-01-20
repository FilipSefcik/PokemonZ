package sk.uniza.fri.interactive.npc;

import java.util.Random;

/**
 * 28. 3. 2022 - 12:58
 *
 * ZoznamHlasok obsahuje všetky hlášky, ktoré môže Npc vysloviť
 *
 * @author Filip Šefčík
 */
public class ZoznamHlasok {

    private String[] prvotneHlasky;
    private String[] rematchHlasky;
    private String[] vitazneHlasky;
    private String[] porazeneHlasky;
    private String[] defaultHlasky;
    private String[] bojoveHlasky;
    private String[] darovacieHlasky;
    private Random random;
    private static ZoznamHlasok instance;

    /**
     * ZoznamHlasok je singleton, preto má metódu getInstance()
     * @return
     */
    public static ZoznamHlasok getInstance() {
        if (instance == null) {
            instance = new ZoznamHlasok();
        }
        return instance;
    }

    /**
     * V konštrukore sa vytvoria zoznamy všetkých typov hlášok, ktoré npc môže povedať
     */
    private ZoznamHlasok() {

        this.random = new Random();

        this.prvotneHlasky = new String[] {
                "Ahoj.", "Ahoj, chceš so mnou bojovať?", "Ukážem ti silu mojich pokémonov.", "Aha! Nová tvár, poď bojovať!",
                "Nepodceňuj ma!", "Lepšie ťa spoznám v boji.", "Trúfaš si ma poraziť?! Len to skús."
        };

        this.rematchHlasky = new String[] {
                "Tentokrát na teba nepôjdem ľahko!", "Podcenil som ťa, teraz už nie!", "Ukážem ti, ako som sa zlepšil!",
                "Priprav sa na prehru.", "Druhý krát už neprehrám.", "Sme ešte silnejší ako predtým."
        };

        this.vitazneHlasky = new String[] {
                "Bolo to nevyhnutné.", "Už od začiatku bolo jasné ako to dopadne.", "Snažil si sa, ale nedostatočne.",
                "Niekto musí byť ten porazený, teraz si to ty.", "A to sa snaha z mojej strany ešte neprejavila!",
                "Bolo to tesné, ale tentokrát som bol na tom lepšie ja."
        };

        this.porazeneHlasky = new String[] {
                "Bol to dobrý zápas.", "Nemožné, ako sa to mohlo stať?!", "Bol to vyrovnaný boj.", "Toto bolo nečakané.",
                "Budem trénovať ešte viac.", "Nabudúce budem s mojimi pokémonmi silnejší.", "Mal si iba šťastie!",
                "Odmietam prijať prehru!"
        };

        this.defaultHlasky = new String[] {
                "Dnes je pekné počasie.", "Toto miesto vyzerá pekne, nemyslíš?", "Najdôležitejšie je si budovať vzťah so svojimi pokémonmi.",
                "Je vidieť, že sa staráš o svojich pokémonov.", "Vidím, že ťa majú tvoji pokémoni radi.", "Tvoj pokémon vyzerá roztomilo.",
                "Pracuj tvrdo a výsledky sa onedlho ukážu.", "Vieš ako sa vraví, práca šľachtí.", "Pokémon je kamarát, nie nástroj!",
                "Váž si ľudí aj pokémonov okolo seba.", "Predmety používaj iba ak ich potrebuješ.",
                "Vieš o tom, že keď pokémon použije útok, ktorý je rovnakého typu ako on, dáva to väčší damage?"
        };

        this.bojoveHlasky = new String[] {
                "Vyberám si teba!", "Teraz si na rade ty!", "Choď!", "Priprav sa na súboj!", "Poď von!", "Ukáž čo dokážeš!"
        };

        this.darovacieHlasky = new String[] {
                "Zober si toto, zíde sa ti to.", "Vyzeráš ako schopný tréner, zober si.", "Vyzeráš sympaticky, nech sa páči", "Aha! Silný tréner, toto sa ti zíde.",
                "K dobrému trénerovi sa hodí dobrý predmet, páči sa.", "Staráš sa o svojich pokémonov, toto ti pomôže."
        };

    }

    /**
     * @return náhodnú hlášku z pola prvotných hlášok
     */
    public String dajRandomPrvotnuHlasku() {
        return this.prvotneHlasky[this.random.nextInt(this.prvotneHlasky.length)];
    }

    /**
     * @return náhodnú hlášku z pola rematch hlášok
     */
    public String dajRandomRematchHlasku() {
        return this.rematchHlasky[this.random.nextInt(this.rematchHlasky.length)];
    }

    /**
     * @return náhodnú hlášku z pola víťazných hlášok
     */
    public String dajRandomVitaznuHlasku() {
        return this.vitazneHlasky[this.random.nextInt(this.vitazneHlasky.length)];
    }

    /**
     * @return náhodnú hlášku z pola porazených hlášok
     */
    public String dajRandomPorazenuHlasku() {
        return this.porazeneHlasky[this.random.nextInt(this.porazeneHlasky.length)];
    }

    /**
     * @return náhodnú hlášku z pola defaultných hlášok
     */
    public String dajRandomDefaultHlasku() {
        return this.defaultHlasky[this.random.nextInt(this.defaultHlasky.length)];
    }

    /**
     * @return náhodnú hlášku z pola bojových hlášok
     */
    public String dajRandomBojovuHlasku() {
        return this.bojoveHlasky[this.random.nextInt(this.bojoveHlasky.length)];
    }

    /**
     * @return náhodnú hlášku z pola darovacích hlášok
     */
    public String dajRandomDarovaciuHlasku() {
        return this.darovacieHlasky[this.random.nextInt(this.darovacieHlasky.length)];
    }
}
