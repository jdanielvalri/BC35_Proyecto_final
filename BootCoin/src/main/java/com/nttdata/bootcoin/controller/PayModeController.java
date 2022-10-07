package com.nttdata.bootcoin.controller;

import com.nttdata.bootcoin.models.PayModeModel;
import com.nttdata.bootcoin.models.TypeDocumentModel;
import com.nttdata.bootcoin.services.implementation.PayModeService;
import com.nttdata.bootcoin.services.implementation.TypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/bootcoin/payMode")
public class PayModeController {

    @Autowired
    private PayModeService payModeService;

    @PostMapping("/")
    public Mono<PayModeModel> addTypeDocument(@RequestBody @Valid PayModeModel request){
        return payModeService.create(request);
    }

    @GetMapping("/all")
    public Flux<PayModeModel> getAllDocument(){
        return payModeService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<PayModeModel> getTypeDocument(@PathVariable String id){
        return payModeService.getOne(id);
    }

    @DeleteMapping("/{id}")
    public  Mono<Long> deleteTypeDocument(@PathVariable String id){
        return payModeService.deleteById(id);
    }
}
