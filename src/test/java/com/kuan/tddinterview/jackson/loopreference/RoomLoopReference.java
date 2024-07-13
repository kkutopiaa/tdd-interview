package com.kuan.tddinterview.jackson.loopreference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomLoopReference {

    private Long id;

    private String description;

    private FarmerLoopReference farmerLoopReference;

}
