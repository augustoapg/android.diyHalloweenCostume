/**
 * Project: DIY Halloween Costume
 * Author: Augusto A P Goncalez
 * Date: Nov. 18, 2019
 */

package sheridan.araujope.diyhalloweencostume.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Costume implements Serializable {
    private String name;
    private String image;
    private List<String> items;
    private String notes;

    public Costume() {
    }

    public Costume(String name, String image, List<String> items, String notes) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
