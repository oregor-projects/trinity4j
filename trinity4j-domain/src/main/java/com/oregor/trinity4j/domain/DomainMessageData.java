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

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * The type Domain message data.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class DomainMessageData {

  @Id
  @Column(updatable = false)
  private UUID id;

  private UUID rootId;

  private UUID tenantId;

  private Integer streamVersion;

  private String messageName;

  private Integer messageVersion;

  @Column(columnDefinition = "LONGTEXT")
  @Lob
  private String message;

  private String principal;

  private String ipAddress;

  private LocalDateTime occurredOn;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract domain message data. */
  protected DomainMessageData() {
    super();
  }

  /**
   * Instantiates a new Abstract domain message data.
   *
   * @param id the id
   * @param rootId the root id
   * @param tenantId the tenant id
   * @param streamVersion the stream version
   * @param messageName the message name
   * @param messageVersion the message version
   * @param message the message
   * @param principal the principal
   * @param ipAddress the ip address
   * @param occurredOn the occurred on
   */
  public DomainMessageData(
      UUID id,
      UUID rootId,
      UUID tenantId,
      Integer streamVersion,
      String messageName,
      Integer messageVersion,
      String message,
      String principal,
      String ipAddress,
      LocalDateTime occurredOn) {
    this.id = id;
    this.rootId = rootId;
    this.tenantId = tenantId;
    this.streamVersion = streamVersion;
    this.messageName = messageName;
    this.messageVersion = messageVersion;
    this.message = message;
    this.principal = principal;
    this.ipAddress = ipAddress;
    this.occurredOn = occurredOn;
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  /**
   * Gets id.
   *
   * @return the id
   */
  public UUID getId() {
    return id;
  }

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
   * Gets stream version.
   *
   * @return the stream version
   */
  public Integer getStreamVersion() {
    return streamVersion;
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
   * Gets message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Gets principal.
   *
   * @return the principal
   */
  public String getPrincipal() {
    return principal;
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

}
