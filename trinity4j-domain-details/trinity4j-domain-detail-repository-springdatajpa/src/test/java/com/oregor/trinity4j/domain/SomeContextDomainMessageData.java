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
import javax.persistence.Entity;

/**
 * The type Context domain message data.
 *
 * @author Christos Tsakostas
 */
@Entity
public class SomeContextDomainMessageData extends AbstractDomainMessageData {

  private SomeContextDomainMessageData() {
    super();
  }

  /**
   * Instantiates a new Some context domain message data.
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
  public SomeContextDomainMessageData(
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
    super(
        messageId,
        occurredOn,
        rootId,
        rootVersion,
        messageType,
        messageName,
        messageVersion,
        messageBody,
        userId,
        ipAddress);
  }
}
