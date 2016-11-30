package DB;import java.sql.Connection;import java.sql.DriverManager;import java.sql.PreparedStatement;import java.sql.ResultSet;import java.sql.Statement;import java.util.ArrayList;import java.util.concurrent.locks.Lock;import java.util.concurrent.locks.ReentrantLock;import Entity.*;/** * @author Yaacov */public class getterDB {    private final Lock lock = new ReentrantLock();    private static Connection connect;    private static PreparedStatement preparedStatement;    private static Statement statement;    private static ResultSet resultSet;    private static String TABLE_HOUSE_NAME = "shamayim.house";    private static String TABLE_HOUSE_LANGUAGE_NAME = "shamayim.dictionary";    private static String TABLE_BANK_NAME = "shamayim.bank";    private static String TABLE_TEMP_DEBTS_NAME = "shamayim.temp_debts";    private static String TABLE_GROUP_USERS_NAME = "shamayim.group_users";    private static String TABLE_GROUPS_NAME = "shamayim.groups";    private static String TABLE_USERS_NAME = "shamayim.users";    private static String DATA_BASE_USER_NAME = "root";    private static String DATA_BASE_PASSWORD_NAME = "sh123456";    EHouseKind eHouseKind = EHouseKind.ALONE;    ELocationKind eLocationKind = ELocationKind.WHITE;    /***     * Get all the user name of the users in all groups     * @return a list with all users name     */    public ArrayList<String> getUserNames() {        lock.lock();        ArrayList<String> userNames = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get User-Names");            // Looping over the result user-names            while ((resultSet.next()) && (!resultSet.isClosed())) {                userNames.add(resultSet.getString("user_name"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return userNames;    }    /***     *     * @param szUserName - the user name of the user     * @return the group names of the user     */    public ArrayList<String> getUserGroupsName(String szUserName) {        lock.lock();        ArrayList<String> userNames = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select group_name from " + TABLE_GROUPS_NAME +                    " where group_id in(SELECT group_id FROM " + TABLE_GROUP_USERS_NAME +                    " where user_id = (select user_id from " + TABLE_USERS_NAME + " where user_name = \'" + szUserName + "'))");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get group names of user name " + szUserName);            // Looping over the result user-names            while ((resultSet.next()) && (!resultSet.isClosed())) {                userNames.add(resultSet.getString("group_name"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return userNames;    }    public ArrayList<String> getUserNamesOfGroup(String szGroupName) {        lock.lock();        ArrayList<String> userNames = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select user_name from " + TABLE_USERS_NAME + " where user_id in ( SELECT user_id FROM " + TABLE_GROUP_USERS_NAME + " where group_id = (select group_id from " + TABLE_GROUPS_NAME + " where group_name = '" + szGroupName + "'))");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get User-Names");            // Looping over the result user-names            while ((resultSet.next()) && (!resultSet.isClosed())) {                userNames.add(resultSet.getString("user_name"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return userNames;    }    public String getUserNameById(int nUserId) {        lock.lock();        String szUserNameToReturn = null;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME + " WHERE user_id=" + nUserId);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get User Name for id: " + nUserId);            // Looping over the result user-names            while ((resultSet.next()) && (!resultSet.isClosed())) {                szUserNameToReturn = resultSet.getString("user_name");            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return szUserNameToReturn;    }    public int getUserIdByName(String szUserName) {        lock.lock();        int nUserId = -1;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select user_id from " + TABLE_USERS_NAME + " WHERE user_name=" + "\'" + szUserName + "\'");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get user-id by user-name");            // Looping over the result users id            while ((resultSet.next()) && (!resultSet.isClosed())) {                nUserId = resultSet.getInt("user_id");            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return nUserId;    }    public ArrayList<String> getEmails() {        lock.lock();        ArrayList<String> emails = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get Emails");            // Looping over the result user-names            while (resultSet.next()) {                emails.add(resultSet.getString("email"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return emails;    }    public ArrayList<UserLogin> getUserLogins() {        lock.lock();        ArrayList<UserLogin> userLogins = new ArrayList<UserLogin>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get User-Login");            // Looping over the result user-names            while (resultSet.next()) {                userLogins.add(new UserLogin(resultSet.getString("user_name"), resultSet.getString("password")));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return userLogins;    }    public ArrayList<User> getUsers() {        lock.lock();        ArrayList<User> usersLst = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get Users");            // Looping over the result user-names            while (resultSet.next()) {                usersLst.add(new User(resultSet.getString("user_name"), resultSet.getString("first_name"),                        resultSet.getString("last_name"), resultSet.getString("email"),                        resultSet.getString("telephone"), resultSet.getString("password"),                        resultSet.getString("birthdate"), resultSet.getString("user_id")));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return usersLst;    }    public ArrayList<Group> getGroupsUser(int nUserId) {        ArrayList<Group> userGroupsLstToReturn = new ArrayList<>();        lock.lock();        Group groupToInputInTheList = null;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("SELECT * FROM " + TABLE_GROUPS_NAME + " WHERE group_id IN (SELECT group_id FROM " + TABLE_GROUP_USERS_NAME + " WHERE user_id=\'" + nUserId + "\')");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get User id: " + nUserId);            // Looping over the result user-names            while (resultSet.next()) {                groupToInputInTheList = new Group(resultSet.getString("group_name"), resultSet.getInt("group_id"), resultSet.getInt("group_owner_id"));                userGroupsLstToReturn.add(groupToInputInTheList);            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return userGroupsLstToReturn;    }    /***     *  Get all user information by the user - id     * @param nUserId - the id of a user     * @return a user     */    public User getUser(int nUserId) {        lock.lock();        User userToReturn = null;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_USERS_NAME + " WHERE user_id=\'" + nUserId + "\'");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get User id: " + nUserId);            // Looping over the result user-names            while (resultSet.next()) {                userToReturn = new User(resultSet.getString("user_name"), resultSet.getString("first_name"),                        resultSet.getString("last_name"), resultSet.getString("telephone"),                        resultSet.getString("email"), resultSet.getString("password"),                        resultSet.getString("birthdate"), resultSet.getString("user_id"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return userToReturn;    }    public ArrayList<Gelt> getGelts() {        lock.lock();        ArrayList<Gelt> gelts = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_BANK_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get Gelts");            // Looping over the result user-names            while (resultSet.next()) {                gelts.add(new Gelt(resultSet.getInt("debter_id"), resultSet.getInt("amount"),                        resultSet.getInt("entitled_id"), resultSet.getInt("group_id")));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return gelts;    }    /**     * Will get the the debts concerning a group     *     * @param szGroupName - the group name     * @return an array with all debtes concerning the group     */    public ArrayList<Gelt> getGelts(String szGroupName) {        lock.lock();        ArrayList<Gelt> gelts = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_BANK_NAME + " WHERE group_id=(select group_id from " + TABLE_GROUPS_NAME + " where group_name=\'" + szGroupName + "')");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get Gelts");            // Looping over the result user-names            while (resultSet.next()) {                gelts.add(new Gelt(resultSet.getInt("debter_id"), resultSet.getInt("amount"),                        resultSet.getInt("entitled_id"), resultSet.getInt("group_id")));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return gelts;    }    /**     * Getting the temp gelts who weiting for confirming from the debter     *     * @return an array witch gelts who weiting for confirming from the debter     */    public ArrayList<Gelt> getTempGelts() {        lock.lock();        ArrayList<Gelt> gelts = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_TEMP_DEBTS_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get Temp Gelts");            // Looping over the result user-names            while (resultSet.next()) {                gelts.add(new Gelt(resultSet.getInt("debter_id"), resultSet.getInt("amount"),                        resultSet.getInt("entitled_id"), resultSet.getInt("group_id")));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return gelts;    }    /**     * Getting the users of a group     *     * @return an array witch gelts who weiting for confirming from the debter     */    public UsersGroup getUsersOfAGroup(int nGroupId) {        lock.lock();        UsersGroup usersGroupToReturn = new UsersGroup();        usersGroupToReturn.setGroup(getGroup(nGroupId));        ArrayList<Integer> lstUsersId = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_GROUP_USERS_NAME + " WHERE group_id=" + nGroupId);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get users for group id : " + nGroupId);            // Looping over the result users id's in the group            while (resultSet.next()) {                lstUsersId.add(resultSet.getInt("user_id"));            }            usersGroupToReturn.setLstUsersId(lstUsersId);        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return usersGroupToReturn;    }    /**     * Getting a group     *     * @return an array witch gelts who weiting for confirming from the debter     */    public Group getGroup(int nGroupId) {        lock.lock();        Group group = new Group();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_GROUPS_NAME + " WHERE group_id=" + nGroupId);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get group id :" + nGroupId);            // Looping over the result user-names            while (resultSet.next()) {                group = new Group(resultSet.getString("group_name"), resultSet.getInt("group_id"), resultSet.getInt("group_owner_id"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return group;    }    /**     * Get group id by the group name     *     * @param szGroupName - the string that will contain the group name     * @return an integer will contain the group id     */    public int getGroupIdByGroupName(String szGroupName) {        lock.lock();        int nGroupId = -1;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select group_id from " + TABLE_GROUPS_NAME + " WHERE group_name ='" + szGroupName + "'");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get group id with name :" + szGroupName);            // Looping over the result user-names            while (resultSet.next()) {                nGroupId = resultSet.getInt("group_id");            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return nGroupId;    }    // Closing the resultSet    private static void close() {        try {            if (resultSet != null) {                resultSet.close();            }            if (preparedStatement != null) {                preparedStatement.close();            }            if (statement != null) {                statement.close();            }            if (connect != null) {                connect.close();            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());            ;        }    }    public ArrayList<House> getListOfHouse() {        lock.lock();        ArrayList<House> lstHouseToReturn = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_HOUSE_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get list of houses");            // Looping over the result user-names            while (resultSet.next()) {                eHouseKind.setValue(resultSet.getInt("house_kind"));                eLocationKind.setValue(resultSet.getInt("location_kind"));                lstHouseToReturn.add(new House(resultSet.getInt("house_id"),                        resultSet.getString("state"),                        resultSet.getString("city"),                        resultSet.getString("street"),                        resultSet.getInt("house_number"),                        eHouseKind,                        resultSet.getInt("number_of_rooms"),                        resultSet.getInt("number_of_living_rooms"),                        resultSet.getInt("number_of_kitchens"),                        resultSet.getInt("number_of_bedrooms"),                        resultSet.getInt("number_of_bathrooms"),                        eLocationKind,                        resultSet.getString("comments")));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return lstHouseToReturn;    }    public House getHouseById(int nhouseId) {        lock.lock();        House houseToReturn = null;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_HOUSE_NAME + " WHERE house_Id=" + nhouseId);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get list of houses");            // Looping over the result user-names            while (resultSet.next()) {                eHouseKind.setValue(resultSet.getInt("house_kind"));                eLocationKind.setValue(resultSet.getInt("location_kind"));                houseToReturn = new House(resultSet.getInt("house_id"),                        resultSet.getString("state"),                        resultSet.getString("city"),                        resultSet.getString("street"),                        resultSet.getInt("house_number"),                        eHouseKind,                        resultSet.getInt("number_of_rooms"),                        resultSet.getInt("number_of_living_rooms"),                        resultSet.getInt("number_of_kitchens"),                        resultSet.getInt("number_of_bedrooms"),                        resultSet.getInt("number_of_bathrooms"),                        eLocationKind,                        resultSet.getString("comments"),                        resultSet.getDouble("purchase_price"),                        resultSet.getDouble("treatment_fees"),                        resultSet.getDouble("renovation_fees_fo_sale"),                        resultSet.getDouble("renovation_fees_fo_renting"),                        resultSet.getDouble("divers_fees"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return houseToReturn;    }    public HouseLanguage getHouseLanguageByLanguage(String szHouseLanguage) {        lock.lock();        HouseLanguage houseLanguageToReturn = null;        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_HOUSE_LANGUAGE_NAME + " WHERE house_language= \"" + szHouseLanguage+"\"");            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get House Language, For Language : " + szHouseLanguage);            // Looping over the result user-names            while (resultSet.next()) {                houseLanguageToReturn = new HouseLanguage();                houseLanguageToReturn.setHouseLanguage(resultSet.getString("house_language"));                houseLanguageToReturn.setHouseId(resultSet.getString("house_id"));                houseLanguageToReturn.setAddress(resultSet.getString("address"));                houseLanguageToReturn.setState(resultSet.getString("state"));                houseLanguageToReturn.setCity(resultSet.getString("city"));                houseLanguageToReturn.setStreet(resultSet.getString("street"));                houseLanguageToReturn.setHouseNumber(resultSet.getString("house_number"));                houseLanguageToReturn.setHouseKind(resultSet.getString("house_kind"));                houseLanguageToReturn.setNumberOfRooms(resultSet.getString("number_of_rooms"));                houseLanguageToReturn.setNumberOfLivingRooms(resultSet.getString("number_of_living_rooms"));                houseLanguageToReturn.setNumberOfKitchens(resultSet.getString("number_of_kitchens"));                houseLanguageToReturn.setNumberOfBedrooms(resultSet.getString("number_of_bedrooms"));                houseLanguageToReturn.setNumberOfBathrooms(resultSet.getString("number_of_bathrooms"));                houseLanguageToReturn.setLocationKind(resultSet.getString("location_kind"));                houseLanguageToReturn.setScore(resultSet.getString("score"));                houseLanguageToReturn.setComments(resultSet.getString("comments"));                houseLanguageToReturn.setPurchasePrice(resultSet.getString("purchase_price"));                houseLanguageToReturn.setTreatmentFees(resultSet.getString("treatment_fees"));                houseLanguageToReturn.setRenovationFees(resultSet.getString("renovation_fees"));                houseLanguageToReturn.setDiversFees(resultSet.getString("divers_fees"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return houseLanguageToReturn;    }    public ArrayList<String> getListOFExistingLanguage() {        lock.lock();        ArrayList<String> lstExistingLanguageToReturn = new ArrayList<>();        try {            Class.forName("com.mysql.jdbc.Driver").newInstance();            connect = DriverManager.getConnection("jdbc:mysql://localhost/shamayim?user=" + DATA_BASE_USER_NAME                    + "&password=" + DATA_BASE_PASSWORD_NAME);            // Statements allow to issue SQL queries to the database            preparedStatement = connect.prepareStatement("select * from " + TABLE_HOUSE_LANGUAGE_NAME);            // Result set get the result of the SQL query            resultSet = preparedStatement.executeQuery();            // INFO            play.Logger.info("<DATA_BASE> Get List Of Existing Language");            // Looping over the result user-names            while (resultSet.next()) {                lstExistingLanguageToReturn.add(resultSet.getString("house_language"));            }        } catch (Exception e) {            e.printStackTrace();            play.Logger.error(e.getMessage());        } finally {            // Closing the resultSet            close();        }        lock.unlock();        return lstExistingLanguageToReturn;    }}