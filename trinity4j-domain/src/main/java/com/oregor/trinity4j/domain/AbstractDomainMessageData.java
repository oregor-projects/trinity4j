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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

/**
 * The type Abstract domain message data.
 *
 * @author Christos Tsakostas
 */
@MappedSuperclass
public abstract class AbstractDomainMessageData implements DomainMessageData {

  @Id
  @Column(updatable = false)
  private UUID messageId;

  private LocalDateTime occurredOn;

  private UUID rootId;

  private Integer rootVersion;

  @Enumerated(EnumType.STRING)
  private DomainMessageType messageType;

  private String messageName;

  private Integer messageVersion;

  @Column(columnDefinition = "LONGTEXT")
  @Lob
  private String messageBody;

  private UUID userId;

  private String ipAddress;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract domain message data. */
  protected AbstractDomainMessageData() {
    super();
  }

  /**
   * Instantiates a new Abstract domain message data.
   *
   * @param messageId the message id
   * @param occurredOn the occurred on
   * @param rootId the root id
   * @param rootVersion the root version
   * @param messageType the message type
   * @param messageName the message name
   * @param messageVersion the message version
   * @param messageBody the message body
   * @param userId the user id
   * @param ipAddress the ip address
   */
  public AbstractDomainMessageData(
      UUID messageId,
      LocalDateTime occurredOn,
      UUID rootId,
      Integer rootVersion,
      DomainMessageType messageType,
      String messageName,
      Integer messageVersion,
      String messageBody,
      UUID userId,
      String ipAddress) {
    this.messageId = messageId;
    this.occurredOn = occurredOn;
    this.rootId = rootId;
    this.rootVersion = rootVersion;
    this.messageType = messageType;
    this.messageName = messageName;
    this.messageVersion = messageVersion;
    this.messageBody = messageBody;
    this.userId = userId;
    this.ipAddress = ipAddress;
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public UUID getMessageId() {
    return messageId;
  }

  @Override
  public LocalDateTime getOccurredOn() {
    return occurredOn;
  }

  @Override
  public UUID getRootId() {
    return rootId;
  }

  @Override
  public Integer getRootVersion() {
    return rootVersion;
  }

  @Override
  public String getMessageType() {
    return messageType.name();
  }

  @Override
  public String getMessageName() {
    return messageName;
  }

  @Override
  public Integer getMessageVersion() {
    return messageVersion;
  }

  @Override
  public String getMessageBody() {
    return messageBody;
  }

  @Override
  public UUID getUserId() {
    return userId;
  }

  @Override
  public String getIpAddress() {
    return ipAddress;
  }
}
