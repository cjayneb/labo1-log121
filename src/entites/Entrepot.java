package entites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Entrepot extends Noeud{
    private List<Usine> usines = new ArrayList<>();
    private Queue<Composant> avions = new LinkedList<>();
    private StrategieDeVente strategieDeVente;

    public Entrepot(){
        setType("entrepot");
    }

    public Entrepot(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, int intervalProduction, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        super(type, id, coordinates, sortieType, entrees, intervalProduction, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein);
    }

    public void ajouterAvion(Composant avion) {
        if (avions.size() < getEntreeTypes().getOrDefault("avion", 5)) {
            avions.add(avion);
        }
        notifyUsines(avions.size());
    }

    public StrategieDeVente getStrategieDeVente() {
        return strategieDeVente;
    }

    public void setStrategieDeVente(StrategieDeVente strategieDeVente) {
        this.strategieDeVente = strategieDeVente;
    }

    public void vendreAvion() {
        strategieDeVente.vendre(avions);
    }

    public List<Usine> getUsines() {
        return usines;
    }

    public void setUsines(List<Usine> usines) {
        this.usines = usines;
    }

    public void addObserver(Usine usine) {
        this.usines.add(usine);
    }

    public void removeObserver(Usine usine) {
        this.usines.remove(usine);
    }

    public void notifyUsines(Object o) {
        for (Usine usine : this.usines) {
            usine.update(o);
        }
    }

}
