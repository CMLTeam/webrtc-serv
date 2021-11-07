package com.cmlteam.webrtc_serv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgoraService {
  private final AgoraAppRepository agoraAppRepository;

  public boolean isExistingApp(String appId) {
    return agoraAppRepository.existsById(appId);
  }

  public AgoraAppPersisted addApp(AgoraAppAddRequest agoraAppAddRequest) {
    return agoraAppRepository.save(new AgoraAppPersisted(agoraAppAddRequest));
  }
}
