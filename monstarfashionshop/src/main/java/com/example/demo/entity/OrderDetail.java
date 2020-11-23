package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantities_product")
    private int quantitiesProduct;

    @Column(name = "total_product_pay")
    private float totalProductPay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantitiesProduct() {
        return quantitiesProduct;
    }

    public void setQuantitiesProduct(int quantitiesProduct) {
        this.quantitiesProduct = quantitiesProduct;
    }

    public float getTotalProductPay() {
        return totalProductPay;
    }

    public void setTotalProductPay(float totalProductPay) {
        this.totalProductPay = totalProductPay;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }
}
