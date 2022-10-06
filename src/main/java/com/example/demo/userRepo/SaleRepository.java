package com.example.demo.userRepo;

import com.example.demo.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(value = "SELECT * FROM sale s\n" +
            "WHERE s.purchase_date >= date(now()-interval '1 week')\n"
            , nativeQuery = true)
    List<Sale> findSaleLastWeek();
//------------------------------------------------------------------

    @Query(value = "SELECT sale.purchase_item FROM sale\n" +
            "WHERE purchase_date >= date(now() - interval '1 month')\n" +
            "GROUP BY purchase_item\n" +
            "HAVING COUNT(purchase_item) =\n" +
            "(SELECT MAX(count_it)\n" +
            "           FROM (\n" +
            "          SELECT COUNT(purchase_item) AS count_it FROM Sale\n" +
            "          WHERE purchase_date >= date(now() - interval '1 month')\n" +
            "\t\t\tGROUP BY purchase_item\n" +
            "              ) e1 )", nativeQuery = true)
    List<String> findSaleLastMonth();



//---------------------------------------------------------------------------



    @Query(value = "SELECT\n" +
            "   Sale.name,Sale.last_name\n" +
            "FROM\n" +
            "    Sale\n" +
            "WHERE\n" +
            "        purchase_date >= date(now() - interval '6 month')\n" +
            "GROUP BY\n" +
            "    name,\n" +
            "    last_name\n" +
            "HAVING\n" +
            "        COUNT(Sale.name) = (\n" +
            "        SELECT\n" +
            "            MAX(count_it)\n" +
            "        FROM  (SELECT\n" +
            "                            Sale.name,\n" +
            "                            Sale.last_name,\n" +
            "                            COUNT(Sale.last_name) AS count_it\n" +
            "             FROM\n" +
            "                            Sale\n" +
            "             WHERE\n" +
            "                                purchase_date  >= date(now() - interval '6 month')\n" +
            "             group by Sale.name, Sale.last_name) e2\n" +
            "                   )", nativeQuery = true)
    List<String> findSaleHalfAYear();

//----------------------------------------------------------------------------------

    @Query(value = "SELECT purchase_item ,sum(count)\n" +
            "FROM\n" +
            "    Sale\n" +
            "WHERE\n" +
            "        age = 25\n" +
            "GROUP BY\n" +
            "    purchase_item\n" +
            "HAVING\n" +
            "        COUNT(purchase_item) = (\n" +
            "        SELECT\n" +
            "            MAX(count_it)\n" +
            "        FROM\n" +
            "            (             SELECT\n" +
            "                              purchase_item,\n" +
            "                              COUNT(*) count_it\n" +
            "                          FROM\n" +
            "                              Sale\n" +
            "                          WHERE\n" +
            "                                  age = 25\n" +
            "                          GROUP BY\n" +
            "                              purchase_item               ) e1 )", nativeQuery = true)
    List<String> findSaleBy18YearOld();

}
