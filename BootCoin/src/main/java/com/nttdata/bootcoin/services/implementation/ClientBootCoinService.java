package com.nttdata.bootcoin.services.implementation;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import com.nttdata.bootcoin.repositories.IClientBootCoinRepositories;
import com.nttdata.bootcoin.services.IClientBootCoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ClientBootCoinService implements IClientBootCoinService {
    @Autowired
    private IClientBootCoinRepositories clientBootCoinRepositories;

    @Override
    public Mono<ClientBootCoinModel> register(ClientBootCoinModel model) {
        if(model.getNumberDocument() == null || model.getNumberDocument().isBlank())
            return Mono.empty();
        model.setId(UUID.randomUUID().toString());

        return clientBootCoinRepositories.save(model);
    }

    @Override
    public Mono<ClientBootCoinModel> findByNumberDocumentAndTypeDocument(String numberDocument, String typeDocument) {
        return clientBootCoinRepositories.findByNumberDocumentAndTypeDocument(numberDocument, typeDocument);
    }

    @Override
    public Mono<ClientBootCoinModel> findByNumberPhone(String number) {
        return clientBootCoinRepositories.findByNumberPhone(number);
    }

    @Override
    public Mono<ClientBootCoinModel> findById(String id) {
        return clientBootCoinRepositories.findById(id);
    }
}
