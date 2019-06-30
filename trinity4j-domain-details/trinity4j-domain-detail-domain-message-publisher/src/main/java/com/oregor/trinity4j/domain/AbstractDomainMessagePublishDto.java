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

/**
 * The type Abstract domain message publish dto.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractDomainMessagePublishDto implements DomainMessagePublishDto {

  private UUID messageId;
  private LocalDateTime occurredOn;
  private UUID rootId;
  private String messageName;
  private Integer messageVersion;
  private String messageBody;
  private String context;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract domain message publish dto.
   *
   * @param messageId the message id
   * @param occurredOn the occurred on
   * @param rootId the root id
   * @param messageName the message name
   * @param messageVersion the message version
   * @param messageBody the message body
   * @param context the context
   */
  public AbstractDomainMessagePublishDto(
      UUID messageId,
      LocalDateTime occurredOn,
      UUID rootId,
      String messageName,
      Integer messageVersion,
      String messageBody,
      String context) {
    this.messageId = messageId;
    this.occurredOn = occurredOn;
    this.rootId = rootId;
    this.messageName = messageName;
    this.messageVersion = messageVersion;
    this.messageBody = messageBody;
    this.context = context;
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
  public String getContext() {
    return context;
  }
}
