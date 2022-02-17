package interview.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import interview.constants.ProductConstants;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false, length = ProductConstants.CODE_MAX_SIZE, unique = true)
    private String code;

    @Column(name = "name", nullable = false, length = ProductConstants.NAME_MAX_SIZE)
    private String name;

    @Column(name = "description", nullable = false, length = ProductConstants.DESCRIPTION_MAX_SIZE)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    public ProductEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
