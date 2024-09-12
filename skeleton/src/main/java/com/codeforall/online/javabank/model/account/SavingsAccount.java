package com.codeforall.online.javabank.model.account;

import com.codeforall.online.javabank.services.AccountServiceImpl;

/**
 * A savings account domain entity which requires a minimum balance
 * and can only be used for transferring money, not for debiting
 * @see Account
 * @see AccountType#SAVINGS
 */
public class SavingsAccount extends Account{

    /**
     * The minimum balance to maintain on the account
     */
    public static final double MIN_BALANCE = 100;

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    public boolean canDebit(double amount) {
        return canDebit(amount) && (getBalance() - amount) >= MIN_BALANCE;
    }

    public boolean canWithdraw() {
        return false;
    }
}