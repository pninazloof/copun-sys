package com.jb.copunsysbhp2.services;


import com.jb.copunsysbhp2.beans.Company;
import com.jb.copunsysbhp2.beans.*;

import com.jb.copunsysbhp2.exceptions.CouponSystemException;
import com.jb.copunsysbhp2.exceptions.ErrMsg;
import com.jb.copunsysbhp2.utils.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_EXIST);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if (!companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST_CANNOT_UPDATE);
        }
        if (!companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST_CANNOT_UPDATE);
        }
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if (!companyRepository.existsById(companyId)) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_EXIST_CANNOT_UPDATE);
        }
        List<Coupon> coupons = couponRepository.findByCompany_id(companyId);
        coupons.forEach(System.out::println);

        for (Coupon c : coupons) {
            couponRepository.deleteById(c.getId());
            couponRepository.deleteFromCustomers_coupons(c.getId());
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(ExceptionUtils::notFound);

    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_EXIST);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (!customerRepository.existsById(customer.getId())) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_EXIST);
        }
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        //todo
        //יש למחוק בנוסף גם את היסטוריית רכישת הקופונים של הלקוח.
        customerRepository.deleteById(customerId);
    }


    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(ExceptionUtils::notFound);
    }


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }
}
