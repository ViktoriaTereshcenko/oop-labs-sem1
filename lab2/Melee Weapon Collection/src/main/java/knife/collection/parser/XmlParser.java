package knife.collection.parser;

import knife.Knife;

import java.io.IOException;
import java.util.List;

public abstract class XmlParser {
    abstract public List<Knife> parseFromXml(String xmlFilePath, String xsdFilePath) throws IOException;
}
