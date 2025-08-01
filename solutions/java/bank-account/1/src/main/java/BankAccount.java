class BankAccount {
    private boolean opened = false;
    private int balance;
    void open() throws BankAccountActionInvalidException {
        if (opened) throw new BankAccountActionInvalidException("Account already open");
        opened = true;
        balance = 0;
    }

    void close() throws BankAccountActionInvalidException {
        if (!opened) throw new BankAccountActionInvalidException("Account not open");
        opened = false;
    }

    synchronized int getBalance() throws BankAccountActionInvalidException {
        assertOpened();
        return balance;
    }

    synchronized void deposit(int amount) throws BankAccountActionInvalidException {
        if (amount < 0) throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        assertOpened();
        balance += amount;
    }

    synchronized void withdraw(int amount) throws BankAccountActionInvalidException {
        assertOpened();
        if (amount < 0) throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        if (balance < amount) throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        balance -= amount;
    }

    private void assertOpened() throws BankAccountActionInvalidException {
        if (!opened) throw new BankAccountActionInvalidException("Account closed");
    }
}