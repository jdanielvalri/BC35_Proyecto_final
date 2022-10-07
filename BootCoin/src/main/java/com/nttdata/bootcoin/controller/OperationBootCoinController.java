package com.nttdata.bootcoin.controller;

import com.nttdata.bootcoin.models.ClientBootCoinModel;
import com.nttdata.bootcoin.models.OperationBootCoinModel;
import com.nttdata.bootcoin.services.IClientBootCoinService;
import com.nttdata.bootcoin.services.IOperationBootCoinService;
import com.nttdata.bootcoin.services.IPayModeService;
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
@RequestMapping("/bootcoin/operation")
public class OperationBootCoinController {
    @Autowired
    private IOperationBootCoinService operationBootCoinService;
    @Autowired
    private IPayModeService payModeService;

    @PostMapping("/")
    public Mono<Map<String, Object>> registerOperation(@RequestBody @Valid Mono<OperationBootCoinModel> request){
        Map<String, Object> response = new HashMap<>();
        return request
                .flatMap(x ->
                        payModeService.getOne(x.getPayMode())
                                .map(y -> x)
                                .defaultIfEmpty(new OperationBootCoinModel())
                )
                .flatMap(x -> {
                        if(x.getPayMode() == null || x.getPayMode().isBlank())
                        {
                                response.put("valid", "No se logró identificar el modo de pago");
                                return Mono.just(response);
                        }
                        return operationBootCoinService.register(x)
                                .flatMap(y -> {
                                    response.put("operationBootCoin", y);
                                    return Mono.just(response);
                                });
                });
    }
}
