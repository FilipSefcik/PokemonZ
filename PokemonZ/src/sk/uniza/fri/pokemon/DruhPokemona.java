package sk.uniza.fri.pokemon;

import java.util.HashMap;

/**
 * 28. 3. 2022 - 12:58
 *
 * Enum DruhPokemona obsahuje základné informácie o každom pokémonovi, názov pokémona,
 * číslo v pokedexe, na akom leveli sa vyvíja, jeho staty, na akých leveloch sa učí aké útoky a hlavne jeho typ.
 *
 * @author Filip Šefčík
 */
public enum DruhPokemona {

    BULBASAUR("Bulbasaur", 1,  14, new double[] {45, 49, 65}, new int[] {0, 6, 13, 16, 21, 25}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.RAZOR_LEAF, DruhUtoku.POISON_JAB, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.LEAF_STORM}, Typ.GRASS, Typ.POISON),
    IVYSAUR("Ivysaur", 2,  21, new double[] {60, 62, 80}, new int[] {0, 1, 2, 20, 23, 28}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.RAZOR_LEAF, DruhUtoku.POISON_JAB, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.LEAF_STORM}, Typ.GRASS, Typ.POISON),
    VENUSAUR("Venusaur", 3,  0, new double[] {80, 82, 100}, new int[] {0, 1, 2, 3, 22, 25, 33}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.RAZOR_LEAF, DruhUtoku.POISON_JAB, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.LEAF_STORM, DruhUtoku.SLUDGE_BOMB}, Typ.GRASS, Typ.POISON),
    CHARMANDER("Charmander", 4,  15, new double[] {39, 52, 60}, new int[] {0, 7, 14, 20, 24}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.EMBER, DruhUtoku.FLAMETHROWER, DruhUtoku.FIRE_BLAST, DruhUtoku.DRAGON_BREATH}, Typ.FIRE),
    CHARMELEON("Charmeleon", 5,  20, new double[] {58, 64, 80}, new int[] {0, 1, 2, 21, 25}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.EMBER, DruhUtoku.FLAMETHROWER, DruhUtoku.FIRE_BLAST, DruhUtoku.DRAGON_BREATH}, Typ.FIRE),
    CHARIZARD("Charizard", 6,  0, new double[] {78, 84, 90}, new int[] {0, 1, 2, 23, 28, 34}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.EMBER, DruhUtoku.FLAMETHROWER, DruhUtoku.FIRE_BLAST, DruhUtoku.BRAVE_BIRD, DruhUtoku.DRAGON_PULSE}, Typ.FIRE, Typ.FLYING),
    SQUIRTLE("Squirtle", 7,  14, new double[] {44, 48, 65}, new int[] {0, 7, 13, 16, 19, 22}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WATER_GUN, DruhUtoku.BITE, DruhUtoku.SURF, DruhUtoku.ICY_WIND, DruhUtoku.HYDRO_PUMP}, Typ.WATER),
    WARTORTLE("Wartortle", 8,  19, new double[] {59, 63, 80}, new int[] {0, 1, 2, 16, 20, 24}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WATER_GUN, DruhUtoku.BITE, DruhUtoku.SURF, DruhUtoku.ICY_WIND, DruhUtoku.HYDRO_PUMP}, Typ.WATER),
    BLASTOISE("Blastoise", 9,  0, new double[] {79, 83, 100}, new int[] {0, 1, 2, 3, 24, 30}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WATER_GUN, DruhUtoku.BITE, DruhUtoku.SURF, DruhUtoku.ICE_BEAM, DruhUtoku.HYDRO_PUMP}, Typ.WATER),
    WEEDLE("Weedle", 10,  10, new double[] {40, 35, 30}, new int[] {0}, new DruhUtoku[] {DruhUtoku.BUG_BITE}, Typ.BUG, Typ.POISON),
    KAKUNA("Kakuna", 11,  16, new double[] {45, 25, 50}, new int[] {0, 15}, new DruhUtoku[] {DruhUtoku.BUG_BITE, DruhUtoku.BUG_BUZZ}, Typ.BUG, Typ.POISON),
    BEEDRILL("Beedrill", 12 ,  0, new double[] {65, 80, 40}, new int[] {0, 1, 20, 23, 27}, new DruhUtoku[] {DruhUtoku.BUG_BITE, DruhUtoku.POISON_JAB, DruhUtoku.FLY, DruhUtoku.MEGAHORN, DruhUtoku.SLUDGE_BOMB}, Typ.BUG, Typ.POISON),
    PIDGEY("Pidgey", 13 ,  10, new double[] {40, 45, 40}, new int[] {0, 5, 9, 15}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WING_ATTACK, DruhUtoku.SLAM, DruhUtoku.FLY}, Typ.NORMAL, Typ.FLYING),
    PIDGEOTTO("Pidgeotto", 14,  15, new double[] {63, 60, 55}, new int[] {0, 1, 14, 17, 22, 26}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WING_ATTACK, DruhUtoku.SLAM, DruhUtoku.FLY, DruhUtoku.CROSS_CHOP, DruhUtoku.BRAVE_BIRD}, Typ.NORMAL, Typ.FLYING),
    PIDGEOT("Pidgeot", 15,  0, new double[] {83, 80, 75}, new int[] {0, 1, 2, 18, 21, 27}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WING_ATTACK, DruhUtoku.SLAM, DruhUtoku.FLY, DruhUtoku.CLOSE_COMBAT, DruhUtoku.BRAVE_BIRD}, Typ.NORMAL, Typ.FLYING),
    PIKACHU("Pikachu", 16,  20, new double[] {35, 55, 30}, new int[] {0, 1, 12, 16, 25}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.SPARK, DruhUtoku.IRON_TAIL, DruhUtoku.THUNDERBOLT, DruhUtoku.THUNDER}, Typ.ELECTRIC),
    RAICHU("Raichu", 17,  0, new double[] {60, 90, 55}, new int[] {0, 1, 2, 3, 23}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.SPARK, DruhUtoku.IRON_TAIL, DruhUtoku.THUNDERBOLT, DruhUtoku.THUNDER}, Typ.ELECTRIC),
    JIGGLYPUFF("Jigglypuff", 18,  18, new double[] {115, 45, 20}, new int[] {0, 5, 10, 15, 25}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.FAIRY_KISS, DruhUtoku.SLAM, DruhUtoku.PSYCHIC, DruhUtoku.DAZZLING_GLEAM}, Typ.NORMAL, Typ.FAIRY),
    WIGGLYTUFF("Wigglytuff", 19,  0, new double[] {140, 70, 45}, new int[] {0, 1, 2, 3, 22, 25, 28}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.FAIRY_KISS, DruhUtoku.SLAM, DruhUtoku.PSYCHIC, DruhUtoku.DAZZLING_GLEAM, DruhUtoku.HYPER_BEAM, DruhUtoku.MOONBLAST}, Typ.NORMAL, Typ.FAIRY),
    ODDISH("Oddish", 20,  13, new double[] {45, 50, 55}, new int[] {0, 6, 12, 15, 19, 22}, new DruhUtoku[] {DruhUtoku.RAZOR_LEAF, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.EARTH_POWER, DruhUtoku.DAZZLING_GLEAM, DruhUtoku.LEAF_STORM}, Typ.GRASS, Typ.POISON),
    GLOOM("Gloom", 21,  18, new double[] {60, 65, 70}, new int[] {0, 1, 2, 14, 17, 21}, new DruhUtoku[] {DruhUtoku.RAZOR_LEAF, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.EARTH_POWER, DruhUtoku.DAZZLING_GLEAM, DruhUtoku.LEAF_STORM}, Typ.GRASS, Typ.POISON),
    VILEPLUME("Vileplume", 22,  0, new double[] {75, 80, 85}, new int[] {0, 1, 2, 3, 4, 20, 23}, new DruhUtoku[] {DruhUtoku.RAZOR_LEAF, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.EARTH_POWER, DruhUtoku.DAZZLING_GLEAM, DruhUtoku.LEAF_STORM, DruhUtoku.SLUDGE_BOMB}, Typ.GRASS, Typ.POISON),
    PSYDUCK("Psyduck", 23,  15, new double[] {50, 52, 48}, new int[] {0, 4, 10, 18}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.WATER_GUN, DruhUtoku.CONFUSION, DruhUtoku.HYDRO_PUMP}, Typ.WATER),
    GOLDUCK("Golduck", 24,  0, new double[] {80, 82, 78}, new int[] {0, 1, 2, 16, 19, 22, 25}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.WATER_GUN, DruhUtoku.CONFUSION, DruhUtoku.SURF, DruhUtoku.ICE_BEAM, DruhUtoku.PSYCHIC, DruhUtoku.HYDRO_PUMP}, Typ.WATER),
    ABRA("Abra", 25,  10, new double[] {25, 20, 15}, new int[] {0, 6, 15, 26}, new DruhUtoku[] {DruhUtoku.CONFUSION, DruhUtoku.PSYCHIC, DruhUtoku.ENERGY_BALL, DruhUtoku.SHADOW_BALL}, Typ.PSYCHIC),
    KADABRA("Kadabra", 26 ,  18, new double[] {40, 35, 30}, new int[] {0, 1, 13, 20, 25}, new DruhUtoku[] {DruhUtoku.CONFUSION, DruhUtoku.PSYCHIC, DruhUtoku.ENERGY_BALL, DruhUtoku.SHADOW_BALL, DruhUtoku.FUTURE_SIGHT}, Typ.PSYCHIC),
    ALAKAZAM("Alakazam", 27,  0, new double[] {55, 50, 45}, new int[] {0, 1, 2, 19, 23, 28}, new DruhUtoku[] {DruhUtoku.CONFUSION, DruhUtoku.PSYCHIC, DruhUtoku.ENERGY_BALL, DruhUtoku.SHADOW_BALL, DruhUtoku.FUTURE_SIGHT, DruhUtoku.MOONBLAST}, Typ.PSYCHIC),
    MACHOP("Machop", 28,  12, new double[] {70, 80, 50}, new int[] {0, 6, 10, 15}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.KARATE_CHOP, DruhUtoku.SLAM, DruhUtoku.CROSS_CHOP}, Typ.FIGHTING),
    MACHOKE("Machoke", 29,  19, new double[] {80, 100, 70}, new int[] {0, 1, 2, 13, 20, 24}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.KARATE_CHOP, DruhUtoku.SLAM, DruhUtoku.CROSS_CHOP, DruhUtoku.HEAVY_SLAM, DruhUtoku.CLOSE_COMBAT}, Typ.FIGHTING),
    MACHAMP("Machamp", 30,  0, new double[] {90, 130, 80}, new int[] {0, 1, 2, 3, 22, 25, 29}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.KARATE_CHOP, DruhUtoku.SLAM, DruhUtoku.CROSS_CHOP, DruhUtoku.HEAVY_SLAM, DruhUtoku.CLOSE_COMBAT, DruhUtoku.STONE_EDGE}, Typ.FIGHTING),
    GEODUDE("Geodude", 31,  13, new double[] {40, 80, 100}, new int[] {0, 8, 15, 21}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.ROCK_THROW, DruhUtoku.MUD_SLAP, DruhUtoku.IRON_HEAD}, Typ.ROCK, Typ.GROUND),
    GRAVELER("Graveler", 32,  20, new double[] {55, 95, 115}, new int[] {0, 1, 14, 18, 22, 26}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.ROCK_THROW, DruhUtoku.MUD_SLAP, DruhUtoku.IRON_HEAD, DruhUtoku.EARTH_POWER, DruhUtoku.ROCK_SLIDE}, Typ.ROCK, Typ.GROUND),
    GOLEM("Golem", 33,  0, new double[] {80, 110, 130}, new int[] {0, 1, 2, 3, 21, 26, 30, 33, 37}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.ROCK_THROW, DruhUtoku.MUD_SLAP, DruhUtoku.IRON_HEAD, DruhUtoku.EARTH_POWER, DruhUtoku.ROCK_SLIDE, DruhUtoku.EARTHQUAKE, DruhUtoku.STONE_EDGE, DruhUtoku.HEAVY_SLAM}, Typ.ROCK, Typ.GROUND),
    PONYTA("Ponyta", 34,  16, new double[] {50, 85, 55}, new int[] {0, 8, 14, 19}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.EMBER, DruhUtoku.SLAM, DruhUtoku.IRON_TAIL}, Typ.FIRE),
    RAPIDASH("Rapidash", 35,  0, new double[] {65, 100, 70}, new int[] {0, 1, 2, 17, 21, 25, 29, 35}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.EMBER, DruhUtoku.SLAM, DruhUtoku.FLAMETHROWER, DruhUtoku.POISON_JAB, DruhUtoku.MEGAHORN, DruhUtoku.FIRE_BLAST, DruhUtoku.FOUL_PLAY}, Typ.FIRE),
    MAGNEMITE("Magnemite", 36,  14, new double[] {25, 35, 70}, new int[] {0, 6, 10}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.SPARK, DruhUtoku.IRON_HEAD}, Typ.ELECTRIC, Typ.STEEL),
    MAGNETON("Magneton", 37,  20, new double[] {50, 60, 95}, new int[] {0, 1, 2, 15, 18, 22, 25}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.SPARK, DruhUtoku.IRON_HEAD, DruhUtoku.THUNDERBOLT, DruhUtoku.DARK_PULSE, DruhUtoku.HEAVY_SLAM, DruhUtoku.THUNDER}, Typ.ELECTRIC, Typ.STEEL),
    MAGNEZONE("Magnezone", 38,  0, new double[] {70, 75, 105}, new int[] {0, 1, 2, 3, 4, 22, 25, 29}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.SPARK, DruhUtoku.IRON_HEAD, DruhUtoku.THUNDERBOLT, DruhUtoku.DARK_PULSE, DruhUtoku.HEAVY_SLAM, DruhUtoku.THUNDER, DruhUtoku.EARTH_POWER}, Typ.ELECTRIC, Typ.STEEL),
    SHELLDER("Shellder", 39,  15, new double[] {30, 65, 100}, new int[] {0, 5, 12, 17}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WATER_GUN, DruhUtoku.ICY_WIND, DruhUtoku.ICE_BEAM}, Typ.WATER),
    CLOYSTER("Cloyster", 40,  0, new double[] {50, 95, 180}, new int[] {0, 1, 2, 16, 20, 25, 29, 33}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.WATER_GUN, DruhUtoku.ICY_WIND, DruhUtoku.ICE_BEAM, DruhUtoku.SURF, DruhUtoku.DARK_PULSE, DruhUtoku.HYDRO_PUMP, DruhUtoku.BLIZZARD}, Typ.WATER, Typ.ICE),
    GASTLY("Gastly", 41,  11, new double[] {30, 35, 30}, new int[] {0, 6, 13, 19}, new DruhUtoku[] {DruhUtoku.LICK, DruhUtoku.POISON_JAB, DruhUtoku.DARK_PULSE, DruhUtoku.ICY_WIND}, Typ.GHOST, Typ.POISON),
    HAUNTER("Haunter", 42,  17, new double[] {45, 50, 45}, new int[] {0, 1, 12, 16, 21, 26}, new DruhUtoku[] {DruhUtoku.LICK, DruhUtoku.POISON_JAB, DruhUtoku.DARK_PULSE, DruhUtoku.SHADOW_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.ENERGY_BALL}, Typ.GHOST, Typ.POISON),
    GENGAR("Gengar", 43, 0, new double[] {60, 65, 60}, new int[] {0, 1, 12, 16, 20, 24, 30, 35}, new DruhUtoku[] {DruhUtoku.LICK, DruhUtoku.POISON_JAB, DruhUtoku.DARK_PULSE, DruhUtoku.SHADOW_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.ENERGY_BALL, DruhUtoku.PHANTOM_FORCE, DruhUtoku.SLUDGE_BOMB}, Typ.GHOST, Typ.POISON),
    DRATINI("Dratini", 44,  18, new double[] {41, 64, 45}, new int[] {0, 8, 13, 15, 17, 20, 24}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.DRAGON_BREATH, DruhUtoku.WATER_GUN, DruhUtoku.EMBER, DruhUtoku.DRAGON_PULSE, DruhUtoku.SLAM, DruhUtoku.ICE_BEAM}, Typ.DRAGON),
    DRAGONAIR("Dragonair", 45, 25, new double[] {61, 84, 65}, new int[] {0, 1, 2, 3, 4, 20, 23, 27, 31, 35}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.DRAGON_BREATH, DruhUtoku.WATER_GUN, DruhUtoku.EMBER, DruhUtoku.DRAGON_PULSE, DruhUtoku.FLY, DruhUtoku.ENERGY_BALL, DruhUtoku.THUNDERBOLT, DruhUtoku.HYDRO_PUMP, DruhUtoku.OUTRAGE}, Typ.DRAGON),
    DRAGONITE("Dragonite", 46,  0, new double[] {91, 134, 95}, new int[] {0, 1, 2, 3, 4, 5, 6, 28, 32, 36, 39, 43, 46}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.DRAGON_BREATH, DruhUtoku.WATER_GUN, DruhUtoku.EMBER, DruhUtoku.DRAGON_PULSE, DruhUtoku.FLY, DruhUtoku.ENERGY_BALL, DruhUtoku.THUNDER, DruhUtoku.BRAVE_BIRD, DruhUtoku.OUTRAGE, DruhUtoku.BLIZZARD, DruhUtoku.FIRE_BLAST, DruhUtoku.HYPER_BEAM}, Typ.DRAGON, Typ.FLYING),
    ARTICUNO("Articuno", 47, 0, new double[] {90, 85, 100}, new int[] {0, 7, 10, 13, 17, 20, 24, 27, 33, 37, 40}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.ICY_WIND, DruhUtoku.WING_ATTACK, DruhUtoku.EARTH_POWER, DruhUtoku.ROCK_SLIDE, DruhUtoku.ICE_BEAM, DruhUtoku.FLY, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.BLIZZARD, DruhUtoku.BRAVE_BIRD}, Typ.ICE, Typ.FLYING),
    ZAPDOS("Zapdos", 48, 0, new double[] {90, 90, 85}, new int[] {0, 7, 10, 13, 17, 20, 24, 27, 33, 37, 40}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.SPARK, DruhUtoku.WING_ATTACK, DruhUtoku.EARTH_POWER, DruhUtoku.ROCK_SLIDE, DruhUtoku.THUNDERBOLT, DruhUtoku.FLY, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.THUNDER, DruhUtoku.BRAVE_BIRD}, Typ.ELECTRIC, Typ.FLYING),
    MOLTRES("Moltres", 49, 0, new double[] {90, 100, 90}, new int[] {0, 7, 10, 13, 17, 20, 24, 27, 33, 37, 40}, new DruhUtoku[] {DruhUtoku.SCRATCH, DruhUtoku.EMBER, DruhUtoku.WING_ATTACK, DruhUtoku.EARTH_POWER, DruhUtoku.ROCK_SLIDE, DruhUtoku.FLAMETHROWER, DruhUtoku.FLY, DruhUtoku.ENERGY_BALL, DruhUtoku.SLUDGE_WAVE, DruhUtoku.FIRE_BLAST, DruhUtoku.BRAVE_BIRD}, Typ.FIRE, Typ.FLYING),
    MEWTWO("Mewtwo", 50, 0, new double[] {106, 110, 90}, new int[] {0, 7, 10, 13, 17, 20, 24, 27, 33, 37, 40, 45, 50}, new DruhUtoku[] {DruhUtoku.TACKLE, DruhUtoku.CONFUSION, DruhUtoku.WATER_GUN, DruhUtoku.EMBER, DruhUtoku.PSYCHIC, DruhUtoku.EARTH_POWER, DruhUtoku.ENERGY_BALL, DruhUtoku.THUNDER, DruhUtoku.SHADOW_BALL, DruhUtoku.FUTURE_SIGHT, DruhUtoku.BLIZZARD, DruhUtoku.FIRE_BLAST, DruhUtoku.HYPER_BEAM}, Typ.PSYCHIC);



    private String nazovDruhu;
    private int cisloVPokedexe;
    private int evolveLevel;
    private Typ[] typPokemona;
    private DruhPokemona dalsiaEvolucia;
    private double[] baseStats;
    private HashMap<Integer, DruhUtoku> poolUtokov;

    /**
     * V konštruktore sa inicializujú atribúty a ako parametre sa vkladajú všetky informácie spomínané v popise enumu.
     * @param meno
     * @param cisloVPokedexe
     * @param evolveLevel
     * @param baseStats
     * @param levelLearn
     * @param druhUtokuLearn
     * @param typPokemona
     */
    DruhPokemona(String meno, int cisloVPokedexe, int evolveLevel, double[] baseStats, int[] levelLearn, DruhUtoku[] druhUtokuLearn, Typ... typPokemona) {
        this.nazovDruhu = meno;
        this.cisloVPokedexe = cisloVPokedexe;
        this.evolveLevel = evolveLevel;
        this.baseStats = baseStats;
        this.poolUtokov = new HashMap<>();
        for (int i = 0; i < levelLearn.length; i++) {
            this.poolUtokov.put(levelLearn[i], druhUtokuLearn[i]);
        }
        this.typPokemona = typPokemona;
    }

    /**
     * @return názov druhu pokémona
     */
    public String getNazovDruhu() {
        return this.nazovDruhu;
    }

    /**
     * @return číslo v pokédexe
     */
    public int getCisloVPokedexe() {
        return this.cisloVPokedexe;
    }

    /**
     * @return číslo v pokédexe ale ako String, používa sa pri výpise pokédexu
     */
    public String getCisloVPokedexeString() {
        if (this.cisloVPokedexe < 10) {
            return "0" + this.cisloVPokedexe;
        } else {
            return String.valueOf(this.cisloVPokedexe);
        }
    }

    /**
     * @return evolučné štádium druhu slovne
     */
    public String getStadiumEvolucie() {
        if (this.evolveLevel == 0) {
            return "finálne";
        }

        DruhPokemona predoslyPokemon = DruhPokemona.dajDruhPokemona(this.cisloVPokedexe - 1);
        if (predoslyPokemon != null && predoslyPokemon.getEvolveLevel() > 0) {
            return "druhé";
        }

        return "prvé";
    }

    /**
     * @return level, na ktorom sa pokémon vyvíja
     */
    public int getEvolveLevel() {
        return this.evolveLevel;
    }

    /**
     * @return typ pokémona
     */
    public Typ[] getTypPokemona() {
        return this.typPokemona;
    }

    /**
     * @return typ pokémona v String forme
     */
    public String getNazovTypu() {
        String typ = this.typPokemona[0].getNazovTypu();
        if (this.typPokemona.length > 1) {
            typ += "/" + this.typPokemona[1].getNazovTypu();
        }
        return typ;
    }

    /**
     * @return vráti druh pokémona, do ktorého sa tento druh vyvíja
     */
    public DruhPokemona getDalsiaEvolucia() {
        return this.dalsiaEvolucia;
    }

    /**
     * @return základné staty, ktorý tento pokémon má
     */
    public double[] getBaseStats() {
        return this.baseStats;
    }

    /**
     * @param level
     * @return druh útoku, ktorý prislúcha danému levelu v Hashmape poolUtokov
     */
    public DruhUtoku getUtokNaLeveli(int level) {
        return this.poolUtokov.get(level);
    }

    /**
     * @param level
     * @return true/false ak sa tento pokémon naučí/nenaučí útok na danom leveli
     */
    public boolean nauciSaUtok(int level) {
        for (Integer levelNaucenia : this.poolUtokov.keySet()) {
            if (level == levelNaucenia) {
                return true;
            }
        }
        return false;
    }

    /**
     * Nastaví druh pokémona, na ktorý sa tento druh pokémona vyvíja
     */
    public void setDalsiaEvolucia() {
        if (this.evolveLevel > 0) {
            this.dalsiaEvolucia = DruhPokemona.dajDruhPokemona(this.cisloVPokedexe + 1);
        }
    }

    /**
     * Vypíšu sa informácie o druhu pokémona
     */
    public void vypisSa() {
        System.out.printf("%s %s TYP: %s, evolučné štádium: %s\n", this.getCisloVPokedexeString(), this.getNazovDruhu(), this.getNazovTypu(), this.getStadiumEvolucie());
    }

    /**
     * @param cisloVPokedexe
     * @return druh pokémona, ktorý prislúcha danému číslu v pokédexe
     */
    public static DruhPokemona dajDruhPokemona(int cisloVPokedexe) {
        switch (cisloVPokedexe) {
            case 1:
                return DruhPokemona.BULBASAUR;
            case 2:
                return DruhPokemona.IVYSAUR;
            case 3:
                return DruhPokemona.VENUSAUR;
            case 4:
                return DruhPokemona.CHARMANDER;
            case 5:
                return DruhPokemona.CHARMELEON;
            case 6:
                return DruhPokemona.CHARIZARD;
            case 7:
                return DruhPokemona.SQUIRTLE;
            case 8:
                return DruhPokemona.WARTORTLE;
            case 9:
                return DruhPokemona.BLASTOISE;
            case 10:
                return DruhPokemona.WEEDLE;
            case 11:
                return DruhPokemona.KAKUNA;
            case 12:
                return DruhPokemona.BEEDRILL;
            case 13:
                return DruhPokemona.PIDGEY;
            case 14:
                return DruhPokemona.PIDGEOTTO;
            case 15:
                return DruhPokemona.PIDGEOT;
            case 16:
                return DruhPokemona.PIKACHU;
            case 17:
                return DruhPokemona.RAICHU;
            case 18:
                return DruhPokemona.JIGGLYPUFF;
            case 19:
                return DruhPokemona.WIGGLYTUFF;
            case 20:
                return DruhPokemona.ODDISH;
            case 21:
                return DruhPokemona.GLOOM;
            case 22:
                return DruhPokemona.VILEPLUME;
            case 23:
                return DruhPokemona.PSYDUCK;
            case 24:
                return DruhPokemona.GOLDUCK;
            case 25:
                return DruhPokemona.ABRA;
            case 26:
                return DruhPokemona.KADABRA;
            case 27:
                return DruhPokemona.ALAKAZAM;
            case 28:
                return DruhPokemona.MACHOP;
            case 29:
                return DruhPokemona.MACHOKE;
            case 30:
                return DruhPokemona.MACHAMP;
            case 31:
                return DruhPokemona.GEODUDE;
            case 32:
                return DruhPokemona.GRAVELER;
            case 33:
                return DruhPokemona.GOLEM;
            case 34:
                return DruhPokemona.PONYTA;
            case 35:
                return DruhPokemona.RAPIDASH;
            case 36:
                return DruhPokemona.MAGNEMITE;
            case 37:
                return DruhPokemona.MAGNETON;
            case 38:
                return DruhPokemona.MAGNEZONE;
            case 39:
                return DruhPokemona.SHELLDER;
            case 40:
                return DruhPokemona.CLOYSTER;
            case 41:
                return DruhPokemona.GASTLY;
            case 42:
                return DruhPokemona.HAUNTER;
            case 43:
                return DruhPokemona.GENGAR;
            case 44:
                return DruhPokemona.DRATINI;
            case 45:
                return DruhPokemona.DRAGONAIR;
            case 46:
                return DruhPokemona.DRAGONITE;
            case 47:
                return DruhPokemona.ARTICUNO;
            case 48:
                return DruhPokemona.ZAPDOS;
            case 49:
                return DruhPokemona.MOLTRES;
            case 50:
                return DruhPokemona.MEWTWO;
            default:
                return null;
        }
    }
}
