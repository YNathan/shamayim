package DB;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Entity.ESuccessFailed;
import Entity.Gelt;
import Entity.House;
import Entity.WebResponce;

import javax.sql.DataSource;

/**
 * @author Yaacov
 */
public class setterDB {
    private static Connection connect;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;
    private static String TABLE_USERS_NAME = "shamayim.users";
    private static String TABLE_HOUSE_NAME = "shamayim.house";
    private static String TABLE_HOUSE_FINANCIAL_NAME = "shamayim.house_financial";
    private static String TABLE_BANK_NAME = "shamayim.bank";
    private static String TABLE_TEMP_DEBTS_NAME = "shamayim.temp_debts";
    private static String DATA_BASE_USER_NAME = "root";
    private static String DATA_BASE_PASSWORD_NAME = "sh123456";

    /**
     * @param m_gelts
     */
    public boolean setGelts(ArrayList<Gelt> m_gelts) {
        boolean bIsWasAdded = true;
        // Deleting all data-base like this we dosn't want to have duplicate
        // data ("debts")
        deleteTable("shamayim.bank");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            play.Logger.info("Insert new gelts to the data-base");
            for (Gelt currGelts : m_gelts) {
                // PreparedStatements can use variables and are more efficient
                preparedStatement = connect.prepareStatement("insert into " + TABLE_BANK_NAME + " values (? , ?, ?,?)");
                preparedStatement.setInt(1, currGelts.getDebterID());
                preparedStatement.setInt(2, currGelts.getAmount());
                preparedStatement.setInt(3, currGelts.getEntitledID());
                preparedStatement.setInt(4, currGelts.getGroupID());
                preparedStatement.executeUpdate();
            }

        } catch (Exception e) {
            bIsWasAdded = false;
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        return bIsWasAdded;
    }

    /**
     * @param m_gelt
     */
    public boolean setTempGelt(Gelt m_gelt) {
        boolean bIsWasAdded = true;
        // Deleting all data-base like this we dosn't want to have duplicate
        // data ("debts")
        deleteTable("shamayim.temp_debts");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            play.Logger.info("Insert new temp gelt to the data-base");

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_TEMP_DEBTS_NAME + " values (? , ?, ?,?)");
            preparedStatement.setInt(1, m_gelt.getDebterID());
            preparedStatement.setInt(2, m_gelt.getAmount());
            preparedStatement.setInt(3, m_gelt.getEntitledID());
            preparedStatement.setInt(4, m_gelt.getGroupID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            bIsWasAdded = false;
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        return bIsWasAdded;
    }

