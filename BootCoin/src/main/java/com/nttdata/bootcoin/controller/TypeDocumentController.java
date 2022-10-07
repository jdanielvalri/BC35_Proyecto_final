package com.nttdata.bootcoin.controller;

import com.nttdata.bootcoin.models.TypeDocumentModel;
import com.nttdata.bootcoin.services.implementation.TypeDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/bootcoin/typeDocument")
public class TypeDocumentController {

    @Autowired
    private TypeDocumentService typeDocumentService;

    @PostMapping("/")
    public Mono<TypeDocumentModel> addTypeDocument(@RequestBody @Valid TypeDocumentModel request){
        return typeDocumentService.create(request);
    }

    @GetMapping("/all")
    public Flux<TypeDocumentModel> getAllDocument(){
        return typeDocumentService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<TypeDocumentModel> getTypeDocument(@PathVariable String id){
        return typeDocumentService.getOne(id);
    }

    @DeleteMapping("/{id}")
    public  Mono<Long> deleteTypeDocument(@PathVariable String id){
        return typeDocumentService.deleteById(id);
    }
}
