package com.vistar.streamcinema.dto.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    @JsonProperty("access_token")
    private String accessToken;
    private String username;
}
