/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeff
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private List<Item> itemStock = new ArrayList<>();
    private Item item1, item2;

    public VendingMachineDaoStubImpl() throws VendingMachinePersistenceException {
        loadInventory();
    }
    
    @Override
    public List<Item> getStockOnHand() throws VendingMachinePersistenceException {
        return itemStock;
    }

    @Override
    public Item getItem(String itemKey) throws VendingMachinePersistenceException {
        if (itemKey.equals(item1.getItemId())) {
            return item1;
        } else if (itemKey.equals(item2.getItemId())) {
            return item2;
        } else {
            return null;
        }
    }

    @Override
    public Item vendItem(String itemKey) throws VendingMachinePersistenceException {
        Item item = getItem(itemKey);
        if (item != null) {
            item.setQty(item.getQty() - 1);
        }
        return item;
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException {
        item1 = new Item();
        item1.setItemId("1");
        item1.setName("Snickers");
        item1.setPrice(new BigDecimal("1.85"));
        item1.setQty(9);
        itemStock.add(item1);
        
        item2 = new Item();
        item2.setItemId("2");
        item2.setName("Almond Joy");
        item2.setPrice(new BigDecimal("1.85"));
        item2.setQty(0);
        itemStock.add(item2);
    }

    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        // Do nothing.
    }
    
}
