package pl.kriscool.transport.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="person",schema = "public")
public class User {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role", 
        joinColumns = { @JoinColumn(name = "user_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<UserRole> roles = new HashSet<UserRole>();


    private String token;
	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Expose
    private String firstname;
    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLasttname() {
		return lasttname;
	}

	public void setLasttname(String lasttname) {
		this.lasttname = lasttname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	private String password;
	@Expose
	private String lasttname;
	@Expose
    @Column(unique=true)
    private String email;
	@Expose
    private String telephone;
    @Column(name = "active", columnDefinition = "boolean default false", nullable = false)
    @Expose
    private Boolean active;
    @Expose
    @Column(unique=true)
	private String login;
	
	@OneToOne(cascade = { CascadeType.REMOVE })
	private Order order;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


}
