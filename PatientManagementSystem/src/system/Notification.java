package system;

import java.io.*;

public class Notification implements Serializable {

    private String Message;

    public Notification(String Message) {
        this.Message = Message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
