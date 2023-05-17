package com.dm.customer.dto;

import com.dm.customer.exception.RestApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Message response class to pass the details in http response
 */
@RequiredArgsConstructor
@Getter
@Setter
public class MessageResponse implements RestApiResponse {

    private String status;
    private int statusCode;
    private String code;
    private List<Message> messages;
}
