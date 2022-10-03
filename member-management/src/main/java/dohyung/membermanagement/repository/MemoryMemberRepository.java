package dohyung.membermanagement.repository;

import dohyung.membermanagement.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRespoitory{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member Save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));

    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //스트림 생성 -> 필터(조건에 부합하는 데이터 거르기) -> 찾기
        // return Optional.ofNullable(store.get(name));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
