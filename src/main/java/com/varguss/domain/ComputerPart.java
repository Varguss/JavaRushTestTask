package com.varguss.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class ComputerPart implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private boolean isImportant;

    @Column(nullable = false)
    private Long count;

    public ComputerPart(String name, boolean isImportant, Long count) {
        this.name = name;
        this.isImportant = isImportant;
        this.count = count;
    }

    public ComputerPart(String name, boolean isImportant) {
        this.name = name;
        this.isImportant = isImportant;
        this.count = 1L;
    }

    // default
    public ComputerPart() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerPart that = (ComputerPart) o;
        return isImportant == that.isImportant &&
                Objects.equals(name, that.name) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, isImportant, count);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public void setImportant(boolean important) {
        isImportant = important;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ComputerPart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isImportant=" + isImportant +
                ", count=" + count +
                '}';
    }
}
