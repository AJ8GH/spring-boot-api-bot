package com.aj.esa.models;

import com.aj.models.ExchangePrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class RunnerCache {
    @JsonProperty("id")
    private Long selectionId;
    private List<ExchangePrice> batl;
    private List<ExchangePrice> batb;
    private List<ExchangePrice> bdatb;
    private List<ExchangePrice> bdatl;

    @JsonProperty("batb")
    public void unpackBatb(List<List<Double>> prices) {
        this.batb = unpackPrices(prices);
    }

    @JsonProperty("batl")
    public void unpackBatl(List<List<Double>> prices) {
        this.batb = unpackPrices(prices);
    }

    @JsonProperty("bdatb")
    public void unpackBdatb(List<List<Double>> prices) {
        this.batb = unpackPrices(prices);
    }

    @JsonProperty("bdatl")
    public void unpackBdatl(List<List<Double>> prices) {
        this.batb = unpackPrices(prices);
    }

    private List<ExchangePrice> unpackPrices(List<List<Double>> prices) {
        List<ExchangePrice> returnList = new java.util.ArrayList<>();

        for (List<Double> price : prices) {

            if (!(price.get(1) + price.get(2) > (double) 0)) {
                ExchangePrice exchangePrice = new ExchangePrice();
                exchangePrice.setPrice(price.get(1));
                exchangePrice.setSize(price.get(2));
                returnList.add(exchangePrice);
            }
        }
        return returnList;
    }
}
