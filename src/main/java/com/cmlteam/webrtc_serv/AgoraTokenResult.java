package com.cmlteam.webrtc_serv;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AgoraTokenResult {
  private boolean success;
  private String error;
  private String token;
}
