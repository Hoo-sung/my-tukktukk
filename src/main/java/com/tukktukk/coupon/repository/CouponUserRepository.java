package com.tukktukk.coupon.repository;


import com.tukktukk.coupon.entity.CouponUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponUserRepository extends JpaRepository<CouponUser, Long> {
    public Long countByCoupon_CouponKey(Long couponKey);

}
