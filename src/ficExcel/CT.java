package ficExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import xml.parse.SimpleErrorHandler;

/**
 * . Objet CT consitué du contenu du fichier fichierCT lu conformément au
 * fichier xml xmlDefCT
 * 
 * @author eric
 *
 */
public class CT {
	/**
	 * . Fichier excel
	 */
	HSSFWorkbook wb;

	/**
	 * . Table contenant tous les onglets
	 */
	ArrayList<Sheet> table = new ArrayList<Sheet>();

	/**
	 * . Constructeur de la classe Remplit table avec des objet de type Sheet
	 * conformément au fichier xml
	 * 
	 * @param fichierCT
	 * @param xmlDefCT
	 */
	public CT(String fichierCT, String xmlDefCT) {
		//if (table == null) {
			ouvertureCT(fichierCT);
			ParcoursXml(xmlDefCT);
		/*} else {
			System.out.println("Object table déjà crée.");
		/*/
	}
	
	public int ligneEleUniqueDansColonne(Sheet sheet, String colonne, String element){
		int ret = 0;
		ret = sheet.ligneContenantEleDansColonne(colonne, element);
		return ret;
	}

	/**
	 * . Création de l'objet wb
	 * 
	 * @param nomFichier
	 */
	private void ouvertureCT(String nomFichier) {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(nomFichier));
			wb = new HSSFWorkbook(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * . Renvoi l'objet sheet dont le nom correspond au paramètre name
	 * 
	 * @param name
	 * @return
	 */
	public Sheet chercherOnglet(String name) {
		int i = 0;
		while (i < table.size()) {
			if (table.get(i).toString().equals(name)) {
				// System.out.println("recherche fructueuse !");
				return table.get(i);
			}
			i++;
		}
		; // message erreur : aucun onglet de ce nom n'existe
			// System.out.println("recherche non fructueuse !");
		return null;
	}

	/**
	 * . Lance la lecture du fichier xml dans le but de remplir l'objet table
	 * 
	 * @param nFichier
	 */
	private void ParcoursXml(String nFichier) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			ErrorHandler errHandler = new SimpleErrorHandler();
			builder.setErrorHandler(errHandler);
			File fileXML = new File(nFichier);
			Document xml;
			try {
				xml = builder.parse(fileXML);
				Element root = xml.getDocumentElement();
				System.out.println(root.getNodeName());
				lireXml(root);
			} catch (SAXParseException e) {
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * . Remplit l'object table avec des objets sheet suivant le fichier xml
	 * 
	 * @param n
	 */
	private void lireXml(Node n) {
		// variable qui vont contenir les paramètres servant à créer les objets
		// Sheet
		String nameSheet; // nom de l'onglet
		// ArrayList<DefCol> listColumn = new ArrayList<DefCol>(); // liste du
		// nom des colonnes à lire dans
		// l'onglet
		String nameColumn;
		Types type;

		// Verifie que le noeud est une instance d'Element (et pas un texte,
		// espace...)
		if (n instanceof Element) {

			// noeud "table" :
			// Element element = (Element) n;
			int nbChild = n.getChildNodes().getLength();
			NodeList listCol = n.getChildNodes();

			// parcours des noeuds "sheet" qui suivent "table"
			for (int i = 0; i < nbChild; i++) {
				nameSheet = null;
				Node n2 = listCol.item(i);
				if (n2 instanceof Element) {
					// récupération de l'attribut de "sheet"
					if (n2.getAttributes() != null && n2.getAttributes().getLength() == 1) {
						// System.out.println("présence attribut");
						NamedNodeMap att = n2.getAttributes();
						nameSheet = att.item(0).getNodeValue();
						// test
						System.out.println("nameSheet : " + nameSheet);
						// fin test
						// System.out.println("\t Attribut : " + nomAtt);
					} else {
						; // message erreur de lecture du fichier xml (autre
							// chose qu'1 et 1 seul attribut
					}

					// recuperation des enfants de "sheet" :
					NodeList listCol2 = n2.getChildNodes();
					int nbChild2 = n2.getChildNodes().getLength();
					// System.out.println(nbChild2);

					ArrayList<DefCol> listColumn = new ArrayList<DefCol>();

					// recherche des noeuds "column"
					for (int j = 0; j < nbChild2; j++) {
						Node n3 = listCol2.item(j);
						// si on se trouve sur un noeud "column"
						if ((n3 instanceof Element) && n3.getNodeName().equals("column")) {
							NodeList listColDef = n3.getChildNodes();
							int nbChild3 = n3.getChildNodes().getLength();
							int k = 0;
							while (k < nbChild3 && !((listColDef.item(k) instanceof Element)
									&& listColDef.item(k).getNodeName().equals("name"))) {
								// System.out.println("-" +
								// listColDef.item(k).getNodeName() + "-");
								k++;
							}
							System.out.println(k + "  " + nbChild2);
							Node n4 = listColDef.item(k);
							if (n4.getNodeName().equals("name")) {
								System.out.println("name !!!");
								nameColumn = n4.getChildNodes().item(0).getNodeValue();
							} else {
								nameColumn = null;
								; // erreur de lecture du fichier xml
							}
							while (k < nbChild3 && !((listColDef.item(k) instanceof Element)
									&& listColDef.item(k).getNodeName().equals("type"))) {
								k++;
							}
							n4 = listColDef.item(k);
							if (n4.getNodeName().equals("type")) {
								System.out.println("type !!!  :  " + n4.getChildNodes().item(0).getNodeValue());
								switch (n4.getChildNodes().item(0).getNodeValue()) {
								case "ENTIER":
									type = Types.ENTIER;
									break;
								case "STR":
									type = Types.CHAINE;
									break;
								case "STRLISTE":
									type = Types.LISTE_CHAINES;
									break;
								default:
									type = null;
									; // code erreur : type inconnu provenant du
										// fichier XML
								}
							} else {
								type = null;
								; // erreur de lecture du fichier xml
							}
							listColumn.add(new DefCol(nameColumn, type));
						}
					}
					table.add(new Sheet(wb, nameSheet, listColumn));
				}
			}

		} else {
			; // message erreur de lecture du fichier xml
		}
	}
}
