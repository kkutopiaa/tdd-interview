package com.kuan.tddinterview.springdatajpa.nplus1;

import com.kuan.tddinterview.springdatajpa.nplus1.batchsize.AddressBatchSize;
import com.kuan.tddinterview.springdatajpa.nplus1.batchsize.UserInfoBatchSize;
import com.kuan.tddinterview.springdatajpa.nplus1.entitygraph.AddressEntityGraph;
import com.kuan.tddinterview.springdatajpa.nplus1.entitygraph.UserInfoEntityGraph;
import com.kuan.tddinterview.springdatajpa.nplus1.fetchmode.AddressFetchModel;
import com.kuan.tddinterview.springdatajpa.nplus1.fetchmode.UserInfoFetchModel;
import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.Address;
import com.kuan.tddinterview.springdatajpa.nplus1.reproduce.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.IntStream;

public class UserGenerator {

    public static void save(JpaRepository repository, Type type) {
        List<NPlusOneEntity> users = getUsers(type);
        List entities = switch (type) {
            case REPRODUCE -> users.stream().map(e -> (UserInfo) e).toList();
            case BATCH_SIZE -> users.stream().map(e -> (UserInfoBatchSize) e).toList();
            case FETCH_MODE -> users.stream().map(e -> (UserInfoFetchModel) e).toList();
            case ENTITY_GRAPH -> users.stream().map(e -> (UserInfoEntityGraph) e).toList();
        };
        repository.saveAllAndFlush(entities);
    }

    public static List<NPlusOneEntity> getUsers(Type type) {
        return IntStream.rangeClosed(1, 3)
                .mapToObj(i -> getUserInfo("name" + i, "tel" + i,
                        "city" + i, "city" + (i + 10), type))
                .toList();
    }

    private static NPlusOneEntity getUserInfo(String name, String number, String city0, String city1, Type type) {
        return switch (type) {
            case REPRODUCE -> UserInfo.builder().name(name).telephone(number)
                    .addressList(getAddresses(city0, city1, type)).build();
            case BATCH_SIZE -> UserInfoBatchSize.builder().name(name).telephone(number)
                    .addressList(getAddresses(city0, city1, type)).build();
            case FETCH_MODE -> UserInfoFetchModel.builder().name(name).telephone(number)
                    .addressList(getAddresses(city0, city1, type)).build();
            case ENTITY_GRAPH -> UserInfoEntityGraph.builder().name(name).telephone(number)
                    .addressList(getAddresses(city0, city1, type)).build();
        };
    }

    private static List getAddresses(String city0, String city1, Type type) {
        return switch (type) {
            case REPRODUCE -> List.of(Address.builder().city(city0).build(), Address.builder().city(city1).build());
            case BATCH_SIZE ->
                    List.of(AddressBatchSize.builder().city(city0).build(), AddressBatchSize.builder().city(city1).build());
            case FETCH_MODE ->
                    List.of(AddressFetchModel.builder().city(city0).build(), AddressFetchModel.builder().city(city1).build());
            case ENTITY_GRAPH ->
                    List.of(AddressEntityGraph.builder().city(city0).build(), AddressEntityGraph.builder().city(city1).build());
        };
    }

    enum Type {
        REPRODUCE, BATCH_SIZE, FETCH_MODE, ENTITY_GRAPH
    }


}
