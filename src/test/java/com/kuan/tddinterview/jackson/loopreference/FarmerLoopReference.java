package com.kuan.tddinterview.jackson.loopreference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmerLoopReference {

    private Long id;

    private String name;

    private List<RoomLoopReference> roomLoopReferences;

}
