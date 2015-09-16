package zaietsv.complextask.mvc.holder;

import java.util.ArrayList;

import zaietsv.complextask.mvc.instance.Instance;

public interface Holder<I extends Instance> {

	/**
	 * Returns Instance interface and its inherited classes instances list
	 * @return a list to return
	 */
	ArrayList<I> getList();
	
	/**
	 * Sets Instance interface and its inherited classes instances list
	 * @param list a list to be set
	 */
	void setList(ArrayList<I> list);
	
}
