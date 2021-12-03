package com.testcontainers.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shinobi {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String rank;

    private HiddenVillage village;

    public Shinobi(String name, String rank, HiddenVillage village) {
        this.name = name;
        this.rank = rank;
        this.village = village;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public HiddenVillage getVillage() {
        return village;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shinobi shinobi = (Shinobi) o;
        return Objects.equals(name, shinobi.name) &&
                Objects.equals(rank, shinobi.rank) &&
                village == shinobi.village;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rank, village);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", city='" + rank + '\'' +
                ", universum=" + village.getDisplayName() +
                '}';
    }
}
