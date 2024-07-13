package com.kuan.tddinterview.jackson.loopreference;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    private Long id;

    private String description;

    @JsonBackReference
    private Farmer farmer;

}
