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

package com.oregor.trinity4j.api;

/**
 * The type Error.
 *
 * @author Christos Tsakostas
 */
public class Error {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private String errorCode;
  private String errorMessage;
  private String hintCode;
  private String hintMessage;

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  /**
   * With error error.
   *
   * @param errorCode the error code
   * @param errorMessage the error message
   * @return the error
   */
  public static Error withError(String errorCode, String errorMessage) {
    return new Error(errorCode, errorMessage, null, null);
  }

  /**
   * With error and hint error.
   *
   * @param errorCode the error code
   * @param errorMessage the error message
   * @param hintCode the hint code
   * @param hintMessage the hint message
   * @return the error
   */
  public static Error withErrorAndHint(
      String errorCode, String errorMessage, String hintCode, String hintMessage) {
    return new Error(errorCode, errorMessage, hintCode, hintMessage);
  }

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  private Error(String errorCode, String errorMessage, String hintCode, String hintMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.hintCode = hintCode;
    this.hintMessage = hintMessage;
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets error code.
   *
   * @return the error code
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * Gets error message.
   *
   * @return the error message
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * Gets hint code.
   *
   * @return the hint code
   */
  public String getHintCode() {
    return hintCode;
  }

  /**
   * Gets hint message.
   *
   * @return the hint message
   */
  public String getHintMessage() {
    return hintMessage;
  }
}
