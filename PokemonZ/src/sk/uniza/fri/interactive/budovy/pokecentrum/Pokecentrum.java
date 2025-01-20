package sk.uniza.fri.interactive.budovy.pokecentrum;

import sk.uniza.fri.hrac.Hrac;
import sk.uniza.fri.interactive.budovy.AbstractBudova;
import sk.uniza.fri.world.location.Lokacia;

/**
 * 28. 3. 2022 - 12:58
 *
 * V Pokécentre si môže hráč oživiť svojich pokémonov alebo pracovať s PC
 *
 * @author Filip Šefčík
 */
public class Pokecentrum extends AbstractBudova {

    private Lokacia lokacia;

    /**
     * V konštruktore sa nastavia príkazy používané v Pokécentre a inicializujú atribúty
     * @param lokacia ktorá sa nastaví ako respawn lokácia hráčovi keď si vyliečí pokémonov v tomto Pokécentre
     */
    public Pokecentrum(Lokacia lokacia) {
        super("pokecentrum", "vyliecit", "pc", "pomoc", "odist");
        this.lokacia = lokacia;
    }

    @Override
    public void odchodZBudovy() {
        System.out.println("Radi sme Vám boli k službám. Príďte zas!");
        System.out.println("Pokécentrum mesta " + this.lokacia.getNazovLokacie() + " je Vám vždy k dispozicii.");
    }

    @Override
    public void uvitaciaHlaska() {
        System.out.println("Vitajte v pokécentre mesta " + this.lokacia.getNazovLokacie() + ".");
        System.out.println("Volám sa sestrička Joy, k Vaším službám.");
    }

    @Override
    public boolean spracujInput(Hrac hrac, String[] vstup) {
        switch (super.getCisloPrikazu(vstup[0])) {
            case 1:
                this.vyliecHracoviPokemonov(hrac);
                return true;
            case 2:
                PC.getInstance().pracujSPC(hrac, super.getNazovBudovy());
                return true;
            case 3:
                super.vypisPrikazy();
                return true;
            case 4:
                this.odchodZBudovy();
                return false;
            default:
                System.out.println("Nerozumiem Vás, môžete to zopakovať?");
                return true;
        }
    }

    /**
     * Hráčovi sa vyliečia pokémoni a nastaví sa mu respawn lokácia na lokáciu tohto Pokécentra
     * @param hrac
     */
    private void vyliecHracoviPokemonov(Hrac hrac) {
        if (hrac.getPocetPokemonovVParte() > 0) {
            hrac.vyliecPokemonov();
            System.out.println("Vaša parta je zas a znova plná energie.");
            hrac.setRespawnLokacia(this.lokacia);
        } else {
            System.out.println("Nemáte pri sebe pokémonov!");
        }
    }
}
