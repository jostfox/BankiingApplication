package org.example.service;

import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import org.example.entity.Transaction;
import org.json.JSONException;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();

    Transaction getById(Long id);

    Transaction transfer(String creditIban, String debitIban, BigDecimal amount) throws CurrencyNotSupportedException, EndpointException, ServiceException, JSONException, StorageException;

    void delete(Long id);
}