package com.nttdata.bootcoin.services.implementation;

import com.nttdata.bootcoin.models.TypeDocumentModel;
import com.nttdata.bootcoin.repositories.ITypeDocumentRepositories;
import com.nttdata.bootcoin.services.ITypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TypeDocumentService implements ITypeDocumentService {

    @Autowired
    private ITypeDocumentRepositories typeDocumentRepositories;

    @Override
    public Mono<TypeDocumentModel> create(TypeDocumentModel model) {
        return typeDocumentRepositories.save(model);
    }

    @Override
    public Flux<TypeDocumentModel> getAll() {
        return typeDocumentRepositories.getAll();
    }

    @Override
    public Mono<TypeDocumentModel> getOne(String id) {
        return typeDocumentRepositories.get(id);
    }

    @Override
    public Mono<Long> deleteById(String id) {
        return typeDocumentRepositories.delete(id);
    }
}
