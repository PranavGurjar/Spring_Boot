package com.jpa.test;


import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jpa.test.dao.UserRepo;
import com.jpa.test.entities.User;

@SpringBootApplication
public class JpaTestApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JpaTestApplication.class, args);
		
		UserRepo userrepo = context.getBean(UserRepo.class);
		
		User user = new User();
		//Create by details
//		user.setName("Pranav");
//		user.setCity("Jalgaon");
//		user.setStatus("I am java programmer.");
//		
////		User user1 = userrepo.save(user);
////		System.out.println("User save : "+user1);
//		
//		User user1 = new User();
//		user1.setName("Yash");
//		user1.setCity("Malegaon");
//		user1.setStatus("python programmer.");
//		
//		User user2 = new User();
//		user2.setName("Pranav");
//		user2.setCity("Kinhi");
//		user2.setStatus("php programmer.");
//		
//		List<User> users = List.of(user, user1, user2);
//		Iterable<User> result = userrepo.saveAll(users);
//		
//		
//		result.forEach(userobj ->{
//			System.out.println(userobj);
//		});
		
//		for(User u : result) {
//			System.out.println(u);
//		}
		
		
		//Update by id
//		Optional<User> optional = userrepo.findById(204);
//		User userId = optional.get();
//		userId.setName("Kunal M");
//		User r = userrepo.save(userId);
//		System.out.println(r);
		
		
		//Read/Display All data
//		Iterable<User> itr = userrepo.findAll();
//		itr.forEach(System.out::println);
		
		//Delete 
//		Optional<User> byId = userrepo.findById(254);
//		System.out.println(byId);
//		userrepo.deleteById(254);
//		System.out.println("deleted");
		
//		Iterable<User> all = userrepo.findAll();
//		all.forEach(alluser ->{
//			System.out.println(alluser);
//		});
//		userrepo.deleteAll();
//		System.out.println("Deleted all user.");
		
//		Custom method for repository
//		Custom finder method
		
//		List<User> byName = userrepo.findByName("Pranav");
//		byName.forEach(name -> System.out.println(name));
//		
//		List<User> byCity = userrepo.findByCity("Malegaon");
//		byCity.forEach(city -> System.out.println(city));
		
//		List<User> byNameAndCity = userrepo.findByNameAndCity("Pranav", "Jalgaon");
//		byNameAndCity.forEach(System.out::println);
		
//		List<User> sname = userrepo.findByNameStartingWith("ya");
//		sname.forEach(System.out::println);
//		
//		List<User> ename = userrepo.findByNameEndingWith("av");
//		ename.forEach(System.out::println);
//		
//		List<User> cname = userrepo.findByNameContaining("ran");
//		cname.forEach(System.out::println);
//		
//		List<User> nlname = userrepo.findByNameLike("%ran%");
//		nlname.forEach(System.out::println);
//
//		List<User> lname = userrepo.findByIdLessThan(253);
//		lname.forEach(System.out::println);
//
//		List<User> gname = userrepo.findByIdGreaterThanEqual(252);
//		gname.forEach(System.out::println);
//
//		List<User> iname = userrepo.findByIdIn(List.of(252, 253));
//		iname.forEach(System.out::println);
//
//		List<User> allUser = userrepo.getAllUser();
//		allUser.forEach(all -> System.out.println(all));
//		
//		List<User> userByName = userrepo.getUserByName("Yash");
//		userByName.forEach(e ->{
//			System.out.println(e);
//		});
//		
//		List<User> userByNameAndCity = userrepo.getUserByNameAndCity("Pranav", "Jalgaon");
//		userByNameAndCity.forEach(e ->{
//			System.out.println(e);
//		});
		
		List<User> users = userrepo.getUsers();
		users.forEach(e ->{
			System.out.println(e);
		});
		
	}

}
