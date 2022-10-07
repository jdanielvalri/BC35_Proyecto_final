package com.nttdata.bootcoin.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "operationBootCoin")
public class OperationBootCoinModel {
    @Id
    private String id;
    @NotEmpty
    private String payMode;
    private String state; // S= Solicitado C=Completado
    @NotEmpty
    private String codClientBootCoin;
    private String documentVendor;
    private String typDocumentVendor;
    private Double exchangeRate;
    @DecimalMin(value = "0.00", message = "Cantidad de bootcoins no negativo")
    private Double amount;
    private String transactionNumber;
    private String operationDate;
   }
