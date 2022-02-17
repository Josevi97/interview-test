package interview.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import interview.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    public boolean existsByCode(String code);
}
