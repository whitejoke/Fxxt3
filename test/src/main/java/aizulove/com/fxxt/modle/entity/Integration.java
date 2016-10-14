package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;

/**
 * Created by joker on 2016/8/3.
 */
public class Integration implements Serializable {
    private String title;
    private String content;
    private String addtime;
    private String reason;
    private int balance;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
