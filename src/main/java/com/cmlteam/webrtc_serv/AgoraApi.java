package com.cmlteam.webrtc_serv;

import io.agora.media.RtcTokenBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
  public ResponseEntity<ApiResult> addApp(
      @Valid @RequestBody AgoraAppAddRequest agoraAppAddRequest) {
    if (agoraAppAddRequest.getExpirationTimeInSec() == 0) {
      return ResponseEntity.badRequest()
          .body(
              ApiResult.error(
                  "must set expirationTimeInSec to positive value")); // TODO redo via javax
      // validation
    }
    if (agoraService.isExistingApp(agoraAppAddRequest.getAppId())) {
      return ResponseEntity.badRequest().body(ApiResult.error("appId already exists"));
    }

    AgoraAppPersisted agoraApp = agoraService.addApp(agoraAppAddRequest);
    return ResponseEntity.ok(ApiResult.success());
  }

  @ApiOperation(value = "delete Agora app")
  @DeleteMapping(value = "app/{appId}")
  public ResponseEntity<ApiResult> deleteApp(@PathVariable String appId) {
    if (!agoraService.isExistingApp(appId)) {
      return ResponseEntity.notFound().build();
    }

    agoraService.deleteApp(appId);

    return ResponseEntity.ok(ApiResult.success());
  }

  @ApiOperation(value = "list apps")
  @GetMapping(value = "app")
  public ResponseEntity<List<AgoraAppDisplayed>> listApps() {
    return ResponseEntity.ok(agoraService.listApps());
  }

  @ApiOperation(value = "generate new Agora API token")
  @PostMapping(value = "token")
  public ResponseEntity<AgoraTokenResult> generateToken(
      @Valid @RequestBody AgoraTokenRequest tokenRequest) {
    String appId = tokenRequest.getAppId();
    Optional<AgoraAppPersisted> appIdOpt = agoraService.findApp(appId);
    if (appIdOpt.isPresent()) {
      AgoraAppPersisted agoraApp = appIdOpt.get();
      int expirationTimeInSeconds = agoraApp.getExpirationTimeInSec();
      int timestamp = (int) (System.currentTimeMillis() / 1000 + expirationTimeInSeconds);
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
