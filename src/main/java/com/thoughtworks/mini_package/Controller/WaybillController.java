package com.thoughtworks.mini_package.Controller;

import com.thoughtworks.mini_package.Entity.Waybill;
import com.thoughtworks.mini_package.Service.WaybillService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waybill")
public class WaybillController {

    public static final String WAY_BILL_NOT_FOUND = "WayBill Not found";
    public static final String STATUS_NOT_FOUND = "Status not found";

    @Autowired
    WaybillService waybillService;

    @PostMapping(produces = "application/json")
    public HttpEntity addPackage (@RequestBody Waybill waybill) throws NotFoundException {

        if (waybill != null){
            waybillService.addPackage(waybill);
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new NotFoundException(WAY_BILL_NOT_FOUND);
    }

    @GetMapping(produces = "application/json")
    public List<Waybill> getAllWaybill (){
        return waybillService.getAllWaybill();
    }

    @GetMapping(value = "/getWayBillByStatus/{status}",produces = "application/json")
    public List<Waybill> getAllByStatus (@PathVariable String status) throws NotFoundException {
        if (status != null){
            return waybillService.getAllWaybillByStatus(status);
        }
        throw new NotFoundException(STATUS_NOT_FOUND);
    }

    @PatchMapping(value = "bookAPickup/{waybillNum}",produces = "application/json")
    public HttpEntity bookAPickup (@PathVariable String waybillNum, @RequestBody Waybill waybill ) throws NotFoundException {
        if (waybillNum != null || waybill != null){
            waybillService.bookAPickup(waybillNum, waybill);
            return new ResponseEntity(HttpStatus.OK);
        }

        throw new NotFoundException("WayBill Not found");
    }



}

