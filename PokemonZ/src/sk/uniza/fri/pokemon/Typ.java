package sk.uniza.fri.pokemon;

/**
 * 28. 3. 2022 - 12:58
 *
 * Typ je informácia, ktorú zdieľaju pokémoni aj útoky, podľa týchto informácií sa určuje efektivita jednotlivých typov proti sebe.
 * Niektoré majú voči sebe imunitu, niektoré sú voči sebe silné iné zas slabé.
 *
 * @author Filip Šefčík
 */
public enum Typ {

    BUG("Bug",
            new String[]{"Grass", "Psychic", "Dark"},
            new String[]{"Fighting", "Flying", "Poison", "Ghost", "Steel", "Fire", "Fairy"},
            new String[]{}),

    DARK("Dark",
            new String[]{"Psychic", "Ghost"},
            new String[]{"Fighting", "Dark", "Steel", "Fairy"},
            new String[]{}),

    DRAGON("Dragon",
            new String[]{"Dragon"},
            new String[]{"Steel"},
            new String[]{"Fairy"}),

    ELECTRIC("Electric",
            new String[]{"Water", "Flying"},
            new String[]{"Grass", "Electric", "Dragon"},
            new String[]{"Ground"}),

    FAIRY("Fairy",
            new String[]{"Dragon", "Fighting", "Dark"},
            new String[]{"Steel", "Fire", "Poison"},
            new String[]{}),

    FIRE("Fire",
            new String[]{"Grass", "Ice", "Bug"},
            new String[]{"Fire", "Water", "Rock", "Dragon"},
            new String[]{}),

    FIGHTING("Fighting",
            new String[]{"Normal", "Rock", "Steel", "Ice", "Dark"},
            new String[]{"Flying", "Poison", "Bug", "Psychic", "Fairy"},
            new String[]{"Ghost"}),

    FLYING("Flying",
            new String[]{"Fighting", "Grass"},
            new String[]{"Rock", "Steel", "Electric"},
            new String[]{}),

    GRASS("Grass",
            new String[]{"Water", "Ground", "Rock"},
            new String[]{"Fire", "Grass", "Poison", "Flying", "Bug", "Dragon", "Steel"},
            new String[]{}),

    GHOST("Ghost",
            new String[]{"Ghost", "Psychic"},
            new String[]{"Dark"},
            new String[]{"Normal"}),

    GROUND("Ground",
            new String[]{"Poison", "Rock", "Steel", "Fire", "Electric"},
            new String[]{"Bug", "Grass"},
            new String[]{"Flying"}),

    ICE("Ice",
            new String[]{"Grass", "Ground", "Flying", "Dragon"},
            new String[]{"Fire", "Water", "Ice", "Steel"},
            new String[]{}),

    NORMAL("Normal",
            new String[]{},
            new String[]{"Rock", "Steel"},
            new String[]{"Ghost"}),

    POISON("Poison",
            new String[]{"Grass", "Fairy"},
            new String[]{"Poison", "Ground", "Rock", "Bug"},
            new String[]{"Steel"}),

    PSYCHIC("Psychic",
            new String[]{"Fighting", "Poison"},
            new String[]{"Psychic", "Steel"},
            new String[]{"Dark"}),

    ROCK("Rock",
            new String[]{"Flying", "Bug", "Fire"},
            new String[]{"Fighting", "Ground", "Steel"},
            new String[]{}),

    STEEL("Steel",
            new String[]{"Rock", "Ice", "Fairy"},
            new String[]{"Steel", "Fire", "Water", "Electric"},
            new String[]{}),

    WATER("Water",
            new String[]{"Fire", "Rock", "Ground"},
            new String[]{"Water", "Grass", "Dragon"},
            new String[]{}),

    NONE("None",
            new String[]{},
            new String[]{},
            new String[]{});


    private String nazovTypu;
    private String[] superEffective;
    private String[] notVeryEffective;
    private String[] noEffect;

    /**
     * Typ obsahuje informáciu o svojom názve, proti akým typom je silný, proti akým slabý, proti akým nemá žiaden efekt
     * @param nazovTypu
     * @param superEffective
     * @param notVeryEffective
     * @param noEffect
     */
    Typ(String nazovTypu, String[] superEffective, String[] notVeryEffective, String[] noEffect) {
        this.nazovTypu = nazovTypu;
        this.superEffective = superEffective;
        this.notVeryEffective = notVeryEffective;
        this.noEffect = noEffect;
    }

    /**
     * @return názov typu
     */
    public String getNazovTypu() {
        return this.nazovTypu;
    }

    /**
     * @return zoznam útokov, proti ktorým je typ super efektívny
     */
    public String[] getSuperEffective() {
        return this.superEffective;
    }

    /**
     * @return zoznam útokov, proti ktorým nie je typ efektívny
     */
    public String[] getNotVeryEffective() {
        return this.notVeryEffective;
    }

    /**
     * @return zoznam útokov, proti ktorým je typ neúčinný
     */
    public String[] getNoEffect() {
        return this.noEffect;
    }

    /**
     * Skontroluje či sa daný typ nachádza v stanovenom zozname typov
     * @param druhEfektu
     * @param nazovTypu
     * @return true/false ak daný zoznam typov obsahuje/neobsahuje daný typ
     */
    private boolean obsahujeTyp(String[] druhEfektu, String nazovTypu) {

        if (druhEfektu.length == 0) {
            return false;
        }

        for (String typ : druhEfektu) {
            if (typ.equals(nazovTypu)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param typ
     * @return true/false ak sa typ nachádza/nenachádza v zozname super effective typov
     */
    public boolean jeSuperEffectiveProti(Typ typ) {
        return this.obsahujeTyp(this.getSuperEffective(), typ.getNazovTypu());
    }

    /**
     * @param typ
     * @return true/false ak sa typ nachádza/nenachádza v zozname not very effective typov
     */
    public boolean jeNotVeryEffectiveProti(Typ typ) {
        return this.obsahujeTyp(this.getNotVeryEffective(), typ.getNazovTypu());
    }

    /**
     * @param typ
     * @return true/false ak sa typ nachádza/nenachádza v zozname no effective typov
     */
    public boolean nemaEffectProti(Typ typ) {
        return this.obsahujeTyp(this.getNoEffect(), typ.getNazovTypu());
    }
}
