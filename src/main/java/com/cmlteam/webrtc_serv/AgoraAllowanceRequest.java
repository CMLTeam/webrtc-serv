package com.cmlteam.webrtc_serv;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class AgoraAllowanceRequest {
  @ApiModelProperty @NotBlank @Email private String email;
  @ApiModelProperty @NotBlank private String appId;
}
