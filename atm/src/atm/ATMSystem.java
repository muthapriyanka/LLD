
package atm;

import atm.chainofresponsibility.CashDispenser;
import atm.chainofresponsibility.DispenseChain;
import atm.chainofresponsibility.NoteDispenser100;
import atm.chainofresponsibility.NoteDispenser20;
import atm.chainofresponsibility.NoteDispenser50;
import atm.entities.Card;
import atm.entities.OperationType;
import atm.entities.Transaction;
import atm.entities.TransactionStatus;
import atm.state.ATMState;
import atm.state.IdleState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ATMSystem {
    private static ATMSystem INSTANCE;
    private final BankService bankService;
    private final CashDispenser cashDispenser;
    private final List<Transaction> transactionHistory;
    private static final AtomicLong transactionCounter = new AtomicLong(0);
    private ATMState currentState;
    private Card currentCard;

    private ATMSystem() {
        this.currentState = new IdleState();
        this.bankService = new BankService();
        this.transactionHistory = new ArrayList<>();

        // Setup the dispenser chain
        DispenseChain c1 = new NoteDispenser100(10); // 10 x $100 notes
        DispenseChain c2 = new NoteDispenser50(20); // 20 x $50 notes
        DispenseChain c3 = new NoteDispenser20(30); // 30 x $20 notes
        c1.setNextChain(c2);
        c2.setNextChain(c3);
        this.cashDispenser = new CashDispenser(c1);
    }

    public static ATMSystem getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ATMSystem();
        }
        return INSTANCE;
    }

    public void changeState(ATMState newState) { this.currentState = newState; }
    public void setCurrentCard(Card card) { this.currentCard = card; }

    public void insertCard(String cardNumber) {
        currentState.insertCard(this, cardNumber);
    }

    public void enterPin(String pin) {
        currentState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int... args) { currentState.selectOperation(this, op, args); }

    public Card getCard(String cardNumber) {
        return bankService.getCard(cardNumber);
    }

    public boolean authenticate(String pin) {
        return bankService.authenticate(currentCard, pin);
    }

    public void checkBalance() {
        double balance = bankService.getBalance(currentCard);
        System.out.printf("Your current account balance is: $%.2f%n", balance);
        recordTransaction(OperationType.CHECK_BALANCE, 0, TransactionStatus.SUCCESS, "Balance checked");
    }

    public void withdrawCash(int amount) {
        if (!cashDispenser.canDispenseCash(amount)) {
            recordTransaction(OperationType.WITHDRAW_CASH, amount, TransactionStatus.FAILED,
                    "Insufficient cash available in ATM");
            throw new IllegalStateException("Insufficient cash available in the ATM.");
        }

        boolean isWithdrawn = bankService.withdrawMoney(currentCard, amount);
        if (!isWithdrawn) {
            recordTransaction(OperationType.WITHDRAW_CASH, amount, TransactionStatus.FAILED,
                    "Insufficient account balance");
            throw new IllegalStateException("Insufficient account balance.");
        }

        try {
            cashDispenser.dispenseCash(amount);
            recordTransaction(OperationType.WITHDRAW_CASH, amount, TransactionStatus.SUCCESS, "Withdrawal completed");
        } catch (Exception e) {
            bankService.depositMoney(currentCard, amount); // Deposit back if dispensing fails
            recordTransaction(OperationType.WITHDRAW_CASH, amount, TransactionStatus.FAILED,
                    "Cash dispensing failed. Amount refunded");
            throw new IllegalStateException("Cash dispensing failed. Amount refunded.", e);
        }
    }

    public void depositCash(int amount) {
        bankService.depositMoney(currentCard, amount);
        recordTransaction(OperationType.DEPOSIT_CASH, amount, TransactionStatus.SUCCESS, "Deposit completed");
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public BankService getBankService() {
        return bankService;
    }

    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    private synchronized void recordTransaction(OperationType operationType, double amount,
                                                TransactionStatus status, String message) {
        long transactionId = transactionCounter.incrementAndGet();
        Transaction transaction = new Transaction(transactionId, operationType, amount, status, message);
        transactionHistory.add(transaction);
    }

}

// Every ATMState implementation only ever talks to the ATMSystem.
//State pattern needs one main object whose behavior changes depending on state:
// The state objects don’t know anything about BankService, CashDispenser, the current card, transaction counter, etc.
//Use State when you have: same object + same method + many statuses + different behavior