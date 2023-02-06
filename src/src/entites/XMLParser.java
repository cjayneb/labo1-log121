package entites;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static simulation.Simulation.chaine;

public class XMLParser {

    private static final int NOMBRE_TYPE_USINE = 5;
    private static final int NOMBRE_USINE = 7;
    private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    private DocumentBuilder db = null;
    private Document doc = null;

    public void parseXML(File selectedFile) {
        // Récupérer le parser
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }

        // Parser le fichier XML
        Document doc = null;
        try {
            doc = db.parse(selectedFile);
        } catch (SAXException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        // Extraire les usines et les chemins
        parseUsines(doc.getElementsByTagName("usine"));
        parseChemins(doc.getElementsByTagName("chemin"));
    }

    private void parseUsines(NodeList usines) {
        for (int i = 0; i < NOMBRE_TYPE_USINE; i++) {
            String type = usines.item(i).getAttributes().item(0).getNodeValue();

            NodeList icones = usines.item(i).getChildNodes().item(1).getChildNodes();
            String iconeVide = icones.item(1).getAttributes().item(0).getNodeValue();
            String iconeUnTiers = icones.item(3).getAttributes().item(0).getNodeValue();
            String iconeDeuxTiers = icones.item(5).getAttributes().item(0).getNodeValue();
            String iconePlein = icones.item(7).getAttributes().item(0).getNodeValue();
            String sortie = "";
            Map<String, Integer> entrees = new HashMap<>();
            int intervalProduction = 0;

            // Extraire les valeurs d'entrées, sortie et l'interval de production
            for (int k = 3; k < usines.item(i).getChildNodes().getLength(); k++) {
                if (usines.item(i).getChildNodes().item(k).getNodeName().equals("sortie")) {
                    sortie = usines.item(i).getChildNodes().item(k).getAttributes().getNamedItem("type").getNodeValue();
                } else if (usines.item(i).getChildNodes().item(k).getNodeName().equals("entree")) {
                    String entreeType = usines.item(i).getChildNodes().item(k).getAttributes().getNamedItem("type").getNodeValue();
                    Integer qte = usines.item(i).getChildNodes().item(k).getAttributes().getNamedItem("quantite") != null ?
                            Integer.parseInt(usines.item(i).getChildNodes().item(k).getAttributes().getNamedItem("quantite").getNodeValue()) :
                            Integer.parseInt(usines.item(i).getChildNodes().item(k).getAttributes().getNamedItem("capacite").getNodeValue());
                    entrees.put(entreeType, qte);
                } else if (usines.item(i).getChildNodes().item(k).getNodeName().equals("interval-production")) {
                    intervalProduction = Integer.parseInt(usines.item(i).getChildNodes().item(k).getTextContent());
                }
            }

            // Extraire les icones et créer les usines et l'entrepôt
            for (int j = 5; j < NOMBRE_TYPE_USINE+NOMBRE_USINE; j++) {
                String sousType = usines.item(j).getAttributes().getNamedItem("type").getNodeValue();
                if (sousType.equals(type)) {
                    int id = Integer.parseInt(usines.item(j).getAttributes().getNamedItem("id").getNodeValue());
                    int x = Integer.parseInt(usines.item(j).getAttributes().getNamedItem("x").getNodeValue());
                    int y = Integer.parseInt(usines.item(j).getAttributes().getNamedItem("y").getNodeValue());

                    if (type.startsWith("u")) {
                        Usine usine = new Usine(type, id, new Point(x, y), sortie, entrees, intervalProduction,
                                iconeVide, iconeUnTiers, iconeDeuxTiers,
                                iconePlein);
                        chaine.entrepot.addObserver(usine);
                        chaine.noeuds.add(usine);
                    } else {
                        chaine.entrepot.setId(id);
                        chaine.entrepot.setCoordinates(new Point(x, y));
                        chaine.entrepot.setSortieType(sortie);
                        chaine.entrepot.setEntreeTypes(entrees);
                        chaine.entrepot.setIntervalProduction(intervalProduction);
                        chaine.entrepot.setIconeVide(iconeVide);
                        chaine.entrepot.setIconeUnTiers(iconeUnTiers);
                        chaine.entrepot.setIconeDeuxTiers(iconeDeuxTiers);
                        chaine.entrepot.setIconePlein(iconePlein);
                        chaine.noeuds.add(chaine.entrepot);
                    }
                }
            }
        }
    }

    private void parseChemins(NodeList chemins) {
        for (int i = 0; i < chemins.getLength(); i++) {
            int startId = Integer.parseInt(chemins.item(i).getAttributes().getNamedItem("de").getNodeValue());
            int endId = Integer.parseInt(chemins.item(i).getAttributes().getNamedItem("vers").getNodeValue());
            AtomicReference<Point> startCoordinates = new AtomicReference<>(new Point());
            AtomicReference<Point> endCoordinates = new AtomicReference<>(new Point());

            chaine.noeuds.forEach(n -> {
                if(n.getId() == startId) {
                    startCoordinates.set(n.getCoordinates());
                } else if (n.getId() == endId) {
                    endCoordinates.set(n.getCoordinates());
                }
            });

            chaine.noeuds.forEach(n -> {
                if (n.getId() == startId) {
                    n.setDestination(endCoordinates.get());
                }
            });

            chaine.chemins.put(startCoordinates.get(), endCoordinates.get());
        }
    }
}
