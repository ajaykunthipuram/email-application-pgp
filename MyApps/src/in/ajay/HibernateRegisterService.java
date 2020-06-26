package in.ajay;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateRegisterService {
	public static void registerUser(User user){
		SessionFactory sessionFactory = HibernateUtil.sessionFactoty();
		
		System.out.println("sessioned");
		//get the connection
		Session session = sessionFactory.openSession();
		System.out.println("connected");
		Transaction txn = session.beginTransaction();
		System.out.println("transferred");
	
		session.save(user);
		System.out.println("saved");
		txn.commit();
		System.out.println("saved");
		session.close();
		System.out.println("closed");
		
	
	
	
	
	}
}
