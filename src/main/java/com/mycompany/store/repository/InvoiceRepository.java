package com.mycompany.store.repository;
import com.mycompany.store.domain.Invoice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Invoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

//	@Query("select * from invoice in cross join customer c cross join jhi_user u where po.customer_id = ?1")
	public Invoice findOneByIdAndOrderCustomerUserLogin(Long id, String login);
	
}
