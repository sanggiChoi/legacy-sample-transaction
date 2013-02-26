package sample.member;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sample.domain.Member;

//@Transactional
@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberDao dao;

	@Transactional(readOnly=true)//, propagation=Propagation.REQUIRES_NEW)	//propagation=Propagation.SUPPORTS, )	//, rollbackFor={SQLException.class}, propagation = Propagation.REQUIRED, isolation=Isolation.REPEATABLE_READ)
	public void add(Member member) {
		member.setJoined(new Date());
		dao.add(member);
	}

	//@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=true)
	public void delete(int id) {
		dao.delete(id);
	}

	//@Transactional(readOnly=true)
	public Member get(int id) {
		return dao.get(id);
	}

	@Transactional(readOnly=true)
	public List<Member> list() {
		return dao.list();
	}

	//@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=true)
	public void update(Member member) {
		dao.update(member);
	}
	
	//@Transactional(readOnly=true)
	public List<Member> listByName(String name){
		return dao.listByName(name);
}
}
