package xml.parse;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ParcoursXml {

	ParcoursXml(String nFichier) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			//factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			ErrorHandler errHandler = new SimpleErrorHandler();
			builder.setErrorHandler(errHandler);
			File fileXML = new File(nFichier);
			Document xml;
			try {
				xml = builder.parse(fileXML);
				Element root = xml.getDocumentElement();
				//System.out.println(description(root, ""));
				pppppp(root);
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
	
	public static void pppppp(Node n){
		String essai ; // pour debug
		
		// Verifie que le noeud est une instance d'Element (et pas un texte, espace...)
		if(n instanceof Element) {
			
			// noeud "table" :
			Element element = (Element) n;
			int nbChild = n.getChildNodes().getLength();
			//System.out.println(nbChild);
			NodeList listCol = n.getChildNodes();
			
			// parcours des noeuds "sheet" qui suivent "table"
			for (int i = 0; i < nbChild; i++) {
				Node n2 = listCol.item(i);
				if (n2 instanceof Element){
					essai = n2.getNodeName();
					System.out.println(essai); // ***************************AFFICHE SHEET
					// r�cup�ration de l'attribut de "sheet"
					if (n2.getAttributes() != null && n2.getAttributes().getLength() == 1) {
						//System.out.println("pr�sence attribut");
						NamedNodeMap att = n2.getAttributes();
						String nomAtt = att.item(0).getNodeValue();
						System.out.println("\t Attribut : " + nomAtt);
					} else {
						; // message erreur de lecture du fichier xml (autre chose qu'1 et 1 seul attribut
					}
					
					// recuperation des enfants de "sheet" :
					NodeList listCol2 = n2.getChildNodes();
					int nbChild2 = n2.getChildNodes().getLength();
					//System.out.println(nbChild2);
					
					// recherche des noeuds "column"
					for (int j=0; j<nbChild2; j++){
						Node n3 = listCol2.item(j);
						// si on se trouve sur un noeud "column"
						if((n3 instanceof Element) && n3.getNodeName().equals("column")){
							NodeList listColDef = n3.getChildNodes();
							int nbChild3 = n3.getChildNodes().getLength();
							int k=0;
							while(k<nbChild3 && !((listColDef.item(k) instanceof Element) && listColDef.item(k).getNodeName().equals("name"))) {
								//System.out.println("-" + listColDef.item(k).getNodeName() + "-");
								k++;
							}
							System.out.println(k + "  " + nbChild2);
							Node n4 = listColDef.item(k);
							if(n4.getNodeName().equals("name")) {
								System.out.println("name !!!");
							} else {
								; // erreur de lecture du fichier xml
							}
							while(k<nbChild3 && !((listColDef.item(k) instanceof Element) && listColDef.item(k).getNodeName().equals("type"))) {
								k++;
							}
							//n4 = listColDef.item(k);
							if(listColDef.item(k).getNodeName().equals("type")) {
								System.out.println("type !!!");
							} else {
								; // erreur de lecture du fichier xml
							}
						}
					}
				}
			}

			
		} else
			; // message erreur de lecture du fichier xml
	}

	/**
	 * M�thode qui va parser le contenu d'un n�ud
	 * 
	 * @param n
	 * @param tab
	 * @return
	 */
	public static String description(Node n, String tab) {

		String str = new String();

		// Verifie que le noeud est une instance d'Element (et pas un texte, espace...)
		if (n instanceof Element) {
			Element element = (Element) n;

			// Nous pouvons r�cup�rer le nom du n�ud actuellement parcouru
			// gr�ce � cette m�thode, nous ouvrons donc notre balise
			str += "<" + n.getNodeName();

			// nous contr�lons la liste des attributs pr�sents
			if (n.getAttributes() != null && n.getAttributes().getLength() > 0) {

				// nous pouvons r�cup�rer la liste des attributs d'un �l�ment
				NamedNodeMap att = n.getAttributes();
				int nbAtt = att.getLength();

				// nous parcourons tous les n�uds pour les afficher
				for (int j = 0; j < nbAtt; j++) {
					Node noeud = att.item(j);

					// On r�cup�re le nom de l'attribut et sa valeur gr�ce � ces
					// deux m�thodes
					str += " " + noeud.getNodeName() + "=\""
							+ noeud.getNodeValue() + "\" ";
				}
			}

			// nous refermons notre balise car nous avons trait� les diff�rents
			// attributs
			str += ">";

			// La m�thode getChildNodes retournant le contenu du n�ud + les
			// n�uds enfants
			// Nous r�cup�rons le contenu texte uniquement lorsqu'il n'y a que
			// du texte, donc un seul enfant
			if (n.getChildNodes().getLength() == 1) {
				str += n.getTextContent();
			}
			// Nous allons maintenant traiter les n�uds enfants du n�ud en cours
			// de traitement
			int nbChild = n.getChildNodes().getLength();

			// Nous r�cup�rons la liste des n�uds enfants
			NodeList list = n.getChildNodes();
			String tab2 = tab + "\t";

			// nous parcourons la liste des n�uds
			for (int i = 0; i < nbChild; i++) {
				Node n2 = list.item(i);

				// si le n�ud enfant est un Element, nous le traitons
				if (n2 instanceof Element) {
					// appel r�cursif � la m�thode pour le traitement du n�ud et
					// de ses enfants
					str += "\n " + tab2 + description(n2, tab2);
				}
			}

			// Nous fermons maintenant la balise
			if (n.getChildNodes().getLength() < 2) {
				str += "</" + n.getNodeName() + ">";
			} else {
				str += "\n" + tab + "</" + n.getNodeName() + ">";
			}
		}
		return str;
	}
}