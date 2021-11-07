package com.cmlteam.webrtc_serv;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApiResult {
  private final boolean success;
  private final String error;

  private static final ApiResult SUCCESS = new ApiResult(true, null);

  public static ApiResult success() {
    return SUCCESS;
  }

  public static ApiResult error(String error) {
    return new ApiResult(false, error);
  }
}
