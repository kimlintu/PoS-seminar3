package model;

/**
 * Represents an identification number.
 * @author kim
 *
 */
public class IdentificationNumber {
	private long number;
	
	/**
	 * Constructs an id and initializes its value to the 
	 * provided <code>number</code>.
	 * @param number The value that this <code>IdentificationNumber</code> will have.
	 */
    public IdentificationNumber(long number) {
    	this.number = number;
    }
    
    /**
     * Returns the number that the <code>IdentificationNumber</code> is representing.
     * @return The number as a <code>long</code> data type.
     */
    public long getID() {
    	return number;
    }
    
    /**
     * Compares the numbers of two ids. 
     * 
     * @param id One of the <code>IdentificationNumber</code> to compare.
     * @return Returns <code>true</code> if the numbers that the <code>IdentificationNumber</code> 
     * objects are representing are equal, <code>false</code> otherwise.
     */
    public boolean equals(IdentificationNumber id) {
    	if(id.getID() == this.getID()) return true;
    	
    	return false;
    }
    
}
