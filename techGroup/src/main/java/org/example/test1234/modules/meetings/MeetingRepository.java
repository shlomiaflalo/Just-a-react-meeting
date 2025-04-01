package org.example.test1234.modules.meetings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
    @Transactional(readOnly = true)
    List<Meeting> findAllByTechGroup_Id(int techGroupId);
}
