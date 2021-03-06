package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "product_color")
@JsonIgnoreProperties(value = "productDetails")
public class ProductColor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "color_name")
    private String colorName;

    @Column (name = "image")
    private String image;

    @Column (name = "created_at")
    private Date createdAt;

    @Column (name = "updated_at")
    private Date updatedAt;

    @OneToMany (mappedBy = "productColor", fetch = FetchType.LAZY)
    private Set<ProductDetail> productDetails;

    public ProductColor () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getColorName () {
        return colorName;
    }

    public void setColorName (String colorName) {
        this.colorName = colorName;
    }

    public String getImage () {
        return image;
    }

    public void setImage (String image) {
        this.image = image;
    }

    public Date getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt () {
        return updatedAt;
    }

    public void setUpdatedAt (Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<ProductDetail> getProductDetails () {
        return productDetails;
    }

    public void setProductDetails (Set<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public void addProductDetail (ProductDetail productDetail) {
        if (productDetails == null) {
            productDetails = new HashSet<>();
        }
        productDetails.add(productDetail);
    }
}
