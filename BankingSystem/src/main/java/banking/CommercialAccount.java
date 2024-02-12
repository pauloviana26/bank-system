package banking;

import java.util.List;

/**
 * Account implementation for commercial (business) customers.
 * The account's holder is a {@link Company}.
 */
public class CommercialAccount {
    private List<Person> authorizedUsers;
    private Company company;
    private Long accountNumber;
    private int pin;
    private double startingDeposit;

    public CommercialAccount(Company company, Long accountNumber, int pin, double startingDeposit) {
        this.company = company;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.startingDeposit = startingDeposit;
    }

    /**
     * Add person to list of authorized users.
     *
     * @param person The authorized user to be added to the account.
     */
    protected void addAuthorizedUser(Person person) {
        authorizedUsers.add(person);
    }

    /**
     * Verify if the person is part of the list of authorized users for this account.
     *
     * @param person
     * @return <code>true</code> if person matches an authorized user in {@link #authorizedUsers}; <code>false</code> otherwise.
     */
    public boolean isAuthorizedUser(Person person) {
        return authorizedUsers.contains(person);
    }

    public List<Person> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public Company getCompany() {
        return company;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getStartingDeposit() {
        return startingDeposit;
    }
}
