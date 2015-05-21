package xml.parse;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ParseurVerifXsd {

   ParseurVerifXsd(String nFichierXml, String nFichierXsd) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

      try {
         
         //Ces trois lignes servent � informer que la validation se fait via un fichier XSD
         SchemaFactory sfactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
         //On cr�� notre sch�ma XSD
         //Ici, c'est un sch�ma interne, pour un sch�ma externe il faut mettre l'URI
         Schema schema = sfactory.newSchema(new File(nFichierXsd));
         //On l'affecte � notre factory afin que le document prenne en compte le fichier XSD
         factory.setSchema(schema);    
         
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         //cr�ation de notre objet d'erreurs
         ErrorHandler errHandler = new SimpleErrorHandler();
         //Affectation de notre objet au document pour interception des erreurs �ventuelles
         builder.setErrorHandler(errHandler);
         File fileXML = new File(nFichierXml);
         
         //On rajoute un bloc de capture
         //pour intercepter les erreurs au cas o� il y en ait
         try {
            Document xml = builder.parse(fileXML);
            Element root = xml.getDocumentElement();
            System.out.println(root.getNodeName());
         } catch (SAXParseException e) {} 
           
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }  
   }
 }