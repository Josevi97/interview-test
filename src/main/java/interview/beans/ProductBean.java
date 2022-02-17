package interview.beans;

import interview.constants.ProductConstants;
import interview.entities.ProductEntity;

public class ProductBean {
    private String code;
    private String name;
    private String description;
    private Double price;

    public ProductBean() {
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

    public boolean isValid() {
        boolean bCode = this.code.length() <= ProductConstants.CODE_MAX_SIZE
                && this.code.length() >= ProductConstants.CODE_MIN_SIZE;
        boolean bName = this.name.length() <= ProductConstants.NAME_MAX_SIZE
                && this.name.length() >= ProductConstants.NAME_MIN_SIZE;
        boolean bDescription = this.description.length() <= ProductConstants.DESCRIPTION_MAX_SIZE &&
                this.description.length() >= ProductConstants.DESCRIPTION_MIN_SIZE;
        boolean bPrice = this.price >= ProductConstants.MIN_PRICE;

        return bCode && bName && bDescription && bPrice;
    }

    public ProductEntity toEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCode(this.code);
        productEntity.setName(this.name);
        productEntity.setDescription(this.description);
        productEntity.setPrice(this.price);

        return productEntity;
    }
}
