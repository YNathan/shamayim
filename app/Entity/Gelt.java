package Entity;

/**
 * 
 * @author Yaacov
 *
 */
public class Gelt {
	private int nDebterID = -1;
	private int nAmount = -1;
	private int nEntitledID = -1;
	private int nGroupID = -1;



	public Gelt(int nDebterID, int nAmount, int nEntitledID,int nGroupID) {
		super();
		this.nDebterID = nDebterID;
		this.nAmount = nAmount;
		this.nEntitledID = nEntitledID;
		this.nGroupID = nGroupID;
	}

	public Gelt() {
	}

	public int getDebterID() {
		return nDebterID;
	}

	public int getAmount() {
		return this.nAmount;
	}

	public int getEntitledID() {
		return nEntitledID;
	}

	public void setnDebterID(int nDebterID) {
		this.nDebterID = nDebterID;
	}

	public void setnAmount(int nAmount) {
		this.nAmount = nAmount;
	}

	public void setnEntitledID(int nEntitledID) {
		this.nEntitledID = nEntitledID;
	}
	public int getGroupID() {
		return nGroupID;
	}

	public void setnGroupID(int nGroupID) {
		this.nGroupID = nGroupID;
	}

	@Override
	public String toString() {
		return "Gelt{" +
				"nDebterID=" + nDebterID +
				", nAmount=" + nAmount +
				", nEntitledID=" + nEntitledID +
				", nGroupID=" + nGroupID +
				'}';
	}

}
