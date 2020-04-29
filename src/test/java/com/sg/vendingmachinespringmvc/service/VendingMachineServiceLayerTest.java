/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jeff
 */
public class VendingMachineServiceLayerTest {
    
    private VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("serviceLayer", VendingMachineServiceLayer.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStockOnHand method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetStockOnHand() throws Exception {
        List<Item> testStockList = service.getStockOnHand();
        assertEquals(2, testStockList.size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem1() throws Exception {
        String testId = "1";
        Item fromService1 = service.getItem(testId);
        assertTrue(fromService1.getName().equals("Snickers"));
    }
    
    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem2() throws Exception {
        String testId = "2";
        Item fromService2 = service.getItem(testId);
        assertTrue(fromService2.getName().equals("Almond Joy"));
    }
    
    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItemXxy() throws Exception {
        String testId = "xyz";
        Item fromService = service.getItem(testId);
        assertNull(fromService);
    }

    /**
     * Test of vendItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testDepositMoneyVendItem1GetUserBalance() throws Exception {
        BigDecimal testDeposit = new BigDecimal("2.00");
        service.depositMoney(testDeposit);
        String testId = "1";
        long preVendQty = service.getItem(testId).getQty();
        Item vendedItem = service.vendItem(testId);
        assertNotNull(vendedItem);
        assertEquals(testDeposit.subtract(vendedItem.getPrice()), service.getUserBalance());
        assertEquals(preVendQty - 1, vendedItem.getQty());
    }
    
    /**
     * Test of vendItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testVendItem1IsfGetUserBalance() throws Exception {
        BigDecimal testDeposit = new BigDecimal("1.00");
        service.depositMoney(testDeposit);
        String testId = "1";
        try {
            Item vendedItem = service.vendItem(testId);
            fail("Expected VendingMachineInsufficientFundsException not thrown.");
        } catch (Exception e) {
            // Do nothing;
        }
        assertEquals(testDeposit, service.getUserBalance());
    }
    /**
     * Test of vendItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testVendItem2NoStockGetUserBalance() throws Exception {
        BigDecimal testDeposit = new BigDecimal("2.00");
        service.depositMoney(testDeposit);
        String testId = "2";
        try {
            Item vendedItem = service.vendItem(testId);
            fail("Expected VendingMachineNoItemInventoryException not thrown.");
        } catch (Exception e) {
            // Do nothing;
        }
        assertEquals(testDeposit, service.getUserBalance());
    }
    /**
     * Test of getChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetChange() throws Exception {
        BigDecimal testDeposit = new BigDecimal("2.00");
        service.depositMoney(testDeposit);
        String testId = "1";
        Item vendedItem = service.vendItem(testId);
        Change testChange = service.getChange(service.getUserBalance());
        assertEquals(testDeposit.subtract(vendedItem.getPrice()), testChange.getAmount());
    }

    /**
     * Test of getSelectedItemId method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAndSetSelectedItemId() {
        String testId = "2";
        service.setSelectedItemId(testId);
        String fromService = service.getSelectedItemId();
        assertEquals(testId, fromService);
    }

    
}
