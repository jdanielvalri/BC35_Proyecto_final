package com.nttdata.bootcoin.services;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import com.nttdata.bootcoin.models.OperationBootCoinModel;
import reactor.core.publisher.Mono;

public interface IOperationBootCoinService {
    Mono<OperationBootCoinModel> register(OperationBootCoinModel model);
}
