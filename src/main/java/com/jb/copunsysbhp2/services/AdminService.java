package com.jb.copunsysbhp2.services;


import com.jb.copunsysbhp2.beans.*;
import com.jb.copunsysbhp2.exceptions.*;

import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;

    void updateCompany(int companyId, Company company) throws CouponSystemException;

    void deleteCompany(int companyId) throws CouponSystemException;

    List<Company> getAllCompanies();

    Company getSingleCompany(int companyId) throws CouponSystemException;

    void addCustomer(Customer customer) throws CouponSystemException;

    void updateCustomer(int customerId, Customer customer) throws CouponSystemException;

    void deleteCustomer(int customerId);

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int customerId) throws CouponSystemException;

    boolean login(String email, String password);
}
