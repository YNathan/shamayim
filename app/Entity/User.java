package Entity;

/**
 * 
 * @author Yaacov
 *
 */
public class User {
	private String szUserName;
	private String szfirstName;
	private String szLastName;
	private String szTelephone;
	private String szEmail;
	private String szPassword;
	private String szBirthday;
	private String szUserId;

	public User(String szUserName, String szfirstName, String szLastName, String szTelephone, String szEmail,
			String szPassword, String szBirthday, String szUserId) {
		super();
		this.szUserName = szUserName;
		this.szfirstName = szfirstName;
		this.szLastName = szLastName;
		this.szTelephone = szTelephone;
		this.szEmail = szEmail;
		this.szPassword = szPassword;
		this.szBirthday = szBirthday;
		this.szUserId = szUserId;
	}
	public String getUserName() {
		return szUserName;
	}

	public void setUserName(String szUserName) {
		this.szUserName = szUserName;
	}

	public String getfirstName() {
		return szfirstName;
	}

	public void setfirstName(String szfirstName) {
		this.szfirstName = szfirstName;
	}

	public String getLastName() {
		return szLastName;
	}

	public void setLastName(String szLastName) {
		this.szLastName = szLastName;
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

	public String getBirthday() {
		return szBirthday;
	}

	public void setBirthday(String szBirthday) {
		this.szBirthday = szBirthday;
	}

	public String getUserId() {
		return szUserId;
	}

	public void setUserId(String szUserId) {
		this.szUserId = szUserId;
	}

	@Override
	public String toString() {
		return "User [UserName=" + szUserName + ", firstName=" + szfirstName + ", LastName=" + szLastName
				+ ", Telephone=" + szTelephone + ", Email=" + szEmail + ", Password=" + szPassword
				+ ", Birthday=" + szBirthday + ", UserId=" + szUserId + "]";
	}


}
