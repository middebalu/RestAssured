package main.java.com.petstore.api.pojo;

import java.util.List;

public class AddPet {
    int id;
    Category category;
    String name;
    List<String> photoUrl;
    List<Tags> tags;
    String status;

    @Override
    public String toString() {
        return "AddPet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrl=" + photoUrl +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(List<String> photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
