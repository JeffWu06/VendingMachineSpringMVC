/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Jeff
 */
public interface VendingMachineServiceLayer {
    BigDecimal userBalance = new BigDecimal("0.00");
    String selectedItemId = "";
    
    public List<Item> getStockOnHand() throws VendingMachinePersistenceException;
    
    public Item getItem(String itemKey) throws VendingMachinePersistenceException;
    
    public Item vendItem(String itemKey) throws VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException,   
            VendingMachineInsufficientFundsException;
    
    public Change getChange(BigDecimal userBalance);
    
    public BigDecimal depositMoney(BigDecimal deposit);

    public BigDecimal getUserBalance();
    
    public String getSelectedItemId();
    
    public void setSelectedItemId(String itemId);
    
    public void writeInventory() throws 
            VendingMachinePersistenceException;
    
    public void loadInventory() throws 
            VendingMachinePersistenceException;
}
