package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertAddressData(Address address){
        boolean success = false;
        String sql = "INSERT INTO test VALUES(?,?,?)";
        int result = jdbcTemplate.update(sql, address.getAddress(), address.getLat(), address.getLon());
        if(result != 0)
            success = true;
        return success;
    }

    public List<String> getAddress(){
        List<String> addressList = new ArrayList<>();
        addressList.addAll(jdbcTemplate.queryForList("SELECT add FROM address", String.class));
        return addressList;
    }
}
