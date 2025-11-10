package org.example.web_sockets;

public class WebSocketMessage {
    private String type;
    private String message;
    private Object data;

    public WebSocketMessage(String type, String message, Object data) {
        this.type = type;
        this.message = message;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
