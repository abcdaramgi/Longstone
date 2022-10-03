package dohyung.membermanagement.service;

import dohyung.membermanagement.domain.Member;
import dohyung.membermanagement.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memoryRepository.clearStore();
    }
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("one");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("first");

        Member member2 = new Member();
        member2.setName("first");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//람다를 했을 때일리걸스테이트익셉션이 터져야한다. null익셉션 넣으면 테스트 실패나옴
        Assertions.assertThat(e.getMessage()).isEqualTo(("이미 같은 이름이 존재합니다."));
/*      try{
            memberService.join(member2);
            fail();
        }
        catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 같은 이름이 존재합니다.");
        }
*/

        //then


    }
//테스트는 한글로 적어도됨, 직관적
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}