package com.example.demo.controler;


import com.example.demo.model.Sale;
import com.example.demo.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
//    private SaleDto saleToDto (Sale sale){
//        return  SaleDto.builder()
//                .id(sale.getId())
//                .name(sale.getName())
//                .lastName(sale.getLastName())
//                .age(sale.getAge())
//                .purchase_item(sale.getPurchase_item())
//                .count(sale.getCount())
//                .amount(sale.getAmount())
////                .purchase_date((sale.getPurchase_date()))
//                .build();
//    }
    //------------------------------------------

    //    @GetMapping("/sales")
//    public List<SaleDto> findAllControllerDTO() {
//        return saleService.getAll()
//                .stream()
//                .map(this::saleToDto)
//                .collect(Collectors.toList());
//    }
    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> findAllController() {

        return ResponseEntity.ok(saleService.getAll());
    }

    //---------------------------
    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> findByIdController(@PathVariable("id") long id) {


        return ResponseEntity.ok(saleService.getSaleById(id));
    }


    //----------------------------------------
    @PostMapping("/add")
    public ResponseEntity<Sale> saveController(@RequestBody Sale sale) {
        return ResponseEntity.ok(saleService.save(sale));
    }


    //-------------------------------------------------------
    @PutMapping("/edit/{id}")
    public ResponseEntity<Sale> updateController(@RequestBody Sale sale) {
        return ResponseEntity.ok(saleService.updateSale(sale));
    }

    //--------------------------------------------
    @DeleteMapping("/delete/{id}")
    public void DeleteController(@PathVariable("id") Long id) {
        saleService.deleteSaleById(id);
    }

    //--------------------------------------
    @DeleteMapping("/deleteAll")
    public void deleteAllController() {
        saleService.deleteAll();
    }


    //    ----------------------------------------------
    @GetMapping("/request1")

    public ResponseEntity<List<Sale>> getSaleListLastWeekController() {

        return ResponseEntity.ok(saleService.getSaleListLastWeek());

    }

    //------------------------------------------------------------
    @GetMapping("/request2")

    public ResponseEntity<List<Sale>> getSaleListMostPopularLastMonth() {

        return ResponseEntity.ok(saleService.getSaleListMostPopularLastMonth());

    }
}