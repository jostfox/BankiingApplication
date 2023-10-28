package org.example.service.utility;

import com.ritaja.xchangerate.api.CurrencyConverter;
import com.ritaja.xchangerate.api.CurrencyConverterBuilder;
import com.ritaja.xchangerate.api.CurrencyNotSupportedException;
import com.ritaja.xchangerate.endpoint.EndpointException;
import com.ritaja.xchangerate.service.ServiceException;
import com.ritaja.xchangerate.storage.StorageException;
import com.ritaja.xchangerate.util.Currency;
import com.ritaja.xchangerate.util.Strategy;
import org.example.entity.Account;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CustomCurrencyConverter {

    private CurrencyConverter converter = new CurrencyConverterBuilder()
            .strategy(Strategy.CURRENCY_LAYER_FILESTORE)
            .accessKey("44e99f7cf84d7bffe06756645efec3b4")
            .buildConverter();


    private BigDecimal convertUsdToEur(BigDecimal amount) throws CurrencyNotSupportedException,
            EndpointException, ServiceException, JSONException, StorageException {
        return
                converter.convertCurrency(amount, Currency.EUR, Currency.USD);
    }

    private  BigDecimal convertEurToUsd(BigDecimal amount) throws CurrencyNotSupportedException,
            EndpointException, ServiceException, JSONException, StorageException {
        return
                converter.convertCurrency(amount, Currency.USD, Currency.EUR);
    }

    public BigDecimal convertedAmount (Account account, BigDecimal amount) throws CurrencyNotSupportedException, EndpointException, ServiceException, JSONException, StorageException {
        return account.getCurrency().equals(org.example.enums.Currency.EUR) ? convertEurToUsd(amount)
                :convertUsdToEur(amount);
    }
}
