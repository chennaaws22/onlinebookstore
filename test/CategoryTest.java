import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.Category;
import com.bookstore.entity.Users;

public class CategoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Category cat1 = new Category();
		cat1.setName("programming");
		
		entityManager.getTransaction().begin();
		entityManager.persist(cat1);
		
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("*******cat1 Added");
	}

}
