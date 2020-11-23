package com.example.demo.dto;

public class SizeDTO {
    private Long id;
    private String sizeName;
    private int quantities;

    public SizeDTO() {
    }

    public SizeDTO(Long id, String sizeName, int quantities) {
        this.id = id;
        this.sizeName = sizeName;
        this.quantities = quantities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }
}
