package knife.collection;

import knife.Knife;
import knife.collection.parser.XmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Collection {
    private List<Knife> knives;

    public Collection() {
        knives = new ArrayList<>();
    }

    public void clear() {
        knives.clear();
    }

    public void addKnife(Knife knife) {
        knives.add(knife);
    }

    public Knife getKnife(int index) {
        return knives.get(index);
    }

    public void removeKnife(int index) {
        knives.remove(index);
    }

    public void fromXmlFile(String xmlFilePath, String xsdFilePath, XmlParser parser) throws IOException {
        knives = parser.parseFromXml(xmlFilePath, xsdFilePath);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Collection that)) {
            return false;
        }
        return Objects.equals(knives, that.knives);
    }

    @Override
    public int hashCode() {
        return Objects.hash(knives);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<knives.size(); i++) {
            Knife knife = knives.get(i);
            sb.append(i).append(" # ").append(knife.toString()).append("\n");
        }
        return sb.toString();
    }
}
