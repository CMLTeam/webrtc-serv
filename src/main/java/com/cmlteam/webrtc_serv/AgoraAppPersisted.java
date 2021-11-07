package com.cmlteam.webrtc_serv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("agora_app")
public class AgoraAppPersisted {
  @Id private String appId;
  private String appCert;

  public AgoraAppPersisted(AgoraAppAddRequest appAddRequest) {
    this(appAddRequest.getAppId(), appAddRequest.getAppCert());
  }
}
