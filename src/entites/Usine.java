package entites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Usine extends Noeud implements IUsine {

    private Queue<Composant> composants = new LinkedList<>();


    public Usine(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, int intervalProduction, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        super(type, id, coordinates, sortieType, entrees, intervalProduction, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein);
    }

    public boolean peutProduire() {
        Map<String, Integer> quantitesActuelles = new HashMap();
        Map<String, Integer> quatitesRequises = getEntreeTypes();

        // Verifier que cette usine contient assez de composant pour produire
//        for (Composant c : composants) {
//
//        }
        return true;
    }

    public Composant produire() {
        // remove composants utilise
        return new Composant(getIcone(String.format("\\src\\ressources\\{0}.png", getSortieType())), getCoordinates(), getVecteurVitesse(), getSortieType());
    }

    private Point getVecteurVitesse() {
        int x = 0;
        int y = 0;

        // creer vecteur vitesse dependant des coordonnees et de la destination
        // (0,1), (0,-1), (1,0), (-1,0), (1,1), (-1,-1), (-1,1), (1,-1)

        return new Point(x, y);
    }

    @Override
    public void update(Object o) {

    }
}
