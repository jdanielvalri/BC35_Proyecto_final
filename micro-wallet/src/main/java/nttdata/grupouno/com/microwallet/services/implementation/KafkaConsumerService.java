package nttdata.grupouno.com.microwallet.services.implementation;

import nttdata.grupouno.com.microwallet.models.ClientWalletModel;
import nttdata.grupouno.com.microwallet.models.OperationBootCoinModel;
import nttdata.grupouno.com.microwallet.repositories.IClientWalletRepositories;

import nttdata.grupouno.com.microwallet.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @Autowired
    private IClientWalletRepositories clientWalletRepositories;

    @Autowired
    @Qualifier("kafkaOperationTemplate")
    private KafkaTemplate<String, OperationBootCoinModel> kafkaTemplate;

    @KafkaListener(topics = "kafka_target_response", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
    public void consume(ClientWalletModel user){
        clientWalletRepositories.findById(user.getId())
            .flatMap(x -> {
                x.setTargetAssociated(user.getTargetAssociated());
                return clientWalletRepositories.save(x);
            })
            .subscribe();
    }

    @KafkaListener(topics = "Kafka_operation", groupId = "group_json", containerFactory = "operationKafkaListenerFactory")
    public void consumeJsonTarjet(OperationBootCoinModel model) {
        clientWalletRepositories.findByNumberDocumentAndTypeDocument(model.getDocumentVendor(), model.getTypDocumentVendor())
                .map(x -> {
                    x.setAmount(x.getAmount()+model.getAmount()*model.getExchangeRate());
                    clientWalletRepositories.save(x);
                    model.setState("C"); //completado
                    return x;
                })
                .doOnSuccess(x -> kafkaTemplate.send("kafka_operation_response", model))
                .subscribe();
    }

}
