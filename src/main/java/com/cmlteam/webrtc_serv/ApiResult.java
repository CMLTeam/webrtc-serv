package com.cmlteam.webrtc_serv;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel(
    value =
        "Represents the generic result of a REST call. In case of error the HTTP status should be 400+")
public class ApiResult {
  @ApiModelProperty private final boolean success;
  @ApiModelProperty private final String error;

  private static final ApiResult SUCCESS = new ApiResult(true, null);

  public static ApiResult success() {
    return SUCCESS;
  }

  public static ApiResult error(String error) {
    return new ApiResult(false, error);
  }
}
