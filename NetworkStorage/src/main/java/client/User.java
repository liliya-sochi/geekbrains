package client;

public class User {
    private Client client;
    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) throws InterruptedException {
        this.name = name;
        this.login = login;
        this.password = password;
        new Thread(new Runnable() {
            @Override
            public void run() {
                client = new Client();
                client.start(login);
            }
        }).start();
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public void userExit() {
        client.stop();
    }
}
