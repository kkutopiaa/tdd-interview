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
public class Farmer {

    private Long id;

    private String name;

    private List<Room> rooms;

}
