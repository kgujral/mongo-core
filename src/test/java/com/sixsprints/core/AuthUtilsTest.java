package com.sixsprints.core;

import org.junit.Test;
import org.springframework.util.Assert;

import com.sixsprints.core.exception.NotAuthorizedException;
import com.sixsprints.core.utils.AuthUtil;

public class AuthUtilsTest {

  private static final String RANDOM_TOKEN = "random_token";

  private static final String DEFAULT_PAYLOAD = "payload";

  @Test
  public void shouldCreateAndDecodeToken() throws NotAuthorizedException {
    String token = AuthUtil.createToken(DEFAULT_PAYLOAD);
    Assert.isTrue(DEFAULT_PAYLOAD.equals(AuthUtil.decodeToken(token)), "Token matching failed");
  }

  @Test
  public void shouldCreateAndDecodeTokenWithExpiryProvided() throws NotAuthorizedException {
    String token = AuthUtil.createToken(DEFAULT_PAYLOAD, 1);
    Assert.isTrue(DEFAULT_PAYLOAD.equals(AuthUtil.decodeToken(token)), "Token matching failed");
  }

  @Test(expected = NotAuthorizedException.class)
  public void shouldThrowExceptionBecauseOfWrongToken() throws NotAuthorizedException {
    AuthUtil.createToken(DEFAULT_PAYLOAD);
    AuthUtil.decodeToken(RANDOM_TOKEN);
  }

  @Test(expected = NotAuthorizedException.class)
  public void shouldThrowExceptionBecauseOfExpiredToken() throws NotAuthorizedException {
    String token = AuthUtil.createToken(DEFAULT_PAYLOAD, -1);
    AuthUtil.decodeToken(token);
  }

}
