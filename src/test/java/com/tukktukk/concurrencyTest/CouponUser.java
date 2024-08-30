package com.tukktukk.concurrencyTest;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "app_coupon_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponUser {//N:M 1:N, N:1로 나눠서

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_user_key")
    private Long couponUserKey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coupon_key", nullable = false, updatable = false)
    private Coupon coupon;

    @Column(name = "user_key", nullable = false, updatable = false)
    private Long userKey;

    public CouponUser(Coupon coupon, Long userKey) {
        this.coupon = coupon;
        this.userKey = userKey;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof CouponUser)) return false;

        CouponUser other = (CouponUser) obj;
        if (coupon != other.coupon)
            return false;
        if (userKey != other.userKey) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = coupon.hashCode();
        result = 31 * result + userKey.hashCode();
        return result;
    }
}
