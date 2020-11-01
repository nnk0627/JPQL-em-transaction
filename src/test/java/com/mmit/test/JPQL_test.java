package com.mmit.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mmit.entity.Item;

class JPQL_test {
	
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

	//@Test
	void testForDynamicQry() {
		//Dynamic Query->createQuery //select * from item
		
		TypedQuery<Item> query=em.createQuery("SELECT i FROM Item i", Item.class);
		//Query  query=em.createQuery("SELECT i FROM Item i", Item.class);
		//Item item=query.getSingleResult();
		//System.out.println("Name:"+item.getName());
		//retrieve data
		List<Item> itemList=query.getResultList();
		System.out.println("Size: "+itemList.size());
	}
	//@Test
	void testForStatic() {
		//Static Query->createNamedQuery
		TypedQuery<Item> query=em.createNamedQuery("Item.getItems", Item.class);
		List<Item> itemList=query.getResultList();
		System.out.println("Size: "+itemList.size());
	}
	//@Test
	void testForUpdate() {
		em.getTransaction().begin();
		Query query=em.createQuery("UPDATE Item i SET i.price=13500 WHERE i.id=4 AND i.category.id=2");
		query.executeUpdate();
		em.getTransaction().commit();
	}
	@Test
	void testForDelete() {
		em.getTransaction().begin();
		Query query=em.createQuery("DELETE FROM Item i WHERE i.category.id=2");
		query.executeUpdate();
		em.getTransaction().commit();

	}
}
