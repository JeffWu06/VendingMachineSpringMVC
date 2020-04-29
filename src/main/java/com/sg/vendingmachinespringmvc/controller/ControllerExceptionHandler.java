/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineNoItemInventoryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Jeff
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler({VendingMachineNoItemInventoryException.class, 
            VendingMachineInsufficientFundsException.class, NullPointerException.class})
    public ModelAndView processVendingMachineNoItemInventoryException(
            Exception e){
        ModelAndView model = new ModelAndView("redirect:displayVendingMachinePage", "message", e.getMessage());
        return model;
    }
}
