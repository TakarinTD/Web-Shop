package com.example.demo.service;

import com.example.demo.entity.ReplyReview;
import com.example.demo.entity.Review;

public interface ReviewService {

    Review save(Review review);

    ReplyReview saveReply(ReplyReview replyReview);

    Review findReviewById(Long id);
}
