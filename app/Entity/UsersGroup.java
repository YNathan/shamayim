package Entity;

import java.util.ArrayList;

/**
 * Created by User on 11/09/2016.
 */
public class UsersGroup {
    private Group group;
    private ArrayList<Integer> lstUsersId;

    public UsersGroup() {
        lstUsersId = new ArrayList<>();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ArrayList<Integer> getLstUsersId() {
        return lstUsersId;
    }

    public void setLstUsersId(ArrayList<Integer> lstUsersId) {
        this.lstUsersId = lstUsersId;
    }
}
