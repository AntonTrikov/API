package com.rest.API.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_typology")
public class ProductTypologyModel implements Serializable {
    public static final String ENTITY_NAME ="product typology";
    public static final String ID_NAME ="id";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_typo_id_generator")
    @SequenceGenerator(name="prod_typo_id_generator", sequenceName = "product_typology_sequence",
            allocationSize = 1)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    public ProductTypologyModel() {}

    public ProductTypologyModel(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductTypologyModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
