package login.register.society.model;

import javax.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;

    @ManyToOne
    private User user;

    public Friend() {}

    public Friend(String username) {
        this.id = id;
        this.username = username;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
