package com.nttdata.bootcoin.services;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import reactor.core.publisher.Mono;

public interface IClientBootCoinService {
    Mono<ClientBootCoinModel> register(ClientBootCoinModel model);
    Mono<ClientBootCoinModel> findByNumberDocumentAndTypeDocument(String numberDocument, String typeDocument);
    Mono<ClientBootCoinModel> findByNumberPhone(String number);
    Mono<ClientBootCoinModel> findById(String id);
}
