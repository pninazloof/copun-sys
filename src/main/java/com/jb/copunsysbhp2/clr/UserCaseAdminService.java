package com.jb.copunsysbhp2.clr;


import com.jb.copunsysbhp2.beans.*;


import com.jb.copunsysbhp2.security.*;
import com.jb.copunsysbhp2.services.*;
import com.jb.copunsysbhp2.utils.Art;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UserCaseAdminService implements CommandLineRunner {
    @Autowired
    private LoginManager loginManager;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.ADMIN_SERIVE);
        AdminService adminService = (AdminService) loginManager.Login("admin@admin.com", "admin", ClientType.Administrator);

        System.out.println("\n********** Company ************");
        System.out.println(Art.ADD);
        Company comp1 = Company.builder()
                .email("spring@gmail.com")
                .password("4321")
                .name("spring")
                .build();
        adminService.addCompany(comp1);


        System.out.println(Art.UPDATE);
        Company companyToUpdate = adminService.getSingleCompany(1);
        companyToUpdate.setPassword("111222");
        adminService.updateCompany(1, companyToUpdate);

        System.out.println(Art.DELETE);
        adminService.deleteCompany(2);

        System.out.println("AdminService - getAllCompanies");
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("AdminService - getSingleCompany");
        System.out.println(adminService.getSingleCompany(1));

        System.out.println("\n********** Customer ************");
        System.out.println(Art.ADD);

        Customer cust1 = Customer.builder()
                .email("Avishay@gmail.com")
                .password("9999")
                .firstName("Avishay")
                .lastName("Shaked")
                .build();
        adminService.addCustomer(cust1);

        System.out.println(Art.UPDATE);
        Customer customerToUpdate = adminService.getSingleCustomer(4);
        customerToUpdate.setFirstName("Shira");
        customerToUpdate.setEmail("Shira@gmail.com");
        adminService.updateCustomer(4, customerToUpdate);

        System.out.println(Art.DELETE);
        adminService.deleteCustomer(4);

        System.out.println("AdminService - getAllCustomers");
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("AdminService - getSingleCustomer");
        System.out.println(adminService.getSingleCustomer(2));

        System.out.println("End");
    }


}
