package com.easyaccounting.repository;

import com.easyaccounting.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = "SELECT * FROM payment ORDER BY to_date(month,'Month')",nativeQuery = true)
    List<Payment> findAllByYearOrderByMonth(String year);

    Payment findPaymentById(Long id);
}
