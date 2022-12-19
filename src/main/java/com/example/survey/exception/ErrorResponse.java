package com.example.survey.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

           //necessary data bundle (@ToString, @EqualsAndHashCode, @Getter, @Setter)
@Data      // with RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private Integer statusCode;
    private String message;

    public ErrorResponse(String message){
        super();
        this.message = message;
    }
}
