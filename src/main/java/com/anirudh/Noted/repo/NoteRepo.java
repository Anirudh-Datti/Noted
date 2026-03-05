package com.anirudh.Noted.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anirudh.Noted.model.Notes;
import com.anirudh.Noted.model.User;


@Repository
public interface NoteRepo extends JpaRepository<Notes,Long> {

    List<Notes> findAllByUser(User user);

    Optional<Notes> findBynoteIdAndUser(Long noteId, User user);

    List<Notes> findAllByUserAndDeletedTrue(User user);

    List<Notes> findAllByUserAndDeletedFalse(User user);

    @Query("""
    SELECT n FROM Notes n
    WHERE n.user = :user
    AND n.deleted = false
    AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
    OR LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%')))
    """)
    List<Notes> searchNotes(User user, String keyword);




}