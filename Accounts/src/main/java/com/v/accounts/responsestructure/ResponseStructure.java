package com.v.accounts.responsestructure;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor // Required for @Builder with final fields
public class ResponseStructure<T> {

    private final T data;
    private final String message;
    private final int statusCode;

}
