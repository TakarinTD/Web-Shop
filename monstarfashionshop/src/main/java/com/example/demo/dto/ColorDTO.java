package com.example.demo.dto;

public class ColorDTO {
    private Long id;
    private String colorName;
    private String image;

    public ColorDTO() {
    }

    public ColorDTO(Long id, String colorName, String image) {
        this.id = id;
        this.colorName = colorName;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
