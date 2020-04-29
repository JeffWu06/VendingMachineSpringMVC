/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Jeff
 */
public class VendingMachineInMemDao implements VendingMachineDao {

    private Map<String, Item> itemStock = new HashMap<>();

    public VendingMachineInMemDao() throws VendingMachinePersistenceException {
        loadInventory();
    }    
    
    @Override
    public void loadInventory() throws VendingMachinePersistenceException {
        Item item1 = new Item();
        item1.setItemId("1");
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQty(9);
        itemStock.put(item1.getItemId(), item1);
        
        Item item2 = new Item();
        item2.setItemId("2");
        item2.setName("M & M's");
        item2.setPrice(new BigDecimal("1.50"));
        item2.setQty(2);
        itemStock.put(item2.getItemId(), item2);
        
        Item item3 = new Item();
        item3.setItemId("3");
        item3.setName("Pringles");
        item3.setPrice(new BigDecimal("2.10"));
        item3.setQty(5);
        itemStock.put(item3.getItemId(), item3);
        
        Item item4 = new Item();
        item4.setItemId("4");
        item4.setName("Reese's");
        item4.setPrice(new BigDecimal("1.85"));
        item4.setQty(4);
        itemStock.put(item4.getItemId(), item4);
        
        Item item5 = new Item();
        item5.setItemId("5");
        item5.setName("Pretzels");
        item5.setPrice(new BigDecimal("1.25"));
        item5.setQty(9);
        itemStock.put(item5.getItemId(), item5);
        
        Item item6 = new Item();
        item6.setItemId("6");
        item6.setName("Twinkies");
        item6.setPrice(new BigDecimal("3.25"));
        item6.setQty(3);
        itemStock.put(item6.getItemId(), item6);
        
        Item item7 = new Item();
        item7.setItemId("7");
        item7.setName("Doritos");
        item7.setPrice(new BigDecimal("1.75"));
        item7.setQty(11);
        itemStock.put(item7.getItemId(), item7);
        
        Item item8 = new Item();
        item8.setItemId("8");
        item8.setName("Almond Joy");
        item8.setPrice(new BigDecimal("1.85"));
        item8.setQty(0);
        itemStock.put(item8.getItemId(), item8);
        
        Item item9 = new Item();
        item9.setItemId("9");
        item9.setName("Trident");
        item9.setPrice(new BigDecimal("1.95"));
        item9.setQty(6);
        itemStock.put(item9.getItemId(), item9);        
    }
    
    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        // Do nothing.
    }
    
    @Override
    public List<Item> getStockOnHand() throws VendingMachinePersistenceException {
        return itemStock.values().stream().collect(Collectors.toList());
    }

    @Override
    public Item getItem(String itemKey) throws VendingMachinePersistenceException {
        return itemStock.get(itemKey);
    }

    @Override
    public Item vendItem(String itemKey) throws VendingMachinePersistenceException {
        Item purchasedItem = getItem(itemKey);
        purchasedItem.setQty(purchasedItem.getQty() - 1);
        return purchasedItem;
    }
    
}
