package com.yu.kickoff.repository;

import com.yu.kickoff.model.RelationCk;
import com.yu.kickoff.model.RelationEnum;
import com.yu.kickoff.model.Relations;
import com.yu.kickoff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationsRepository extends JpaRepository<Relations, RelationCk> {
    @Query("SELECT r.type FROM Relations r WHERE r.sourceUserName = :sourceUser AND r.targetUserName = :targetUser")
    RelationEnum findRelationTypeByUsers(@Param("sourceUser") User sourceUser, @Param("targetUser") User targetUser);
}
