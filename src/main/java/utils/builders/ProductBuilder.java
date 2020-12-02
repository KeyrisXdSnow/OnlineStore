package utils.builders;


import model.beans.Product;
import model.entities.Specification;

public class ProductBuilder {

    private Product product;

    public ProductBuilder() {
        product = new Product();
    }

    public ProductBuilder withId (long id) {
        this.product.setId(id);

        return this;
    }

    public ProductBuilder withName (String name) {
        this.product.setName(name);

        return this;
    }

    public ProductBuilder withCost (double cost) {
        this.product.setCost(cost);

        return this;
    }

    public ProductBuilder withSpecification (Specification specification) {
        this.product.setSpecification(specification);

        return this;
    }

    public ProductBuilder withinStore (boolean inStore) {
        this.product.setInStore(inStore);

        return this;
    }

    public Product getProduct() {
        return product;
    }
}
