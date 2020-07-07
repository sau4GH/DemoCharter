package com.charter.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.charter.demo.entity.CustomerPurchase;
import com.charter.demo.entity.CustomerTotalRewards;
import com.charter.demo.entity.MonthlyRewards;

@Repository
public interface CustomerPurchaseRepo extends JpaRepository<CustomerPurchase, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM CUSTOMER_PURCHASE WHERE CUST_ID = ?1")
	public List<CustomerPurchase> findByCustId(Integer custId);

	@Query(nativeQuery = true, value = "SELECT * FROM CUSTOMER_PURCHASE WHERE CUST_ID = ?1 AND UPPER(MONTHNAME(TXN_DATE)) = ?2")
	public List<CustomerPurchase> findByMonth(Integer custId, String month);

	@Query(nativeQuery = true, value = "SELECT c.NAME as customerName, MONTHNAME(cp.TXN_DATE) as month, sum(cp.REWARDS) as totalRewards  FROM CUSTOMER c, CUSTOMER_PURCHASE cp "
			+ " WHERE cp.CUST_ID = ?1 AND cp.CUST_ID = c.ID GROUP BY c.NAME , MONTHNAME(cp.TXN_DATE)")
	public List<MonthlyRewards> getMonthlyRewards(Integer custId);

	@Query(nativeQuery = true, value = "SELECT c.NAME as customerName, sum(cp.REWARDS) as totalRewards  FROM CUSTOMER c, CUSTOMER_PURCHASE cp "
			+ " WHERE cp.CUST_ID = ?1 AND cp.CUST_ID = c.ID GROUP BY c.NAME")
	public List<CustomerTotalRewards> getCustomerTotalRewards(Integer custId);

}
