package com.tukktukk.coupon.service;

import com.tukktukk.coupon.entity.Coupon;
import com.tukktukk.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponRepository couponRepository;

    @Transactional
    public void issueWithNoLock(Long userKey, Long couponKey) {
        Coupon coupon = couponRepository.findById(couponKey).orElseThrow(() -> new IllegalStateException("찾을 수 없는 쿠폰입니다."));
        coupon.issue(userKey);
        couponRepository.save(coupon);// ?
    }

}
