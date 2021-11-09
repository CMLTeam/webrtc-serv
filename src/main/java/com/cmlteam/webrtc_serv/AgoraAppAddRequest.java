package com.cmlteam.webrtc_serv;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class AgoraAppAddRequest {
  @ApiModelProperty @NotBlank private String appId;
  @ApiModelProperty @NotBlank private String appCert;

  @ApiModelProperty(value = "Token expiration time in seconds", example = "600 (for 10 min)")
  private int expirationTimeInSec;
}
