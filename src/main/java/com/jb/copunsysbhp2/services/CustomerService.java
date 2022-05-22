package com.jb.copunsysbhp2.services;

import com.jb.copunsysbhp2.beans.*;
import com.jb.copunsysbhp2.exceptions.*;

import java.util.List;

public interface CustomerService {
    boolean login(String email, String password);

    void purchaseCoupon(Coupon coupon) throws CouponSystemException;

    Coupon getSingleCoupon(int couponId) throws CouponSystemException;


    List<Coupon> getAllPurchaseCoupons();

    List<Coupon> getAllPurchaseCouponsByCategory(Category category);

    List<Coupon> getAllPurchaseCouponsByMaxPrice(double maxPrice);

    Customer getSingleCustomer();
}