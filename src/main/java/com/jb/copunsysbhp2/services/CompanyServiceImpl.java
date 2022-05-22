package com.jb.copunsysbhp2.services;


import com.jb.copunsysbhp2.beans.*;
import com.jb.copunsysbhp2.exceptions.*;

import com.jb.copunsysbhp2.utils.ExceptionUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService {
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean login(String email, String password) {
        if (companyRepository.existsByEmail(email) && companyRepository.existsByPassword(password)) {
            setCompanyID(companyRepository.findByEmailAndPassword(email, password).getId());
            return true;
        }
        return false;
    }

    public void setCompanyID(int companyID) {
        this.companyId = companyID;
    }

    @Override
    public void addCoupon(Coupon coupon) throws SQLException, CouponSystemException {
        if (couponRepository.existsByTitleAndCompany_Id(coupon.getTitle(), coupon.getCompany().getId())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EXIST_SAME_TITLE);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.COUPOM_NOT_EXIST);
        }
        if (couponRepository.getById(couponId).getCompany().getId() == coupon.getCompany().getId()) {
            throw new CouponSystemException(ErrMsg.CANNOT_CHANGE_COMPANYID_FOT_COUPON);
        }
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        couponRepository.deleteFromCustomers_coupons(couponId);
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return null;
    }

    @Override
    public List<Coupon> getAllCompanyCoupons() {
        return couponRepository.findByCompany_id(companyId);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByCategory(Category category) {
        System.out.println(category);

        System.out.println(companyId);
        return couponRepository.findByCompany_idAndCategory(companyId, category);
    }

    @Override
    public List<Coupon> getAllCompanyCouponsByPrice(double price) {
        return couponRepository.findByCompany_idAndPriceLessThan(companyId, price);
    }

    @Override
    public Coupon getSingleCoupon(int couponId) throws CouponSystemException {
        return couponRepository.findById(couponId).orElseThrow(ExceptionUtils::notFound);
    }

    @Override
    public Company getSingleCompany(String email, String password) {
        return companyRepository.findByEmailAndPassword(email, password);
    }


}
