package org.example.saver.repo;

import org.example.saver.dto.OffsetData;
import org.example.saver.dto.OffsetDataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffsetDataRepository extends JpaRepository<OffsetData, OffsetDataId> {
}
