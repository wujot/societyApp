package login.register.society.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private List<Invitation> invitations;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    public User(){}

    public User(String username, String email, String password, boolean enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.invitations = invitations;
        this.friends = friends;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<Invitation> invitations) {
        this.invitations = invitations;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
