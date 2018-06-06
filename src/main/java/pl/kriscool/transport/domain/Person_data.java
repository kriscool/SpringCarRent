package pl.kriscool.transport.domain;

import javax.persistence.*;

@Entity
@Table(name="person_data",schema = "public")
public class Person_data {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String token;
    
    @OneToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "id_person_data")
    private User user;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
