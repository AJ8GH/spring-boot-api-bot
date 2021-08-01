package com.aj.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long betId;

    private String marketId;
    private Integer selectionId;
    private Double price;
    private Double size;
    private String side;
    private Double bspLiability;
    private String status;

    public Bet() {
    }

    public Bet(String marketId, Integer selectionId, Double price, Double size, String side, Double bspLiability, String status) {
        this.marketId = marketId;
        this.selectionId = selectionId;
        this.price = price;
        this.size = size;
        this.side = side;
        this.bspLiability = bspLiability;
        this.status = status;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public Integer getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Integer selectionId) {
        this.selectionId = selectionId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Double getBspLiability() {
        return bspLiability;
    }

    public void setBspLiability(Double bspLiability) {
        this.bspLiability = bspLiability;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "betId=" + betId +
                ", marketId='" + marketId + '\'' +
                ", selectionId=" + selectionId +
                ", price=" + price +
                ", size=" + size +
                ", side='" + side + '\'' +
                ", bspLiability=" + bspLiability +
                ", status='" + status + '\'' +
                '}';
    }

    @JsonProperty("priceSize")
    private void unpackNested(Map<String, Object> priceSize) {
        setPrice((Double) priceSize.get("price"));
        setSize((Double) priceSize.get("size"));
    }
}
