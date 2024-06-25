package com.yu.kickoff.repository;

import com.yu.kickoff.model.AddPitchRequest;
import com.yu.kickoff.model.UpgradeAccountRequest;
import com.yu.kickoff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddPitchRequestRepository extends JpaRepository<AddPitchRequest, Long> {
    long countByAuthor(User user);
    void deleteByAuthor(User user);
    List<UpgradeAccountRequest> findAllByAuthor(User user);
}
