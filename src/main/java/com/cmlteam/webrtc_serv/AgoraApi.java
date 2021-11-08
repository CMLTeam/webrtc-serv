package com.cmlteam.webrtc_serv;

import io.agora.media.RtcTokenBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("agora")
@Api(value = "Agora helper API")
public class AgoraApi {
  private final AgoraService agoraService;

  private static final RtcTokenBuilder rtcTokenBuilder = new RtcTokenBuilder();

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
  public ResponseEntity<AgoraTokenResult> generateToken(@Valid AgoraTokenRequest tokenRequest) {
    String appId = tokenRequest.getAppId();
    Optional<AgoraAppPersisted> appIdOpt = agoraService.findApp(appId);
    if (appIdOpt.isPresent()) {
      int expirationTimeInSeconds = 10 * 60; // 10 min
      int timestamp = (int) (System.currentTimeMillis() / 1000 + expirationTimeInSeconds);
      AgoraAppPersisted agoraApp = appIdOpt.get();
      RtcTokenBuilder.Role role = getRole(tokenRequest.getRole());
      if (role == null) {
        return ResponseEntity.badRequest()
            .body(new AgoraTokenResult(false, "role not found", null));
      }
      String result =
          rtcTokenBuilder.buildTokenWithUid(
              appId,
              agoraApp.getAppCert(),
              tokenRequest.getChannelName(),
              tokenRequest.getUid(),
              role,
              timestamp);
      return ResponseEntity.ok(new AgoraTokenResult(true, null, result));
    } else {
      return ResponseEntity.badRequest()
          .body(new AgoraTokenResult(false, "app ID not found", null));
    }
  }

  private RtcTokenBuilder.Role getRole(int roleId) {
    for (RtcTokenBuilder.Role role : RtcTokenBuilder.Role.values()) {
      if (roleId == role.initValue) return role;
    }
    return null;
  }
}
