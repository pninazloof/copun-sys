package com.jb.copunsysbhp2.services;


import com.jb.copunsysbhp2.repos.CompanyRepository;
import com.jb.copunsysbhp2.repos.CouponRepository;
import com.jb.copunsysbhp2.repos.CustomerRepository;
import com.jb.copunsysbhp2.repos.CompanyRepository;
import com.jb.copunsysbhp2.repos.CouponRepository;
import com.jb.copunsysbhp2.repos.CustomerRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public abstract class ClientService {

    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    @Autowired
    protected CompanyRepository companyRepository;


    public abstract boolean login(String email, String password);

}