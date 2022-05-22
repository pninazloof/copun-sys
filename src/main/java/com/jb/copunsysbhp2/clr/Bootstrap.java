package com.jb.copunsysbhp2.clr;


import com.jb.copunsysbhp2.beans.*;
import com.jb.copunsysbhp2.repos.*;
import com.jb.copunsysbhp2.utils.Art;
import lombok.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;


@Component
@Order(1)
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Art.BOOTSTRAP_1);
        System.out.println(Art.COMPANIES);
        Company comp1 = Company.builder()
                .email("cola@gmail.com")
                .password("1234")
                .name("Cola")
                .build();
        Company comp2 = Company.builder()
                .email("pepsi@gmail.com")
                .password("1234")
                .name("Pepsi")
                .build();
        Company comp3 = Company.builder()
                .email("ivory@gmail.com")
                .password("1234")
                .name("Ivory")
                .build();
        companyRepository.saveAll(Arrays.asList(comp1, comp2, comp3));
        companyRepository.findAll().forEach(System.out::println);


        System.out.println(Art.COUPONS);
        Coupon coup1 = Coupon.builder()
                .title("1+1")
                .description("all drinks")
                .amount(100)
                .company(companyRepository.getById(1))
                .price(4.9)
                .image("http://bla.com")
                .category(Category.FOOD)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().minusDays(3)))
                .build();

        Coupon coup2 = Coupon.builder()
                .title("2+2")
                .description("all drinks")
                .amount(100)
                .company(companyRepository.getById(1))
                .price(8.9)
                .image("http://bla.com")
                .category(Category.FOOD)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .build();

        Coupon coup3 = Coupon.builder()
                .title("3+3")
                .description("all drinks")
                .amount(555)
                .company(companyRepository.getById(2))
                .price(3.9)
                .image("http://bla.com")
                .category(Category.FOOD)
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.now().plusDays(14)))
                .build();

        couponRepository.saveAll(Arrays.asList(coup1, coup2, coup3));
        couponRepository.findAll().forEach(System.out::println);


        System.out.println(Art.CUSTOMERS);
        Customer cust1 = Customer.builder()
                .email("Pnina@gmail.com")
                .password("1234")
                .firstName("Pnina")
                .lastName("Shaked")
                .coupon(couponRepository.getById(3))
                .coupon(couponRepository.getById(4))
                .coupon(couponRepository.getById(1))
                .coupon(couponRepository.getById(5))
                .build();

        Customer cust2 = Customer.builder()
                .email("tamar@gmail.com")
                .password("1234")
                .firstName("Tamar")
                .lastName("Shaked")
                .coupon(couponRepository.getById(3))
                .build();
        Customer cust3 = Customer.builder()
                .email("David@gmail.com")
                .password("1234")
                .firstName("David")
                .lastName("Shaked")
                .coupon(couponRepository.getById(3))
                .coupon(couponRepository.getById(4))
                .coupon(couponRepository.getById(5))
                .build();

        customerRepository.saveAll(Arrays.asList(cust1, cust2, cust3));
        customerRepository.findAll().forEach(System.out::println);

    }
}

