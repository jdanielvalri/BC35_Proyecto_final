package com.nttdata.bootcoin.controller;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import com.nttdata.bootcoin.services.IClientBootCoinService;
import com.nttdata.bootcoin.services.ITypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/bootcoin/client")
public class ClientBootCoinController {
    @Autowired
    private IClientBootCoinService clientBootCoinService;
    @Autowired
    private ITypeDocumentService typeDocumentService;

    @PostMapping("/")
    public Mono<Map<String, Object>> registerClient(@RequestBody @Valid Mono<ClientBootCoinModel> request){
        Map<String, Object> response = new HashMap<>();
        return request
                .flatMap(x ->
                        typeDocumentService.getOne(x.getTypeDocument())
                                .map(y -> x)
                                .defaultIfEmpty(new ClientBootCoinModel())
                )
                .flatMap(x -> {
                        if(x.getNumberDocument() == null || x.getNumberDocument().isBlank())
                        {
                                response.put("valid", "No se logró identificar el tipo de documento");
                                return Mono.just(response);
                        }
                        return clientBootCoinService.findByNumberDocumentAndTypeDocument(x.getNumberDocument(), x.getTypeDocument())
                                .flatMap(y -> {
                                        response.put("validBootCoin", "El cliente Bootcoin ya existe");
                                        response.put("clientBootCoin", y);
                                        return Mono.just(response);
                                })
                                .switchIfEmpty(
                                        clientBootCoinService.register(x).flatMap(
                                                y -> {
                                                        response.put("clientBootCoin", y);
                                                        return Mono.just(response);
                                                }
                                        )
                                );
                });
    }
}
