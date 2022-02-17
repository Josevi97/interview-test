package interview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import interview.beans.ProductBean;
import interview.entities.ProductEntity;
import interview.exceptions.InvalidProductDataException;
import interview.exceptions.ProductAlreadyExistsException;
import interview.exceptions.ProductNotFoundException;
import interview.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<ProductEntity> getProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public ProductEntity getProductById(Long id) throws ProductNotFoundException {
        return this.productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public ProductEntity createProduct(ProductBean productBean)
            throws InvalidProductDataException, ProductAlreadyExistsException {

        if (productBean == null || !productBean.isValid()) {
            throw new InvalidProductDataException();
        }

        if (this.productRepository.existsByCode(productBean.getCode())) {
            throw new ProductAlreadyExistsException();
        }

        return this.productRepository.save(productBean.toEntity());
    }

    public ProductEntity updateProduct(Long id, ProductBean productBean)
            throws InvalidProductDataException, ProductNotFoundException, ProductAlreadyExistsException {

        if (productBean == null || !productBean.isValid()) {
            throw new InvalidProductDataException();
        }

        ProductEntity productEntity = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);

        if (this.productRepository
                .existsByCode(productBean.getCode()) && !productEntity.getCode().equals(productBean.getCode())) {
            throw new ProductAlreadyExistsException();
        }

        productEntity.setCode(productBean.getCode());
        productEntity.setName(productBean.getName());
        productEntity.setDescription(productBean.getDescription());
        productEntity.setPrice(productBean.getPrice());

        return this.productRepository.save(productEntity);
    }

    public void deleteProduct(Long id) throws ProductNotFoundException {
        if (!this.productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }

        this.productRepository.deleteById(id);
    }
}
