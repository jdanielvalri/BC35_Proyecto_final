package com.nttdata.bootcoin.services.implementation;

import com.nttdata.bootcoin.models.OperationBootCoinModel;
import com.nttdata.bootcoin.repositories.IOperationBootCoinRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private IOperationBootCoinRepositories operationBootCoinRepositories;

    @KafkaListener(topics = "kafka_operation_response", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
    public void consume(OperationBootCoinModel model){
        if("C".equals(model.getState())) {
            operationBootCoinRepositories.findByTransactionNumber(model.getTransactionNumber())
                    .flatMap(x -> {
                        x.setState(model.getState());
                        return operationBootCoinRepositories.save(x);
                    })
                    .subscribe();
        }
    }


}
