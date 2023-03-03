package rocks.basset.spring5webfluxrest.domain;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Category {

    @Id
    private String id;
    private String description;
}
