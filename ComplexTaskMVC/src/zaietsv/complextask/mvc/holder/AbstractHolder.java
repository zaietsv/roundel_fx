package zaietsv.complextask.mvc.holder;

import java.util.ArrayList;

import zaietsv.complextask.mvc.instance.AbstractInstance;

public abstract class AbstractHolder<I extends AbstractInstance> implements Holder<I> {

	/**
	 * a list of AbstractInstance class and its inherits
	 */
	protected ArrayList<I> list;
	
	/**
	 * Constructs an empty AbstractHolder instance
	 */
	public AbstractHolder() {
		
	}
	
	/**
	 * Constructs an AbstractHolder instance using input parameters
	 * @param list - an AbstractHolder content list.
	 */
	public AbstractHolder(ArrayList<I> list) {
		this.list = list;
	}

	/**
	 * @return the list
	 */
	public ArrayList<I> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<I> list) {
		this.list = list;
	}
	
	

}
