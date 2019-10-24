package com.thoughtworks.mini_package.Service;

import com.thoughtworks.mini_package.Entity.Recipients;
import com.thoughtworks.mini_package.Entity.Waybill;
import com.thoughtworks.mini_package.Repositoy.RecipientsRepo;
import com.thoughtworks.mini_package.Repositoy.WayBillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WaybillService {

    public static final String RESERVATION = "Reservation";
    @Autowired
    WayBillRepo wayBillRepo;

    @Autowired
    RecipientsRepo recipientsRepo;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    public void addPackage(Waybill waybill) {
        recipientsRepo.save(waybill.getRecipients());
        wayBillRepo.save(waybill);
    }

    public List<Waybill> getAllWaybill() {
        return wayBillRepo.findAll();
    }

    public List<Waybill> getAllWaybillByStatus(String status) {
        return wayBillRepo.findWaybillByStatus(status);
    }

    public Optional<Waybill> bookAPickup(String waybillNumber, Waybill waybill) {
        Optional<Waybill> foundWaybill = wayBillRepo.findById(waybillNumber);

        foundWaybill.ifPresent(a -> {
            a.setTime(dateFormat.format(waybill.getTime()));
            a.setStatus(RESERVATION);
            wayBillRepo.save(a);
        });

        return foundWaybill;
    }
}
