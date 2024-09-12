package com.codeforall.online.javabank.model.account;

/**
 * A checking account with no restrictions
 * @see Account
 * @see AccountType#CHECKING
 */
public class CheckingAccount extends Account {

    /**
     * @see Account#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
