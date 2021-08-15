package com.aj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long entityId;
    @JsonProperty("id")
    private String id;
    private String name;
}
