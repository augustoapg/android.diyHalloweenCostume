package sheridan.araujope.diyhalloweencostume.beans;

import android.graphics.Bitmap;

import java.util.List;
import java.util.Objects;

public class Costume {
    private String name;
    private Bitmap image;
    private List<String> items;
    private String notes;

    public Costume() {
    }

    public Costume(String name, Bitmap image, List<String> items, String notes) {
        this.name = name;
        this.image = image;
        this.items = items;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Costume{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", items=" + items +
                ", notes='" + notes + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Costume costume = (Costume) o;
        return Objects.equals(name, costume.name) &&
                Objects.equals(image, costume.image) &&
                Objects.equals(items, costume.items) &&
                Objects.equals(notes, costume.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, image, items, notes);
    }
}
