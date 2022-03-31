import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Users;

public class UsersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Users user1 = new Users();
		user1.setFullName("Mohamed Shaker2");
		user1.setEmail("mshaker@test.test2");
		user1.setPassword("password1232");
		
		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("*******User1 Added");
	}

}
