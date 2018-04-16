package login.register.society.model;

import javax.persistence.*;

@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;
    private boolean accepted;

    @ManyToOne
    private User user;

    public Invitation() {}

    public Invitation(String username, boolean accepted) {
        this.id = id;
        this.username = username;
        this.accepted = accepted;
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
