package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    @Modifying
    @Query("UPDATE Task t SET t.done = TRUE WHERE t.id =:id")
    void markAsDone(@Param("id") long id);

    @Query("SELECT t FROM Task t WHERE t.user_id =:user_id")
    Iterable<Task> findAllByUser_id(@Param("user_id") long id);

    @Query("SELECT t FROM Task t WHERE t.id =: id AND t.user_id =: user_id")
    Task findByIdAndUser_id(@Param("id") long id, @Param("user_id") long user_id);

    @Modifying
    @Query("UPDATE Task t SET t.done = FALSE WHERE t.id =:id")
    void markAsUndone(@Param("id") long id);
}
