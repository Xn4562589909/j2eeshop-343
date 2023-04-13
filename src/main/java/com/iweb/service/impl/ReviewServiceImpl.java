package com.iweb.service.impl;

import com.iweb.DAO.ReviewDAO;
import com.iweb.DAO.impl.ReviewDAOImpl;
import com.iweb.entity.Review;
import com.iweb.service.ReviewService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/11 19:20
 */
public class ReviewServiceImpl implements ReviewService {
    ReviewDAO reviewDAO = new ReviewDAOImpl();
    @Override
    public List<Review> list(int pid) {
        return reviewDAO.list(pid);
    }

    @Override
    public void add(Review review) {
        reviewDAO.add(review);
    }
}
