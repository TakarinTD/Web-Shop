package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "product_size")
@JsonIgnoreProperties (value = "productDetails")
public class ProductSize {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "size_name")
    private String sizeName;

    @Column (name = "created_at")
    private Date createdAt;

    @Column (name = "updated_at")
    private Date updatedAt;

    @OneToMany (mappedBy = "productSize", fetch = FetchType.LAZY)
    private Set<ProductDetail> productDetails;

    public ProductSize () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getSizeName () {
        return sizeName;
    }

    public void setSizeName (String sizeName) {
        this.sizeName = sizeName;
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
