package banking;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private final LinkedHashMap<Long, Account> accounts;

    private static long nextAccountNumber = 1;

    public Bank() {
        accounts = new LinkedHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        long accountNumber = generateUniqueAccountNumber();
        CommercialAccount account = new CommercialAccount(company, accountNumber, pin, startingDeposit);
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        long accountNumber = generateUniqueAccountNumber();
        ConsumerAccount account = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
        accounts.put(accountNumber, account);
        return accountNumber;
    }

    public double getBalance(Long accountNumber) {
        Account account = getAccount(accountNumber);
        double balance = -1;
        if (account != null) {
            balance = account.getBalance();
        }
        return balance;
    }

    public void credit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.creditAccount(amount);
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            return account.debitAccount(amount);
        }
        return false;
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        return account.validatePin(pin);
    }

    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        CommercialAccount commercialAccount = (CommercialAccount) getAccount(accountNumber);
        commercialAccount.addAuthorizedUser(authorizedPerson);
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        boolean userChecked = false;
        if (authorizedPerson == null || accountNumber == null) {
            return userChecked;
        }
        Account account = getAccount(accountNumber);
        if (account instanceof CommercialAccount) {
            CommercialAccount commercialAccount = (CommercialAccount) account;
            userChecked = commercialAccount.isAuthorizedUser(authorizedPerson);
        }
        return userChecked;
    }

    public Map<String, Double> getAverageBalanceReport() {
        // Map to store average balance for each account type
        Map<String, Double> averageBalanceMap = new HashMap<>();
        // Map to store total balance for each account type
        Map<String, Double> totalBalanceMap = new HashMap<>();
        // Map to store count of accounts for each account type
        Map<String, Integer> countMap = new HashMap<>();

        // Initialize totalBalanceMap and countMap with initial values
        totalBalanceMap.put(ConsumerAccount.class.getSimpleName(), 0.0);
        totalBalanceMap.put(CommercialAccount.class.getSimpleName(), 0.0);
        countMap.put(ConsumerAccount.class.getSimpleName(), 0);
        countMap.put(CommercialAccount.class.getSimpleName(), 0);

        // Iterate over all accounts
        for (Account account : accounts.values()) {
            // Get the type of the account
            String accountType = account.getClass().getSimpleName();
            // Get the balance of the account
            double balance = account.getBalance();

            // Update total balance and count for the account type
            totalBalanceMap.put(accountType, totalBalanceMap.get(accountType) + balance);
            countMap.put(accountType, countMap.get(accountType) + 1);
        }

        // Calculate average balance for each account type
        for (String accountType : totalBalanceMap.keySet()) {
            // Get total balance for the account type
            double totalBalance = totalBalanceMap.get(accountType);
            // Get count of accounts for the account type
            int count = countMap.get(accountType);
            // Calculate average balance (avoid division by zero)
            double averageBalance = count > 0 ? totalBalance / count : 0.0;
            // Store average balance in the map
            averageBalanceMap.put(accountType, averageBalance);
        }

        return averageBalanceMap;
    }

    private long generateUniqueAccountNumber() {
        return nextAccountNumber++;
    }
}
