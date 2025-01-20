package sk.uniza.fri.items.potion;

import java.util.HashMap;

/**
 * 28. 3. 2022 - 12:58
 *
 * Táto trieda obsahuje všetky typy Potionov a PowerPotionov s ich názvami a efektivitou
 *
 * @author Filip Šefčík
 */
public class TypPotionov {

    private HashMap<String, Integer> potiony;
    private HashMap<String, Integer> powerPotiony;
    private static TypPotionov instance;

    /**
     * Inštancia triedy TypPotionov je singleton, pretože viac inštancií nie je potrebných
     * @return inštancia
     */
    public static TypPotionov getInstance() {
        if (instance == null) {
            instance = new TypPotionov();
        }
        return instance;
    }

    /**
     * V bezparametrickom konštruktore sa nastavia všetky typy Potionov a PowerPotionov v dvoch HashMap-ách
     */
    private TypPotionov() {
        this.potiony = new HashMap<>();
        this.potiony.put("Potion", 20);
        this.potiony.put("SuperPotion", 50);
        this.potiony.put("HyperPotion", 200);

        this.powerPotiony = new HashMap<>();
        this.powerPotiony.put("Energizer", 5);
        this.powerPotiony.put("RedTauros", 10);
        this.powerPotiony.put("GokuEnergy", 20);
    }

    /**
     * @return HashMap obsahujúca Potiony
     */
    public HashMap<String, Integer> getPotiony() {
        return this.potiony;
    }

    /**
     * @return HashMap obsahujúca PowerPotiony
     */
    public HashMap<String, Integer> getPowerPotiony() {
        return this.powerPotiony;
    }
}
