package com.yu.kickoff.service;

import com.yu.kickoff.model.RelationEnum;
import com.yu.kickoff.model.User;
import com.yu.kickoff.repository.RelationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationsService {

    private final RelationsRepository relationsRepository;

    @Autowired
    public RelationsService(RelationsRepository relationsRepository) {
        this.relationsRepository = relationsRepository;
    }

    public RelationEnum getRelationType(User sourceUser, User targetUser) {
        RelationEnum relationType = relationsRepository.findRelationTypeByUsers(sourceUser, targetUser);
        return relationType != null ? relationType : null;
    }
}
