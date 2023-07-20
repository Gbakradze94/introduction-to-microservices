package org.resourceservice.repository;

import org.resourceservice.domain.SongRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRecordRepository extends JpaRepository<SongRecord, Integer> {
}
