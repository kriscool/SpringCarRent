package pl.kriscool.transport.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role",schema = "public")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String role;
	  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	 @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
	  private Set<User> users = new HashSet<User>();
	
}
