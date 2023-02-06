package entites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Noeud {

    private String type;
    private int id;
    private Point coordinates;
    private Point destination;
    private String sortieType;
    private Map<String, Integer> entreeTypes;
    private int intervalProduction;

    private BufferedImage iconeVide;
    private BufferedImage iconeUnTiers;
    private BufferedImage iconeDeuxTiers;
    private BufferedImage iconePlein;

    public Noeud(){}

    public Noeud(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, int intervalProduction, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        this.type = type;
        this.id = id;
        this.coordinates = coordinates;
        this.sortieType = sortieType;
        this.entreeTypes = entrees;
        this.intervalProduction = intervalProduction;
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

    public Point getDestination() {
        return this.destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
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

    public int getIntervalProduction() {
        return intervalProduction;
    }

    public void setIntervalProduction(int intervalProduction) {
        this.intervalProduction = intervalProduction;
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

    public BufferedImage getIcone(String path) {
        try {
            File file = new File(path);
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
