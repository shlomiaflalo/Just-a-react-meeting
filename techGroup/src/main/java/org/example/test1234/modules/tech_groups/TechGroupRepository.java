package org.example.test1234.modules.tech_groups;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechGroupRepository extends JpaRepository<TechGroup, Integer> {
}
