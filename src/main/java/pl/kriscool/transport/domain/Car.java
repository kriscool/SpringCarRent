package pl.kriscool.transport.domain;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="car",schema = "public")
public class Car {
	
    @ManyToMany(cascade = { CascadeType.REMOVE },mappedBy = "cars",fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<Order>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Expose
    private String marka;
    @Expose
    private String tablica;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTablica() {
        return tablica;
    }

    public void setTablica(String tablica) {
        this.tablica = tablica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
