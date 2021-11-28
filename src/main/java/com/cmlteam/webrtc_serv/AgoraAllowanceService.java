package com.cmlteam.webrtc_serv;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgoraAllowanceService {
  private final AgoraUserRepository agoraUserRepository;

  public void allowAccess(AgoraAllowanceRequest agoraAllowanceRequest) {
    AgoraUserPersisted agoraUserPersisted =
        agoraUserRepository
            .getFirstByEmail(agoraAllowanceRequest.getEmail())
            .orElseGet(
                () -> new AgoraUserPersisted(agoraAllowanceRequest.getEmail(), new HashSet<>()));

    Set<String> appIds = agoraUserPersisted.getAppIds();
    appIds.add(agoraAllowanceRequest.getAppId());
    agoraUserRepository.save(agoraUserPersisted);
  }

  public boolean revokeAccess(AgoraAllowanceRequest agoraAllowanceRequest) {
    Optional<AgoraUserPersisted> agoraUserPersistedOpt =
        agoraUserRepository.getFirstByEmail(agoraAllowanceRequest.getEmail());

    if (agoraUserPersistedOpt.isEmpty()) {
      return false;
    }

    AgoraUserPersisted agoraUserPersisted = agoraUserPersistedOpt.get();
    agoraUserPersisted.getAppIds().remove(agoraAllowanceRequest.getAppId());
    agoraUserRepository.save(agoraUserPersisted);
    return true;
  }

  public boolean checkAccess(AgoraAllowanceRequest agoraAllowanceRequest) {
    Optional<AgoraUserPersisted> agoraUserPersistedOpt =
        agoraUserRepository.getFirstByEmail(agoraAllowanceRequest.getEmail());

    return agoraUserPersistedOpt.isPresent()
        && agoraUserPersistedOpt.get().getAppIds().contains(agoraAllowanceRequest.getAppId());
  }

  public List<AgoraUser> listUsers() {
    return agoraUserRepository.findAll().stream().map(AgoraUser::new).collect(Collectors.toList());
  }
}
