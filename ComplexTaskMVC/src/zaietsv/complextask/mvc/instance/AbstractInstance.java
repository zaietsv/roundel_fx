package zaietsv.complextask.mvc.instance;

public abstract class AbstractInstance implements Instance {
	
	/**
	 * an AbstractInstance numeric identifier.
	 */
	protected long id;
	
	/**
	 * Constructs an empty abstract instance
	 */
	public AbstractInstance() {

	}
	
	/**
	 * Constructs an abstract instance using input parameters
	 * @param id - an AbstractInstance numeric identifier.
	 */
	public AbstractInstance(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.instance.Instance#getId()
	 */
	public long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see zaietsv.complextask.mvc.instance.Instance#setId(long)
	 */
	public void setId(long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractInstance [id=" + id + "]";
	}
}
