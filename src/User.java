public class User {
    private String username;
    private String password;
    private String phoneNumber;
    private boolean broker;

    public User(String userName, String password, String phoneNumber, boolean broker) {
        this.username = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.broker = broker;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsBroker() {
        return broker;
    }

    public void setBroker(boolean broker) {
        this.broker = broker;
    }

    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", broker=" + broker +
                '{';
    }
}
