package com.nttdata.bootcoin.repositories.implementation;

import com.nttdata.bootcoin.config.ObjectMapperUtils;
import com.nttdata.bootcoin.models.TypeDocumentModel;
import com.nttdata.bootcoin.repositories.ITypeDocumentRepositories;
import com.nttdata.bootcoin.repositories.redis.ReactiveRedisComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static com.nttdata.bootcoin.config.ObjectMapperUtils.BOOK_KEY_TD;

@Repository
public class TypeDocumentRepositories implements ITypeDocumentRepositories {

    @Autowired
    private ReactiveRedisComponent reactiveRedisComponent;

    @Override
    public Mono<TypeDocumentModel> save(TypeDocumentModel model) {
        return reactiveRedisComponent.set(BOOK_KEY_TD, model.getId(), model).map(b -> model);
    }

    @Override
    public Mono<TypeDocumentModel> get(String key) {
        return reactiveRedisComponent.get(BOOK_KEY_TD, key).flatMap(d -> Mono.just(ObjectMapperUtils.objectMapper(d, TypeDocumentModel.class)));
    }

    @Override
    public Flux<TypeDocumentModel> getAll() {
        return reactiveRedisComponent.get(BOOK_KEY_TD).map(b -> ObjectMapperUtils.objectMapper(b, TypeDocumentModel.class))
                .collectList().flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Long> delete(String id) {
        return reactiveRedisComponent.remove(BOOK_KEY_TD,id);
    }
}
