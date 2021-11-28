package com.cmlteam.webrtc_serv;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgoraUserRepository extends MongoRepository<AgoraUserPersisted, String> {
  Optional<AgoraUserPersisted> getFirstByEmail(String email);
}
