package knife.collection.parser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import knife.Knife;
import knife.collection.parser.validator.XsdValidator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DomXmlParser extends XmlParser {
    @Override
    public List<Knife> parseFromXml(String xmlFilePath, String xsdFilePath) throws IOException {
        XsdValidator validation = new XsdValidator(xmlFilePath, xsdFilePath);
        if (!validation.validate()) {
            throw new RuntimeException("XSD-validation failed.");
        }
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(xmlFilePath));

            SaxHandler handler = new SaxHandler();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new DOMSource(document), new SAXResult(handler));
            return handler.getKnifeList();
        } catch (SAXException | TransformerException | ParserConfigurationException e) {
            throw new RuntimeException("XML parsing failed.");
        }
    }
}
