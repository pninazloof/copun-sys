package com.jb.copunsysbhp2.services;

import com.jb.copunsysbhp2.beans.*;
import com.jb.copunsysbhp2.exceptions.CouponSystemException;

import java.sql.SQLException;
import java.util.List;


public interface CompanyService {
    void addCoupon(Coupon coupon) throws CouponSystemException, SQLException;

    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponId) throws CouponSystemException;

    List<Coupon> getAllCoupons();

    List<Coupon> getAllCompanyCoupons();

    List<Coupon> getAllCompanyCouponsByCategory(Category category);

    List<Coupon> getAllCompanyCouponsByPrice(double price);

    Coupon getSingleCoupon(int couponId) throws CouponSystemException;

    Company getSingleCompany(String email, String password);

    boolean login(String email, String password);


}