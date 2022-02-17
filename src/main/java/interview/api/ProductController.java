package interview.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import interview.beans.ProductBean;
import interview.entities.ProductEntity;
import interview.exceptions.ApiException;
import interview.exceptions.ProductNotFoundException;
import interview.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<?> getPage(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<Page<ProductEntity>>(
                this.productService.getProducts(pageable),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return new ResponseEntity<ProductEntity>(this.productService.getProductById(id), HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<Long>(-1L, e.getCode());
        }
    }

    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody ProductBean productBean) {
        try {
            return new ResponseEntity<ProductEntity>(this.productService.createProduct(productBean), HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<Long>(-1L, e.getCode());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody ProductBean productBean) {
        try {
            return new ResponseEntity<ProductEntity>(this.productService.updateProduct(id, productBean), HttpStatus.OK);
        } catch (ApiException e) {
            return new ResponseEntity<Long>(-1L, e.getCode());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        try {
            this.productService.deleteProduct(id);
            return new ResponseEntity<Long>(1L, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<Long>(-1L, e.getCode());
        }
    }
}
