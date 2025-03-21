package com.jpa.test.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.test.entities.User;

public interface UserRepo extends CrudRepository<User, Integer>{
	
	//Derived query method
	//Custom finder method
	
	public List<User> findByName(String name);
	
	public List<User> findByCity(String city);
	
	public List<User> findByNameAndCity(String name, String city);
	
	public List<User> findByNameStartingWith(String prefix);

	public List<User> findByNameEndingWith(String suffix);
	
	public List<User> findByNameContaining(String word);

	public List<User> findByNameLike(String likePattern);

	public List<User> findByIdLessThan(int id);

	public List<User> findByIdGreaterThanEqual(int id);

	public List<User> findByIdIn(Collection<Integer> id);

	public List<User> findByNameOrderByName(String name);
	
	@Query("SELECT u FROM User u")
	public List<User> getAllUser();
	
	@Query("SELECT u FROM User u WHERE u.name =:n")
	public List<User> getUserByName(@Param("n") String name);
	
	@Query("SELECT u FROM User u WHERE u.name =:n and u.city =:c")
	public List<User> getUserByNameAndCity(@Param("n") String name, @Param("c") String city);
	
	//native query
	@Query(value = "select * from user", nativeQuery=true)
	public List<User> getUsers();
	
}
