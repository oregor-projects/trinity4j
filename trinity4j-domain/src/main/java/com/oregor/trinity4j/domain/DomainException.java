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
 * The type Domain exception.
 *
 * @author Christos Tsakostas
 */
public class DomainException extends RuntimeException {

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  private static final long serialVersionUID = 1L;

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private String errorCode;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Domain exception.
   *
   * @param errorCode the key
   */
  public DomainException(String errorCode) {
    super(errorCode, null, false, errorCode == null ? true : false);
    this.errorCode = errorCode;
  }

  /**
   * Instantiates a new Domain exception.
   *
   * @param errorCode the error code
   * @param cause the cause
   * @param enableSuppression the enable suppression
   * @param writableStackTrace the writable stack trace
   */
  private DomainException(
      String errorCode, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(errorCode, cause, enableSuppression, writableStackTrace);
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
}
