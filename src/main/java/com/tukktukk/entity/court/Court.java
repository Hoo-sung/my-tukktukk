package com.tukktukk.entity.court;

import com.tukktukk.entity.stadium.Stadium;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CourtType courtType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    public int getCourtMinimumPlayer(){
        return courtType.getMinimumPlayer();
    }

    public int getCourtMaximumPlayer(){
        return courtType.getMaximumPlayer();
    }
}
