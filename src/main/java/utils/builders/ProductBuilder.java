package utils.builders;


import model.beans.Product;
import model.entities.Specification;

public class ProductBuilder {

    private Product product;

    public ProductBuilder() {
        product = new Product();
    }

    public ProductBuilder withName (String name) {
        this.product.setName(name);

        return this;
    }

    public ProductBuilder withCost (long cost) {
        this.product.setCost(cost);

        return this;
    }

    public ProductBuilder withSpecification (Specification specification) {
        this.product.setSpecification(specification);

        return this;
    }

    public Product getProduct() {
        return product;
    }
}
