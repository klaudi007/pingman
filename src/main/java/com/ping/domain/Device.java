package com.ping.domain;

/**
 * @author musaal
 * @date 2/2/2021 : 6:08 PM
 * Currently model is not thread safe.
 * Use it within one Thread
 */
public class Device {

    private String name;
    private String ip;
    private String address;
    private String desc;
    private boolean responsiblePersonNotified;
    private boolean reachable;
    private boolean issueFixed;
    public Device(){
        super();
    }

    public Device(String name, String ip){
        this.name = name;
        this.ip = ip;
    }

    public Device(String name, String ip, String address, String desc) {
        this.name = name;
        this.ip = ip;
        this.address = address;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isResponsiblePersonNotified() {
        return responsiblePersonNotified;
    }

    public void setResponsiblePersonNotified(boolean responsiblePersonNotified) {
        this.responsiblePersonNotified = responsiblePersonNotified;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public boolean isIssueFixed() {
        return issueFixed;
    }

    public void setIssueFixed(boolean issueFixed) {
        this.issueFixed = issueFixed;
    }
}
