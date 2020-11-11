package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.config.*;

@SpringBootApplication
@RequiredArgsConstructor
@EnableJpaAuditing
public class MonstarfashionshopApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MonstarfashionshopApplication.class, args);
	}

	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final ProductDetailRepository productDetailRepository;
	private final ProductColorRepository productColorRepository;
	private final ProductSizeRepository productSizeRepository;
	private final PromotionRepository promotionRepository;
	private final OrderRepository orderRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {

//		// lấy một User từ db
//		User user = userRepository.getOne((long) 2);
//
//		ProductDetail productDetail = productDetailRepository.getOne((long) 9);
//
//		// Tạo một order vơi các thông tin
//		Order order = new Order();
//		order.setReciverName("Nguyễn Quỳnh Anh");
//		order.setAddress("Hà Nội");
////		order.setUser(user);
//
//		OrderDetail orderDetail1 = new OrderDetail();
//		orderDetail1.setProductDetail(productDetail);
//		orderDetail1.setOrder(order);
//
//		order.addOrderDetail(orderDetail1);
//
//		orderRepository.save(order);


//		// tạo một danh sách khuyến mại
//		Promotion promotion1 = new Promotion();
//		promotion1.setPromotionName("Siêu sales cuối năm");
//		promotion1.setDiscount(50);
//		promotion1.setStartDate(new Date());
//
//		Promotion promotion2 = new Promotion();
//		promotion2.setPromotionName("Siêu sales tết dương lịch");
//		promotion2.setDiscount(20);
//		promotion2.setStartDate(new Date());
//
//		Set<Product> productList1 = new HashSet<>();
//		productList1.add(productDb1);
//		productList1.add(productDb2);
//
//		Set<Product> productList2 = new HashSet<>();
//		productList2.add(productDb1);
//
//		promotion1.setProducts(productList1);
//		promotion2.setProducts(productList2);
//
//		// lưu vào db
//		Promotion promotionDb1 = promotionRepository.save(promotion1);
//		Promotion promotionDb2 =  promotionRepository.save(promotion2);

//		promotionDb1.getProducts().forEach(product -> {
//			System.out.println(product.toString());
//		} );
	}
}
