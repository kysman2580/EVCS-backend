package com.example.evcs.notice.service;

import com.example.evcs.notice.model.Notice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoticeService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Notice> getAll() {
        return entityManager.createQuery("SELECT n FROM Notice n", Notice.class)
                .getResultList();
    }

    public Optional<Notice> getById(String id) {
        Notice notice = entityManager.find(Notice.class, id);
        return Optional.ofNullable(notice);
    }
}
