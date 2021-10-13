package com.qiongqi.utils.response;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class dataEntity<T> {
    private T t;

}
