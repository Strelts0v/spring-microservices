package com.straltsou.limitsservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitConfig {

    int maximum;
    int minimum;
}
