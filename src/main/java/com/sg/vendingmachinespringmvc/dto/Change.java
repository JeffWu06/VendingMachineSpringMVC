/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dto;

import java.math.BigDecimal;

/**
 *
 * @author Jeff
 */
public class Change {
    private final int QUARTER = 25;
    private final int DIME = 10;
    private final int NICKEL = 5;
    private final int PENNY = 1;
    
    private final BigDecimal PENNY_CONVERSION = new BigDecimal("100.00");
    
    private BigDecimal amount;
    private int quarters, dimes, nickels, pennies;

    public Change(BigDecimal amount) {
        this.amount = amount;
        
        int amountInPennies =  amount.multiply(PENNY_CONVERSION).intValue();
        
        quarters = amountInPennies / QUARTER;
        int penniesBalance = amountInPennies % QUARTER;
        dimes = penniesBalance / DIME;
        penniesBalance = penniesBalance % DIME;
        nickels = penniesBalance / NICKEL;
        penniesBalance = penniesBalance % NICKEL;
        pennies = penniesBalance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }

    @Override
    public String toString() {
        String denominationString = "";
        if (quarters > 0) {
            if (quarters > 1) {
                denominationString += quarters + " quarters ";
            } else {
                denominationString += quarters + " quarter ";
            }
        }
        if (dimes > 0) {
            if (dimes > 1) {
                denominationString += dimes + " dimes ";
            } else {
                denominationString += dimes + " dime ";
            }
        }
        if (nickels > 0) {
            if (nickels > 1) {
                denominationString += nickels + " nickels ";
            } else {
                denominationString += nickels + " nickel ";
            }
        }
        if (pennies > 0) {
            if (pennies > 1) {
                denominationString += pennies + " pennies ";
            } else {
                denominationString += pennies + " penny ";
            }
        }
        return denominationString;
    }
    
}
