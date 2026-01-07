package net.thewesthill.wps.model.drive_freq.items.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class Data {

    @JsonProperty("items")
    private List<Items> items;

    @JsonProperty("next_page_token")
    private String nextPageToken;
}
