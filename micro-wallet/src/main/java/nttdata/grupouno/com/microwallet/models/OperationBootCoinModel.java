package nttdata.grupouno.com.microwallet.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationBootCoinModel {
    private String id;
    private String payMode;
    private String state; // S= Solicitado C=Completado
    private String codClientBootCoin;
    private String documentVendor;
    private String typDocumentVendor;
    private Double exchangeRate;
    private Double amount;
    private String transactionNumber;
    private String operationDate;
   }
