package BL;

import java.io.*;
import java.util.ArrayList;

import java.util.List;

import Entity.ESuccessFailed;
import Entity.User;
import File.FileSetter;
import DB.getterDB;
import DB.setterDB;
import Entity.House;
import Entity.WebResponce;
import play.mvc.Http;


/**
 * @author Yaacov
 */
public class setterBL {
    private setterDB setterDB = new setterDB();
    private getterDB getterDB = new getterDB();
    private getterBL getterBL = new getterBL();
    private FileSetter fileSetter = new FileSetter();
    private WebResponce webResponce = new WebResponce();
    private mailBL mailBl = new mailBL();

    /**
     * Registering a new user into the system.
     *
     * @param szUserName
     * @param szTelephone
     * @param szEmail
     * @param szPassword
     * @return
     * @throws Exception
     */
    public boolean registerNewUser(String szUserName, String szTelephone,
                                   String szEmail, String szPassword, String szPermissionManager, String szPermissionView) throws Exception {
        // INFO
        play.Logger.info("<BUISNESS_LOGIC> Register new user : ");
        play.Logger.info("============================");
        play.Logger.info("For : =>>");
        play.Logger.info("User name : " + szUserName);
        play.Logger.info("Telephone : " + szTelephone);
        play.Logger.info("Email : " + szEmail);
        play.Logger.info("Password : " + szPassword);
        play.Logger.info("============================");
        int nPermissionManager = Integer.parseInt(szPermissionManager);
        int nPermissionView = Integer.parseInt(szPermissionView);
        boolean isRegitred = false;
        if (setterDB.registerNewUser(szUserName, szTelephone, szEmail, szPassword, nPermissionManager, nPermissionView)) {
            isRegitred = true;
        }
        return isRegitred;
    }

    /**
     * Registering a new user into the system.
     *
     * @param szUserName
     * @param szTelephone
     * @param szEmail
     * @param szPassword
     * @return
     * @throws Exception
     */
    public WebResponce addNewUser(String szUserName, String szTelephone, String szEmail, String szPassword, String szPermissionManager, String szPermissionView) throws Exception {
        webResponce = new WebResponce();
        // INFO
        play.Logger.info("<BUISNESS_LOGIC> Register new user : ");
        play.Logger.info("============================");
        play.Logger.info("For : =>>");
        play.Logger.info("User name : " + szUserName);
        play.Logger.info("Telephone : " + szTelephone);
        play.Logger.info("Email : " + szEmail);
        play.Logger.info("Password : " + szPassword);
        play.Logger.info("============================");
        int nPermissionManager = Integer.parseInt(szPermissionManager);
        int nPermissionView = Integer.parseInt(szPermissionView);
        webResponce = setterDB.addNewUser(szUserName, szTelephone, szEmail, szPassword, nPermissionManager, nPermissionView);
        return webResponce;
    }

    public WebResponce uodateUser(String sUserId, String userName, String telephone, String email, String password, String sPermissionManager, String sPermissionView) throws Exception {
        webResponce = new WebResponce();
        int nUserId = Integer.parseInt(sUserId);
        int nPermissionManager = Integer.parseInt(sPermissionManager);
        int nPermissionView = Integer.parseInt(sPermissionView);
        webResponce = setterDB.updateUser(nUserId, userName, telephone, email, password, nPermissionManager, nPermissionView);
        return webResponce;
    }

    public WebResponce deleteUser(User m_user) {
        int nUserId = Integer.parseInt(m_user.getUserId());
        webResponce = setterDB.deleteUser(nUserId);
        return webResponce;
    }

