package com.thoughtworks.mini_package.Repositoy;

import com.thoughtworks.mini_package.Entity.Recipients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientsRepo extends JpaRepository<Recipients, String> {

}

