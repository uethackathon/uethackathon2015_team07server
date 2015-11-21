package com.uet.quantity.uethackathon2015_team7.entity;

/**
 * Created by James Crabby on 11/21/2015.
 */
public class TimeSettingEntity {
    String time;
    String reply;
    Boolean status;

    public TimeSettingEntity(String reply, Boolean status, String time) {
        this.reply = reply;
        this.status = status;
        this.time = time;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
