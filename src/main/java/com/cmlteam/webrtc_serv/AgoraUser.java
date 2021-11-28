package com.cmlteam.webrtc_serv;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@ApiModel
public class AgoraUser {
  private final String email;
  private final Set<String> appIds;

  public AgoraUser(AgoraUserPersisted agoraUserPersisted) {
    this(agoraUserPersisted.getEmail(), agoraUserPersisted.getAppIds());
  }
}
