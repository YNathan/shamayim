package BL;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;


import DB.getterDB;
import Entity.*;
import File.FileGetter;
import play.Logger;

import javax.mail.MessagingException;

import static play.mvc.Http.Context.Implicit.request;

/**
 * Will do all the logic of the data who asked from the server
 *
 * @author Yaacov
 */
public class getterBL {
    getterDB getterDB = new getterDB();
    FileGetter fileGetter = new FileGetter();
    mailBL mail = new mailBL();
    private static String HOUSES_DOCUMENTS_DIR = "HousesDocuments";

    /**
     * @param szUserName - the user-name that the user send
     * @param szPassword - the password that the user send
     * @return true is the user-name and the password is correct
     */
    public String isLoginPermited(String szUserName, String szPassword) {
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get is login permited");
        String szPermission = "-1";
        ArrayList<User> userList = getterDB.getUsers();
        boolean isGreateLogin = false;
        for (User currUser : userList) {
            if (currUser.getUserName().equals(szUserName) && currUser.getPassword().equals(szPassword)) {
                szPermission = currUser.getPermission();
               /* try {
                    mail.sendLoginSuccess(szUserName, request().remoteAddress());
                } catch (MessagingException e) {
                    Logger.error(e.getMessage());
                    e.printStackTrace();
                }*/
            }
        }
        return szPermission;
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
            sbHousesInformationToReturn.append("\"renovation_fees\":\"" + currHouse.getRenovationFeesForSale() + "\",");
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
            return new StringBuilder("A Problem has occured when try to get the house, נוצרה בעיה בעת נסיון קבלת הבית");
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
            sbHousesInformationToReturn.append("\"renovation_fees\":\"" + house.getRenovationFeesForSale() + "\",");
            sbHousesInformationToReturn.append("\"divers_fees\":\"" + house.getDiversFees() + "\"}");
        }
        sbHousesInformationToReturn.append(" }");
        return sbHousesInformationToReturn;
    }

    public StringBuilder getHouseByIdToPrint(String szHouseId) {
        int nHouseId = 0;
        try {
            nHouseId = Integer.parseInt(szHouseId);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return new StringBuilder("A Problem has occured when try to get the house, נוצרה בעיה בעת נסיון קבלת הבית");
        }

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> Get house for print by id : " + nHouseId);
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
            sbHousesInformationToReturn.append("\"renovation_fees\":\"" + house.getRenovationFeesForSale() + "\",");
            sbHousesInformationToReturn.append("\"divers_fees\":\"" + house.getDiversFees() + "\"}");
        }
        sbHousesInformationToReturn.append(" }");
        return sbHousesInformationToReturn;
    }


    public Dictionary getHouseLanguageByLanguage(String szLanguage) {
        Dictionary dictionaryToReturn = new Dictionary();

        dictionaryToReturn = getterDB.getHouseLanguageByLanguage(szLanguage);
        System.out.println(dictionaryToReturn.toJson());
        return dictionaryToReturn;

    }

    public StringBuilder getListOFExistingLanguage() {
        StringBuilder sbExistingLanguageToReturn = new StringBuilder();
        sbExistingLanguageToReturn.append("{ \"languages\": [");
        ArrayList<String> lstExistingLaguages = getterDB.getListOFExistingLanguage();

        Iterator<String> ltrHouseLanguage = lstExistingLaguages.iterator();
        String currHouseLanguage = null;
        if (ltrHouseLanguage.hasNext()) {
            currHouseLanguage = ltrHouseLanguage.next();
        }
        while (currHouseLanguage != null) {
            sbExistingLanguageToReturn.append("\"" + currHouseLanguage + "\"");
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

    public StringBuilder getFilePaths(String szHouseId) {
        int nHouseId = Integer.parseInt(szHouseId);
        House house = getterDB.getHouseById(nHouseId);
        StringBuilder sbExistingFilesToReturn = new StringBuilder();
        sbExistingFilesToReturn.append("{ \"files\": [");
        String szFolderName = house.getState() + "_" + house.getCity() + "_" + house.getStreet() + "_" + house.getHouseNumber();
        ArrayList<String> lstExistingFiles = fileGetter.getImagesName(HOUSES_DOCUMENTS_DIR, szFolderName);

        Iterator<String> ltrFiles = lstExistingFiles.iterator();
        String currFile = null;
        if (ltrFiles.hasNext()) {
            currFile = ltrFiles.next();
        }
        while (currFile != null) {
            sbExistingFilesToReturn.append("\"" + currFile + "\"");
            if (ltrFiles.hasNext()) {
                sbExistingFilesToReturn.append(",");
                currFile = ltrFiles.next();
            } else {
                currFile = null;
            }

        }
        sbExistingFilesToReturn.append("]}");
        return sbExistingFilesToReturn;
    }

    public File getAspecificFile(String szFolderName, String szFileName) {
        String szFullFilePath = System.getProperty("user.dir") + "\\HousesDocuments\\" + szFolderName + "\\" + szFileName;
        File fileToReturn = fileGetter.getFile(szFullFilePath);
        System.out.println("Get File " + szFullFilePath);
        return fileToReturn;
    }
}
