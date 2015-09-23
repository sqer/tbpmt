package pl.sqer.dao.entity.priority;

import java.io.File;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.sqer.dao.priority.PriorityDAO;
import pl.sqer.dao.priority.PriorityDAOImpl;

public class PriorityTest {

	private static Configuration config;
	private static SessionFactory factory;
	private static Session hibernateSession;
	private final PriorityDAO priorityDAO = new PriorityDAOImpl(
			hibernateSession);

	@BeforeClass
	public static void init() {
		config = new AnnotationConfiguration();
		config.configure(new File("src/main/test/hibernate.cfg.xml"));
		factory = config.buildSessionFactory();
		hibernateSession = factory.openSession();
	}

	@Test
	public void testShouldPersist() {
		final int expCount = 1;
		final PriorityEntity priority = new PriorityEntity();
		priority.setName("test");
		priority.setDescription("description");
		hibernateSession.getTransaction().begin();
		priorityDAO.persist(priority);
		hibernateSession.getTransaction().commit();

		final int result = priorityDAO.countAll();
		TestCase.assertEquals(expCount, result);
		hibernateSession.close();
	}

}
