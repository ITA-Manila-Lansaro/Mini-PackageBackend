package com.thoughtworks.mini_package.Entity;

import com.thoughtworks.mini_package.Entity.Recipients;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.sound.midi.Receiver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Waybill {
    @Id
    @GeneratedValue(generator =  "uuid")
    @GenericGenerator(name = "uuid", strategy =  "uuid")
    private String wayBillNumber;

    private String status;
    private String time;

    @ManyToOne
    private Recipients recipients;

    public Recipients getRecipients() {
        return recipients;
    }

    public void setRecipients(Recipients recipients) {
        this.recipients = recipients;
    }

    public String getWayBillNumber() {
        return wayBillNumber;
    }

    public void setWayBillNumber(String wayBillNumber) {
        this.wayBillNumber = wayBillNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
