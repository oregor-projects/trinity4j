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
 * The interface Domain message publish dto.
 *
 * @author Christos Tsakostas
 */
public interface DomainMessagePublishDto {

  /**
   * Gets message id.
   *
   * @return the message id
   */
  UUID getMessageId();

  /**
   * Gets occurred on.
   *
   * @return the occurred on
   */
  LocalDateTime getOccurredOn();

  /**
   * Gets root id.
   *
   * @return the root id
   */
  UUID getRootId();

  /**
   * Gets message name.
   *
   * @return the message name
   */
  String getMessageName();

  /**
   * Gets message version.
   *
   * @return the message version
   */
  Integer getMessageVersion();

  /**
   * Gets message body.
   *
   * @return the message body
   */
  String getMessageBody();

  /**
   * Gets context.
   *
   * @return the context
   */
  String getContext();
}
