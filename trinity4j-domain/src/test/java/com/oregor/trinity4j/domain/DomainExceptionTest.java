package com.oregor.trinity4j.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/**
 * @author Christos Tsakostas
 */
class DomainExceptionTest {

  @Test
  void getKey() {
    assertThatThrownBy(() -> throwDomainException("someMessageKey")).hasMessage("someMessageKey");
  }

  @Test
  void nullMessageKey() {
    assertThatThrownBy(() -> throwDomainException(null)).hasMessage(null);
  }

  private void throwDomainException(String messageKey) {
    throw new DomainException(messageKey);
  }
}