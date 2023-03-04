package entites;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Composant {

    private String type;
    private BufferedImage icone;
    private Point position;
    private Point vitesse;

    public Composant(BufferedImage icone, Point position, Point vitesse, String type) {
        this.icone = icone;
        this.position = new Point(position.x, position.y);
        this.vitesse = vitesse;
        this.type = type;
    }

    public BufferedImage getIcone() {
        return icone;
    }

    public Point getPosition() {
        return position;
    }

    public Point getVitesse() {
        return vitesse;
    }

    public String getType() {
        return type;
    }
}
