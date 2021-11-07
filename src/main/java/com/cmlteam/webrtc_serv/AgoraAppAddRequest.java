package com.cmlteam.webrtc_serv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgoraAppAddRequest {
    @NotBlank private String appId;
    @NotBlank private String appCert;
}
