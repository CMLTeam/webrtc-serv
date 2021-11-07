package com.cmlteam.webrtc_serv;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("agora")
@Api(value = "Agora helper API")
public class AgoraApi {
  private final AgoraService agoraService;

  @ApiOperation(value = "register new Agora app")
  @PostMapping(value = "app")
  public ResponseEntity<ApiResult> addApp(@Valid AgoraAppAddRequest agoraAppAddRequest) {
    if (agoraService.isExistingApp(agoraAppAddRequest.getAppId())) {
      return ResponseEntity.badRequest().body(ApiResult.error("appId already exists"));
    }

    AgoraAppPersisted agoraApp = agoraService.addApp(agoraAppAddRequest);
    return ResponseEntity.ok(ApiResult.success());
  }

  @ApiOperation(value = "generate new Agora API token")
  @PostMapping(value = "token")
  public ResponseEntity<AgoraTokenResult> generateToken(AgoraTokenRequest tokenRequest) {
    throw new UnsupportedOperationException();
  }
}
