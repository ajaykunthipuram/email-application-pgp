package in.ajay;

//import javax.security.auth.login.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

		public static SessionFactory sessionFactoty(){
			SessionFactory sessionFactory = null;
			if(sessionFactory == null){
				sessionFactory = new Configuration().configure().buildSessionFactory();
				System.out.println("builded session factory");
			}
			return sessionFactory;
		}
}
