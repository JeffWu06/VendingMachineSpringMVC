/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dto.Item;
import java.util.List;

/**
 *
 * @author Jeff
 */
public interface VendingMachineDao {
    
    List<Item> getStockOnHand() throws VendingMachinePersistenceException;
    
    Item getItem(String itemKey) throws VendingMachinePersistenceException;
    
    Item vendItem(String itemKey) throws VendingMachinePersistenceException;
    
    void loadInventory() throws VendingMachinePersistenceException;
    
    void writeInventory() throws VendingMachinePersistenceException;
}
