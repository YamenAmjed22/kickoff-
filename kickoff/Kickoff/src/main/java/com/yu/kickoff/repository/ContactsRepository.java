package com.yu.kickoff.repository;

import com.yu.kickoff.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactUs,Long> {
}
