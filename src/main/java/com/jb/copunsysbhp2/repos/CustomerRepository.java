package com.jb.copunsysbhp2.repos;


import com.jb.copunsysbhp2.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    boolean existsByEmail(String email);

    Customer findByEmailAndPassword(String email, String password);


    @Transactional
    @Query(value = "SELECT count(*)  FROM `couponsys-p2`.customers_coupons where customer_id = :customerId and coupons_id= :couponId ;", nativeQuery = true)
    Integer getCountCustomers_coupons(@Param("customerId") int customerId, @Param("couponId") int couponId);


}