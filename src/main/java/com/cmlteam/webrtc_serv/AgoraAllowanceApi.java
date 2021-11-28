package com.cmlteam.webrtc_serv;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("agora/allowance")
@Api(value = "Agora allowance API")
public class AgoraAllowanceApi {
  private final AgoraAllowanceService agoraAllowanceService;

  @ApiOperation(value = "Allow user access to app")
  @PostMapping(value = "")
  public ResponseEntity<ApiResult> allowAccess(AgoraAllowanceRequest agoraAllowanceRequest) {
    throw new UnsupportedOperationException("TDB");
  }

  @ApiOperation(value = "Revoke user access to app")
  @DeleteMapping(value = "")
  public ResponseEntity<ApiResult> revokeAccess(AgoraAllowanceRequest agoraAllowanceRequest) {
    throw new UnsupportedOperationException("TDB");
  }

  @ApiOperation(value = "Check user has access to app")
  @PostMapping(value = "/check")
  public ResponseEntity<ApiBooleanResult> checkAccess(AgoraAllowanceRequest agoraAllowanceRequest) {
    throw new UnsupportedOperationException("TDB");
  }

  @ApiOperation(value = "List users (with allowed apps)")
  @GetMapping(value = "/user")
  public ResponseEntity<List<AgoraUser>> listUsers() {
    throw new UnsupportedOperationException("TDB");
  }
}
