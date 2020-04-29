/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Jeff
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private BigDecimal userBalance;
    private String selectedItemId;
       
    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
        userBalance = new BigDecimal("0.00");
        selectedItemId = "";
    }
    
    @Override
    public String getSelectedItemId() {
        return selectedItemId;
    }

    @Override
    public void setSelectedItemId(String itemId) {
        selectedItemId = itemId;
    }
    
    @Override
    public BigDecimal getUserBalance() {
        return userBalance;
    }

    @Override
    public BigDecimal depositMoney(BigDecimal deposit) {
        if (deposit.compareTo(BigDecimal.ZERO) == -1) {
            deposit = new BigDecimal("0.00");
        }
        userBalance = userBalance.add(deposit);
        return deposit;
    }

    @Override
    public List<Item> getStockOnHand() throws VendingMachinePersistenceException {
        return dao.getStockOnHand();
    }

    @Override
    public Item getItem(String itemKey) throws VendingMachinePersistenceException {
        return dao.getItem(itemKey);
    }

    @Override
    public Item vendItem(String itemKey) throws VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException,
            NullPointerException {
        Item item = dao.getItem(itemKey);
        checkForSufficientFunds(item, getUserBalance());
        checkItemStock(item);
        userBalance = userBalance.subtract(item.getPrice());
        setSelectedItemId("");
        return dao.vendItem(itemKey);
    }
    
    @Override
    public Change getChange(BigDecimal changeOwed) {
        Change coins = new Change(changeOwed);
        userBalance = userBalance.subtract(coins.getAmount());
        setSelectedItemId("");
        return coins;
    }

    private void checkItemStock(Item item) throws 
            VendingMachineNoItemInventoryException {
        if (item.getQty() == 0) {
            throw new VendingMachineNoItemInventoryException("SOLD OUT!!!");
        }
    }
    
    private void checkForSufficientFunds(Item item, BigDecimal userBalance) 
            throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        
        if (item == null) {
            throw new VendingMachineNoItemInventoryException("SOLD OUT!!!");
        } else if (item.getPrice().compareTo(userBalance) == 1) {
            throw new VendingMachineInsufficientFundsException("Please deposit: $"
                    + item.getPrice().subtract(userBalance));
        }
    }
    
    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        // Do nothing.        
    }

    @Override
    public void loadInventory() throws VendingMachinePersistenceException {
        dao.loadInventory();
    }

    
}
