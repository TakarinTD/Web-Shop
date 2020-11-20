package com.example.demo.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "product")
@JsonIgnoreProperties (value = {"category", "productDetails"})
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "product_name")
    private String productName;

    @Column (name = "img_url")
    private String image;

    @Column (name = "purchase_price")
    private float purchasePrice;

    @Column (name = "sale_price")
    private float salePrice;

    @Column (name = "description")
    private String description;

    @Temporal (TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column (name = "created_at")
    private Date createdAt;

    @Column (name = "updated_at")
    @Temporal (TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn (name = "category_id")
    private Category category;

    @OneToMany (mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductDetail> productDetails;

    @ManyToMany (mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Promotion> promotions;

    public Product () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getProductName () {
        return productName;
    }

    public void setProductName (String productName) {
        this.productName = productName;
    }
  
    public float getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice (float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public float getSalePrice () {
        return salePrice;
    }

    public void setSalePrice (float salePrice) {
        this.salePrice = salePrice;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
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

    public Category getCategory () {
        return category;
    }

    public void setCategory (Category category) {
        this.category = category;
    }

    public Set<ProductDetail> getProductDetails () {
        return productDetails;
    }

    public void setProductDetails (Set<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public Set<Promotion> getPromotions () {
        return promotions;
    }

    public void setPromotions (Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void addProductDetail(ProductDetail productDetail) {
        if(productDetails == null) {
            productDetails = new HashSet<>();
        }
        productDetails.add(productDetail);
    }
}
