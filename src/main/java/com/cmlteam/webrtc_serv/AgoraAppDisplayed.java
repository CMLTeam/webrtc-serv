package com.cmlteam.webrtc_serv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@AllArgsConstructor
public class AgoraAppDisplayed {
  private final String appId;
  private final int expirationTimeInSec;

  public AgoraAppDisplayed(AgoraAppPersisted agoraApp) {
    this(agoraApp.getAppId(), agoraApp.getExpirationTimeInSec());
  }
}
