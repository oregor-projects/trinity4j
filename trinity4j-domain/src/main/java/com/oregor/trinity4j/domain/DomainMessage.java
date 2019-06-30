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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base class for Domain Messages.
 *
 * <p>Domain Messages can be Domain Events or Domain Commands.
 *
 * <p>A domain event captures the memory of something interesting which affects the domain. In
 * principle, the changes should be captured. Additional info can be transferred as well, which can
 * be useful to the subscribers.
 *
 * <p>A domain command captures the intent of something that needs to happen in the bounded context
 * that created it or in another one.
 *
 * <p>References: - https://martinfowler.com/eaaDev/DomainEvent.html
 *
 * @author Christos Tsakostas
 */
public abstract class DomainMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID rootId;
  private UUID tenantId;
  private transient DomainMessageType messageType;
  private transient String messageName;
  private Integer messageVersion;
  private LocalDateTime occurredOn;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Domain message. */
  public DomainMessage() {
    this.setOccurredOn(LocalDateTime.now());
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets root id.
   *
   * @return the root id
   */
  public UUID getRootId() {
    return rootId;
  }

  /**
   * Gets tenant id.
   *
   * @return the tenant id
   */
  public UUID getTenantId() {
    return tenantId;
  }

  /**
   * Gets message type.
   *
   * @return the message type
   */
  public DomainMessageType getMessageType() {
    return messageType;
  }

  /**
   * Gets message name.
   *
   * @return the message name
   */
  public String getMessageName() {
    return messageName;
  }

  /**
   * Gets message version.
   *
   * @return the message version
   */
  public Integer getMessageVersion() {
    return messageVersion;
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
   * Sets root id.
   *
   * @param rootId the root id
   */
  public void setRootId(UUID rootId) {
    this.rootId = rootId;
  }

  /**
   * Sets tenant id.
   *
   * @param tenantId the tenant id
   */
  public void setTenantId(UUID tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * Sets message type.
   *
   * @param messageType the message type
   */
  public void setMessageType(DomainMessageType messageType) {
    this.messageType = messageType;
  }

  /**
   * Sets message name.
   *
   * @param messageName the message name
   */
  public void setMessageName(String messageName) {
    this.messageName = messageName;
  }

  /**
   * Sets message version.
   *
   * @param messageVersion the message version
   */
  public void setMessageVersion(Integer messageVersion) {
    this.messageVersion = messageVersion;
  }

  // ===============================================================================================
  // GUARDS
  // ===============================================================================================

  /**
   * Sets occurred on.
   *
   * @param occurredOn the occurred on
   */
  private void setOccurredOn(LocalDateTime occurredOn) {
    this.occurredOn = occurredOn;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

}
