/*-
 * ==========================LICENSE_START=================================
 * TRINITY4J: A set of Domain-Driven Design Libraries for Java Applications
 * ========================================================================
 * Copyright (C) 2019 Christos Tsakostas, OREGOR LTD
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

/**
 * The Domain Assertions utility class.
 *
 * @author Christos Tsakostas
 */
public final class DomainAssertion {

  private DomainAssertion() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Is null.
   *
   * @param object the object
   * @param message the message
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new DomainException(message);
    }
  }

  /**
   * Is not null.
   *
   * @param object the object
   * @param message the message
   */
  public static void isNotNull(Object object, String message) {
    if (object == null) {
      throw new DomainException(message);
    }
  }

  /**
   * Is not empty.
   *
   * @param stringToCheck the string to check
   * @param message the message
   */
  public static void isNotEmpty(String stringToCheck, String message) {
    if (stringToCheck == null || stringToCheck.trim().equals("")) {
      throw new DomainException(message);
    }
  }

  /**
   * Is true.
   *
   * @param expression the expression
   * @param message the message
   */
  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new DomainException(message);
    }
  }

  /**
   * Is false.
   *
   * @param condition the condition
   * @param message the message
   */
  public static void isFalse(boolean condition, String message) {
    DomainAssertion.isTrue(!condition, message);
  }
}
