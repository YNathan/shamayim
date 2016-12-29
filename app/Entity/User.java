package Entity;

/**
 * 
 * @author Yaacov
 *
 */
public class User {
	private String szUserName;
	private String szTelephone;
	private String szEmail;
	private String szPassword;
	private String szUserId;
	private String szPermission_manager;
	private String szPermission_view;

	public User(String szUserName, String szTelephone, String szEmail,String szPassword, String szUserId,String szPermission_manager,String szPermission_view) {
		super();
		this.szUserName = szUserName;
		this.szTelephone = szTelephone;
		this.szEmail = szEmail;
		this.szPassword = szPassword;
		this.szUserId = szUserId;
		this.szPermission_manager = szPermission_manager;
		this.szPermission_view = szPermission_view;
	}
	public String getUserName() {
		return szUserName;
	}

	public void setUserName(String szUserName) {
		this.szUserName = szUserName;
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

	public String getSzPermission_manager() {
		return szPermission_manager;
	}

	public void setSzPermission_manager(String szPermission_manager) {
		this.szPermission_manager = szPermission_manager;
	}

	public String getSzPermission_view() {
		return szPermission_view;
	}

	public void setSzPermission_view(String szPermission_view) {
		this.szPermission_view = szPermission_view;
	}

	@Override
	public String toString() {
		return "User{" +
				"UserName='" + szUserName + '\'' +
				", Telephone='" + szTelephone + '\'' +
				", Email='" + szEmail + '\'' +
				", Password='" + szPassword + '\'' +
				", UserId='" + szUserId + '\'' +
				", Permission_manager='" + szPermission_manager + '\'' +
				", Permission_view='" + szPermission_view + '\'' +
				'}';
	}

	public String toJson() {
		return "{ \"User\":[ {" +
				"\"UserName=\":\"" + szUserName + "\"" +
				",\"Telephone=\":\"" + szTelephone + "\"" +
				",\"Email=\":\"" + szEmail + "\"" +
				",\"Password=\":\"" + szPassword + "\"" +
				",\"UserId=\":\"" + szUserId + "\"" +
				",\"Permission_manager=\":\"" + szPermission_manager + "\"" +
				",\"Permission_view=\":\"" + szPermission_view + "\"" +
				"} ]}";
	}
}
