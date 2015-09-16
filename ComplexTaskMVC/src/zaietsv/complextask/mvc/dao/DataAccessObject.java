package zaietsv.complextask.mvc.dao;

import java.util.ArrayList;

import zaietsv.complextask.mvc.instance.Instance;

/**
 * An interface describes access to a database
 * @author Voww
 *
 * @param <I> a class which implements Instance interface
 */
public interface DataAccessObject<I extends Instance> {
	
	/**
	 * Inserts a new record into a database
	 * @param instance - an instance of a class which implements Instance interface
	 * @return
	 */
	long insert(I instance);
	
	//boolean insert(ArrayList<Instance> instances);
	
	/**
	 * Updates an existing record in a database
	 * @param instance  - an instance of a class which implements Instance interface
	 * @return a quantity of affected rows (1 on success 0 on fault)
	 */
	int update(I instance);
	
	/**
	 * Deletes an existing record from a database
	 * @param id - the record's key identifier
	 * @return true on success false otherwise
	 */
	boolean delete(long id);
	
	//boolean exists(long id);
	
	//boolean exists(Instance instance);
	
	/**
	 * Reads an existing record from a database
	 * @param id - the record's key identifier
	 * @return an instance of a class which implements Instance interface filled with parameters being red
	 */
	I read(long id);
	
	/**
	 * Reads all of the existing records from a table or a schema
	 * @return a list of instances of a class which implements Instance interface filled with parameters being red
	 */
	ArrayList<I> readAll();

}
