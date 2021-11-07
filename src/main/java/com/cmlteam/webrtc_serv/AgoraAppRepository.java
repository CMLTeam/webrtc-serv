package com.cmlteam.webrtc_serv;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgoraAppRepository extends MongoRepository<AgoraApp, String> {}
