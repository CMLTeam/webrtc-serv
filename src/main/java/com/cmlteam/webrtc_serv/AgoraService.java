package com.cmlteam.webrtc_serv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public Optional<AgoraAppPersisted> findApp(String appId) {
    return agoraAppRepository.getFirstByAppId(appId);
  }

  public void deleteApp(String appId) {
    agoraAppRepository.deleteById(appId);
  }

  public List<AgoraAppDisplayed> listApps() {
    return agoraAppRepository.findAll().stream()
        .map(AgoraAppDisplayed::new)
        .collect(Collectors.toList());
  }
}
