package com.nttdata.bootcoin.services;

import com.nttdata.bootcoin.models.PayModeModel;
import com.nttdata.bootcoin.models.TypeDocumentModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPayModeService {
    Mono<PayModeModel> create(PayModeModel model);

    Flux<PayModeModel> getAll();

    Mono<PayModeModel> getOne(String id);

    Mono<Long> deleteById(String id);
}
