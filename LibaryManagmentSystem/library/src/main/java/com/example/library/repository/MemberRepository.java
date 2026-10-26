
package com.example.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    List<Member> findByDeletedFalse();
}

