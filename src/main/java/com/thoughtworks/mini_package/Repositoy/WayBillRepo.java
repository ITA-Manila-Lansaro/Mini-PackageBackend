package com.thoughtworks.mini_package.Repositoy;

import com.thoughtworks.mini_package.Entity.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WayBillRepo extends JpaRepository<Waybill, String> {
    List<Waybill> findWaybillByStatus(String status);
}
