package sk.uniza.fri;

import sk.uniza.fri.game.Hra;
import sk.uniza.fri.game.essentials.Komunikator;

/**
 * Created by IntelliJ IDEA.
 * User: Filip Šefčík
 * Date: 28. 3. 2022
 * Time: 12:58
 */
public class Main {

    /**
     * Metóda main spúšťa hru, predtým než ju spustí tak skontroluje, či náhodou nemá hráč uloženú hru.
     * Ak má, ponúkne mu načítanie uloženej hry. Ak nemá, spustí sa nová hra.q
     * @param args
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        if (hra.maHracUlozenuHru()) {
            if (Komunikator.vyberAnoAleboNie("Máte uloženú hru, chcete ju načítať?")) {
                hra.nacitajHru();
            } else {
                hra.spustiIntro();
            }
        } else {
            hra.spustiIntro();
        }
        hra.spustiHru();
    }
}
