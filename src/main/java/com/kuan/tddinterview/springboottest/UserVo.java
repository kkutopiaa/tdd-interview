package com.kuan.tddinterview.springboottest;

import com.kuan.tddinterview.springboottest.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVo {
    private Long id;

    private String displayName;
    private String username;

    public static UserVo fromUserModel(User user) {
        return new ModelMapper().map(user, UserVo.class);
    }

}
