package com.example.demo.service;


import com.example.demo.model.Sale;
import com.example.demo.userRepo.SaleRepository;
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
        //если существует найденная сущность с таким id
        if (saleTerminal.isPresent()) {
            return saleTerminal.get();
        }
        //если не существует найденная сущность с таким id
        return null;
    }


    @Override
    public List<Sale> getAll() {

        return (List<Sale>) saleRepository.findAll();
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale updateSale(Sale sale) {

            Sale saleNew=new Sale();
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
    public Sale deleteSaleById(Long id) {
        Optional<Sale> saleTerminal = saleRepository.findById(id);
        if (saleTerminal.isPresent()) {
            saleRepository.deleteById(id);
            return null;
        }
        return null;
    }

    @Override
    public void deleteAll() {
        saleRepository.deleteAll();
    }


    //-------------------------------------------
    @Override
    public List<Sale> getSaleListLastWeek() {
        return saleRepository.findSaleLastWeek();
    }


    @Override
    public List<Sale> getSaleListMostPopularLastMonth() {
        return saleRepository.findSaleLastMonth();
    }

    @Override
    public List<Sale> getSaleListMostBuyingLastHalfAYear() {
        return null;
    }

    @Override
    public List<Sale> getSaleListMostBuyingBy18YearOld() {
        return null;
    }
}
