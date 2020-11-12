package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductDetail;

import java.util.List;
@Service
@Transactional
public interface ProductService {

    ProductDTO convertProductToProductDTO(Product product);
    List<ProductDTO> getProductsByCategoryId(long categoryId);
    List<ProductDTO> getProductsTopSaleMan();
    List<ProductDTO> getProductsTopSaleWomen();

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

    public List<Product> findProducts(String categoryName){
        return productRepository.findProductsByCategoryCategoryName(categoryName);
    }
}