    /**
     * @param m_gelt
     */
    public boolean deleteTempGelt(Gelt m_gelt) {
        boolean bIsWasAdded = true;
        // Deleting the temp debt that was inserted l

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            play.Logger.info(" Insert new temp gelt to the data-base");

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("delete from " + TABLE_TEMP_DEBTS_NAME
                    + " where debter_id = ? and amount = ? and entitled_id = ? and group_id = ? ");
            preparedStatement.setInt(1, m_gelt.getDebterID());
            preparedStatement.setInt(2, m_gelt.getAmount());
            preparedStatement.setInt(3, m_gelt.getEntitledID());
            preparedStatement.setInt(4, m_gelt.getGroupID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            bIsWasAdded = false;
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        return bIsWasAdded;
    }

    /**
     * Will edit a specific debt
     *
     * @param m_origianlGelt - the gelt that we are want to change his values
     * @param m_newGelt      - the gelt with the new values to editing
     * @return
     */
    public boolean updateGelt(Gelt m_origianlGelt, Gelt m_newGelt) {
        boolean bIsWasAdded = true;
        // Deleting the temp debt that was inserted l

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            play.Logger.info(" Update gelt to the data-base");

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("UPDATE " + TABLE_BANK_NAME
                    + " SET debter_id = ? , amount = ? , entitled_id = ? , group_id = ? WHERE debter_id = ? AND amount = ? AND entitled_id = ? AND group_id = ? ;");

            preparedStatement.setInt(1, m_newGelt.getDebterID());
            preparedStatement.setInt(2, m_newGelt.getAmount());
            preparedStatement.setInt(3, m_newGelt.getEntitledID());
            preparedStatement.setInt(4, m_newGelt.getGroupID());
            preparedStatement.setInt(5, m_origianlGelt.getDebterID());
            preparedStatement.setInt(6, m_origianlGelt.getAmount());
            preparedStatement.setInt(7, m_origianlGelt.getEntitledID());
            preparedStatement.setInt(8, m_origianlGelt.getGroupID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            bIsWasAdded = false;
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        return bIsWasAdded;
    }

    /**
     * @param m_gelt
     */
    public boolean deleteGelt(Gelt m_gelt) {
        boolean bIsWasAdded = true;
        // Deleting the temp debt that was inserted l

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            play.Logger.info(" Insert new temp gelt to the data-base");

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("delete from " + TABLE_BANK_NAME
                    + " where debter_id = ? and amount = ? and entitled_id = ? ");
            preparedStatement.setInt(1, m_gelt.getDebterID());
            preparedStatement.setInt(2, m_gelt.getAmount());
            preparedStatement.setInt(3, m_gelt.getEntitledID());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            bIsWasAdded = false;
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
        return bIsWasAdded;
    }


    /**
     * This method its for helping the testing and init the table that asked
     *
     * @param tableName the table that want to delete
     */
    public void deleteTable(String tableName) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("DELETE FROM " + tableName + ";");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes
        commit();
    }

    /**
     * Register a new user into the data-base
     *
     * @param userName  - user name
     * @param firstName - first name
     * @param lastName  - last name
     * @param telephone - telephone
     * @param email     - email
     * @param password  - password
     * @param birthdate - birthdate
     * @return
     * @throws Exception
     */
    public boolean registerNewUser(String userName, String firstName, String lastName, String telephone, String email,
                                   String password, Date birthdate) throws Exception {

        boolean bWasRegister = true;
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println("User name : " + userName);
        System.out.println("First name : " + firstName);
        System.out.println("Last name : " + lastName);
        System.out.println("Telephone : " + telephone);
        System.out.println("Email : " + email);
        System.out.println("Password : " + password);
        System.out.println("Birthdate : " + birthdate);
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_USERS_NAME
                    + " (user_name,first_name,last_name,telephone,email,password,birthdate) values (?, ?, ?, ?, ? , ?, ?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, telephone);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, password);
            preparedStatement.setDate(7, birthdate);
            preparedStatement.executeUpdate();
            System.out.println("registered successfully!!!");
            System.out.println("============================");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to registered");
            System.out.println("==========================================");
            bWasRegister = false;
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return bWasRegister;

    }


    public WebResponce setNewHouseDetails(House m_house) {
        WebResponce webResponceToReturn = new WebResponce();
        // INFO
        play.Logger.info(" " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime())
                + " <SETTER> Register new user : ");
        System.out.println("============================");
        System.out.println("For : =>>");
        System.out.println(m_house.toString());
        System.out.println("============================");

        try {
            // The newInstance() call is a work around for some broken Java
            // implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();


            String url = "jdbc:mysql://localhost/shamayim?characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //connect = DriverManager.getConnection(url, DATA_BASE_USER_NAME, DATA_BASE_USER_NAME);

            // String strConnection=
            // "Server=127.0.0.1;Port=3306;Database=shamayim;Uid=root;password=Ny7516399;";
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);

            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("insert into " + TABLE_HOUSE_NAME
                    + " (state, city, street, house_number, house_kind, number_of_rooms, number_of_living_rooms, number_of_kitchens, number_of_bedrooms, number_of_bathrooms, location_kind, comments,purchase_price,treatment_fees,renovation_fees_for_sale,renovation_fees_for_renting,divers_fees) values (?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ? , ?,?,?,?,?,?)");
            play.Logger.info(" Insert new user to the data-base");
            // Parameters start with 1
            preparedStatement.setString(1, m_house.getState());
            preparedStatement.setString(2, m_house.getCity());
            preparedStatement.setString(3, m_house.getStreet());
            preparedStatement.setInt(4, m_house.getHouseNumber());
            preparedStatement.setInt(5, m_house.getHouseKind().getValue());
            preparedStatement.setInt(6, m_house.getNumberOfRooms());
            preparedStatement.setInt(7, m_house.getNumberOfLivingRooms());
            preparedStatement.setInt(8, m_house.getNumberOfKitchens());
            preparedStatement.setInt(9, m_house.getNumberOfBedrooms());
            preparedStatement.setInt(10, m_house.getNumberOfBathrooms());
            preparedStatement.setInt(11, m_house.getLocationKind().getValue());
            preparedStatement.setString(12, m_house.getComments());
            preparedStatement.setDouble(13, m_house.getPurchasePrice());
            preparedStatement.setDouble(14, m_house.getTreatmentFees());
            preparedStatement.setDouble(15, m_house.getRenovationFeesForSale());
            preparedStatement.setDouble(16, m_house.getRenovationFeesForRenting());
            preparedStatement.setDouble(17, m_house.getDiversFees());
            preparedStatement.executeUpdate();
            System.out.println("registered successfully!!!");
            System.out.println("============================");
        } catch (Exception e) {
            System.out.println("a problem occurred when try to registered");
            System.out.println("==========================================");
            webResponceToReturn.seteSuccessFailed(ESuccessFailed.FAILED);
            webResponceToReturn.setReason("a problem occurred when try to registered");
            play.Logger.info("<setterDB> " + e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
        // Commit changes;
        commit();
        return webResponceToReturn;

    }


    /**
     * Commit changes of data base
     */
    public void commit() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME
                    + "&password=" + DATA_BASE_PASSWORD_NAME);
            // PreparedStatements can use variables and are more efficient
            preparedStatement = connect.prepareStatement("commit");
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            play.Logger.error(e.getMessage());
        } finally {
            // Closing the resultSet
            close();
        }
    }

    // Closing the resultSet
    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}

