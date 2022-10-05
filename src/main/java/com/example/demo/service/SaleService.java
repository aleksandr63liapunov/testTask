package com.example.demo.service;


import com.example.demo.model.Sale;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SaleService {


    Sale getSaleById(long id);

    List<Sale> getAll();

    Sale save(Sale sale);

    Sale updateSale(Sale sale);

    Sale deleteSaleById(Long id);

    void deleteAll();

    //---------------------------------------

    List<Sale> getSaleListLastWeek();

    List<Sale> getSaleListMostPopularLastMonth();

    List<Sale> getSaleListMostBuyingLastHalfAYear();

    List<Sale> getSaleListMostBuyingBy18YearOld();
}
