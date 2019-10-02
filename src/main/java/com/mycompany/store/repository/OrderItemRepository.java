package com.mycompany.store.repository;
import com.mycompany.store.domain.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the OrderItem entity.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(
        value = "select * from order_item cross join product_order po cross join customer c cross join jhi_user u where u.login = :login",
        nativeQuery = true)
    public Page<OrderItem> findAllByOrderCustomerUserLogin(@Param("login") String login, Pageable pageable);

    @Query(
        value = "select * from order_item cross join product_order po cross join customer c cross join jhi_user u " +
            "where order_item = :id and u.login = :login",
        nativeQuery = true)
    public Optional<OrderItem> findOneByIdAndOrderCustomerUserLogin(@Param("id") Long id, @Param("login") String login);

}
