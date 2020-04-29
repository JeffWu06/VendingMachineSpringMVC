/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Jeff
 */
public class VendingMachineDaoTest {
    private VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
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
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStockOnHand method, of class VendingMachineDao.
     */
    @Test
    public void testLoadInventoryAndGetStockOnHand() throws Exception {
        dao.loadInventory();
        List<Item> testStockList = dao.getStockOnHand();
        assertNotEquals(0, testStockList.size());        
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem1() throws Exception {
        Item testItem = dao.getItem("1");
        assertTrue(testItem.getName().equals("Snickers"));
        assertEquals(new BigDecimal("1.85"), testItem.getPrice());
        assertEquals(9, testItem.getQty());
    }
    
    @Test
    public void testGetItem999() throws Exception {
        Item testItem = dao.getItem("999");
        assertNull(testItem);
    }

    /**
     * Test of vendItem method, of class VendingMachineDao.
     */
    @Test
    public void testVendItem1() throws Exception {
        String itemId = "1";
        long preVendQty = dao.getItem(itemId).getQty();
        Item vendedItem1 = dao.vendItem(itemId);
        assertTrue(vendedItem1.getName().equals("Snickers"));
        assertEquals(new BigDecimal("1.85"), vendedItem1.getPrice());
        assertEquals(preVendQty - 1, dao.getItem(itemId).getQty());
    }
    
    @Test
    public void testVendItem999() throws Exception {
        String itemId = "999";
        Item vendedItem = null;
        try {
            vendedItem = dao.vendItem(itemId);
            fail("Expected NullPointerException not thrown.");
        } catch (Exception e) {
            return;
        }
    }
    
    @Test
    public void testVendItemBlank() throws Exception {
        String itemId = "";
        Item vendedItem = null;
        try {
            vendedItem = dao.vendItem(itemId);
            fail("Expected NullPointerException not thrown.");
        } catch (Exception e) {
            return;
        }
    }
    
}
