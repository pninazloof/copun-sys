package com.jb.copunsysbhp2.repos;


import com.jb.copunsysbhp2.beans.Category;
import com.jb.copunsysbhp2.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    List<Coupon> findByCompany_id(int companyId);

    boolean existsByTitleAndCompany_Id(String title, Integer company_Id);

    @Transactional
    @Modifying
    @Query(value = "delete from `couponsys-p2`.customers_coupons where coupons_id = :couponId", nativeQuery = true)
    void deleteFromCustomers_coupons(@Param("couponId") int couponId);


    List<Coupon> findByCompany_idAndCategory(int companyId, Category category);

    List<Coupon> findByCompany_idAndPriceLessThan(int companyId, double price);


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `couponsys-p2`.`customers_coupons` (`customer_id`, `coupons_id`) VALUES (:customerId, :couponId)", nativeQuery = true)
    void addCouponToCustomer(@Param("customerId") int customerId, @Param("couponId") int couponId);


    List<Coupon> findByEndDateBefore(Date endDate);

    @Transactional
    @Modifying
    @Query(value = "delete from `couponsys-p2`.coupons where id = :couponId", nativeQuery = true)
    void deleteFromCoupons(@Param("couponId") int couponId);

    @Transactional
    //@Modifying
    @Query(value = "SELECT id,amount,category,description,end_date,image,price,start_date,title,company_id FROM `couponsys-p2`.customers_coupons  inner join `couponsys-p2`.coupons on `couponsys-p2`.customers_coupons.coupons_id=`couponsys-p2`.coupons.id where customer_id = :customerId", nativeQuery = true)
    List<Coupon> getAllPurchaseCoupons(@Param("customerId") int customerId);

    @Transactional
    //@Modifying
    @Query(value = "SELECT id,amount,category,description,end_date,image,price,start_date,title,company_id FROM `couponsys-p2`.customers_coupons  inner join `couponsys-p2`.coupons on `couponsys-p2`.customers_coupons.coupons_id=`couponsys-p2`.coupons.id where customer_id = :customerId and category = :category ", nativeQuery = true)
    List<Coupon> getAllPurchaseCouponsByCategory(@Param("customerId") int customerId, @Param("category") int category);


    @Transactional
    //@Modifying
    @Query(value = "SELECT id,amount,category,description,end_date,image,price,start_date,title,company_id FROM `couponsys-p2`.customers_coupons  inner join `couponsys-p2`.coupons on `couponsys-p2`.customers_coupons.coupons_id=`couponsys-p2`.coupons.id where customer_id = :customerId and price < :maxPrice ", nativeQuery = true)
    List<Coupon> getAllPurchaseCouponsByMaxPrice(@Param("customerId") int customerId, @Param("maxPrice") double maxPrice);

}