package com.example.demo.service;


import com.example.demo.model.Sale;
import com.example.demo.userRepo.SaleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;

    public SaleServiceImpl(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale getSaleById(long id) {
        Optional<Sale> saleTerminal = saleRepository.findById(id);
        if (saleTerminal.isPresent()) {
            return saleTerminal.get();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<Sale>> getAll() {
        Optional<List<Sale>> saleTerminal = Optional.of(saleRepository.findAll());
        if (saleTerminal.isPresent()) {
            return ResponseEntity.ok(saleTerminal.get());
        }
        return null;
    }


//    @Override
//    public ResponseEntity<List<Sale>> getAll() {
//        return ResponseEntity.ok( saleRepository.findAll());
//    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateSale(Sale sale) {

        Sale saleNew = new Sale();
        saleNew.setId(sale.getId());
        saleNew.setName(sale.getName());
        saleNew.setLastName(sale.getLastName());
        saleNew.setAge(sale.getAge());
        saleNew.setPurchase_item(sale.getPurchase_item());
        saleNew.setCount(sale.getCount());
        saleNew.setAmount(sale.getAmount());
        saleNew.setPurchase_date(sale.getPurchase_date());

        return saleNew;
    }

    @Override
    public void deleteSaleById(Long id) {
        Optional saleTerminal = saleRepository.findById(id);
        if (saleTerminal.isPresent()) {
            saleRepository.deleteById(id);

        }

    }

    @Override
    public void deleteAll() {
       Optional<List<Sale>> saleTerminal = Optional.of(saleRepository.findAll());
       if(saleTerminal.isPresent()){
        saleRepository.deleteAll();
       }
       else {
           System.out.println("таблица пуста");
       }

    }


    //-------------------------------------------
    @Override
    public List<Sale> getSaleListLastWeek() {
        return saleRepository.findSaleLastWeek();
    }


    @Override
    public List<String> getSaleListMostPopularLastMonth() {
        return saleRepository.findSaleLastMonth();
    }

    @Override
    public List<String> getSaleListMostBuyingLastHalfAYear() {
        return saleRepository.findSaleHalfAYear();
    }

    @Override
    public List<String> getSaleListMostBuyingBy18YearOld() {
        return saleRepository.findSaleBy18YearOld();
    }
}
