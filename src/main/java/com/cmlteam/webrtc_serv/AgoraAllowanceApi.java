package com.cmlteam.webrtc_serv;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("agora/allowance")
@Api(value = "Agora allowance API")
public class AgoraAllowanceApi {
  private final AgoraAllowanceService agoraAllowanceService;

  @ApiOperation(value = "Allow user access to app")
  @PostMapping(value = "")
  public ResponseEntity<ApiResult> allowAccess(
      @Valid @RequestBody AgoraAllowanceRequest agoraAllowanceRequest) {
    agoraAllowanceService.allowAccess(agoraAllowanceRequest);
    return ResponseEntity.ok(ApiResult.success());
  }

  @ApiOperation(value = "Revoke user access to app")
  @DeleteMapping(value = "")
  public ResponseEntity<ApiResult> revokeAccess(
      @Valid @RequestBody AgoraAllowanceRequest agoraAllowanceRequest) {
    if (agoraAllowanceService.revokeAccess(agoraAllowanceRequest)) {
      return ResponseEntity.ok(ApiResult.success());
    }
    return ResponseEntity.notFound().build();
  }

  @ApiOperation(value = "Check user has access to app")
  @PostMapping(value = "/check")
  public ResponseEntity<ApiBooleanResult> checkAccess(
      @Valid @RequestBody AgoraAllowanceRequest agoraAllowanceRequest) {
    return ResponseEntity.ok(
        ApiBooleanResult.of(agoraAllowanceService.checkAccess(agoraAllowanceRequest)));
  }

  // TODO paging?
  @ApiOperation(value = "List users (with allowed apps)")
  @GetMapping(value = "/user")
  public ResponseEntity<List<AgoraUser>> listUsers() {
    return ResponseEntity.ok(agoraAllowanceService.listUsers());
  }
}
