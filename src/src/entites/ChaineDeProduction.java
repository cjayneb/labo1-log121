package entites;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChaineDeProduction {
    public Entrepot entrepot = new Entrepot();
    public List<Noeud> noeuds = new ArrayList<>();
    public Map<Point, Point> chemins = new HashMap<>();
}
