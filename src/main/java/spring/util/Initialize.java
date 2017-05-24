package spring.util;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import spring.model.Aidat;
import spring.model.Employee;
import spring.model.Role;
import spring.model.User;

/**
 * Created by sinan.ulgen on 10/05/2017.
 */

public class Initialize implements Serializable {

	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory;

	@PostConstruct
	public void init() throws IOException {
		if (check() == false) {
			System.out.println("STARTING PROJECT");
			create();
		} else {
			System.out.println("STARTING PROJECT");
		}

	}

	@SuppressWarnings({ "deprecation", "unused", "rawtypes", "unchecked" })
	public boolean check() {
		boolean exist = false;
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Employee> empList = new ArrayList<Employee>();
		try {
			String query = "from Employee where eMail=:eMail";
			List list = (List) session.createQuery(query).setParameter("eMail", "admin").list();
			empList.addAll(list);
			session.close();
			sessionFactory.close();
			if (empList.size() != 0) {
				exist = true;
			} else {
				exist = false;
			}

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return exist;
	}

	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public void create() throws IOException {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		Role role = new Role();
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Role> roleList = new ArrayList<Role>();
		try {
			tx = session.beginTransaction();

			String query = "from Role";
			List list = (List) session.createQuery(query).list();
			roleList.addAll(list);
			spring.model.Role role2 = new spring.model.Role();
			if (roleList.size() == 0) {
				spring.model.Role role1 = new spring.model.Role();
				role1.setId(1L);
				role1.setRole("Admin");
				session.save(role1);

				role2.setId(2L);
				role2.setRole("User");
				session.save(role2);

				role = role1;
			} else {
				role.setId(1L);
				role2.setId(2L);
			}

			Employee employee = new Employee();
			employee.setAd("Mustafa");
			employee.seteMail("admin");
			employee.setSoyad("E");
			employee.setTelNo("012362366");
			employee.setAdres("Ankara Cebeci");
			session.save(employee);

			Employee employee2 = new Employee();
			employee2.setAd("Ahmet");
			employee2.seteMail("user");
			employee2.setSoyad("Dal");
			employee2.setTelNo("321233212");
			employee2.setAdres("Karabük");
			session.save(employee2);

			User user = new User();
			user.setEmployee(employee);
			user.setPassword("1234qqqQ");
			user.setRole(role);
			session.save(user);

			User user2 = new User();
			user2.setEmployee(employee2);
			user2.setPassword("1234qqqQ");
			user2.setRole(role2);
			session.save(user2);

			Aidat aidat = new Aidat();
			aidat.setAy(5);
			aidat.setYil(2017);
			aidat.setFlag("F");
			aidat.setIslemTarih(new Date());
			aidat.setOdendi(false);
			aidat.setGoruldu(false);
			aidat.setMiktar(new BigDecimal(200));
			aidat.setMukellef(employee);
			session.save(aidat);

			Aidat aidat2 = new Aidat();
			aidat2.setAy(4);
			aidat2.setYil(2017);
			aidat2.setFlag("F");
			aidat2.setIslemTarih(new Date());
			aidat2.setOdendi(false);
			aidat2.setGoruldu(false);
			aidat2.setMiktar(new BigDecimal(150));
			aidat2.setMukellef(employee2);
			session.save(aidat2);

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void setSessionFactory(SessionFactory sessionFactory) {
		Initialize.sessionFactory = sessionFactory;
	}

}
