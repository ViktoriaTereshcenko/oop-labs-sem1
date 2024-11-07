package knife.collection.parser;

import knife.Knife;
import knife.collection.parser.validator.XsdValidator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stax.StAXSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class StaxXmlParser extends XmlParser {
    @Override
    public List<Knife> parseFromXml(String xmlFilePath, String xsdFilePath) throws IOException {
        XsdValidator validation = new XsdValidator(xmlFilePath, xsdFilePath);
        if (!validation.validate()) {
            throw new RuntimeException("XSD-validation failed.");
        }
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFilePath));

            SaxHandler handler = new SaxHandler();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.transform(new StAXSource(reader), new SAXResult(handler));
            return handler.getKnifeList();
        } catch (XMLStreamException | TransformerException e) {
            throw new RuntimeException("XML parsing failed.");
        }
    }
}
