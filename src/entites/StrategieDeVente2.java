package entites;

import java.util.Queue;

public class StrategieDeVente2 implements StrategieDeVente {

    // Vendre a des intervalles fixes
    @Override
    public void vendre(Entrepot entrepot) {
        Queue<Composant> avions = entrepot.getComposantsEntree();
        if (avions.size() > 2) {
            avions.remove();
            entrepot.notifyUsines(avions.size());
        }
    }
}
