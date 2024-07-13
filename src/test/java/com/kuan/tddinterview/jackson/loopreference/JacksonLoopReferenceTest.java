package com.kuan.tddinterview.jackson.loopreference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JacksonLoopReferenceTest {

    ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void should_get_exception_for_loop_reference() {
        FarmerLoopReference farmerLoopReference = FarmerLoopReference.builder().id(1L).name("qxk").build();
        RoomLoopReference roomLoopReference1 =
                RoomLoopReference.builder().id(1L).description("room-1").farmerLoopReference(farmerLoopReference).build();
        RoomLoopReference roomLoopReference2 =
                RoomLoopReference.builder().id(2L).description("room-2").farmerLoopReference(farmerLoopReference).build();
        RoomLoopReference roomLoopReference3 =
                RoomLoopReference.builder().id(3L).description("room-3").farmerLoopReference(farmerLoopReference).build();
        farmerLoopReference.setRoomLoopReferences(List.of(roomLoopReference1, roomLoopReference2, roomLoopReference3));

        Assertions.assertThrows(JsonMappingException.class, () -> {
            objectMapper.writeValueAsString(farmerLoopReference);
        });
    }


    @Test
    public void should_serialization_one_to_many_correctly() throws JsonProcessingException {
        Farmer farmer = Farmer.builder().id(1L).name("qxk").build();
        Room room1 = Room.builder().id(1L).description("room-1").farmer(farmer).build();
        Room room2 = Room.builder().id(2L).description("room-2").farmer(farmer).build();
        Room room3 = Room.builder().id(3L).description("room-3").farmer(farmer).build();
        farmer.setRooms(List.of(room1, room2, room3));

        String farmerJson = objectMapper.writeValueAsString(farmer);
        Assertions.assertTrue(farmerJson.contains("rooms"));
    }

    @Test
    public void should_serialization_many_to_one_correctly() throws JsonProcessingException {
        Farmer farmer = Farmer.builder().id(1L).name("qxk").build();
        Room room1 = Room.builder().id(1L).description("room-1").farmer(farmer).build();
        Room room2 = Room.builder().id(2L).description("room-2").farmer(farmer).build();
        Room room3 = Room.builder().id(3L).description("room-3").farmer(farmer).build();
        farmer.setRooms(List.of(room1, room2, room3));

        String room1Json = objectMapper.writeValueAsString(room1);
        Assertions.assertFalse(room1Json.contains("farmer"));
    }


}
