package simulation;

import entites.Composant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import static simulation.Simulation.chaine;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanneauPrincipal() {
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// Commencer la visualisation lorsque le fichier de configuration a été chargé
		if(chaine.noeuds.size() > 6) {

			// Dessiner les chemins
			chaine.chemins.forEach((k, l)-> {
				g.drawLine(k.x, k.y, l.x, l.y);
			});

			// Dessiner les composants
			chaine.entrepot.getUsines().forEach(u -> {
				if (u.productionCompletee()) {
					u.produire();
				}

				List<Composant> composantsADetruire = new ArrayList<>();
				u.getComposantsSortie().forEach(c -> {
					if (u.composantArriveADestination(c)) {
						composantsADetruire.add(c);
						chaine.noeuds
								.stream()
								.filter(n -> n.getId() == u.getDestinationId())
								.findFirst().get()
								.ajouterComposantEntree(c);
					} else {
						c.getPosition().translate(c.getVitesse().x, c.getVitesse().y);
						g.drawImage(c.getIcone(), c.getPosition().x, c.getPosition().y, null);
					}
				});
				u.getComposantsSortie().removeAll(composantsADetruire);
			});

			// Dessiner les usines et les entrepots
			chaine.noeuds.forEach(n -> {
				g.drawImage(n.getIconeToDisplay(),
						n.getCoordinates().x,
						n.getCoordinates().y, null);
			});

			// Vendre un avion si la strategie de vente le permet
			if (chaine.entrepot.getStrategieDeVente() != null) {
				chaine.entrepot.vendreAvion();
			}
		}
	}

}