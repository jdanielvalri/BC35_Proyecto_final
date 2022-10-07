package com.nttdata.bootcoin.repositories.implementation;

import com.nttdata.bootcoin.config.ObjectMapperUtils;
import com.nttdata.bootcoin.models.PayModeModel;
import com.nttdata.bootcoin.models.TypeDocumentModel;
import com.nttdata.bootcoin.repositories.IPayModeRepositories;
import com.nttdata.bootcoin.repositories.ITypeDocumentRepositories;
import com.nttdata.bootcoin.repositories.redis.ReactiveRedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.nttdata.bootcoin.config.ObjectMapperUtils.BOOK_KEY_PM;
import static com.nttdata.bootcoin.config.ObjectMapperUtils.BOOK_KEY_TD;

@Repository
public class PayModeRepositories implements IPayModeRepositories {

    @Autowired
    private ReactiveRedisComponent reactiveRedisComponent;

    @Override
    public Mono<PayModeModel> save(PayModeModel model) {
        return reactiveRedisComponent.set(BOOK_KEY_PM, model.getId(), model).map(b -> model);
    }

    @Override
    public Mono<PayModeModel> get(String key) {
        return reactiveRedisComponent.get(BOOK_KEY_PM, key).flatMap(d -> Mono.just(ObjectMapperUtils.objectMapper(d, PayModeModel.class)));
    }

    @Override
    public Flux<PayModeModel> getAll() {
        return reactiveRedisComponent.get(BOOK_KEY_PM).map(b -> ObjectMapperUtils.objectMapper(b, PayModeModel.class))
                .collectList().flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Long> delete(String id) {
        return reactiveRedisComponent.remove(BOOK_KEY_PM,id);
    }
}
