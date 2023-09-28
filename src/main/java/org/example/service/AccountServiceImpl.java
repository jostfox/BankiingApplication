package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.NotEmptyBalanceException;
import org.example.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository,
                              @Qualifier("clientService") ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getByIban(String iban) {
        return accountRepository.findById(iban)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Account with IBAN %s not found", iban)));
    }

    @Override
    public BigDecimal checkBalance(String iban) {
        return getClientAccountByIban(iban).getBalance();
    }

    @Override
    public void close(String iban) {
        Account account = getClientAccountByIban(iban);
        if ((account.getBalance().compareTo(BigDecimal.valueOf(0))) == 0) {
            accountRepository.delete(account);
            return;
        }
        throw new NotEmptyBalanceException("Balance is not 0.00. You can not close the account");
    }

    @Override
    public BigDecimal topUp(String iban, BigDecimal amount) {
        Account account = getClientAccountByIban(iban);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return account.getBalance();
    }

    @Override
    public BigDecimal withdraw(String iban, BigDecimal amount) {
        Account account = getClientAccountByIban(iban);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
        return account.getBalance();
    }

    public Account getClientAccountByIban(String iban){
        Client client = clientService.getCurrent();
        String clientsIban =
                client.getAccounts().stream()
                        .map(Account::getIban).filter(accountIban -> accountIban.equals(iban))
                        .findFirst().orElseThrow(() -> new ItemNotFoundException(String.format(
                                "Account " + "with IBAN %s not found or " +
                                "does not belong to you", iban)));
        return getByIban(clientsIban);
    }
}


