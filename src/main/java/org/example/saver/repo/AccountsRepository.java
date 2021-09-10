package org.example.saver.repo;

import org.example.saver.dto.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountsRepository extends JpaRepository<Account, UUID> {

    List<Account> findByShardName(String shardName);
}
