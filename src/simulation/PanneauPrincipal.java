package simulation;

import entites.Composant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import static simulation.Simulation.chaine;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private int compteurTour = 0;

	public PanneauPrincipal() {
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		if (compteurTour > 1000) {
			compteurTour = 0;
		}

		// Commencer la visualisation lorsque le fichier de configuration a �t� charg�
		if(chaine.noeuds.size() > 6) {
			compteurTour += 5;

			// Dessiner les chemins
			chaine.chemins.forEach((k, l)-> {
				g.drawLine(k.x, k.y, l.x, l.y);
			});

			// Dessiner les composants
			chaine.entrepot.getUsines().forEach(u -> {
				if (u.productionCompletee(compteurTour)) {
					u.produire();
				}

				List<Composant> composantsADetruire = new ArrayList<>();
				u.getComposantsSortie().forEach(c -> {
					if (u.composantArriveADestination(c)) {
						composantsADetruire.add(c);

					} else {
						c.getPosition().translate(c.getVitesse().x, c.getVitesse().y);
						g.drawImage(c.getIcone(), c.getPosition().x, c.getPosition().y, null);
					}
				});
				u.getComposantsSortie().removeAll(composantsADetruire);
				chaine.noeuds
						.stream()
						.filter(i -> i.getId() == u.getDestinationId())
						.findFirst().get()
						.getComposantsEntree().addAll(composantsADetruire);
			});

			// Dessiner les usines et les entrepots
			chaine.noeuds.forEach(n -> {
				g.drawImage(n.getIconeToDisplay(compteurTour),
						n.getCoordinates().x,
						n.getCoordinates().y, null);
			});


		}
	}

}