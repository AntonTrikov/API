package com.rest.API.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "product")

public class ProductModel {
    public static final String ENTITY_NAME ="product";
    public static final String ID_NAME ="name";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_id_generator")
    @SequenceGenerator(name="app_user_id_generator", sequenceName = "app_user_sequence",
            allocationSize = 1)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="photo")
    private String photo;

    @ManyToOne
    @JoinColumn(name="typology_id")
    private ProductTypologyModel productTypology;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "product_ingredients",
            joinColumns = { @JoinColumn(name = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "ingredient_id") }
    )
    Set<IngredientModel> ingredientSet = new HashSet<>();

    public ProductModel(){}

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ProductTypologyModel getProductTypology() {
        return productTypology;
    }

    public void setProductTypology(ProductTypologyModel productTypology) {
        this.productTypology = productTypology;
    }

    public Set<IngredientModel> getIngredientSet() {
        return ingredientSet;
    }

    public void setIngredientSet(Set<IngredientModel> ingredientSet) {
        this.ingredientSet = ingredientSet;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", productTypology=" + productTypology +
                ", ingredientSet=" + ingredientSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductModel that = (ProductModel) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
