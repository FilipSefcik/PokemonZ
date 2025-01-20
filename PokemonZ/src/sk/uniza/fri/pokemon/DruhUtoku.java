package sk.uniza.fri.pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Enum DruhUtoku obsahuje všetky útoky v hre, ktoré sa môžu pokémoni naučiť.
 * Obsahuje informácie o názve, typu útoku, koľkokrát sa môže použiť, akú má silu a presnosť.
 *
 * @author Filip Šefčík
 */
public enum DruhUtoku {
    TACKLE("Tackle", Typ.NORMAL, 25, 20, 100),
    SCRATCH("Scratch", Typ.NORMAL, 20, 25, 100),
    KARATE_CHOP("Karate chop", Typ.FIGHTING, 15, 30, 90),
    WATER_GUN("Water gun", Typ.WATER, 25, 20, 90),
    EMBER("Ember", Typ.FIRE, 25, 20, 90),
    RAZOR_LEAF("Razor leaf", Typ.GRASS, 25, 20, 90),
    SPARK("Spark", Typ.ELECTRIC, 20, 25, 90),
    MUD_SLAP("Mud slap", Typ.GROUND, 15, 30, 85),
    LICK("Lick", Typ.GHOST, 30, 15, 95),
    BITE("Bite", Typ.DARK, 25, 20, 90),
    DRAGON_BREATH("Dragon breath", Typ.DRAGON, 20, 30, 85),
    BUG_BITE("Bug bite", Typ.BUG, 35, 15, 90),
    POISON_JAB("Poison jab", Typ.POISON, 25, 30, 85),
    WING_ATTACK("Wing attack", Typ.FLYING, 20, 20, 90),
    ICY_WIND("Icy wind", Typ.ICE, 20, 25, 90),
    ROCK_THROW("Rock throw", Typ.ROCK, 15, 25, 90),
    FAIRY_KISS("Fairy kiss", Typ.FAIRY, 30, 15, 95),
    IRON_TAIL("Iron tail", Typ.STEEL, 20, 25, 85),
    CONFUSION("Confusion", Typ.PSYCHIC, 25, 20, 90),
    SLAM("Slam", Typ.NORMAL, 16, 80, 85),
    CROSS_CHOP("Cross chop", Typ.FIGHTING, 16, 95, 70),
    SURF("Surf", Typ.WATER, 20, 85, 80),
    FLAMETHROWER("Flamethrower", Typ.FIRE, 16, 90, 85),
    ENERGY_BALL("Energy ball", Typ.GRASS, 20, 80, 90),
    THUNDERBOLT("Thunderbolt", Typ.ELECTRIC, 16, 90, 90),
    EARTH_POWER("Earth power", Typ.GROUND, 20, 85, 85),
    SHADOW_BALL("Shadow ball", Typ.GHOST, 16, 80, 95),
    DARK_PULSE("Dark pulse", Typ.DARK, 20, 80, 90),
    DRAGON_PULSE("Dragon pulse", Typ.DRAGON, 16, 85, 90),
    BUG_BUZZ("Bug buzz", Typ.BUG, 16, 90, 100),
    SLUDGE_WAVE("Sludge wave", Typ.POISON, 16, 85, 100),
    FLY("Fly", Typ.FLYING, 16, 90, 85),
    ICE_BEAM("Ice beam", Typ.ICE, 16, 85, 90),
    ROCK_SLIDE("Rock slide", Typ.ROCK, 20, 85, 85),
    DAZZLING_GLEAM("Dazzling gleam", Typ.FAIRY, 20, 90, 80),
    IRON_HEAD("Iron head", Typ.STEEL, 20, 85, 90),
    PSYCHIC("Psychic", Typ.PSYCHIC, 16, 90, 90),
    HYPER_BEAM("Hyper beam", Typ.NORMAL, 8, 150, 75),
    CLOSE_COMBAT("Close combat", Typ.FIGHTING, 10, 120, 80),
    HYDRO_PUMP("Hydro pump", Typ.WATER, 8, 110, 85),
    FIRE_BLAST("Fire blast", Typ.FIRE, 8, 120, 80),
    LEAF_STORM("Leaf storm", Typ.GRASS, 8, 150, 75),
    THUNDER("Thunder", Typ.ELECTRIC, 8, 130, 85),
    EARTHQUAKE("Earthquake", Typ.GROUND, 10, 100, 100),
    PHANTOM_FORCE("Phantom force", Typ.GHOST, 12, 100, 90),
    FOUL_PLAY("Foul play", Typ.DARK, 10, 110, 85),
    OUTRAGE("Outrage", Typ.DRAGON, 8, 140, 80),
    MEGAHORN("Megahorn", Typ.BUG, 8, 130, 85),
    SLUDGE_BOMB("Sludge bomb", Typ.POISON, 8, 100, 100),
    BRAVE_BIRD("Brave bird", Typ.FLYING, 8, 120, 90),
    BLIZZARD("Blizzard", Typ.ICE, 8, 130, 85),
    STONE_EDGE("Stone edge", Typ.ROCK, 10, 100, 85),
    MOONBLAST("Moonblast", Typ.FAIRY, 10, 110, 90),
    HEAVY_SLAM("Heavy slam", Typ.STEEL, 12, 100, 85),
    FUTURE_SIGHT("Future sight", Typ.PSYCHIC, 8, 140, 85),
    STRUGGLE("Struggle", Typ.NONE , 1, 50, 100);


    private String nazovUtoku;
    private Typ typUtoku;
    private int maxPocetPouziti;
    private double silaUtoku;
    private int presnostUtoku;

    /**
     * Konštruktor obsahuje parametre spomínané v popise enumu.
     * @param nazovUtoku
     * @param typUtoku
     * @param pocetPouziti
     * @param silaUtoku
     * @param presnostUtoku
     */
    DruhUtoku(String nazovUtoku, Typ typUtoku, int pocetPouziti, double silaUtoku, int presnostUtoku) {
        this.nazovUtoku = nazovUtoku;
        this.typUtoku = typUtoku;
        this.maxPocetPouziti = pocetPouziti;
        this.silaUtoku = silaUtoku;
        this.presnostUtoku = presnostUtoku;
    }

    /**
     * @return názov útoku
     */
    public String getNazovUtoku() {
        return this.nazovUtoku;
    }

    /**
     * @return akého typu je útok
     */
    public Typ getTypUtoku() {
        return this.typUtoku;
    }

    /**
     * @return sila útoku
     */
    public double getSilaUtoku() {
        return this.silaUtoku;
    }

    /**
     * @return presnosť útoku
     */
    public int getPresnostUtoku() {
        return this.presnostUtoku;
    }

    /**
     * @return maximálny počet použití útoku
     */
    public int getMaxPocetPouziti() {
        return this.maxPocetPouziti;
    }

    /**
     * @return popis útoku ako String
     */
    public String dajPopis() {
       return this.nazovUtoku + ": typ - " + this.typUtoku + ", damage - " + this.silaUtoku + ", presnosť - " + this.presnostUtoku + " %";
    }
}
