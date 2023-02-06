package simulation;

import java.awt.*;

import javax.swing.*;

import static simulation.Simulation.chaine;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// Variables temporaires de la demonstration:
	private Point position = new Point(0,0);
	private Point vitesse = new Point(1,1);
	private int taille = 10;

	public PanneauPrincipal() {
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// On ajoute à la position le delta x et y de la vitesse
//		position.translate(vitesse.x, vitesse.y);
//		g.fillRect(position.x, position.y, taille, taille);
		if(chaine.noeuds.size() > 6) {
			chaine.chemins.forEach((k, l)-> {
				g.drawLine(k.x, k.y, l.x, l.y);
			});
			chaine.noeuds.forEach(n -> {
				g.drawImage(n.getIconeVide(),
						n.getCoordinates().x-15,
						n.getCoordinates().y-15,
						null);
			});
		}
	}

}