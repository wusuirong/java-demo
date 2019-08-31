package com.springinaction.training.dao.hibernate;

import java.util.Set;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.springframework.orm.hibernate.HibernateCallback;
import org.springframework.orm.hibernate.HibernateTemplate;
import org.springframework.orm.hibernate.support.HibernateDaoSupport;

import com.springinaction.training.dao.StudentDao;
import com.springinaction.training.model.Student;


public class StudentDaoHibernate extends HibernateDaoSupport 
    implements StudentDao {
  
  public Student findById(final String id) {
    HibernateTemplate hibernateTemplate =
    	  new HibernateTemplate(this.sessionFactory);

    return (Student) hibernateTemplate.execute(
        new HibernateCallback() {
          public Object doInHibernate(Session session)
              throws HibernateException {
        	  return session.load(Student.class, id);
          }
        });

//    return (Student) getHibernateTemplate().load(Student.class, id);
  }

  public void create(Student student) {
  	getHibernateTemplate().saveOrUpdate(student);
  }
  
  public Set getCompletedCourses(Student student) {
    throw new RuntimeException("Method not implemented yet.");
  }
  
  private SessionFactory sessionFactory;

}