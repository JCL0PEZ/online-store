package com.mycompany.store.repository;

import com.mycompany.store.domain.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Invoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query(
        value = "select * from invoice i cross join shipment s cross join customer c cross join jhi_user u where u.login = :login",
        nativeQuery = true)
    public Page<Invoice> findAllByOrderCustomerUserLogin(@Param("login") String login, Pageable pageable);

    @Query(
        value = "select * from invoice i cross join shipment s cross join customer c cross join jhi_user u " +
            "where i = :id and u.login = :login",
        nativeQuery = true)
    public Optional<Invoice> findOneByIdAndOrderCustomerUserLogin(@Param("id") Long id, @Param("login") String login);

	
}
