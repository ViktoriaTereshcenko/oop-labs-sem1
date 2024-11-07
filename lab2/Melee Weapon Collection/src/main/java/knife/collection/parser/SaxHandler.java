package knife.collection.parser;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import knife.Knife;
import knife.KnifeTypes;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {
    private final List<Knife> knifeList;
    private Knife knife;
    private String element;
    private StringBuilder elementData;

    private Knife.Blade blade;
    private Knife.Handle handle;

    public SaxHandler() {
        this.knifeList = new ArrayList<>();
        this.knife = null;
        this.element = null;
        this.elementData = null;
    }

    public List<Knife> getKnifeList() {
        return knifeList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        element = qName;
        elementData = new StringBuilder();

        if (element.equalsIgnoreCase("knife")) {
            knife = new Knife();
        } else if (element.equalsIgnoreCase("blade")) {
            blade = new Knife.Blade(10, 10, "steel");
        } else if (element.equalsIgnoreCase("handle")) {
            handle = new Knife.Handle("wood", "oak");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String strData = elementData.toString();

        switch (element.toLowerCase()) {
            case "type" -> knife.setType(strData);
            case "handy" -> knife.setHandy(strData);
            case "origin" -> knife.setOrigin(strData);
            case "length" -> blade = new Knife.Blade(Integer.parseInt(strData), blade.getWidth(), blade.getMaterial());
            case "width" -> blade = new Knife.Blade(blade.getLength(), Integer.parseInt(strData), blade.getMaterial());
            case "material" -> {
                if ("blade".equalsIgnoreCase(qName)) {
                    blade = new Knife.Blade(blade.getLength(), blade.getWidth(), strData);
                } else if ("handle".equalsIgnoreCase(qName)) {
                    handle = new Knife.Handle(strData, handle.getWoodType());
                }
            }
            case "woodtype" -> handle = new Knife.Handle(handle.getMaterial(), strData);
            case "dole" -> knife.setHasDole(Boolean.parseBoolean(strData));
            case "collectible" -> knife.setCollectible(Boolean.parseBoolean(strData));
        }

        if (qName.equalsIgnoreCase("blade")) {
            knife.setBlade(blade);
        } else if (qName.equalsIgnoreCase("handle")) {
            knife.setHandle(handle);
        } else if (qName.equalsIgnoreCase("knife")) {
            knifeList.add(knife);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String str = new String(ch, start, length).trim();
        if (!str.isEmpty()) {
            elementData.append(str);
        }
    }
}
