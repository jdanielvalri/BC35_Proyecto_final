package com.nttdata.bootcoin.repositories;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import com.nttdata.bootcoin.models.OperationBootCoinModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IOperationBootCoinRepositories extends ReactiveMongoRepository<OperationBootCoinModel, String> {

    Mono<OperationBootCoinModel> findByTransactionNumber (String transactionNumber);
}
