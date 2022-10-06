package com.example.demo.service;


import com.example.demo.model.Sale;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {


    Sale getSaleById(long id);

    ResponseEntity<List<Sale>> getAll();

    Sale save(Sale sale);

    Sale updateSale(Sale sale);

    void deleteSaleById(Long id);

    void deleteAll();

    //---------------------------------------

    List<Sale> getSaleListLastWeek();

    List<String> getSaleListMostPopularLastMonth();

    List<String> getSaleListMostBuyingLastHalfAYear();

    List<String> getSaleListMostBuyingBy18YearOld();
}
