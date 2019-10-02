package com.mycompany.store.repository;
import com.mycompany.store.domain.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Shipment entity.
 */
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query(
        value = "select * from shipment s cross join invoice i cross join shipment s cross join customer c cross join jhi_user u where u.login = :login",
        nativeQuery = true)
    public Page<Shipment> findAllByInvoiceOrderCustomerUserLogin(@Param("login") String login, Pageable pageable);


    @Query(
        value = "select * from shipment s cross join invoice i cross join shipment s cross join customer c cross join jhi_user u " +
            "where s.id = :id and u.login = :login",
        nativeQuery = true)
    public Optional<Shipment> findOneByIdAndInvoiceOrderCustomerUserLogin(@Param("id") Long id, @Param("login") String login);


}
