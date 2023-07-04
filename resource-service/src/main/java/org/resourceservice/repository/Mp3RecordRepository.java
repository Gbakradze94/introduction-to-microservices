package org.resourceservice.repository;

import org.resourceservice.domain.Mp3Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mp3RecordRepository extends JpaRepository<Mp3Record, Integer> {
}
