package com.iweb.service.impl;

import com.iweb.DAO.ProductImageDAO;
import com.iweb.DAO.ReviewDAO;
import com.iweb.DAO.impl.ProductImageDAOImpl;
import com.iweb.DAO.impl.ReviewDAOImpl;
import com.iweb.entity.ProductImage;
import com.iweb.entity.Review;
import com.iweb.service.ReviewService;

import java.util.List;

/**
 * @author Yang
 * @date 2023/4/11 19:20
 */
public class ReviewServiceImpl implements ReviewService {
    ReviewDAO reviewDAO = new ReviewDAOImpl();
    ProductImageDAO productImageDAO = new ProductImageDAOImpl();
    @Override
    public List<Review> list(int pid) {
        return reviewDAO.list(pid);
    }

    @Override
    public void add(Review review) {
        reviewDAO.add(review);
    }

    @Override
    public List<Review> listUserReviews(int uid) {
        List<Review> reviews = reviewDAO.listUserReviews(uid);
        for (Review review:reviews) {
            List<ProductImage> pis = productImageDAO.list(review.getProduct().getId());
            review.getProduct().setImages(pis);
        }
        return reviews;
    }

    @Override
    public Review get(int id) {
        return reviewDAO.get(id);
    }

    @Override
    public void delete(int id) {
        reviewDAO.delete(id);
    }

    @Override
    public void update(Review review) {
        reviewDAO.update(review);
    }
}
