package org.example.service;

import org.example.entity.Account;
import org.example.entity.Client;
import org.example.exceptions.ItemNotFoundException;
import org.example.exceptions.NotEmptyBalanceException;
import org.example.repositories.AccountRepository;
import org.example.repositories.ClientRepository;
import org.example.service.handler.FindById;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

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
        Account account = accountRepository.findById(iban)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Account with IBAN %s not found", iban)));
        Long clientId = null;
        if (!account.getClient().getId().equals(clientId)) {
            //exception
        }
        return account;

//        Client client = clientRepository.getReferenceById(clientId);
//        List<Account> accounts = client.getAccounts();
//        for (Account account : accounts) {
//            if (account.getIban().equals(iban)) {
//                return account;
//            }
//        }
//        throw new ItemNotFoundException(String.format("Account with IBAN %s not found", iban));
    }

    @Override
    public Account update(String iban) {
        return null;
    }

    @Override
    public BigDecimal checkBalance(Long clientId, String iban) {
        return getByIban(iban).getBalance();
    }

    @Override
    public void closeAccount(Long clientId, String iban) {
        Account account = getByIban(iban);
        if ((account.getBalance().compareTo(BigDecimal.valueOf(0))) == 0) {
            accountRepository.delete(account);
            return;
        }
        throw new NotEmptyBalanceException("Balance is not 0.00. You can not close the account");
    }


    @Override
    public void topUpAccount(Long clientId, String iban, BigDecimal amount) {
        Account account = getByIban( iban);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    @Override
    public void withdraw(Long clientId, String iban, BigDecimal amount) {
        Account account = getByIban(iban);
        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }
}


