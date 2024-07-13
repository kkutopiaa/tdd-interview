package com.kuan.tddinterview.jackson.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadBox {
    private String name;
    private String value;
    private String desc;

}
