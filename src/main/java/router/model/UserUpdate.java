package router.model;

public class UserUpdate {
    public String name = null;
    public String password = null;
    public String country = null;
    public String image = null;

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

    public void setImage(String image) {
        this.image = image;
    }
}
