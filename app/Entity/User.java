package Entity;

/**
 * 
 * @author Yaacov
 *
 */
public class User {
	private String szUsername;
	private String szTelephone;
	private String szEmail;
	private String szPassword;
	private String szUserId;
	private String szPermissionManager;
	private String szPermissionView;

	public User(String szUserName, String szTelephone, String szEmail,String szPassword, String szUserId,String szPermission_manager,String szPermission_view) {
		super();
		this.szUsername = szUserName;
		this.szTelephone = szTelephone;
		this.szEmail = szEmail;
		this.szPassword = szPassword;
		this.szUserId = szUserId;
		this.szPermissionManager = szPermission_manager;
		this.szPermissionView = szPermission_view;
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

	public String getPermissionManager() {
		return szPermissionManager;
	}

	public void setSzPermissionManager(String szPermissionManager) {
		this.szPermissionManager = szPermissionManager;
	}

	public String getPermissionView() {
		return szPermissionView;
	}

	public void setSzPermissionView(String szPermissionView) {
		this.szPermissionView = szPermissionView;
	}

	@Override
	public String toString() {
		return "User{" +
				"Username='" + szUsername + '\'' +
				", Telephone='" + szTelephone + '\'' +
				", Email='" + szEmail + '\'' +
				", Password='" + szPassword + '\'' +
				", UserId='" + szUserId + '\'' +
				", PermissionManager='" + szPermissionManager + '\'' +
				", PermissionView='" + szPermissionView + '\'' +
				'}';
	}

	public String toJson() {
		return "{ \"User\":[ {" +
				"\"UserName=\":\"" + szUsername + "\"" +
				",\"Telephone=\":\"" + szTelephone + "\"" +
				",\"Email=\":\"" + szEmail + "\"" +
				",\"Password=\":\"" + szPassword + "\"" +
				",\"UserId=\":\"" + szUserId + "\"" +
				",\"Permission_manager=\":\"" + szPermissionManager + "\"" +
				",\"Permission_view=\":\"" + szPermissionView + "\"" +
				"} ]}";
	}
}
