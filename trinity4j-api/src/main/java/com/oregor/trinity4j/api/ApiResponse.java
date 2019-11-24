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

import java.time.LocalDateTime;

/**
 * The type Api response.
 *
 * @author Christos Tsakostas
 */
public class ApiResponse {

  // ===============================================================================================
  // STATE
  // ===============================================================================================

  private ApiError error;
  private LocalDateTime occurredOn;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Api response. */
  protected ApiResponse() {
    setOccurredOn(LocalDateTime.now());
  }

  /**
   * Instantiates a new Api response.
   *
   * @param error the failure message
   */
  public ApiResponse(ApiError error) {
    setError(error);
    setOccurredOn(LocalDateTime.now());
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets failure message.
   *
   * @return the failure message
   */
  public ApiError getError() {
    return error;
  }

  /**
   * Gets occurred on.
   *
   * @return the occurred on
   */
  public LocalDateTime getOccurredOn() {
    return occurredOn;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets failure message.
   *
   * @param error the failure message
   */
  public void setError(ApiError error) {
    this.error = error;
  }

  /**
   * Sets occurred on.
   *
   * @param occurredOn the occurred on
   */
  public void setOccurredOn(LocalDateTime occurredOn) {
    this.occurredOn = occurredOn;
  }
}
