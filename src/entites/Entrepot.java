package entites;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Entrepot extends Noeud{
    private List<Usine> usines = new ArrayList<>();
    private StrategieDeVente strategieDeVente;

    public Entrepot(){
        setType("entrepot");
    }

    public Entrepot(String type, int id, Point coordinates, String sortieType, Map<String, Integer> entrees, String iconeVide, String iconeUnTiers, String iconeDeuxTiers, String iconePlein) {
        super(type, id, coordinates, sortieType, entrees, iconeVide, iconeUnTiers, iconeDeuxTiers, iconePlein);
    }

    @Override
    public void ajouterComposantEntree(Composant composant) {
        getComposantsEntree().add(composant);
        notifyUsines();
    }

    @Override
    public BufferedImage getIconeToDisplay() {
        float nombreMaxAvions = getEntreeTypes().getOrDefault("avion", 5);
        float nombreActuelAvions = getComposantsEntree().size();
        if (nombreActuelAvions > 0 && nombreActuelAvions <= nombreMaxAvions / 3) {
            return getIconeUnTiers();
        } else if (nombreActuelAvions > nombreMaxAvions / 3 && (nombreActuelAvions <= nombreMaxAvions * 2 / 3 || nombreActuelAvions < nombreMaxAvions)) {
            return getIconeDeuxTiers();
        } else if (nombreActuelAvions == nombreMaxAvions) {
            notifyUsines();
            return getIconePlein();
        }
        return getIconeVide();
    }

    public StrategieDeVente getStrategieDeVente() {
        return strategieDeVente;
    }

    public void setStrategieDeVente(StrategieDeVente strategieDeVente) {
        this.strategieDeVente = strategieDeVente;
    }

    public void vendreAvion() {
        strategieDeVente.vendre(getComposantsEntree());
        notifyUsines();
    }

    public List<Usine> getUsines() {
        return usines;
    }

    public void addObserver(Usine usine) {
        this.usines.add(usine);
    }

    public void removeObserver(Usine usine) {
        this.usines.remove(usine);
    }

    public void notifyUsines() {
        for (Usine usine : this.usines) {
            usine.update(getComposantsEntree().size(), getEntreeTypes().get("avion"));
        }
    }

}
