/*-
 * ==========================LICENSE_START=================================
 * TRINITY4J: A set of Domain-Driven Design Libraries for Java Applications
 * ========================================================================
 * Copyright (C) 2019 - 2020 Christos Tsakostas, OREGOR LTD
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ===========================LICENSE_END==================================
 */

package com.oregor.trinity4j.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;

/** @author Christos Tsakostas */
public class DomainAssertionTest {

  /**
   * Should fail to instantiate.
   *
   * @throws NoSuchMethodException the no such method exception
   */
  @Test
  public void shouldFailToInstantiate() throws NoSuchMethodException {
    Constructor<DomainAssertion> constructor = DomainAssertion.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    assertThatThrownBy(constructor::newInstance).isInstanceOf(InvocationTargetException.class);
  }

  // ===============================================================================================

  /** Should succeed is null. */
  @Test
  public void shouldSucceedIsNull() {
    assertThatCode(() -> DomainAssertion.isNull(null, "is null")).doesNotThrowAnyException();
  }

  /** Should fail is null. */
  @Test
  public void shouldFailIsNull() {
    assertThatThrownBy(() -> DomainAssertion.isNull(new Object(), "is null"))
        .isInstanceOf(DomainException.class);
  }

  // ===============================================================================================

  /** Should succeed is not null. */
  @Test
  public void shouldSucceedIsNotNull() {
    assertThatCode(() -> DomainAssertion.isNotNull(new Object(), "is not null"))
        .doesNotThrowAnyException();
  }

  /** Should fail is not null. */
  @Test
  public void shouldFailIsNotNull() {
    assertThatThrownBy(() -> DomainAssertion.isNotNull(null, "is not null"))
        .isInstanceOf(DomainException.class);
  }

  // ===============================================================================================

  /** Should succeed is not empty. */
  @Test
  public void shouldSucceedIsNotEmpty() {
    assertThatCode(() -> DomainAssertion.isNotEmpty("xxx", "not empty")).doesNotThrowAnyException();
  }

  /** Should fail is not empty for null input. */
  @Test
  public void shouldFailIsNotEmptyForNullInput() {
    assertThatThrownBy(() -> DomainAssertion.isNotEmpty(null, "not empty"))
        .isInstanceOf(DomainException.class);
  }

  /** Should fail is not empty for empty input. */
  @Test
  public void shouldFailIsNotEmptyForEmptyInput() {
    assertThatThrownBy(() -> DomainAssertion.isNotEmpty("", "not empty"))
        .isInstanceOf(DomainException.class);
  }

  // ===============================================================================================

  /** Should succeed is true. */
  @Test
  public void shouldSucceedIsTrue() {
    assertThatCode(() -> DomainAssertion.isTrue(1 == 1, "wrong condition"))
        .doesNotThrowAnyException();
  }

  /** Should fail is true. */
  @Test
  public void shouldFailIsTrue() {
    assertThatThrownBy(() -> DomainAssertion.isTrue(1 == 2, "wrong condition"))
        .isInstanceOf(DomainException.class);
  }

  // ===============================================================================================

  /** Should succeed is false. */
  @Test
  public void shouldSucceedIsFalse() {
    assertThatCode(() -> DomainAssertion.isFalse(1 == 2, "wrong condition"))
        .doesNotThrowAnyException();
  }

  /** Should fail is false. */
  @Test
  public void shouldFailIsFalse() {
    assertThatThrownBy(() -> DomainAssertion.isFalse(1 == 1, "wrong condition"))
        .isInstanceOf(DomainException.class);
  }
}
