package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertAddressData(Address address){
        boolean success = false;
        String sql = "위도경도 넣을게~";
        int result = jdbcTemplate.update(sql, address.getAddress(), address.getLat(), address.getLon());
        if(result != 0)
            success = true;
        return success;
    }
}
