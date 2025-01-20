package sk.uniza.fri.hrac;

/**
 * 28. 3. 2022 - 12:58
 *
 * Odznaky zbiera hráč počas jeho hrania, získava ich pomocou porážanie štadiónov v mestách
 *
 * @author Filip Šefčík
 */
public enum Odznaky {
    START_BADGE("Start badge"),
    MUSCLE_BADGE("Muscle badge"),
    WATERFALL_BADGE("Waterfall badge"),
    DRAGON_BADGE("Dragon badge");

    private String nazovOdznaku;
    private String lokaciaZiskania;

    /**
     * Pre vytvorenie inštancie je potrebý iba jej názov
     * @param nazovOdznaku
     */
    Odznaky(String nazovOdznaku) {
        this.nazovOdznaku = nazovOdznaku;
        this.lokaciaZiskania = null;
    }

    /**
     * Tento parameter nastaví názov lokácie kde sa Odznak dá získať
     * @param lokaciaZiskania
     */
    public void setLokaciaZiskania(String lokaciaZiskania) {
        this.lokaciaZiskania = lokaciaZiskania;
    }

    /**
     * @return názov Odznaku
     */
    public String getNazovOdznaku() {
        return this.nazovOdznaku;
    }

    /**
     * @return názov lokácie, kde sa dá Odznak získať
     */
    public String getLokaciaZiskania() {
        return this.lokaciaZiskania;
    }
}
