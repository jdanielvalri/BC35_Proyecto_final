package com.nttdata.bootcoin.services.implementation;

import com.nttdata.bootcoin.models.PayModeModel;
import com.nttdata.bootcoin.models.TypeDocumentModel;
import com.nttdata.bootcoin.repositories.IPayModeRepositories;
import com.nttdata.bootcoin.repositories.ITypeDocumentRepositories;
import com.nttdata.bootcoin.services.IPayModeService;
import com.nttdata.bootcoin.services.ITypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PayModeService implements IPayModeService {

    @Autowired
    private IPayModeRepositories payModeRepositories;

    @Override
    public Mono<PayModeModel> create(PayModeModel model) {
        return payModeRepositories.save(model);
    }

    @Override
    public Flux<PayModeModel> getAll() {
        return payModeRepositories.getAll();
    }

    @Override
    public Mono<PayModeModel> getOne(String id) {
        return payModeRepositories.get(id);
    }

    @Override
    public Mono<Long> deleteById(String id) {
        return payModeRepositories.delete(id);
    }
}
