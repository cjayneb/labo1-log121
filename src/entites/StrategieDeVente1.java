package entites;

import java.util.NoSuchElementException;
import java.util.Queue;

public class StrategieDeVente1 implements StrategieDeVente {

    // Vendre un avion selon une fonction aleatoire
    @Override
    public void vendre(Entrepot entrepot) {
        Queue<Composant> avions = entrepot.getComposantsEntree();
        if (peutVendre(avions.size())) {
            avions.remove();
            entrepot.notifyUsines(avions.size());
        }
    }

    private boolean peutVendre(int nombreAvions) {
        return nombreAvions > 0 && Math.random() < 0.005d;
    }
}
