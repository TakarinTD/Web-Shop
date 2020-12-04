package com.example.demo.service.impl;

import com.example.demo.entity.ReplyReview;
import com.example.demo.entity.Review;
import com.example.demo.repository.ReplyReviewRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReplyReviewRepository replyReviewRepository;

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public ReplyReview saveReply(ReplyReview replyReview) {
        return replyReviewRepository.save(replyReview);
    }

    @Override
    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }
}
