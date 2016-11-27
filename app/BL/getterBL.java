package BL;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

import javax.mail.MessagingException;


import DB.getterDB;
import Entity.Gelt;
import Entity.Group;
import Entity.House;
import Entity.User;
import play.Logger;
import play.mvc.BodyParser;

import static play.mvc.Http.Context.Implicit.request;

/**
 * Will do all the logic of the data who asked from the server
 *
 * @author Yaacov
 */
public class getterBL {
    getterDB getterDB = new getterDB();

    /**
     * @param szUserName - the user-name that the user send
     * @param szPassword - the password that the user send
     * @return true is the user-name and the password is correct
     */
    public boolean isLoginPermited(String szUserName, String szPassword) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is login permited");

        ArrayList<User> userList = getterDB.getUsers();
        boolean isGreateLogin = false;
        for (User currUser : userList) {
            if (currUser.getUserName().equals(szUserName) && currUser.getPassword().equals(szPassword)) {
                isGreateLogin = true;
                /*mailBL mail = new mailBL();
                try {
                    mail.sendLoginSuccess(szUserName, request().remoteAddress());
                } catch (MessagingException e) {
                    Logger.error(e.getMessage());
                    e.printStackTrace();
                }*/
            }
        }
        return isGreateLogin;
    }

    /**
     * Get all the debts from the data-base
     *
     * @return an array-list contain all debts
     */
    public ArrayList<Gelt> getGelts() {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get gelts");

        return getterDB.getGelts();
    }

    /**
     * Get all the debts from the data-base
     *
     * @return an array-list contain all debts
     */
    public ArrayList<Gelt> getGroupGelts(String szGroupId) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get gelts of group id :" + szGroupId);

        return getterDB.getGelts(szGroupId);
    }

    /**
     * @param szUserName - the name of the user who ask
     * @return an array that will contain the debts that concern the user
     */
    public ArrayList<Gelt> getGeltsByName(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get gelt by name");

        int nUserId = getIdByName(szUserName);
        ArrayList<Gelt> getls = getterDB.getGelts();
        ArrayList<Gelt> geltConcern = new ArrayList<>();
        for (Gelt currGelts : getls) {
            if (currGelts.getDebterID() == nUserId || currGelts.getEntitledID() == nUserId) {
                geltConcern.add(currGelts);
            }
        }
        return geltConcern;
    }

    /**
     * Get all debts that concern the user if he is a debter are user
     *
     * @param szUserName - the name of the user who ask
     * @return an array that will contain the debts that concern the user
     */
    public StringBuilder getGeltsByNameForOutput(String szUserName) {
        // This array will contain all the debts that concern the user
        ArrayList<Gelt> getlsConcerne = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int nUserId = getIdByName(szUserName);
        // Get all debts from the data-base
        ArrayList<Gelt> getls = getterDB.getGelts();
        // Looping over all debts from the data base and checking if there is
        // concerned debts
        for (Gelt currGelts : getls) {
            // check if the debter or the entitled is this user
            if ((currGelts.getDebterID() == nUserId) || (currGelts.getEntitledID() == nUserId)) {
                // Put the concerned debts in an array that contain the
                // concerned debts
                getlsConcerne.add(currGelts);
                // Print for the fun to the screen
                play.Logger.info("<BUSINESS_LOGIC> Get debt " + getNameById(currGelts.getDebterID()) + " : "
                        + currGelts.getAmount() + " : " + getNameById(currGelts.getEntitledID()));
            }
        }
        stringBuilder.append("{ \"debts\":[");

        Iterator<Gelt> curr = getlsConcerne.iterator();

        Gelt currentGelt = null;
        if (curr.hasNext()) {
            currentGelt = curr.next();
        }
        while (currentGelt != null) {
            if (currentGelt.getDebterID() == nUserId || currentGelt.getEntitledID() == nUserId) {
                stringBuilder.append(" {\"Debter\":\"" + getNameById(currentGelt.getDebterID()) + "\",");
                stringBuilder.append("\"Amount\":\"" + currentGelt.getAmount() + "\",");
                stringBuilder.append("\"Entitled\":\"" + getNameById(currentGelt.getEntitledID()) + "\"}");

                if (curr.hasNext()) {
                    stringBuilder.append(",");
                    currentGelt = curr.next();
                } else {
                    currentGelt = null;
                }
            }

        }

        stringBuilder.append(" ]}");
        return stringBuilder;
    }


    /**
     * Get all debts that concern the user and a group if he is a debter are user
     *
     * @param szUserName - the name of the user who ask
     * @return an array that will contain the debts that concern the user
     */
    public StringBuilder getGeltsByNameAndGroupsForOutput(String szUserName, String szGroupName) {
        // This array will contain all the debts that concern the user
        ArrayList<Gelt> getlsConcerne = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int nUserId = getIdByName(szUserName);
        // Get all debts from the data-base
        ArrayList<Gelt> getls = getterDB.getGelts(szGroupName);
        // Looping over all debts from the data base and checking if there is
        // concerned debts
        for (Gelt currGelts : getls) {
            // check if the debter or the entitled is this user
            if ((currGelts.getDebterID() == nUserId) || (currGelts.getEntitledID() == nUserId)) {
                // Put the concerned debts in an array that contain the
                // concerned debts
                getlsConcerne.add(currGelts);
                // Print for the fun to the screen
                play.Logger.info("<BUSINESS_LOGIC> Get debt " + getNameById(currGelts.getDebterID()) + " : "
                        + currGelts.getAmount() + " : " + getNameById(currGelts.getEntitledID()));
            }
        }
        stringBuilder.append("{ \"debts\":[");

        Iterator<Gelt> curr = getlsConcerne.iterator();

        Gelt currentGelt = null;
        if (curr.hasNext()) {
            currentGelt = curr.next();
        }
        while (currentGelt != null) {
            if (currentGelt.getDebterID() == nUserId || currentGelt.getEntitledID() == nUserId) {
                stringBuilder.append(" {\"Debter\":\"" + getNameById(currentGelt.getDebterID()) + "\",");
                stringBuilder.append("\"Amount\":\"" + currentGelt.getAmount() + "\",");
                stringBuilder.append("\"Entitled\":\"" + getNameById(currentGelt.getEntitledID()) + "\"}");

                if (curr.hasNext()) {
                    stringBuilder.append(",");
                    currentGelt = curr.next();
                } else {
                    currentGelt = null;
                }
            }

        }

        stringBuilder.append(" ]}");
        return stringBuilder;
    }

    /**
     * @param szUserName - the user-name that will found for hem the id
     * @return the id of the user in the system
     */
    public int getIdByName(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get Id by name");

        int nUserId = -1;
        ArrayList<User> userLst = getterDB.getUsers();
        for (User currUser : userLst) {
            if (currUser.getUserName().equals(szUserName)) {
                nUserId = Integer.parseInt(currUser.getUserId());
            }
        }
        return nUserId;
    }

    /**
     * @param nUserId - the user-id that will found for hem the user-name
     * @return the name of the user in the system
     */
    public String getNameById(int nUserId) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get Name by Id");

        String szUserName = "";
        ArrayList<User> userLst = getterDB.getUsers();
        for (User currUser : userLst) {
            if (Integer.parseInt(currUser.getUserId()) == nUserId) {
                szUserName = currUser.getUserName();
            }
        }
        return szUserName;
    }

    /**
     * @param szUserName - the userName that we want to check if still exist
     * @return true if there still a user name like this in the system
     */
    public boolean isUserNameExist(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is user_name exist");

        boolean isUserNameAlreadyExist = false;
        ArrayList<String> usersName = getterDB.getUserNames();
        for (String currName : usersName) {
            if (currName.equals(szUserName)) {
                isUserNameAlreadyExist = true;
            }
        }
        return isUserNameAlreadyExist;
    }

    /**
     * @param szEmail - the email that we want to check if still exist
     * @return true if there still a email like this in the system
     */
    public boolean isEmailAlreadyExist(String szEmail) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is email already exist");

        boolean isEmailAlreadyExist = false;
        ArrayList<String> emails = getterDB.getEmails();
        for (String currEmail : emails) {
            if (currEmail.equals(szEmail)) {
                isEmailAlreadyExist = true;
            }
        }
        return isEmailAlreadyExist;
    }

    public ArrayList<String> getUsers(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get users");

        ArrayList<String> usersName = getterDB.getUserNames();
        usersName.remove(szUserName);
        return usersName;
    }

    public ArrayList<String> getUserNameOfGroups(String szUserName, String szGroupName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get users name of group id : " + szGroupName);
        ArrayList<String> usersName = getterDB.getUserNamesOfGroup(szGroupName);
        usersName.remove(szUserName);
        return usersName;
    }

    public Date getDateByString(String szDate) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get date by string");

        String szYear = szDate.substring(0, 4);
        int nYear = Integer.parseInt(szYear);
        nYear -= 1900;
        String szMonth = szDate.substring(5, 7);
        int nMonth = Integer.parseInt(szMonth);
        nMonth -= 1;
        String szDay = szDate.substring(8);
        int nDay = Integer.parseInt(szDay);
        return new Date(nYear, nMonth, nDay);
    }

    /**
     * Check if there is debts concerning to this person Will return in a string
     * the debt and will delete from the data base the temp debts
     *
     * @param szUserName - the user that suppose to be the debter
     * @return a string with the amount and the entitled
     */
    public StringBuilder checkIfUserIsDebter(String szUserName) {

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get user if is a debter");
        StringBuilder sbGeltToReturn = new StringBuilder();
        ArrayList<Gelt> tempGelts = getterDB.getTempGelts();
        Iterator<Gelt> itterGelt = tempGelts.iterator();

        boolean bWasFound = false;
        while ((itterGelt.hasNext()) && (!bWasFound)) {
            Gelt currGelt = itterGelt.next();
            if (currGelt.getDebterID() == getIdByName(szUserName)) {
                sbGeltToReturn.append("{ \"currDebt\":[ {\"Amount\":\"" + currGelt.getAmount() + "\",\"Entitled\":\""
                        + getNameById(currGelt.getEntitledID()) + "\",\"Group\":\"" + getterDB.getGroup(currGelt.getGroupID()).getGroupName() + "\"} ]}");

                bWasFound = true;
            }
        }
        return sbGeltToReturn;
    }

    /***
     *
     * @param szUserName the name of the user
     * @return all personal information about a user
     */
    public StringBuilder getUserInformation(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get user information with user name : " + szUserName);
        int nUserId = getterDB.getUserIdByName(szUserName);
        StringBuilder sbUserInformationToReturn = new StringBuilder();
        User userToReturn = getterDB.getUser(nUserId);
        sbUserInformationToReturn.append("{ \"user\":[ {\"user_name\":\"" + userToReturn.getUserName() + "\",\"first_name\":\"" + userToReturn.getfirstName() + "\",\"last_name\":\"" + userToReturn.getLastName() + "\",\"email\":\"" + userToReturn.getEmail() + "\",\"telephone\":\"" + userToReturn.getTelephone() + "\",\"password\":\"" + userToReturn.getPassword() + "\",\"birthdate\":\"" + userToReturn.getBirthday() + "\",\"user_id\":\"" + userToReturn.getUserId() + "\"} ]}");
        return sbUserInformationToReturn;
    }

    /***
     *
     * @param szUserName the name of the owner
     * @return all personal information about a user
     */
    public StringBuilder getOwnerGroupInformation(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get user information with user name : " + szUserName);
        int nUserId = getterDB.getUserIdByName(szUserName);
        StringBuilder sbUserInformationToReturn = new StringBuilder();
        User userToReturn = getterDB.getUser(nUserId);
        sbUserInformationToReturn.append("{ \"user\":[ {\"user_name\":\"" + userToReturn.getUserName() + "\",\"email\":\"" + userToReturn.getEmail() + "\",\"telephone\":\"" + userToReturn.getTelephone() + "\"} ]}");
        return sbUserInformationToReturn;
    }

    public StringBuilder getGroupsUser(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get the groups information for the user name : " + szUserName);
        int nUserId = getterDB.getUserIdByName(szUserName);
        StringBuilder sbUserInformationToReturn = new StringBuilder();
        ArrayList<Group> lstGroupsToReturn = getterDB.getGroupsUser(nUserId);
        sbUserInformationToReturn.append("{ \"groups\":[");

        Iterator<Group> groupItr = lstGroupsToReturn.iterator();
        Group currGroup = null;
        if (groupItr.hasNext()) {
            currGroup = groupItr.next();
        }
        while (currGroup != null) {
            sbUserInformationToReturn.append(" {\"group_name\":\"" + currGroup.getGroupName() + "\",");
            sbUserInformationToReturn.append("\"group_owner_name\":\"" + getterDB.getUserNameById(currGroup.getOwnerId()) + "\"}");

            if (groupItr.hasNext()) {
                sbUserInformationToReturn.append(",");
                currGroup = groupItr.next();
            } else {
                currGroup = null;
            }

        }
        sbUserInformationToReturn.append(" ]}");
        return sbUserInformationToReturn;
    }

    /**
     * @param szUserName - the user name of the a user
     * @return a list of the groups name concerning the user
     */
    public ArrayList<String> getUserGroupsName(String szUserName) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get the groups name for the user name : " + szUserName);

        ArrayList<String> lstGroupsNameToReturn = getterDB.getUserGroupsName(szUserName);

        return lstGroupsNameToReturn;
    }

    public StringBuilder getListOfHouse() {

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get list of house");
        ArrayList<House> lstHouses = getterDB.getListOfHouse();

        StringBuilder sbHousesInformationToReturn = new StringBuilder();
        sbHousesInformationToReturn.append("{ \"houses\":[");

        Iterator<House> housesLtr = lstHouses.iterator();
        House currHouse = null;
        if (housesLtr.hasNext()) {
            currHouse = housesLtr.next();
        }
        while (currHouse != null) {
            sbHousesInformationToReturn.append(" {\"house_id\":\"" + currHouse.getHouseId() + "\",");
            sbHousesInformationToReturn.append("\"state\":\"" + currHouse.getState() + "\",");
            sbHousesInformationToReturn.append("\"city\":\"" + currHouse.getCity() + "\",");
            sbHousesInformationToReturn.append("\"street\":\"" + currHouse.getStreet() + "\",");
            sbHousesInformationToReturn.append("\"house_number\":\"" + currHouse.getHouseNumber() + "\",");
            sbHousesInformationToReturn.append("\"house_kind\":\"" + currHouse.getHouseKind() + "\",");
            sbHousesInformationToReturn.append("\"number_of_rooms\":\"" + currHouse.getNumberOfRooms() + "\",");
            sbHousesInformationToReturn.append("\"number_of_living_rooms\":\"" + currHouse.getNumberOfLivingRooms() + "\",");
            sbHousesInformationToReturn.append("\"number_of_kitchens\":\"" + currHouse.getNumberOfKitchens() + "\",");
            sbHousesInformationToReturn.append("\"number_of_bedrooms\":\"" + currHouse.getNumberOfBedrooms() + "\",");
            sbHousesInformationToReturn.append("\"number_of_bathrooms\":\"" + currHouse.getNumberOfBathrooms() + "\",");
            sbHousesInformationToReturn.append("\"location_kind\":\"" + currHouse.getLocationKind() + "\",");
            sbHousesInformationToReturn.append("\"comments\":\"" + currHouse.getComments() + "\",");
            sbHousesInformationToReturn.append("\"purchase_price\":\"" + currHouse.getPurchasePrice() + "\",");
            sbHousesInformationToReturn.append("\"treatment_fees\":\"" + currHouse.getTreatmentFees() + "\",");
            sbHousesInformationToReturn.append("\"renovation_fees\":\"" + currHouse.getRenovationFees() + "\",");
            sbHousesInformationToReturn.append("\"divers_fees\":\"" + currHouse.getDiversFees() + "\"}");

            if (housesLtr.hasNext()) {
                sbHousesInformationToReturn.append(",");
                currHouse = housesLtr.next();
            } else {
                currHouse = null;
            }

        }
        sbHousesInformationToReturn.append(" ]}");
        return sbHousesInformationToReturn;
    }

    public StringBuilder getHouseById(String szHouseId) {
        int nHouseId = 0;
        try {
            nHouseId = Integer.parseInt(szHouseId);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return new StringBuilder("A Problem haz occured when try to get the house, נוצרה בעיה בעת נסיון קבלת הבית");
        }

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get house by id : " + nHouseId);
        House house = getterDB.getHouseById(nHouseId);

        StringBuilder sbHousesInformationToReturn = new StringBuilder();
        sbHousesInformationToReturn.append("{ \"house\":");

        if (house != null) {
            sbHousesInformationToReturn.append(" {\"house_id\":\"" + house.getHouseId() + "\",");
            sbHousesInformationToReturn.append("\"state\":\"" + house.getState() + "\",");
            sbHousesInformationToReturn.append("\"city\":\"" + house.getCity() + "\",");
            sbHousesInformationToReturn.append("\"street\":\"" + house.getStreet() + "\",");
            sbHousesInformationToReturn.append("\"house_number\":\"" + house.getHouseNumber() + "\",");
            sbHousesInformationToReturn.append("\"house_kind\":\"" + house.getHouseKind() + "\",");
            sbHousesInformationToReturn.append("\"number_of_rooms\":\"" + house.getNumberOfRooms() + "\",");
            sbHousesInformationToReturn.append("\"number_of_living_rooms\":\"" + house.getNumberOfLivingRooms() + "\",");
            sbHousesInformationToReturn.append("\"number_of_kitchens\":\"" + house.getNumberOfKitchens() + "\",");
            sbHousesInformationToReturn.append("\"number_of_bedrooms\":\"" + house.getNumberOfBedrooms() + "\",");
            sbHousesInformationToReturn.append("\"number_of_bathrooms\":\"" + house.getNumberOfBathrooms() + "\",");
            sbHousesInformationToReturn.append("\"location_kind\":\"" + house.getLocationKind() + "\",");
            sbHousesInformationToReturn.append("\"comments\":\"" + house.getComments() + "\",");
            sbHousesInformationToReturn.append("\"purchase_price\":\"" + house.getPurchasePrice() + "\",");
            sbHousesInformationToReturn.append("\"treatment_fees\":\"" + house.getTreatmentFees() + "\",");
            sbHousesInformationToReturn.append("\"renovation_fees\":\"" + house.getRenovationFees() + "\",");
            sbHousesInformationToReturn.append("\"divers_fees\":\"" + house.getDiversFees() + "\"}");
        }
        sbHousesInformationToReturn.append(" }");
        return sbHousesInformationToReturn;
    }


    public StringBuilder getListOFExistingLanguage()
    {
        StringBuilder sbExistingLanguageToReturn = new StringBuilder();
        sbExistingLanguageToReturn.append("{ \"languages\": [");
        ArrayList<String> lstExistingLaguages = getterDB.getListOFExistingLanguage();

        Iterator<String> ltrHouseLanguage = lstExistingLaguages.iterator();
        String currHouseLanguage = null;
        if (ltrHouseLanguage.hasNext()) {
            currHouseLanguage = ltrHouseLanguage.next();
        }
        while (currHouseLanguage != null) {
            sbExistingLanguageToReturn.append("\""+currHouseLanguage+"\"");
            if (ltrHouseLanguage.hasNext()) {
                sbExistingLanguageToReturn.append(",");
                currHouseLanguage = ltrHouseLanguage.next();
            } else {
                currHouseLanguage = null;
            }

        }
        sbExistingLanguageToReturn.append("]}");
        return sbExistingLanguageToReturn;
    }
}
