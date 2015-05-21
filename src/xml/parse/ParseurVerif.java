package xml.parse;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ParseurVerif {

	ParseurVerif(String nFichier) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// M�thode qui permet d'activer la v�rification du fichier
			factory.setValidating(true);

			DocumentBuilder builder = factory.newDocumentBuilder();

			// cr�ation de notre objet d'erreurs
			ErrorHandler errHandler = new SimpleErrorHandler();
			// Affectation de notre objet au document pour interception des
			// erreurs �ventuelles
			builder.setErrorHandler(errHandler);
			File fileXML = new File(nFichier);

			// On rajoute un bloc de capture
			// pour intercepter les erreurs au cas o� il y en a
			try {
				Document xml = builder.parse(fileXML);
				Element root = xml.getDocumentElement();
				System.out.println(root.getNodeName());
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
}