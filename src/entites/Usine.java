package entites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Usine extends Noeud implements IUsine {

    private int intervalProduction;
    private Queue<Composant> composantsEntree = new LinkedList<>();
    private Queue<Composant> composantsSortie = new LinkedList<>();
    private Point destination;
    private Point vecteurVitesse;

    public Usine(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, int intervalProduction, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        super(type, id, coordinates, sortieType, entrees, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein);
        this.intervalProduction = intervalProduction;
    }

    public boolean peutProduire() {
        if (getType().equals("usine-matiere")) {
            return true;
        }

        Map<String, Integer> quantitesActuelles = new HashMap();
        Map<String, Integer> quatitesRequises = getEntreeTypes();

        // Verifier que cette usine contient assez de composant pour produire
//        for (Composant c : composants) {
//
//        }
        return false;
    }

    public void produire() {
        // remove composants utilise

        composantsSortie.add(new Composant(getIcone(String.format("src/ressources/%s.png",
                getSortieType())), getCoordinates(), vecteurVitesse, getSortieType()));
    }

    private Point getVecteurVitesse() {
        int x = 0;
        int y = 0;

        if (destination.x < getCoordinates().x) {
            x = -5;
        } else if (destination.x > getCoordinates().x) {
            x = 5;
        }

        if (destination.y < getCoordinates().y) {
            y = -5;
        } else if (destination.y > getCoordinates().y) {
            y = 5;
        }
        return new Point(x, y);
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
        setVecteurVitesse(getVecteurVitesse());
    }

    public void setVecteurVitesse(Point vecteurVitesse) {
        this.vecteurVitesse = vecteurVitesse;
    }

    public Queue<Composant> getComposantsSortie() {
        return composantsSortie;
    }

    @Override
    public BufferedImage getIconeToDisplay(int compteurTour) {
        if (peutProduire()) {
            if (compteurTour % intervalProduction > 1 && compteurTour % intervalProduction <= (intervalProduction/3)) {
                return getIconeUnTiers();
            } else if (compteurTour % intervalProduction > (intervalProduction/3) && compteurTour % intervalProduction <= (intervalProduction*2/3)) {
                return getIconeDeuxTiers();
            } else if (compteurTour % intervalProduction > (intervalProduction*2/3) && compteurTour % intervalProduction <= intervalProduction) {
                return getIconePlein();
            }
        }
        return getIconeVide();
    }

    public boolean productionCompletee(int compteurTour) {
        if (peutProduire()) {
            return compteurTour % intervalProduction == 0;
        }
        return false;
    }

    public boolean composantArriveADestination(Composant c) {
        return Math.abs(destination.x - c.getPosition().x) < 5 && Math.abs(destination.y - c.getPosition().y) < 5;
    }

    @Override
    public void update(Object o) {

    }
}
