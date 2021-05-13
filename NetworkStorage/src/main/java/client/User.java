package client;

public class User {
    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) throws InterruptedException {
        this.name = name;
        this.login = login;
        this.password = password;
        new Client().start(login);
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
