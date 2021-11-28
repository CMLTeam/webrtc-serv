package com.cmlteam.webrtc_serv;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@ApiModel
public class ApiBooleanResult {
  @ApiModelProperty private final boolean result;

  private static final ApiBooleanResult TRUE = new ApiBooleanResult(true);
  private static final ApiBooleanResult FALSE = new ApiBooleanResult(true);

  public static ApiBooleanResult of(boolean value) {
    return value ? TRUE : FALSE;
  }
}
