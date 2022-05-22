package com.jb.copunsysbhp2.services;

import com.jb.copunsysbhp2.beans.*;
import com.jb.copunsysbhp2.exceptions.*;

import com.jb.copunsysbhp2.utils.Art;
import com.jb.copunsysbhp2.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    private int customerID;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    @Override
    public boolean login(String email, String password) {
        if (customerRepository.existsByEmail(email) && companyRepository.existsByPassword(password)) {
            setCustomerID(customerRepository.findByEmailAndPassword(email, password).getId());
            return true;
        }
        return false;
    }


    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        Coupon couponTopurchase = getSingleCoupon(coupon.getId());
        //1 --You cannot purchase the same coupon more than once.
        if (customerRepository.getCountCustomers_coupons(customerID, coupon.getId()) > 0) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_PURCHASE_COUPON);
        }
        //2 --The coupon cannot be purchased if its quantity is 0
        if (!(couponTopurchase.getAmount() > 0)) {
            throw new CouponSystemException(ErrMsg.AMOUNT_COUPON_IS_ZERO);
        }
        //3 -- The coupon has expired
        if (couponTopurchase.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrMsg.COUPON_EXPIRED);
        }
        couponRepository.addCouponToCustomer(customerID, coupon.getId());


        couponTopurchase.setAmount(couponTopurchase.getAmount() - 1);
        couponRepository.saveAndFlush(couponTopurchase);
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponSystemException {
        return couponRepository.findById(couponId).orElseThrow(ExceptionUtils::notFound);
    }

    @Override
    public List<Coupon> getAllPurchaseCoupons() {
        return couponRepository.getAllPurchaseCoupons(customerID);
    }

    @Override
    public List<Coupon> getAllPurchaseCouponsByCategory(Category category) {
        return couponRepository.getAllPurchaseCouponsByCategory(customerID, category.ordinal());
    }

    @Override
    public List<Coupon> getAllPurchaseCouponsByMaxPrice(double maxPrice) {
        return couponRepository.getAllPurchaseCouponsByMaxPrice(customerID, maxPrice);
    }

    @Override
    public Customer getSingleCustomer() {
        return customerRepository.getById(customerID);
    }
}
