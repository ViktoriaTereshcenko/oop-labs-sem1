package knife.collection.parser;

import org.xml.sax.SAXException;
import knife.Knife;
import knife.collection.parser.validator.XsdValidator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxXmlParser extends XmlParser {
    @Override
    public List<Knife> parseFromXml(String xmlFilePath, String xsdFilePath) throws IOException {
        XsdValidator validation = new XsdValidator(xmlFilePath, xsdFilePath);
        if (!validation.validate()) {
            throw new RuntimeException("XSD-validation failed.");
        }
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            SaxHandler handler = new SaxHandler();
            saxParser.parse(xmlFilePath, handler);
            return handler.getKnifeList();
        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException("XML parsing failed.");
        }
    }
}
