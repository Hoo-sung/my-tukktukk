package com.tukktukk.coupon;

import com.tukktukk.coupon.entity.Coupon;
import com.tukktukk.coupon.repository.CouponRepository;
import com.tukktukk.coupon.repository.CouponUserRepository;
import com.tukktukk.coupon.service.CouponIssueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class CouponIssueServiceTest {

    @Autowired
    private CouponIssueService couponIssueService;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CouponUserRepository couponUserRepository;

    private static final int THREAD_COUNT = 100;
    private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
    private CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

    @Test
    void 동시성_제어하지_못하는_테스트() throws InterruptedException {
        //given
        Coupon coupon = new Coupon("할인_이벤트", 100L);
        Long couponKey = couponRepository.save(coupon).getCouponKey();
        log.info("couponKey: {}", couponKey);
        //when
        for (long i = 1; i <= 10000L; i++) {
            final Long userKey = i;
            executor.submit(() -> {
                try {
                    couponIssueService.issueWithNoLock(userKey, couponKey);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        //then
        assertThat(couponUserRepository.countByCoupon_CouponKey(couponKey)).isNotEqualTo(100l);
        Coupon result = couponRepository.findById(couponKey).get();
        assertThat(result.getRemainedStock()).isNotEqualTo(0l);
    }

}
