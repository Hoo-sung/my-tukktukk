package com.tukktukk.coupon;

import com.tukktukk.coupon.entity.Coupon;
import com.tukktukk.coupon.repository.CouponRepository;
import com.tukktukk.coupon.repository.CouponUserRepository;
import com.tukktukk.coupon.service.CouponIssueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    private static final int TOTAL_REQUESTS = 10_000;
    private ExecutorService executor;
    private CountDownLatch latch;

    @BeforeEach
    void setUp() {
        executor = Executors.newFixedThreadPool(THREAD_COUNT);
        latch = new CountDownLatch(TOTAL_REQUESTS);
    }

    @AfterEach
    void tearDown() {
        executor.shutdown();
    }

    @Test
    void 동시성_제어하지_못하는_테스트() throws InterruptedException {
        //given
        Coupon coupon = new Coupon("할인_이벤트", 100L);
        Long couponKey = couponRepository.save(coupon).getCouponKey();
        //when
        for (long i = 1; i <= TOTAL_REQUESTS; i++) {
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
//        Coupon result = couponRepository.findById(couponKey).get();
//        assertThat(result.getRemainedStock()).isNotEqualTo(0l);
    }

}
