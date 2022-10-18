package dohyung.membermanagement.service;

import dohyung.membermanagement.domain.Member;
import dohyung.membermanagement.repository.MemberRespoitory;
import dohyung.membermanagement.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRespoitory memberRespoitory;

    public MemberService(MemberRespoitory memberRespoitory) {
        this.memberRespoitory = memberRespoitory;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        //같은 이름의 중복회원은 안된다.
        validateDuplicateMember(member);//중복 회원 검증
        memberRespoitory.Save(member);
        return member.getId();

    }
    //중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRespoitory.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 같은 이름이 존재합니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRespoitory.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRespoitory.findById(memberId);
    }
}
