package com.tukktukk;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "address",nullable = false)
    private String address;

    @OneToMany(mappedBy = "stadium")
    private final List<Court> courts = new ArrayList<>();

    public void addCourt(final Court court) {
        courts.add(court);
    }

    public List<Court> getCourts() {
        return Collections.unmodifiableList(courts);
    }
}
