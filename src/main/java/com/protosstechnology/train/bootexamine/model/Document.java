package com.protosstechnology.train.bootexamine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Document {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String documentNumber;

    @NotNull
    private String description;
}
