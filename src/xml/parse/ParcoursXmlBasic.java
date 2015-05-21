package xml.parse;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParcoursXmlBasic {

	ParcoursXmlBasic(String nFichier) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setIgnoringElementContentWhitespace(true);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File fileXML = new File(nFichier);
			Document xml;

			xml = builder.parse(fileXML);
			Element root = xml.getDocumentElement();

			NodeList nodes = root.getChildNodes();
			int nbNode = nodes.getLength();
			for (int i = 0; i < nbNode; i++) {
				Node n = nodes.item(i);
				System.out.println("* Enfant N°" + (i + 1) + " : "
						+ n.getNodeName() + " - " + n.getNodeValue());
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