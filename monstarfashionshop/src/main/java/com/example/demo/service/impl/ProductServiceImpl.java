package com.example.demo.service.impl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductColor;
import com.example.demo.entity.ProductSize;
import com.example.demo.repository.ProductColorRepository;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSizeRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductColorRepository productColorRepository;

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProductsByCategoryId(long categoryId) {
        List<ProductDTO> listProductDTO = new ArrayList<>();
        List<Product> listProducts = productRepository.findALlByCategoryId(categoryId);
        listProducts.forEach(product -> {
            listProductDTO.add(convertProductToProductDTO(product));
        });
        return listProductDTO;
    }

    @Override
    public Product getProductByIdProduct(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public ProductDTO convertProductToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setImage(product.getImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPurchasePrice(product.getPurchasePrice());
        productDTO.setSalePrice(product.getSalePrice());

        return productDTO;
    }

    @Override
    public Page<Product> pageProductByCategoryId(Long categoryId, int pageNum, int pageSize, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

//    @Override
//    public List<Product> findProducts(String categoryName) {
//        return productRepository.findProductsByCategoryCategoryName(categoryName);
//    }

    @Override
    public Page<Product> findProductByKeyword(String keyword, List<Long> colorIds, List<Long> sizeIds, float price1, float price2, int page, int pageSize, String sortField, String sortDir) {
        Pageable pageable = PageRequest.of(page - 1, pageSize,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        Page<Product> pageResult;
        if ((colorIds != null) && (sizeIds != null)) {
            pageResult = productRepository.findProductsByProductNameOrCategoryName1(keyword, price1, price2, colorIds, sizeIds, pageable);
        } else if ((colorIds != null) && (sizeIds == null)) {
            pageResult = productRepository.findProductsByProductNameOrCategoryName2(keyword, price1, price2, colorIds, pageable);
        } else if ((colorIds == null) && (sizeIds != null)) {
            pageResult = productRepository.findProductsByProductNameOrCategoryName3(keyword, price1, price2, sizeIds, pageable);
        } else {
            pageResult = productRepository.findProductsByProductNameOrCategoryName0(keyword, price1, price2, pageable);
        }
        return pageResult;
    }

    @Override
    public Page<Product> findProductsWithCondition(
            Long categoryId, List<Long> colorIds, List<Long> sizeIds, float price1, float price2,
            int page, int pageSize, String sortField, String sortDir) {

        Pageable pageable = PageRequest.of(page - 1, pageSize,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Product> pageProduct;
        if ((colorIds != null) && (sizeIds != null)) {
            if (categoryId != null) {
                pageProduct = productRepository.searchProductDetailsWithConditional1(categoryId, colorIds, sizeIds, price1, price2, pageable);
            } else {
                pageProduct = productRepository.searchProductDetailsWithConditional5(colorIds, sizeIds, price1, price2, pageable);
            }
        } else if ((colorIds != null) && (sizeIds == null)) {
            if (categoryId != null) {
                pageProduct = productRepository.searchProductDetailsWithConditional2(categoryId, colorIds, price1, price2, pageable);
            } else {
                pageProduct = productRepository.searchProductDetailsWithConditional6(colorIds, price1, price2, pageable);
            }
        } else if ((colorIds == null) && (sizeIds != null)) {
            if (categoryId != null) {
                pageProduct = productRepository.searchProductDetailsWithConditional3(categoryId, sizeIds, price1, price2, pageable);
            } else {
                pageProduct = productRepository.searchProductDetailsWithConditional7(sizeIds, price1, price2, pageable);
            }
        } else {
            if (categoryId != null) {
                pageProduct = productRepository.searchProductDetailsWithConditional4(categoryId, price1, price2, pageable);
            } else {
                pageProduct = productRepository.searchProductDetailsWithConditional8(price1, price2, pageable);
            }
        }
        return pageProduct;
    }

    @Override
    public Map<ProductColor, Integer> getListColorAndAmount(Long categoryId) {
        Map<ProductColor, Integer> listColorAndAmount = new TreeMap<>();
        List<ProductColor> productColorList = productColorRepository.findAll();
        productColorList.forEach(productColor -> {
            Integer totalQuantitiesForColor = productDetailRepository.countAvailableByCategoryAndColor(categoryId, productColor.getId());
            if (totalQuantitiesForColor == null) {
                totalQuantitiesForColor = 0;
            }
            listColorAndAmount.put(productColor, totalQuantitiesForColor);
        });

        return listColorAndAmount;
    }

    @Override
    public Map<ProductColor, Integer> getListColorAndAmount(String keyword) {
        Map<ProductColor, Integer> listColorAndAmount = new TreeMap<>();
        List<ProductColor> productColorList = productColorRepository.findAll();
        productColorList.forEach(productColor -> {
            Integer totalQuantitiesForColor = productDetailRepository.countAvailableByKeywordAndColor(keyword, productColor.getId());
            if (totalQuantitiesForColor == null) {
                totalQuantitiesForColor = 0;
            }
            listColorAndAmount.put(productColor, totalQuantitiesForColor);
        });

        return listColorAndAmount;
    }

    @Override
    public Map<ProductSize, Integer> getListSizeAndAmount(Long categoryId) {
        Map<ProductSize, Integer> listSizeAndAmount = new TreeMap<>();
        List<ProductSize> productSizeList = productSizeRepository.findAll();
        productSizeList.forEach(productSize -> {
            Integer totalQuantitiesForSize = productDetailRepository.countAvailableByCategoryAndSize(categoryId, productSize.getId());
            if (totalQuantitiesForSize == null) {
                totalQuantitiesForSize = 0;
            }
            listSizeAndAmount.put(productSize, totalQuantitiesForSize);
        });

        return listSizeAndAmount;
    }

    @Override
    public Map<ProductSize, Integer> getListSizeAndAmount(String keyword) {
        Map<ProductSize, Integer> listSizeAndAmount = new TreeMap<>();
        List<ProductSize> productSizeList = productSizeRepository.findAll();
        productSizeList.forEach(productSize -> {
            Integer totalQuantitiesForSize = productDetailRepository.countAvailableByKeywordAndSize(keyword, productSize.getId());
            if (totalQuantitiesForSize == null) {
                totalQuantitiesForSize = 0;
            }
            listSizeAndAmount.put(productSize, totalQuantitiesForSize);
        });

        return listSizeAndAmount;
    }


//    @Override
//    public List<ProductDTO> getProductsTopSaleMan() {
//        return null;
//    }
//
//    @Override
//    public List<ProductDTO> getProductsTopSaleWomen() {
//        return null;
//    }
}
