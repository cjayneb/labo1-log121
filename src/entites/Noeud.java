package entites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public abstract class Noeud {

    private String type;
    private int id;
    private Point coordinates;
    private String sortieType;
    private Map<String, Integer> entreeTypes;
    private Queue<Composant> composantsEntree = new LinkedList<>();
    private int intervalProduction;
    private BufferedImage iconeVide;
    private BufferedImage iconeUnTiers;
    private BufferedImage iconeDeuxTiers;
    private BufferedImage iconePlein;

    public Noeud(){}

    public Noeud(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        this.type = type;
        this.id = id;
        this.coordinates = coordinates;
        this.sortieType = sortieType;
        this.entreeTypes = entrees;
        this.iconeVide = getIcone(iconeVide);
        this.iconeUnTiers = getIcone(iconeUnTiers);
        this.iconeDeuxTiers = getIcone(iconeDeuxTiers);
        this.iconePlein = getIcone(iconePlein);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public String getSortieType() {
        return sortieType;
    }

    public void setSortieType(String sortieType) {
        this.sortieType = sortieType;
    }

    public Map<String, Integer> getEntreeTypes() {
        return entreeTypes;
    }

    public void setEntreeTypes(Map<String, Integer> entrees) {
        this.entreeTypes = entrees;
    }

    public Queue<Composant> getComposantsEntree() {
        return composantsEntree;
    }

    public void setComposantsEntree(Queue<Composant> composantsEntree) {
        this.composantsEntree = composantsEntree;
    }

    public BufferedImage getIconeVide() {
        return iconeVide;
    }

    public void setIconeVide(String iconeVide) {
        this.iconeVide = getIcone(iconeVide);
    }

    public BufferedImage getIconeUnTiers() {
        return iconeUnTiers;
    }

    public void setIconeUnTiers(String iconeUnTiers) {
        this.iconeUnTiers = getIcone(iconeUnTiers);
    }

    public BufferedImage getIconeDeuxTiers() {
        return iconeDeuxTiers;
    }

    public void setIconeDeuxTiers(String iconeDeuxTiers) {
        this.iconeDeuxTiers = getIcone(iconeDeuxTiers);
    }

    public BufferedImage getIconePlein() {
        return iconePlein;
    }

    public void setIconePlein(String iconePlein) {
        this.iconePlein = getIcone(iconePlein);
    }

    public abstract void ajouterComposantEntree(Composant composant);

    public abstract BufferedImage getIconeToDisplay(int compteurTour);

    public BufferedImage getIcone(String path) {
        try {
            File file = new File(path);
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
