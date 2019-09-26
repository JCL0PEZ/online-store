package com.mycompany.store.repository;
import com.mycompany.store.domain.ProductOrder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

	@Query("select * from product_order po cross join customer c cross join jhi_user u where po.customer_id = ?1")
	public Page<ProductOrder> findAllByCustomerUserLogin(String string, Pageable pageable);

}
