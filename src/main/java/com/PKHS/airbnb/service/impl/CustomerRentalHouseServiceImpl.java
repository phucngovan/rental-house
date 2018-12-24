package com.PKHS.airbnb.service.impl;

import com.PKHS.airbnb.model.RentalHouse;
import com.PKHS.airbnb.repository.RentalHouseRepository;
import com.PKHS.airbnb.service.CustomerRentalHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerRentalHouseServiceImpl implements CustomerRentalHouseService {

    @Autowired
    RentalHouseRepository houseRepository;

    @Override
    public Page<RentalHouse> findAll(Pageable pageable) {
        return this.houseRepository.findAll(pageable);
    }

    @Override
    public Iterable<RentalHouse> findAll() {
        return this.houseRepository.findAll();
    }

    @Override
    public RentalHouse findById(Integer id) {
        return this.houseRepository.findById(id).orElse(null);
    }

    @Override
    public void save(RentalHouse house) {
        this.houseRepository.save(house);
    }

    @Override
    public void remove(Integer id) {
       this.houseRepository.deleteById(id);
    }

    @Override
    public Page<RentalHouse> findAllByPriceContaining(Optional<String> price, Pageable pageable) {
       return this.houseRepository.findAllByPriceContaining(price, pageable);
    }
}
