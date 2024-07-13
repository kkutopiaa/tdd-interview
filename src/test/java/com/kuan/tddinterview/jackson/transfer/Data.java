package com.kuan.tddinterview.jackson.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty(value = "list")
    private List<Profile> list;


}
