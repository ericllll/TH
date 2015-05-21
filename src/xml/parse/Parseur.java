package xml.parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Parseur {

	Parseur (String nFichier) {
		// Nous r�cup�rons une instance de factory qui se chargera de nous
		// fournir
		// un parseur
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			// Cr�ation de notre parseur via la factory
			DocumentBuilder builder = factory.newDocumentBuilder();
			File fileXML = new File(nFichier);

			// parsing de notre fichier via un objet File et r�cup�ration d'un
			// objet Document
			// Ce dernier repr�sente la hi�rarchie d'objet cr��e pendant le
			// parsing
			Document xml = builder.parse(fileXML);

			// Via notre objet Document, nous pouvons r�cup�rer un objet Element
			// Ce dernier repr�sente un �l�ment XML mais, avec la m�thode
			// ci-dessous,
			// cet �l�ment sera la racine du document
			Element root = xml.getDocumentElement();
			System.out.println(root.getNodeName());
/*
			// Parsing d'un XML via une URL
			String uri = "http://www.w3schools.com/xml/note.xml";
			xml = builder.parse(uri);
			root = xml.getDocumentElement();
			System.out.println(root.getNodeName());

			// Parsing d'un XML via un flux
			InputStream is = new FileInputStream(fileXML);
			xml = builder.parse(is);
			root = xml.getDocumentElement();
			System.out.println(root.getNodeName());
*/
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}