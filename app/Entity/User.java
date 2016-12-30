package Entity;

/**
 * @author Yaacov
 */
public class User {
    private String szUsername;
    private String szTelephone;
    private String szEmail;
    private String szPassword;
    private String szUserId;
    private boolean bPermissionManager;
    private boolean bPermissionView;

    public User(String szUserName, String szTelephone, String szEmail, String szPassword, String szUserId, String szPermission_manager, String szPermission_view) {
        super();
        this.szUsername = szUserName;
        this.szTelephone = szTelephone;
        this.szEmail = szEmail;
        this.szPassword = szPassword;
        this.szUserId = szUserId;
        this.bPermissionManager = convertFromStringDataBaseFormatToBoolean(szPermission_manager);
        this.bPermissionView = convertFromStringDataBaseFormatToBoolean(szPermission_view);
    }

    public User() {
    }

    public String getUsername() {
        return szUsername;
    }

    public void setUserName(String szUserName) {
        this.szUsername = szUserName;
    }

    public String getTelephone() {
        return szTelephone;
    }

    public void setTelephone(String szTelephone) {
        this.szTelephone = szTelephone;
    }

    public String getEmail() {
        return szEmail;
    }

    public void setEmail(String szEmail) {
        this.szEmail = szEmail;
    }

    public String getPassword() {
        return szPassword;
    }

    public void setPassword(String szPassword) {
        this.szPassword = szPassword;
    }

    public String getUserId() {
        return szUserId;
    }

    public void setUserId(String szUserId) {
        this.szUserId = szUserId;
    }

    public boolean getPermissionManager() {
        return bPermissionManager;
    }

    public void setPermissionManager(String szPermissionManager) {
        this.bPermissionManager = convertFromStringClientToBoolean(szPermissionManager);
    }

    public void setPermissionManager(boolean bPermissionManager) {
        this.bPermissionManager = bPermissionManager;
    }

    public boolean getPermissionView() {
        return bPermissionView;
    }

    public void setPermissionView(String szPermissionView) {
        this.bPermissionView = convertFromStringClientToBoolean(szPermissionView);
    }

    public void setPermissionView(boolean bPermissionView) {
        this.bPermissionView = bPermissionView;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + szUsername + '\'' +
                ", Telephone='" + szTelephone + '\'' +
                ", Email='" + szEmail + '\'' +
                ", Password='" + szPassword + '\'' +
                ", UserId='" + szUserId + '\'' +
                ", PermissionManager='" + bPermissionManager + '\'' +
                ", PermissionView='" + bPermissionView + '\'' +
                '}';
    }

    public String toJson() {
        return "{ \"User\":[ {" +
                "\"UserName=\":\"" + szUsername + "\"" +
                ",\"Telephone=\":\"" + szTelephone + "\"" +
                ",\"Email=\":\"" + szEmail + "\"" +
                ",\"Password=\":\"" + szPassword + "\"" +
                ",\"UserId=\":\"" + szUserId + "\"" +
                ",\"Permission_manager=\":\"" + bPermissionManager + "\"" +
                ",\"Permission_view=\":\"" + bPermissionView + "\"" +
                "} ]}";
    }

    public boolean convertFromStringDataBaseFormatToBoolean(String szBoolean) {
        boolean bBooleanToReturn = false;
        if (szBoolean.equals("0")) {
            bBooleanToReturn = true;
        } else if (szBoolean.equals("1")) {
            bBooleanToReturn = false;
        }
        return bBooleanToReturn;
    }

    public boolean convertFromStringClientToBoolean(String szBoolean) {
        boolean bBooleanToReturn = false;
        if (szBoolean.equals("true")) {
            bBooleanToReturn = true;
        } else if (szBoolean.equals("false")) {
            bBooleanToReturn = false;
        }
        return bBooleanToReturn;
    }

    public String convertBooleanToString(boolean bBoolean) {
        String szBooleanToReturn = "false";
        if (bBoolean == true) {
            szBooleanToReturn = "true";
        } else if (bBoolean == false) {
            szBooleanToReturn = "false";
        }
        return szBooleanToReturn;
    }

    public String convertBooleanToDataBaseFormatString(boolean bBoolean) {
        String szBooleanToReturn = "false";
        if (bBoolean == true) {
            szBooleanToReturn = "0";
        } else if (bBoolean == false) {
            szBooleanToReturn = "1";
        }
        return szBooleanToReturn;
    }
}
