
package com.example.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

    boolean existsByBook_Id(int bookId);

    List<Issue> findByMember_Id(int memberId);

    List<Issue> findByBook_Id(int bookId);

    List<Issue> findByStatus(String status);

    boolean existsByMember_IdAndStatusIgnoreCase(
            int memberId, String status);
}

