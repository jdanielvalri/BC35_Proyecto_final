package com.nttdata.bootcoin.repositories;

import com.nttdata.bootcoin.models.PayModeModel;
import com.nttdata.bootcoin.models.TypeDocumentModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPayModeRepositories {
    Mono<PayModeModel> save(PayModeModel model);
    Mono<PayModeModel> get(String key);
    Flux<PayModeModel> getAll();
    Mono<Long> delete(String id);
}
