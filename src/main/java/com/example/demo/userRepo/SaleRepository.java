package com.example.demo.userRepo;

import com.example.demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(value = "SELECT * FROM Sale.Sale\n" +
            "WHERE `Sale_purchase_date` >= date(now()-interval '1 week')\n"
            , nativeQuery = true)
    List<Sale> findSaleLastWeek();
//------------------------------------------------------------------

    @Query(value = "SELECT * FROM Sale\n" +
            "WHERE `purchase_date` >= date(now() - interval '1 month')\n" +
            "GROUP BY purchase_item\n" +
            "HAVING COUNT(purchase_item) =\n" +
            "(SELECT MAX(count_it)\n" +
            "           FROM (\n" +
            "          SELECT COUNT(purchase_item) AS count_it FROM Sale\n" +
            "          WHERE purchase_date >= date(now() - interval '1 month')\n" +
            "\t\t\tGROUP BY purchase_item\n" +
            "              ) e1 )", nativeQuery = true)
    List<Sale> findSaleLastMonth();



//---------------------------------------------------------------------------



    @Query(value = "SELECT * FROM Sale\n" +
            "WHERE purchase_date >= date(now() - interval '6 month')\n" +
            "GROUP BY Sale_name, last_name\n" +
            "HAVING COUNT(Sale_name) =\n" +
            "(SELECT MAX(count_it)\n" +
            "           FROM (\n" +
            "          SELECT Sale_name, last_name, COUNT(last_name) AS count_it FROM Sale\n" +
            "          WHERE purchase_date  >= date(now() - interval '6 month'))\n" +
            "\t\t\tGROUP BY Sale_name, Sale_last_name\n" +
            "              ) e1 )", nativeQuery = true)
    List<Sale> findSaleHalfAYear();

//----------------------------------------------------------------------------------

    @Query(value = "SELECT * " +
            "FROM Sale " +
            "WHERE age = 18 " +
            "GROUP BY purchase_item " +
            "HAVING COUNT(purchase_item) = (SELECT MAX(count_it) " +
            "           FROM (\n" +
            "            SELECT purchase_item, COUNT(*) count_it" +
            "            FROM Sale " +
            "            WHERE age = 18 " +
            "            GROUP BY purchase_item " +
            "              ) e1 )", nativeQuery = true)
    List<Sale> findSaleBy18YearOld();

}
