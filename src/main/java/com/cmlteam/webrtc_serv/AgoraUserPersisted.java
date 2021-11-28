package com.cmlteam.webrtc_serv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("agora_user")
public class AgoraUserPersisted {
  @Id private String email;
  private Set<String> appIds;
}
