package com.management.chestionare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@SuppressWarnings({"SpellCheckingInspection", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "chestionar_efectuat")
@NoArgsConstructor
@AllArgsConstructor
public class ChestionarEfectuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chestionar_efectuat_id")
    @Getter
    private Long chestionarEfectuatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chestionar_id")
    @Getter
    @Setter
    private Chestionar chestionar_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizator_id")
    @Getter
    @Setter
    private Utilizator utilizator;

    @Override
    public String toString() {
        return "ChestionarEfectuat{" +
                "chestionarEfectuatId=" + chestionarEfectuatId +
                ", chestionar_id=" + chestionar_id +
                ", utilizator=" + utilizator +
                '}';
    }
}
