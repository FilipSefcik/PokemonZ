package sk.uniza.fri.interactive.budovy;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.npc.Trener;
import sk.uniza.fri.hrac.Odznaky;
import sk.uniza.fri.world.location.Lokacia;
import sk.uniza.fri.pokemon.Pokemon;

import java.util.ArrayList;

/**
 * 28. 3. 2022 - 12:58
 *
 * V Štadiónoch môže hráč získavať odznaky tým že porazí všetkých trénerov v ňom
 *
 * @author Filip Šefčík
 */
public class Stadion extends AbstractBudova {

    private String nazovLokacie;
    private Odznaky odznakStadionu;
    private ArrayList<Trener> treneri;
    private Trener gymLeader;
    private int pocetZapasov;

    /**
     * V konštruktore sa vytvorí štadión, ktorý so špecifickým názvom mesta kde sa nachádza
     * a odznakom, ktorý sa dá získať za jeho porazenie
     * @param odznakStadionu
     */
    public Stadion(Lokacia lokacia, int pocetZapasov, Odznaky odznakStadionu) {
        super("stadion", "vyzvat", "odist", "pomoc");
        this.nazovLokacie = lokacia.getNazovLokacie();
        this.pocetZapasov = pocetZapasov;
        this.odznakStadionu = odznakStadionu;
        this.odznakStadionu.setLokaciaZiskania(nazovLokacie);
        this.treneri = new ArrayList<>();
    }

    /**
     * Vloží trénera do štadióna
     * @param trener nový tréner
     * @param jeGymLeader true/false ak je/nie je tréner gym leaderom štadióna
     * @param levelyPokemonov levely trénerových pokémonov
     * @param pokemoni trénerovi pokémoni
     */
    public void pridajTrenera(Trener trener, boolean jeGymLeader, int[] levelyPokemonov, Pokemon... pokemoni) {
        trener.setPocetZapasov(this.pocetZapasov);
        trener.nastavPartu(levelyPokemonov, pokemoni);
        if (jeGymLeader) {
            this.gymLeader = trener;
        } else {
            this.treneri.add(trener);
        }
    }

    @Override
    public void vstupDoBudovy(Hrac hrac) {
        if (hrac.ziskalOdznak(this.odznakStadionu)) {
            System.out.println("Tento štadión si už porazil, ako dôkaz máš predsa " + this.odznakStadionu.getNazovOdznaku() + "!");
            if (this.pocetZapasov > 0) {
                System.out.println("Ale to nevadí, môžeš vyzvať všetkých na odvetu ešte " + this.gymLeader.getPocetZapasov() + "-krát, len už nedostaneš odznak.");
            }
        } else {
            System.out.println("Vitaj v štadióne mesta " + this.nazovLokacie + "! V tomto štadióne môžete získať " + this.odznakStadionu.getNazovOdznaku() + "!");
        }
        if (this.pocetZapasov > 0) {
            super.vstupDoBudovy(hrac);
        } else {
            System.out.println("Dokonca si už aj odbojoval všetky odvetné zápasy, už si dostatočne ukázal svoju silu.");
        }
    }

    @Override
    public void odchodZBudovy() {
        System.out.println("Príď kedy len chceš! Štadión mesta " + this.nazovLokacie + "je pre teba vždy k dispozícií.");
    }

    @Override
    public void uvitaciaHlaska() {
        System.out.println(this.gymLeader.getMeno() + " a ďalší tréneri už na teba čakajú, nebudú ťa šetriť!");
    }

    @Override
    public boolean spracujInput(Hrac hrac, String[] vstup) {
        switch (super.getCisloPrikazu(vstup[0])) {
            case 1:
                return this.subojSHracom(hrac);
            case 2:
                this.odchodZBudovy();
                return false;
            case 3:
                super.vypisPrikazy();
                return false;
            default:
                System.out.println("Môžeš to zopakovať ešte raz?");
                return true;
        }
    }

    /**
     * Hráč začne bojovať so všetkými trénermi zaradom, a nakoniec aj s gym leaderom
     * Ak sa mu ich povolí všetkých poraziť, získa odznak
     * Ak už má odznak, tak bude stále bojovať len ho nedostane, bude sa to brať ako priateľská odveta
     * @param hrac
     * @return true/false ak hráč neprehral/prehral
     */
    private boolean subojSHracom(Hrac hrac) {
        for (int i = 0; i < this.treneri.size(); i++) {
            System.out.println("Ako " + (i + 1) + ". sa proti tebe postaví " + this.treneri.get(i).getMeno() + ".");
            if (!this.treneri.get(i).rozhovor(hrac, this.nazovLokacie, super.getNazovBudovy(), "subojSTrenerom")) {
                hrac.hracPrehral(false);
                return false;
            }
        }
        System.out.println("Podarilo sa ti poraziť všetkých trénerov v štadióne, teraz na teba čaká pravá skúška.");
        System.out.println("Ako posledný sa proti tebe postaví samotný gym leader " + this.gymLeader.getMeno() + "!");
        if (!this.gymLeader.rozhovor(hrac, this.nazovLokacie, super.getNazovBudovy(), "subojSGymLeaderom")) {
            hrac.hracPrehral(false);
            return false;
        } else {
            if (!hrac.ziskalOdznak(this.odznakStadionu)) {
                System.out.println("Bol to vynikajúci zápas podarilo sa ti poraziť štadión mesta " + this.nazovLokacie + ".");
                System.out.println("Ako dôkaz tvojho úspechu ti s radosťou odovzdávam " + this.odznakStadionu.getNazovOdznaku() + ".");
                this.odznakStadionu.setLokaciaZiskania(this.nazovLokacie);
                hrac.dajHracoviOdznak(this.odznakStadionu);
                super.premenujPrikaz(0, "rematch");
            } else {
                System.out.println("Bol to vynikajúci zápas, ako inak, od teba " + hrac.getMeno() + " sa nič menej nedá čakať.");
            }
            return true;
        }
    }
}
