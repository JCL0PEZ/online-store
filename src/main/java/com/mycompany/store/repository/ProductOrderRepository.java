package com.mycompany.store.repository;
import com.mycompany.store.domain.ProductOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the ProductOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query(
        value = "select * from product_order po cross join customer c cross join jhi_user u where po.customer_id = :id",
        nativeQuery = true)
	public Page<ProductOrder> findAllByCustomerUserLogin(@Param("id") String customerId, Pageable pageable);

    @Query(
        value = "select * from product_order po cross join customer c cross join jhi_user u " +
            "where po.id = :productId and u.login = :login",
        nativeQuery = true)
    public Optional<ProductOrder> findOneByIdAndCustomerUserLogin(@Param("productId") Long productId, @Param("login") String login);
}
