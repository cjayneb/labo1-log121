package entites;

import java.util.Queue;

public class StrategieDeVente2 implements StrategieDeVente {

    // Vendre a des intervalles fixes
    @Override
    public void vendre(Queue<Composant> avions) {
        if (avions.size() > 2) {
            avions.remove();
        }
    }
}