    public WebResponce insertHouseDetails(House m_house) {
        webResponce = new WebResponce();
        boolean bIsStillExist = false;
        ArrayList<House> lstExictingHouses = getterDB.getListOfHouse();
        for (House currHouse : lstExictingHouses) {
            if ((m_house.getState().equals(currHouse.getState()) &&
                    (m_house.getCity().equals(currHouse.getCity())) &&
                    (m_house.getStreet().equals(currHouse.getStreet())) &&
                    (m_house.getHouseNumber() == currHouse.getHouseNumber()))) {
                bIsStillExist = true;
                break;
            }
        }
        if (!bIsStillExist) {
            setterDB.setNewHouseDetails(m_house);
            fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber());
            fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber()+"\\Docs");
            fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber()+"\\Profile");
            webResponce.setReason("The house was registred In the System. הבית נרשם במערכת");
        } else {
            webResponce.setReason("The house Still Exist In the System. הבית כבר קיים במערכת");
        }
        return webResponce;
    }

    public WebResponce updateHouseGeneralDetails(House m_house) {
        webResponce = new WebResponce();
        webResponce = setterDB.updateHouseGeneralDetails(m_house);
        return webResponce;
    }

    public WebResponce updateHouseFinancialDetails(House m_house) {
        webResponce = new WebResponce();
        webResponce = setterDB.updateHouseFinancialDetails(m_house);
        return webResponce;
    }


    public WebResponce insertHouseAddress(House m_house) {
        int nHouseId = -1;
        webResponce = new WebResponce();
        boolean bIsStillExist = false;
        ArrayList<House> lstExictingHouses = getterDB.getListOfHouse();
        for (House currHouse : lstExictingHouses) {
            if ((m_house.getState().equals(currHouse.getState()) &&
                    (m_house.getCity().equals(currHouse.getCity())) &&
                    (m_house.getStreet().equals(currHouse.getStreet())) &&
                    (m_house.getHouseNumber() == currHouse.getHouseNumber()))) {
                bIsStillExist = true;
                nHouseId = currHouse.getHouseId();
                break;
            }
        }
        if (!bIsStillExist) {
            setterDB.setNewHouseDetails(m_house);
            fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber());
            fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber()+"\\Docs");
            fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber()+"\\Profile");
            webResponce.setReason("The house was registred In the System. הבית נרשם במערכת");
            nHouseId = getterDB.getHouseAddress(m_house.getState(), m_house.getCity(), m_house.getStreet(), m_house.getHouseNumber()).getHouseId();
            webResponce.setMoreDetails(String.valueOf(nHouseId));
        } else {
            webResponce.setReason("The house Still Exist In the System. הבית כבר קיים במערכת");
            webResponce.setMoreDetails(String.valueOf(nHouseId));
        }
        return webResponce;
    }


    /**
     * Copy file from old location to a new location in the system we will use
     * that for save the profile picture who we get from the client to the local
     * directory in the server
     *
     * @param oldLocation
     * @param newLocation
     * @throws IOException
     */
    public static void copyFile(File oldLocation, File newLocation) throws IOException {
        if (oldLocation.exists()) {
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(oldLocation));
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(newLocation, false));
            try {
                byte[] buff = new byte[8192];
                int numChars;
                while ((numChars = reader.read(buff, 0, buff.length)) != -1) {
                    writer.write(buff, 0, numChars);
                }
            } catch (IOException ex) {
                throw new IOException(
                        "IOException when transferring " + oldLocation.getPath() + " to " + newLocation.getPath());
            } finally {
                try {
                    if (reader != null) {
                        writer.close();
                        reader.close();
                    }
                } catch (IOException ex) {
                    System.out.println("Error closing files when transferring " + oldLocation.getPath() + " to "
                            + newLocation.getPath());
                }
            }
        } else {
            throw new IOException("Old location does not exist when transferring " + oldLocation.getPath() + " to "
                    + newLocation.getPath());
        }
    }

    public WebResponce setFiles(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        return fileSetter.setFiles(szHouseName, pictures);
    }

    public WebResponce setHouseDocuments(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        return fileSetter.setHouseDocuments(szHouseName, pictures);
    }
    public WebResponce setHouseProfilePictures(String szHouseName, List<Http.MultipartFormData.FilePart> pictures) {
        return fileSetter.setHouseProfilePicture(szHouseName, pictures);
    }

    public WebResponce sendHouseMail(String szUserName, String szHouseId) {
        webResponce = new WebResponce();
        int nHouseId = 0;
        try {
            nHouseId = Integer.parseInt(szHouseId);
        } catch (Exception e) {
            e.printStackTrace();
            webResponce.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponce.setReason("Your House Id Is Not A Number");
        }
        if (webResponce.getSuccessFailed() != ESuccessFailed.FAILED) {
            // INFO
            play.Logger.info("<BUSINESS_LOGIC> Get house by id : " + nHouseId);
            House house = getterDB.getHouseById(nHouseId);
            String szHouseName = house.getState() + "_" + house.getCity() + "_" + house.getStreet() + "_" + house.getHouseNumber();
            User userToSend = getterDB.getUser(getterDB.getUserIdByName(szUserName));
            mailBl.sendHouse(szHouseName, house.toStringMailFormat(), userToSend.getEmail(),userToSend.getPermissionView());
        }

        return webResponce;
    }
}
