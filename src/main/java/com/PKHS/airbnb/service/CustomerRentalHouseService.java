package com.PKHS.airbnb.service;

import com.PKHS.airbnb.model.RentalHouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CustomerRentalHouseService {
    Page<RentalHouse> findAll(Pageable pageable);

    Iterable<RentalHouse> findAll();

    RentalHouse findById(Integer id);

    void save(RentalHouse house);

    void remove(Integer id);

    Page<RentalHouse> findAllByPriceContaining(Optional<String> price, Pageable pageable);
}
