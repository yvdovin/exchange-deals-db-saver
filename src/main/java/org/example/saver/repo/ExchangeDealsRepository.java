package org.example.saver.repo;

import org.example.saver.dto.ExchangeDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeDealsRepository extends JpaRepository<ExchangeDeal, Long> {

}
