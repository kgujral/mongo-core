package com.sixsprints.core;

import org.junit.Test;
import org.springframework.util.Assert;

import com.sixsprints.core.utils.EncryptionUtil;

public class EncryptionUtilTest {

  private static final String STRING_TO_ENCRYPT = "123";
  private static final String ENCRYPTED_STRING = "202cb962ac59075b964b07152d234b70";

  @Test
  public void shouldEncrypt() {
    String encrypted = EncryptionUtil.encrypt(STRING_TO_ENCRYPT);
    Assert.isTrue(ENCRYPTED_STRING.equals(encrypted), "Invalid encryption");
  }

  @Test
  public void shouldEncryptWithDesiredEncryption() {
    String encrypted = EncryptionUtil.encrypt(STRING_TO_ENCRYPT, "MD5");
    Assert.isTrue(ENCRYPTED_STRING.equals(encrypted), "Invalid encryption");
  }

}
