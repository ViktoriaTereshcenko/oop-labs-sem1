package knife.collection.parser.validator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XsdValidator {
    private final File xmlFile;
    private final File xsdFile;

    public XsdValidator(String xmlFilePath, String xsdFilePath) {
        this.xmlFile = new File(xmlFilePath);
        this.xsdFile = new File(xsdFilePath);
    }

    public boolean validate() {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
