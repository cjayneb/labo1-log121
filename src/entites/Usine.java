package entites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 *
 */
public class Usine extends Noeud implements IUsine {

    private int vitesseComposants = 5;
    private int compteurInterval = 0;
    private int intervalProductionDeBase;
    private int intervalProduction;
    private Queue<Composant> composantsSortie = new LinkedList<>();
    private Point destination;
    private int destinationId;
    private Point vecteurVitesse;
    private boolean stopProduction = false;

    public Usine(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, int intervalProduction, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        super(type, id, coordinates, sortieType, entrees, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein);
        this.intervalProductionDeBase = intervalProduction;
        this.intervalProduction = intervalProduction;
        this.compteurInterval = intervalProduction;
    }

    public boolean peutProduire() {
        if (stopProduction) {
            return false;
        }

        AtomicBoolean peutProduire = new AtomicBoolean(true);

        if (getType().equals("usine-matiere")) {
            return peutProduire.get();
        }

        Map<String, Integer> quantitesActuelles = new HashMap();
        Map<String, Integer> quantitesRequises = getEntreeTypes();

        // Construire le dictionnaire des quantites actuelles de chaque composant
        getComposantsEntree().forEach(c -> {
            if (!quantitesActuelles.containsKey(c.getType())){
                quantitesActuelles.put(
                        c.getType(),
                        getComposantsEntree()
                                .stream().filter(i -> i.getType().equals(c.getType()))
                                .collect(Collectors.toList()).size()
                );
            }
        });

        // Verifier que cette usine contient assez de composant pour produire
        quantitesRequises.forEach((t, q) -> {
            if (!quantitesActuelles.containsKey(t) || quantitesActuelles.get(t) < q) {
                peutProduire.set(false);
            }
        });

        return peutProduire.get();
    }

    public void produire() {
        // Supprimer les composants utilises pour produire le composant
        getEntreeTypes().forEach((t, q) -> {
            for (int i = 0; i < q; i++) {
                Composant composantASupprime = getComposantsEntree()
                        .stream().filter(c -> c.getType().equals(t)).findFirst().get();
                getComposantsEntree().remove(composantASupprime);
            }
        });

        composantsSortie.add(new Composant(getIcone(String.format("src/ressources/%s.png",
                getSortieType())), getCoordinates(), getVecteurVitesse(), getSortieType()));
    }

    private Point getVecteurVitesse() {
        int x = 0;
        int y = 0;

        if (destination.x < getCoordinates().x) {
            x = -(vitesseComposants);
        } else if (destination.x > getCoordinates().x) {
            x = vitesseComposants;
        }

        if (destination.y < getCoordinates().y) {
            y = -(vitesseComposants);
        } else if (destination.y > getCoordinates().y) {
            y = vitesseComposants;
        }
        return new Point(x, y);
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

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public void ajouterComposantEntree(Composant composant) {
        getComposantsEntree().add(composant);
    }

    @Override
    public BufferedImage getIconeToDisplay() {
        if (peutProduire()) {
            compteurInterval -= 5;

            int diff = intervalProduction - compteurInterval;

            if (diff > 0 && diff <= (intervalProduction/3)) {
                return getIconeUnTiers();
            } else if (diff > (intervalProduction/3) && diff <= (intervalProduction*2/3)) {
                return getIconeDeuxTiers();
            } else if (diff > (intervalProduction*2/3) && diff <= (intervalProduction+4)) { // <-- +4 permet aux intervalles de production
                return getIconePlein();                                                     // d'être fixées à n'import quelle valeur
            }                                                                               // (même si cette valeur n'est pas un multiple de cinq)
        }
        compteurInterval = intervalProduction;

        return getIconeVide();
    }

    public boolean productionCompletee() {
        if (peutProduire()) {
            return intervalProduction - compteurInterval >= intervalProduction;
        }
        return false;
    }

    public boolean composantArriveADestination(Composant c) {
        return Math.abs(destination.x - c.getPosition().x) < 5 && Math.abs(destination.y - c.getPosition().y) < 5;
    }

    @Override
    public void update(int nombreAvions, int nombreAvionsMax) {
        stopProduction = false;
        vitesseComposants = 5;
        intervalProduction = intervalProductionDeBase;
        if (nombreAvions == nombreAvionsMax) {
            stopProduction = true;
        }
        if (nombreAvions > (nombreAvionsMax*2/5)) {
            intervalProduction += 100;
        }
        if (nombreAvions > (nombreAvionsMax*3/5)) {
            intervalProduction += 100;
        }
    }
}
