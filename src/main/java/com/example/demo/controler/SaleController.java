package com.example.demo.controler;


import com.example.demo.model.Sale;
import com.example.demo.service.SaleService;
//import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
//@RequestMapping("/api")

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
    @GetMapping("/")
    public String homePage(){
        return "qwerty";
    }

    @Operation(summary = "Getting all Sales",tags = "sale")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all sales", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Sale.class))}),
            @ApiResponse(responseCode = "404", description = "No any found sales", content = @Content)})

    @GetMapping("/sales")

    public ResponseEntity<List<Sale>> findAllController() {
        return saleService.getAll();
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

    public ResponseEntity<List<String>> getSaleListMostPopularLastMonth() {

        return ResponseEntity.ok(saleService.getSaleListMostPopularLastMonth());

    }
    //--------------------------------------------------------------
    @GetMapping("/request3")

    public ResponseEntity<List<String>> getSaleLastHalfAYear() {

        return ResponseEntity.ok(saleService.getSaleListMostBuyingLastHalfAYear());

    }
    @GetMapping("/request4")

    public ResponseEntity<List<String>> getSaleBy18YearOld() {

        return ResponseEntity.ok(saleService.getSaleListMostBuyingBy18YearOld());

    }
}