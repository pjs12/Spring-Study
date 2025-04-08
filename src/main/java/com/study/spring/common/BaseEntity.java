package com.study.spring.common;

import java.io.Serial;
import java.io.Serializable;

public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 762067417550985780L;

    public BaseEntity() {
    }
}
