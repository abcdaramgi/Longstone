package dohyung.membermanagement.repository;

import dohyung.membermanagement.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRespoitory {
    Member Save(Member member);

    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
