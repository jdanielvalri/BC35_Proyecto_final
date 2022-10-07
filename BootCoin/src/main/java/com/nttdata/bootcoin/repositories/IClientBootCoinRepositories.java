package com.nttdata.bootcoin.repositories;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IClientBootCoinRepositories extends ReactiveMongoRepository<ClientBootCoinModel, String> {
    Mono<ClientBootCoinModel> findByNumberDocumentAndTypeDocument(String number, String type);
    Mono<ClientBootCoinModel> findByNumberPhone(String numberPhone);
}
