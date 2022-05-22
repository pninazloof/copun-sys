package com.jb.copunsysbhp2.security;

import com.jb.copunsysbhp2.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class LoginManager {
    @Autowired
    private ApplicationContext ctx;

    public ClientService Login(String email, String password, ClientType clientType) {
        switch (clientType) {
            case Administrator:
                AdminService adminService = ctx.getBean(AdminService.class);
                if (adminService.login(email, password)) {
                    return (ClientService) adminService;
                }

            case Company:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (companyService.login(email, password)) {
                    return (ClientService) companyService;
                }
            case Customer:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(email, password)) {
                    return (ClientService) customerService;
                }
            default:
                break;
        }
        return null;
    }
}