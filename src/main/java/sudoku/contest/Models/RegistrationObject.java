package sudoku.contest.Models;

public class RegistrationObject {

    private String email;
    private String username;
    private String password;

    private int exp;

    public RegistrationObject() {
    }

    public RegistrationObject(String email, String username, String password, int exp) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.exp = exp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "RegistrationObject{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", exp=" + exp +
                '}';
    }
}
