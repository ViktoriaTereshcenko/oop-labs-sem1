package knife.collection;

import org.junit.Before;
import org.junit.Test;
import knife.Knife;
import knife.collection.parser.DomXmlParser;
import knife.collection.parser.SaxXmlParser;
import knife.collection.parser.StaxXmlParser;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CollectionTest {
    private Collection collection;
    private static final String xmlFilePath = "src/main/files/xml_file.xml";
    private static final String xsdFilePath = "src/main/files/xsd_file.xsd";

    @Before
    public void setUp() {
        collection = new Collection();
        Knife knife;

        knife = new Knife();
        knife.setType("knife");
        knife.setHandy("one-handed");
        knife.setOrigin("Japan");
        knife.setBlade(new Knife.Blade(30, 10, "steel"));
        knife.setHandle(new Knife.Handle("wood", "oak"));
        knife.setHasDole(true);
        knife.setCollectible(true);
        collection.addKnife(knife);

        knife = new Knife();
        knife.setType("dagger");
        knife.setHandy("two-handed");
        knife.setOrigin("India");
        knife.setBlade(new Knife.Blade(40, 11, "iron"));
        knife.setHandle(new Knife.Handle("metal", null));
        knife.setHasDole(false);
        knife.setCollectible(false);
        collection.addKnife(knife);

        knife = new Knife();
        knife.setType("saber");
        knife.setHandy("one-handed");
        knife.setOrigin("France");
        knife.setBlade(new Knife.Blade(35, 12, "copper"));
        knife.setHandle(new Knife.Handle("plastic", null));
        knife.setHasDole(true);
        knife.setCollectible(true);
        collection.addKnife(knife);
    }

    @Test
    public void fromXmlFile() throws IOException {
        Collection testCollection = new Collection();

        testCollection.fromXmlFile(xmlFilePath, xsdFilePath, new SaxXmlParser());
        assertEquals(collection, testCollection);

        testCollection.fromXmlFile(xmlFilePath, xsdFilePath, new DomXmlParser());
        assertEquals(collection, testCollection);

        testCollection.fromXmlFile(xmlFilePath, xsdFilePath, new StaxXmlParser());
        assertEquals(collection, testCollection);
    }
}
