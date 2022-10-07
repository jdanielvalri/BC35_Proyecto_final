package com.nttdata.bootcoin.services.implementation;

import com.nttdata.bootcoin.constans.Constants;
import com.nttdata.bootcoin.models.ClientBootCoinModel;
import com.nttdata.bootcoin.models.OperationBootCoinModel;
import com.nttdata.bootcoin.repositories.IClientBootCoinRepositories;
import com.nttdata.bootcoin.repositories.IOperationBootCoinRepositories;
import com.nttdata.bootcoin.services.IClientBootCoinService;
import com.nttdata.bootcoin.services.IOperationBootCoinService;
import com.nttdata.bootcoin.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Service
public class OperationBootCoinService implements IOperationBootCoinService {
    @Autowired
    private IOperationBootCoinRepositories operationBootCoinRepositories;

    @Autowired
    @Qualifier("KafkaProducerTemplate")
    private KafkaTemplate<String, OperationBootCoinModel> kafkaTemplate;

    private static final String TOPIC = "Kafka_operation";

    @Override
    public Mono<OperationBootCoinModel> register(OperationBootCoinModel model) {
        if(model.getPayMode() == null || model.getPayMode().isBlank())
            return Mono.empty();
        OperationBootCoinModel data = new OperationBootCoinModel();
        data.setId(UUID.randomUUID().toString());
        data.setTransactionNumber(Util.generateTrnsactionNumber());
        data.setState("S");
        data.setExchangeRate(Constants.exchangeRate);
        data.setAmount(model.getAmount());
        data.setPayMode(model.getPayMode());
        data.setCodClientBootCoin(model.getCodClientBootCoin());
        data.setOperationDate(Util.dateToString(new Date()));
        data.setDocumentVendor(model.getDocumentVendor());
        data.setTypDocumentVendor(model.getTypDocumentVendor());

        return operationBootCoinRepositories.save(model)
            .doOnSuccess(
                x ->  
                {
                    kafkaTemplate.send(TOPIC, data);
                }
            );
    }
}
