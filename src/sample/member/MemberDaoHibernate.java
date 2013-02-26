package sample.member;

import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import sample.domain.Member;

@Repository
public class MemberDaoHibernate implements MemberDao{

	//@Autowired SessionFactory sessionFactory;
	@Autowired HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	/*
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	*/

	public void add(Member member) {
		//getSession().save(member);
		member.setId(new Random().nextInt());
		hibernateTemplate.save(member);
	}

	public void delete(int id) {
		Member member = get(id);
		hibernateTemplate.delete(member);
		/*
		getSession().createQuery("delete from Member where id = ?")
			.setInteger(0, id)
			.executeUpdate();
		*/
	}

	public Member get(int id) {
		return hibernateTemplate.get(Member.class, id);
		/*
		return (Member) getCriteria()
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		*/
	 }

	@SuppressWarnings("unchecked")
	public List<Member> list() {
		return hibernateTemplate.find("from Member");
		//return getCriteria().list();
	}

	public void update(Member member) {
		//getSession().update(member);
		hibernateTemplate.update(member);
	}

	//private Criteria getCriteria() {
	//	return getSession().createCriteria(Member.class);
	//}

	@SuppressWarnings("unchecked")
	public List<Member> listByName(String name){
		return hibernateTemplate.find("from member order by id desc");
		//return getCriteria()
		//		.add(Restrictions.like("name", name))
		//		.list();
	}

	//private Session getSession() {
	//	return sessionFactory.getCurrentSession();
	//}
}
