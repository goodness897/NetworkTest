package com.mu.networktest;

/**
 * Created by Mu on 2016-10-20.
 */

public class User implements java.io.Serializable {

    private int userNum;
    private String userComment;
    private String userNick;
    private String userId;
    private String userFile;

    public int getUserNum() {
        return userNum;
    }
//
    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserFile() {
        return userFile;
    }

    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }
}
