package com.tukktukk.coupon.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "app_coupon")
@Getter
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_key")
    private Long couponKey;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "remained_stock", nullable = false)
    private Long remainedStock;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "coupon")
    private Set<CouponUser> couponUsers = new HashSet<>();

    public Set<CouponUser> getCouponUsers() {
        return Collections.unmodifiableSet(couponUsers);
    }

    public Coupon (String name, Long remainedStock) {
        this.name = name;
        this.remainedStock = remainedStock;
    }
    public void issue(Long userKey) {
        if (remainedStock < 1) {
            return;
//            throw new IllegalStateException("Remained stock is less than 1!");
        }
        CouponUser couponUser = new CouponUser(this, userKey);

        if (couponUsers.contains(couponUser)) {
            return;
//            throw new IllegalStateException("Coupon already exists!");
        }

        this.couponUsers.add(couponUser);
        this.remainedStock -= 1;
    }

}
