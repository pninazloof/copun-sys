package com.jb.copunsysbhp2.jobs;

import com.jb.copunsysbhp2.beans.Coupon;
import com.jb.copunsysbhp2.repos.CouponRepository;
import com.jb.copunsysbhp2.utils.Art;
import lombok.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RemoveExpierdCoupons {
    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void deleteExpiredCoupons() {
        System.out.println(Art.DAILY_REMOVAL);
        List<Coupon> expiredCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
        expiredCoupons.forEach(System.out::println);
        for (Coupon expiredCoupon : expiredCoupons) {
            couponRepository.deleteFromCustomers_coupons(expiredCoupon.getId());
            couponRepository.deleteFromCoupons(expiredCoupon.getId());
        }
        expiredCoupons.forEach(System.out::println);
        System.out.println("Deleted expired coupons");
    }
}