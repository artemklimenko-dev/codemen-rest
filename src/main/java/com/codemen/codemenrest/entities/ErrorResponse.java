package com.codemen.codemenrest.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    private OffsetDateTime timestamp;
    private int status;
    private List<String> errors;
    private String path;
}
