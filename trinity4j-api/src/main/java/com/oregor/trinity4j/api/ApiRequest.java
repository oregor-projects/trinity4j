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
 * The type Api request.
 *
 * @author Christos Tsakostas
 */
public abstract class ApiRequest {

  private String userId;
  private String userLanguage;
  private String tenantId;
  private String ipAddress;
  private LocalDateTime occurredOn;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Api request. */
  protected ApiRequest() {
    setOccurredOn(LocalDateTime.now());
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets user id.
   *
   * @return the user id
   */
  public String getUserId() {
    return userId;
  }

  /**
   * Gets user language.
   *
   * @return the user language
   */
  public String getUserLanguage() {
    return userLanguage;
  }

  /**
   * Gets tenant id.
   *
   * @return the tenant id
   */
  public String getTenantId() {
    return tenantId;
  }

  /**
   * Gets ip address.
   *
   * @return the ip address
   */
  public String getIpAddress() {
    return ipAddress;
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
   * Sets user id.
   *
   * @param userId the user id
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * Sets user language.
   *
   * @param userLanguage the user language
   */
  public void setUserLanguage(String userLanguage) {
    this.userLanguage = userLanguage;
  }

  /**
   * Sets tenant id.
   *
   * @param tenantId the tenant id
   */
  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * Sets ip address.
   *
   * @param ipAddress the ip address
   */
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
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
