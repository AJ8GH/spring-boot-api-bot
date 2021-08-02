package com.aj.helpers;

public class ListMarketBookResponse {

    public static final String JSON = "[\n" +
            "    {\n" +
            "        \"marketId\": \"1.179465437\",\n" +
            "        \"isMarketDataDelayed\": false,\n" +
            "        \"status\": \"OPEN\",\n" +
            "        \"betDelay\": 0,\n" +
            "        \"bspReconciled\": false,\n" +
            "        \"complete\": true,\n" +
            "        \"inplay\": false,\n" +
            "        \"numberOfWinners\": 1,\n" +
            "        \"numberOfRunners\": 19,\n" +
            "        \"numberOfActiveRunners\": 19,\n" +
            "        \"totalMatched\": 0.0,\n" +
            "        \"totalAvailable\": 0.0,\n" +
            "        \"crossMatching\": false,\n" +
            "        \"runnersVoidable\": false,\n" +
            "        \"version\": 214943490,\n" +
            "        \"runners\": [\n" +
            "            {\n" +
            "                \"selectionId\": 1,\n" +
            "                \"handicap\": 0.0,\n" +
            "                \"status\": \"ACTIVE\",\n" +
            "                \"ex\": {\n" +
            "                    \"availableToBack\": [],\n" +
            "                    \"availableToLay\": [],\n" +
            "                    \"tradedVolume\": []\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"selectionId\": 4,\n" +
            "                \"handicap\": 0.0,\n" +
            "                \"status\": \"ACTIVE\",\n" +
            "                \"ex\": {\n" +
            "                    \"availableToBack\": [],\n" +
            "                    \"availableToLay\": [],\n" +
            "                    \"tradedVolume\": []\n" +
            "                }\n" +
            "            }" +
            "       ]" +
            "   }" +
            "]";

    public static final String JSON_WITH_PRICES = "[\n" +
            "    {\n" +
            "        \"runners\": [\n" +
            "            {\n" +
            "                \"selectionId\": 57405,\n" +
            "                \"handicap\": 0.0,\n" +
            "                \"status\": \"ACTIVE\",\n" +
            "                \"lastPriceTraded\": 10.0,\n" +
            "                \"totalMatched\": 20.0,\n" +
            "                \"ex\": {\n" +
            "                    \"availableToBack\": [],\n" +
            "                    \"availableToLay\": [\n" +
            "                        {\n" +
            "                            \"price\": 3.0,\n" +
            "                            \"size\": 6.0\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"tradedVolume\": [\n" +
            "                        {\n" +
            "                            \"price\": 10.0,\n" +
            "                            \"size\": 20.0\n" +
            "                        }\n" +
            "                    ]\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"selectionId\": 57407,\n" +
            "                \"handicap\": 0.0,\n" +
            "                \"status\": \"ACTIVE\",\n" +
            "                \"totalMatched\": 0.0,\n" +
            "                \"ex\": {\n" +
            "                    \"availableToBack\": [],\n" +
            "                    \"availableToLay\": [\n" +
            "                        {\n" +
            "                            \"price\": 3.0,\n" +
            "                            \"size\": 6.0\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"tradedVolume\": []\n" +
            "                }\n" +
            "            },\n" +
            "            {\n" +
            "                \"selectionId\": 58805,\n" +
            "                \"handicap\": 0.0,\n" +
            "                \"status\": \"ACTIVE\",\n" +
            "                \"totalMatched\": 0.0,\n" +
            "                \"ex\": {\n" +
            "                    \"availableToBack\": [],\n" +
            "                    \"availableToLay\": [\n" +
            "                        {\n" +
            "                            \"price\": 3.0,\n" +
            "                            \"size\": 6.0\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"tradedVolume\": []\n" +
            "                }\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]";
}
