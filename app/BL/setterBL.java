package BL;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Entity.ESuccessFailed;
import File.FileSetter;
import DB.getterDB;
import DB.setterDB;
import Entity.Gelt;
import Entity.House;
import Entity.WebResponce;
import play.mvc.Http;

import static play.mvc.Controller.flash;

/**
 * @author Yaacov
 */
public class setterBL {
    private setterDB setterDB = new setterDB();
    private getterDB getterDB = new getterDB();
    private getterBL getterBL = new getterBL();
    private FileSetter fileSetter = new FileSetter();
    private WebResponce webResponce = new WebResponce();

    /**
     * Will do the algorithm for inserting a new debt
     *
     * @param nDebterName
     * @param nAmount
     * @param nEntitledName
     * @return
     */
    public boolean insertGelt(int nDebterName, int nAmount, int nEntitledName, int nGroupId) {
        boolean isInserted = false;
        Gelt m_gelt = new Gelt(nDebterName, nAmount, nEntitledName, nGroupId);
        // An array that will contain the new gelts that we will need to add to
        // the data-base
        ArrayList<Gelt> geltsAdded = new ArrayList<>();
        // Get all debts from the data-base, in this array we are going to edit
        // some debts if we need
        ArrayList<Gelt> geltsOriginal = getterBL.getGelts();

        // If wasn't found

        // Check if there is already a debt open between hem
        if (!foundChavrousse(geltsOriginal, m_gelt)) {
            // Check if its an idiot because this debtor is already an
            // entitled from the actual entitled
            if (!foundSheyteGomour(geltsOriginal, geltsAdded, m_gelt)) {
                // Check if was shiboude algorithm worked on hem
                if (!shiboudeDeRavNosson(geltsOriginal, geltsAdded, m_gelt)) {
                    geltsAdded.add(m_gelt);
                }
            }
        }
        // In final we adding to the array who contain the original debts the
        // new debts
        for (Gelt currGelt : geltsAdded) {
            geltsOriginal.add(currGelt);
        }
        // Adding to the data-base the debts
        setterDB.setGelts(geltsOriginal);
        isInserted = true;
        return isInserted;
    }

    /**
     * This algorithm of shiboude d rav nosson whom montioned in the talmud
     * masechess gitin daf l"z. Hoyshen mishpot simen p"v. Orouh hashoulhon
     * simen p"v seeiff a b g.
     *
     * @param m_gelts      - the original array contain all debts from the data-base
     * @param m_geltHelper
     * @param m_gelt
     * @return
     */
    private boolean shiboudeDeRavNosson(ArrayList<Gelt> m_gelts, ArrayList<Gelt> m_geltHelper, Gelt m_gelt) {
        play.Logger.info("shiboude De Rav Nosson Algorithm Started");
        boolean isUpdate = false;
        for (Gelt currGelt : m_gelts) {
            if (!isUpdate) {
                if ((currGelt.getDebterID() == m_gelt.getEntitledID()) && (currGelt.getGroupID() == m_gelt.getGroupID())) {
                    int nNewAmount = currGelt.getAmount() - m_gelt.getAmount();
                    if (nNewAmount == 0) {
                        currGelt.setnDebterID(m_gelt.getDebterID());
                        isUpdate = true;
                    } else if ((nNewAmount > 0)) {
                        currGelt.setnAmount(nNewAmount);
                        Gelt geltToAdd = new Gelt(m_gelt.getDebterID(), m_gelt.getAmount(), currGelt.getEntitledID(),
                                currGelt.getGroupID());
                        if ((!foundChavrousse(m_gelts, geltToAdd))
                                && (!foundSheyteGomour(m_gelts, m_geltHelper, geltToAdd))) {
                            m_geltHelper.add(geltToAdd);
                        }

                        isUpdate = true;
                    } else if ((nNewAmount < 0)) {
                        currGelt.setnDebterID(m_gelt.getDebterID());

                        Gelt geltToAdd = new Gelt(m_gelt.getDebterID(), Math.abs(nNewAmount), m_gelt.getEntitledID(),
                                currGelt.getGroupID());
                        if ((!foundChavrousse(m_gelts, geltToAdd))
                                && (!foundSheyteGomour(m_gelts, m_geltHelper, geltToAdd))) {
                            m_geltHelper.add(geltToAdd);
                        }
                        isUpdate = true;
                    }
                } else if ((currGelt.getEntitledID() == m_gelt.getDebterID()) && (currGelt.getGroupID() == m_gelt.getGroupID())) {
                    int nNewAmount = currGelt.getAmount() - m_gelt.getAmount();
                    if (nNewAmount == 0) {
                        currGelt.setnEntitledID(m_gelt.getEntitledID());
                        isUpdate = true;
                    } else if ((nNewAmount > 0)) {
                        currGelt.setnAmount(nNewAmount);

                        Gelt geltToAdd = new Gelt(currGelt.getDebterID(), m_gelt.getAmount(), m_gelt.getEntitledID(),
                                m_gelt.getGroupID());
                        if ((!foundChavrousse(m_gelts, geltToAdd))
                                && (!foundSheyteGomour(m_gelts, m_geltHelper, geltToAdd))) {
                            m_geltHelper.add(geltToAdd);
                        }

                        isUpdate = true;
                    } else if ((nNewAmount < 0)) {
                        currGelt.setnEntitledID(m_gelt.getEntitledID());

                        Gelt geltToAdd = new Gelt(m_gelt.getDebterID(), Math.abs(nNewAmount), m_gelt.getEntitledID(),
                                m_gelt.getGroupID());
                        if ((!foundChavrousse(m_gelts, geltToAdd))
                                && (!foundSheyteGomour(m_gelts, m_geltHelper, geltToAdd))) {
                            m_geltHelper.add(geltToAdd);
                        }
                        isUpdate = true;
                    }

                }

            }
        }
        return isUpdate;
    }

