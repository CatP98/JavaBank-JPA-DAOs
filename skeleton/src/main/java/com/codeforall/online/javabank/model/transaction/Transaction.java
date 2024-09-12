package com.codeforall.online.javabank.model.transaction;

import com.codeforall.online.javabank.model.AbstractModel;
import com.codeforall.online.javabank.model.Recipient;
import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * A class with represents the financial transactions of an account
 */
@Entity
@Table(name = "transactions" )
public class Transaction  extends AbstractModel {

    @Column ( name = "account_id, nullable = false")
    private Integer accountId;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;


    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @JoinColumn(name = "creation_time", nullable = false, updatable = false)
    private Timestamp creationTime;

    /**
     * Get the id of the account to which the transaction is associated with
     * @return the account id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * Get the amount
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Get the purchase
     * @return the purchase
     */
    public Purchase getPurchase() {
        return purchase;
    }

    /**
     * Get the recipient
     * @return the recipient
     */
    public Recipient getRecipient() {
        return recipient;
    }

    /**
     * Get the transaction type
     * @return the transaction type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Get the time when each transaction occur
     * @return the creation time
     */
    public Timestamp getCreationTime() {
        return creationTime;
    }

    /**
     * Set the account id to the given id
     * @param accountId
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * Set the amount to the given amount
     * @param amount to give
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Set the purchase to the given purchase
     * @param purchase to give
     */
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    /**
     * Set the recipient to the given recipient
     * @param recipient to give
     */
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    /**
     * Set the transactionType to the given transactionType
     * @param transactionType to give
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Set the creation time to the given creation time
     * @param creationTime
     */
    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }
}
