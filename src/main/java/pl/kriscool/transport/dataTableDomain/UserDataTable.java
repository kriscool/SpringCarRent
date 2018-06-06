package pl.kriscool.transport.dataTableDomain;

import pl.kriscool.transport.domain.User;

public class UserDataTable {
	
	public UserDataTable() {};
	public UserDataTable(User user,String buttons,String role,String buttonsRole) {
		super();
		this.firstName=user.getFirstname();
		this.lastName=user.getFirstname();
		this.email=user.getEmail();
		this.phone=user.getTelephone();
		this.buttons=buttons;
		this.login=user.getLogin();
		this.role=role;
		this.buttonsRole=buttonsRole;
	}

	private String firstName;
	private String lastName;
	private String phone;
	private String role;
	private String buttonsRole;
	public String getButtonsRole() {
		return buttonsRole;
	}
	public void setButtonsRole(String buttonsRole) {
		this.buttonsRole = buttonsRole;
	}

	private String email;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	private String login;
	private String buttons;

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}
	
}