    /**
     * In the case the the debterId and the EtitledId have a debt together will
     * just added the amount
     *
     * @param m_gelts - the original debts array
     * @param m_gelt  - the new debts who we want to insert
     * @return true if was found (was update)
     */
    private boolean foundChavrousse(ArrayList<Gelt> m_gelts, Gelt m_gelt) {
        play.Logger.info("Found Chavrousee Algorithm Started");
        boolean isUpdate = false;
        for (Gelt currGelt : m_gelts) {
            if (!isUpdate) {
                if ((currGelt.getDebterID() == m_gelt.getDebterID())
                        && (currGelt.getEntitledID() == m_gelt.getEntitledID())
                        && (currGelt.getGroupID() == m_gelt.getGroupID())) {
                    currGelt.setnAmount(currGelt.getAmount() + m_gelt.getAmount());
                    isUpdate = true;
                }
            }
        }
        play.Logger.info("Found Chavrousee Algorithm Finished with value : " + isUpdate);
        return isUpdate;
    }

    /**
     * In the case that that the entitled has a debt to the debter
     *
     * @param m_gelts
     * @param m_gelt
     * @return
     */
    private boolean foundSheyteGomour(ArrayList<Gelt> m_gelts, ArrayList<Gelt> m_geltHelper, Gelt m_gelt) {
        play.Logger.info("Found Sheyte Gomour Algorithm Started");
        final int N_NULL_NUMBER = -1;
        // We did'nt want to continue if we update
        boolean isUpdate = false;
        // The new amount the current debt amount minus the new debt amount
        int nNewAmount = N_NULL_NUMBER;
        int nIndexToRemove = N_NULL_NUMBER;
        for (Gelt currGelt : m_gelts) {
            if (!isUpdate) {
                if ((currGelt.getDebterID() == m_gelt.getEntitledID())
                        && (currGelt.getEntitledID() == m_gelt.getDebterID())
                        && (currGelt.getGroupID() == m_gelt.getGroupID())) {
                    nNewAmount = currGelt.getAmount() - m_gelt.getAmount();
                    if (nNewAmount == 0) {
                        nIndexToRemove = m_gelts.indexOf(currGelt);
                        isUpdate = true;
                    } else if (nNewAmount > 0) {
                        currGelt.setnAmount(nNewAmount);
                        isUpdate = true;
                    } else if (nNewAmount < 0) {
                        nIndexToRemove = m_gelts.indexOf(currGelt);
                        m_geltHelper.add(new Gelt(m_gelt.getDebterID(), Math.abs(nNewAmount), m_gelt.getEntitledID(),
                                m_gelt.getGroupID()));
                        isUpdate = true;
                    }
                }
            }
        }
        if (nIndexToRemove != N_NULL_NUMBER) {
            m_gelts.remove(nIndexToRemove);
        }
        play.Logger.info("Found Sheyte Gomour Algorithm Finished with value :" + isUpdate);
        return isUpdate;
    }

