/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.service.VendingMachineNoItemInventoryException;
import com.sg.vendingmachinespringmvc.dao.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.dto.Change;
import com.sg.vendingmachinespringmvc.dto.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Jeff
 */
@Controller
public class VendingMachineController {
    VendingMachineServiceLayer service;
    
    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }
    
    @RequestMapping(value="/displayVendingMachinePage", method = RequestMethod.GET)
    public String displayVendingMachinePage(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        List<Item> itemsList = service.getStockOnHand();
        BigDecimal currentBalance = service.getUserBalance();
        String selectedItemId = service.getSelectedItemId();
        model.addAttribute("itemsList", itemsList);
        model.addAttribute("currentBalance", currentBalance);
        model.addAttribute("selectedItemId", selectedItemId);
        model.addAttribute("message", request.getParameter("message"));
        model.addAttribute("change", request.getParameter("change"));
        return "vendingMachine";
    }
    
    @RequestMapping(value="/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request, Model model) {
        String selectedItemId = request.getParameter("itemId");
        service.setSelectedItemId(selectedItemId);
        model.addAttribute("selectedItemId", selectedItemId);
        return "redirect:displayVendingMachinePage";
    }
    
    @RequestMapping(value="/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request, Model model) {
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        service.depositMoney(amount);
        return "redirect:displayVendingMachinePage";
    }
    
    @RequestMapping(value="/makePurchase", method = RequestMethod.POST)
    public String makePurchase(Model model) 
            throws VendingMachinePersistenceException,
            VendingMachineNoItemInventoryException, 
            VendingMachineInsufficientFundsException,
            NullPointerException {
        String selectedItemId = service.getSelectedItemId();
        service.vendItem(selectedItemId);
        model.addAttribute("message", "Thank You!!!");
        return "redirect:displayVendingMachinePage";
    }
    
    @RequestMapping(value="/returnChange", method = RequestMethod.POST)
    public String returnChange(Model model) {
        Change change = service.getChange(service.getUserBalance());
        model.addAttribute("change", change.toString());
        return "redirect:displayVendingMachinePage";
    }
}
