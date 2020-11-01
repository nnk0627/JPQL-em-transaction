package com.mmit.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.mmit.entity.Category;
import com.mmit.entity.Item;

@TestMethodOrder(OrderAnnotation.class)
class EM_transaction_test {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf=Persistence.createEntityManagerFactory("jpa-em-transaction");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em=emf.createEntityManager();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
	}

	@Test
	@Order(1)
	void test() {
		em.getTransaction().begin();		
		//create item in object
		Item item=new Item();
		item.setName("Orange");
		item.setPrice(500);
		Category c=new Category();
		c.setName("Juice");
		//em.persist(c);
		item.setCategory(c);
		//persist
		em.persist(item);
		//commit
		em.getTransaction().commit();
	}
	@Test
	@Order(2)
	void test2() {
		em.getTransaction().begin();
		Item item=new Item();
		item.setName("Cola");
		item.setPrice(500);
		//Category c=em.find(Category.class, 1);
		Category c=new Category();
		c.setName("Snack");
		item.setCategory(c);
		em.persist(item);
		em.getTransaction().commit();
	}
	//@Test
	//@Order(3)
	void test1() {
		System.out.println("............................Test1.....................");
		Item item=em.find(Item.class, 1);	//Manage State
		System.out.println("Id:"+item.getId());
		//em.clear();      //Detach State (is not in the Manage state)
		System.out.println("Category Name:"+item.getCategory().getName());
	}
	//@Test
	//@Order(3)
	void testForUpdate() {
		System.out.println("....................Test For Update....................");
		em.getTransaction().begin();//price are updated because still in manage state and not need em.merge().if em.clear() write,will need em.merge()
		Item item=em.find(Item.class, 1);
		em.clear();    //Detach State
		item.setPrice(1000);
		em.merge(item);
		em.getTransaction().commit();
	}
	@Test
	@Order(3)
	void testForDelete() {
		System.out.println(".....................Test For Delete..................");
		em.getTransaction().begin();	//Really Insert,Delete,Update in database
		Item item=em.find(Item.class, 1);
		em.remove(item);
		em.getTransaction().commit();
		//Item item1=em.find(Item.class,1);
		//System.out.println("Item1 Name:"+item1.getName());
	}
}
