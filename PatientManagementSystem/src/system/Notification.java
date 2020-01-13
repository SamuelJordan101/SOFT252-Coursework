package system;

import java.io.*;

public class Notification {

    private String Message;

    public Notification(String Message) {
        this.Message = Message;
    }

    public String getMessage() {
        return Message;
    }

    public String setMessage(String Message) {
        this.Message = Message;
    }
}
