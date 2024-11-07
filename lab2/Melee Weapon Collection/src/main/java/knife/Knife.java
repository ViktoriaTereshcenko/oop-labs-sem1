package knife;

import java.util.Objects;

public class Knife {
    private String type;
    private String handy;
    private String origin;

    private Blade blade;
    private Handle handle;
    private boolean hasDole;
    private boolean isCollectible;

    public Knife() {
        setType("");
        setHandy("one-handed");
        setOrigin("");
        setBlade(new Blade(10, 10, "steel"));
        setHandle(new Handle("wood", "oak"));
        setHasDole(false);
        setCollectible(false);
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setBlade(Blade blade) {
        this.blade = blade;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }

    public void setHasDole(boolean hasDole) {
        this.hasDole = hasDole;
    }

    public void setCollectible(boolean isCollectible) {
        this.isCollectible = isCollectible;
    }

    public String getType() {
        return type;
    }

    public String getHandy() {
        return handy;
    }

    public String getOrigin() {
        return origin;
    }

    public Blade getBlade() {
        return blade;
    }

    public Handle getHandle() {
        return handle;
    }

    public boolean hasDole() {
        return hasDole;
    }

    public boolean isCollectible() {
        return isCollectible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife knife)) {
            return false;
        }
        return hasDole == knife.hasDole && isCollectible == knife.isCollectible &&
               Objects.equals(type, knife.type) && Objects.equals(handy, knife.handy) &&
               Objects.equals(origin, knife.origin) && Objects.equals(blade, knife.blade) &&
               Objects.equals(handle, knife.handle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, handy, origin, blade, handle, hasDole, isCollectible);
    }

    @Override
    public String toString() {
        return "Knife: type=\"" + type + "\", handy=\"" + handy + "\", origin=\"" + origin + "\", blade=" + blade +
               ", handle=" + handle + ", hasDole=" + hasDole + ", collectible=" + isCollectible + ";";
    }

    public static class Blade {
        private int length;
        private int width;
        private String material;

        public Blade(int length, int width, String material) {
            this.length = length;
            this.width = width;
            this.material = material;
        }

        public int getLength() {
            return length;
        }

        public int getWidth() {
            return width;
        }

        public String getMaterial() {
            return material;
        }

        @Override
        public String toString() {
            return "Blade[length=" + length + "cm, width=" + width + "mm, material=\"" + material + "\"]";
        }
    }

    public static class Handle {
        private String material;
        private String woodType;

        public Handle(String material, String woodType) {
            this.material = material;
            this.woodType = "wood".equalsIgnoreCase(material) ? woodType : null;
        }

        public String getMaterial() {
            return material;
        }

        public String getWoodType() {
            return woodType;
        }

        @Override
        public String toString() {
            return "Handle[material=\"" + material + "\"" + (woodType != null ? ", woodType=\"" + woodType + "\"" : "") + "]";
        }
    }
}
