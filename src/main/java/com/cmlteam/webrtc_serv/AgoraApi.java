package com.cmlteam.webrtc_serv;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("agora")
@Api(value = "Agora helper API")
public class AgoraApi {
  private final AgoraService agoraService;

  @ApiOperation(value = "register new Agora app")
  @PostMapping(value = "app")
  public ApiResult addApp(AgoraApp agoraApp) {
    throw new UnsupportedOperationException();
  }

  @ApiOperation(value = "generate new Agora API token")
  @PostMapping(value = "token")
  public AgoraTokenResult generateToken(AgoraTokenRequest tokenRequest) {
    throw new UnsupportedOperationException();
  }
}
