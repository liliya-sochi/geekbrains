package core;

public class NickResponse extends AbstractMessage {
    private final String nick;

    public NickResponse(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }
}
