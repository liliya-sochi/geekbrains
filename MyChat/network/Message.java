package network;

import java.time.LocalDateTime;

public class Message {
    private String message;
    private String from;
    private String to;
    private LocalDateTime date;

    public Message(String message, String from, String to) {
        this.message = message;
        this.from = from;
        this.to = to;
        date = LocalDateTime.now();
    }

    public Message(String message, String from) {
        this.message = message;
        this.from = from;
        date = LocalDateTime.now();
    }
}
