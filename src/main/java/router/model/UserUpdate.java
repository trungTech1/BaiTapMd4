package router.model;

public class UserUpdate {
    String name = null;
    String password = null;
    String country = null;

    public UserUpdate() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
