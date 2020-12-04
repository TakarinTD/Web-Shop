package com.example.demo.controller;

import com.example.demo.dto.ReplyReviewDTO;
import com.example.demo.dto.ReviewDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Product;
import com.example.demo.entity.ReplyReview;
import com.example.demo.entity.Review;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReviewService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ReviewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/postReview")
    @ResponseBody
    public ResponseEntity<Object> postReview(@RequestBody ReviewDTO reviewDTO) {
        User user = userService.findUserById(reviewDTO.getUser().getId());
        Product product = productService.getProductByIdProduct(reviewDTO.getProduct().getId());
        if(user != null && product != null) {
            Review review = new Review(reviewDTO.getTitle(), reviewDTO.getComment(), reviewDTO.getRatting(), product, user);

            Review dbReview = reviewService.save(review);
            System.out.println("review is saved in database with id : " + dbReview.getId());
            UserDTO userDTO = new UserDTO(user.getId(),user.getEmail(), user.getFullName(), user.getPhone(), user.getAddress(), user.getBirthday());
            reviewDTO.setId(dbReview.getId());
            reviewDTO.setCreatedAt(dbReview.getCreatedAt());
            reviewDTO.setUpdatedAt(dbReview.getUpdatedAt());
            reviewDTO.setUser(userDTO);
            return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Đã có lỗi xảy ra", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/postReplyReview")
    @ResponseBody
    public ResponseEntity<Object> postReplyReview(@RequestBody ReplyReviewDTO replyReviewDTO) {
        User user = userService.findUserById(replyReviewDTO.getUser().getId());
        Review review = reviewService.findReviewById(replyReviewDTO.getReview().getId());
        if(user != null && review != null) {
            ReplyReview replyReview = new ReplyReview(replyReviewDTO.getContent(), user, review);

            ReplyReview dbReplyReview = reviewService.saveReply(replyReview);
            System.out.println("reply review is saved in database with id : " + dbReplyReview.getId());
            UserDTO userDTO = new UserDTO(user.getId(),user.getEmail(), user.getFullName(), user.getPhone(), user.getAddress(), user.getBirthday());

            replyReviewDTO.setId(dbReplyReview.getId());
            replyReviewDTO.setCreatedAt(dbReplyReview.getCreatedAt());
            replyReviewDTO.setUser(userDTO);

            return new ResponseEntity<>(replyReviewDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("Đã có lỗi xảy ra", HttpStatus.BAD_REQUEST);
    }
}
