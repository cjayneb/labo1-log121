package entites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Composant {

    private String type;
    private BufferedImage icone;
    private Point position;
    private Point vitesse;

    public Composant() {}

    public Composant(BufferedImage icone, Point position, Point vitesse, String type) {
        this.icone = icone;
        this.position = position;
        this.vitesse = vitesse;
        this.type = type;
    }

    public BufferedImage getIcone() {
        return icone;
    }

    public void setIcone(BufferedImage icone) {
        this.icone = icone;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public void setVitesse(Point vitesse) {
        this.vitesse = vitesse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
