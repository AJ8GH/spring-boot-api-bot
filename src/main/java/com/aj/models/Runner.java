package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Runner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long selectionId;
    private String runnerName;
    private Double handicap;
}
