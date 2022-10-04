package dohyung.membermanagement.repository;

import dohyung.membermanagement.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("one");

        repository.Save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("member1");
        repository.Save(member1);

        Member member2 = new Member();
        member2.setName("member2");
        repository.Save(member2);

        Member result1 = repository.findByName("member2").get();
        assertThat(result1).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member3 = new Member();
        member3.setName("good");
        repository.Save(member3);

        Member member4 = new Member();
        member4.setName("not");
        repository.Save(member4);

        Member member5 = new Member();
        member5.setName("bad");
        repository.Save(member5);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }
}

