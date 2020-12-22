package com.rest.API.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ingredient")
public class IngredientModel implements Serializable {
    private static final String ENTITY_NAME ="ingredient";
    private static final String ID_NAME ="id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_generator")
    @SequenceGenerator(name="ingredient_id_generator", sequenceName = "ingredient_sequence",
            allocationSize = 1)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="is_allergenic")
    private boolean isAllergenic;

    @ManyToMany(mappedBy = "ingredientSet")
    private Set<ProductModel> productSet = new HashSet<>();

    public IngredientModel() {}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllergenic() {
        return isAllergenic;
    }

    public void setAllergenic(boolean allergenic) {
        isAllergenic = allergenic;
    }

    public static String getEntityName() {
        return ENTITY_NAME;
    }

    public static String getIdName() {
        return ID_NAME;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<ProductModel> getProductSet() {
        return productSet;
    }

    public void setProductSet(Set<ProductModel> productSet) {
        this.productSet = productSet;
    }

    @Override
    public String toString() {
        return "IngredientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAllergenic=" + isAllergenic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientModel that = (IngredientModel) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
