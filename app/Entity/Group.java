package Entity;

/**
 * Created by User on 10/09/2016.
 */
public class Group {
    private String szGroupName;
    private int nGroupId;
    private int nOwnerId;

    public Group(String szGroupName, int nGroupId, int nOwnerId) {
        this.szGroupName = szGroupName;
        this.nGroupId = nGroupId;
        this.nOwnerId = nOwnerId;
    }

    public Group() {
        szGroupName = "null";
        nGroupId = -1;
        nOwnerId = -1;
    }

    public String getGroupName() {
        return szGroupName;
    }

    public void setGroupName(String szGroupName) {
        this.szGroupName = szGroupName;
    }

    public int getGroupId() {
        return nGroupId;
    }

    public void setGroupId(int nGroupId) {
        this.nGroupId = nGroupId;
    }

    public int getOwnerId() {
        return nOwnerId;
    }

    public void setOwnerId(int nOwnerId) {
        this.nOwnerId = nOwnerId;
    }
}