    /**
     * Registering a new user into the system.
     *
     * @param szUserName
     * @param szFirstName
     * @param szLastName
     * @param szTelephone
     * @param szEmail
     * @param szPassword
     * @param szBirthdate
     * @return
     * @throws Exception
     */
    public boolean registerNewUser(String szUserName, String szFirstName, String szLastName, String szTelephone,
                                   String szEmail, String szPassword, String szBirthdate) throws Exception {
        // INFO
        play.Logger.info("<BUISNESS_LOGIC> Register new user : ");
        play.Logger.info("============================");
        play.Logger.info("For : =>>");
        play.Logger.info("User name : " + szUserName);
        play.Logger.info("First name : " + szFirstName);
        play.Logger.info("Last name : " + szLastName);
        play.Logger.info("Telephone : " + szTelephone);
        play.Logger.info("Email : " + szEmail);
        play.Logger.info("Password : " + szPassword);
        play.Logger.info("Birthdate : " + szBirthdate);
        play.Logger.info("============================");
        boolean isRegitred = false;
        if (setterDB.registerNewUser(szUserName, szFirstName, szLastName, szTelephone, szEmail, szPassword,
                getterBL.getDateByString(szBirthdate))) {
            isRegitred = true;
        }
        return isRegitred;
    }

    /**
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return
     */
    public boolean insertGelt(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasAdded = false;
        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))) {
            // Inserted to the data base
            bWasAdded = insertTempGelt(szDebterName, szAmount, szEntitledName);
        }
        return bWasAdded;
    }

    /**
     * Insert a new debt in a group
     *
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return
     */
    public boolean insertGelt(String szDebterName, String szAmount, String szEntitledName, String szGroupName) {

        boolean bWasAdded = false;

        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))) {
            // Inserted to the data base
            bWasAdded = insertTempGelt(szDebterName, szAmount, szEntitledName, szGroupName);
        }
        return bWasAdded;
    }

    /**
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return
     */
    public boolean insertTempGelt(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasAdded = false;
        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))) {
            // check if was inserted
            bWasAdded = setterDB.setTempGelt(new Gelt(getterBL.getIdByName(szDebterName), Integer.parseInt(szAmount),
                    getterBL.getIdByName(szEntitledName), 1));
        }
        return bWasAdded;
    }

    /**
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return
     */
    public boolean insertTempGelt(String szDebterName, String szAmount, String szEntitledName, String szGroupName) {
        int nGroupId = getterDB.getGroupIdByGroupName(szGroupName);
        boolean bWasAdded = false;
        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))
                && (nGroupId != -1)) {
            // check if was inserted
            bWasAdded = setterDB.setTempGelt(new Gelt(getterBL.getIdByName(szDebterName), Integer.parseInt(szAmount),
                    getterBL.getIdByName(szEntitledName), nGroupId));
        }
        return bWasAdded;
    }

    /**
     * Delete a debt who waiting in the data base to be confirmed
     *
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return true if was deleted
     */
    public boolean deleteTempDebt(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasDeleted = false;
        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))) {

            // check if was deleted
            bWasDeleted = setterDB.deleteTempGelt(new Gelt(getterBL.getIdByName(szDebterName),
                    Integer.parseInt(szAmount), getterBL.getIdByName(szEntitledName), 1));
        }
        return bWasDeleted;
    }


    /**
     * Delete a debt in a group who waiting in the data base to be confirmed
     *
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return true if was deleted
     */
    public boolean deleteTempDebt(String szDebterName, String szAmount, String szEntitledName, String szGroupName) {
        boolean bWasDeleted = false;
        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))) {

            // check if was deleted
            bWasDeleted = setterDB.deleteTempGelt(new Gelt(getterBL.getIdByName(szDebterName),
                    Integer.parseInt(szAmount), getterBL.getIdByName(szEntitledName), getterDB.getGroupIdByGroupName(szGroupName)));
        }
        return bWasDeleted;
    }

    /**
     * Delete a debt who are in the data base
     *
     * @param szDebterName
     * @param szAmount
     * @param szEntitledName
     * @return true if was deleted
     */
    public boolean deleteDebt(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasDeleted = false;
        // Check if user name exist just for the security
        if ((getterBL.isUserNameExist(szDebterName)) && (getterBL.isUserNameExist(szEntitledName))) {
            // check if was deleted
            bWasDeleted = setterDB.deleteGelt(new Gelt(getterBL.getIdByName(szDebterName), Integer.parseInt(szAmount),
                    getterBL.getIdByName(szEntitledName), 1));
        }
        return bWasDeleted;
    }

    /**
     * Confirm from a debter if the data of this debt is true will insert to the
     * debts data base and deleted from the waiting from the data base
     *
     * @param szDebterName   - name of the debter
     * @param szAmount       - amount that suppose to debt
     * @param szEntitledName - the name of the entitled
     * @return true if was added to the data base and was deleted from the temp
     * data base
     */
    public boolean confirm(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasAdded;

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> The Gelt was confirmed by the debter " + szDebterName
                + " the system will send for input in the data_base");
        // Insert to the records of the data base
        bWasAdded = insertGelt(getterBL.getIdByName(szDebterName), Integer.parseInt(szAmount),
                getterBL.getIdByName(szEntitledName), 1);
        // Delete from the records of the temp debts who waiting for confirm
        bWasAdded = deleteTempDebt(szDebterName, szAmount, szEntitledName);
        return bWasAdded;
    }

    /**
     * Confirm from a debtor in a group if the data of this debt is true will
     * insert to the debts data base and deleted from the waiting from the data
     * base
     *
     * @param szDebterName   - name of the debtor
     * @param szAmount       - amount that suppose to debt
     * @param szEntitledName - the name of the entitled
     * @return true if was added to the data base and was deleted from the temp
     * data base
     */
    public boolean confirmDebt(String szDebterName, String szAmount, String szEntitledName, String szGroupName) {
        boolean bWasAdded;

        // INFO
        play.Logger.info("<BUSINESS_LOGIC> The Gelt was confirmed by the debter " + szDebterName
                + " the system will send for input in the data_base");
        // Insert to the records of the data base
        bWasAdded = insertGelt(getterBL.getIdByName(szDebterName), Integer.parseInt(szAmount),
                getterBL.getIdByName(szEntitledName), getterDB.getGroupIdByGroupName(szGroupName));
        // Delete from the records of the temp debts who waiting for confirm
        bWasAdded = deleteTempDebt(szDebterName, szAmount, szEntitledName, szGroupName);
        return bWasAdded;
    }

    /**
     * Not confirm from a debter if the data of this debt is true will deleted
     * from the waiting from the data base
     *
     * @param szDebterName   - name of the debter
     * @param szAmount       - amount that suppose to debt
     * @param szEntitledName - the name of the entitled
     * @return true if was deleted from the temp data base
     */
    public boolean notConfirm(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasAdded;
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> The Gelt was'nt confirmed by the debter " + szDebterName
                + " the system will delete from the data_base");
        // Delete from the records of the temp debts who waiting for confirm
        bWasAdded = deleteTempDebt(szDebterName, szAmount, szEntitledName);
        return bWasAdded;
    }

    /**
     * Pay a debt send to delete
     *
     * @param szDebterName   - name of the debtor
     * @param szAmount       - amount that suppose to debt
     * @param szEntitledName - the name of the entitled
     * @return true if was deleted from the temp data base
     */
    public boolean pay(String szDebterName, String szAmount, String szEntitledName) {
        boolean bWasAdded;
        // INFO
        play.Logger.info("<BUSINESS_LOGIC> The Gelt was'nt confirmed by the debter " + szDebterName
                + " the system will delete from the data_base");
        // Delete from the records of the temp debts who waiting for confirm
        bWasAdded = deleteDebt(szDebterName, szAmount, szEntitledName);
        return bWasAdded;
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
        } else {
            webResponce.setReason("The house Still Exist In the System");
        }
        fileSetter.CreateFolder(m_house.getState() + "_" + m_house.getCity() + "_" + m_house.getStreet() + "_" + m_house.getHouseNumber());
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
}
