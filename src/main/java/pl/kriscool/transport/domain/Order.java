package pl.kriscool.transport.domain;


import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="order",schema = "public")
public class Order {


    @ManyToMany(cascade = { CascadeType.REMOVE },fetch = FetchType.EAGER)
    @JoinTable(
            name = "car_order",
            joinColumns = { @JoinColumn(name = "car_id") },
            inverseJoinColumns = { @JoinColumn(name = "order_id") }
    )
    private Set<Car> cars = new HashSet<Car>();

    private Date dateorder;
    public Date getDateorder() {
		return dateorder;
	}

	public void setDateorder(Date dateorder) {
		this.dateorder = dateorder;
	}

	private String description;
    
    @OneToOne
    private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }


}
