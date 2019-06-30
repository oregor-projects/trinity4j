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

package com.oregor.trinity4j.api.clients.domainmessage;

/**
 * The type Abstract incoming domain message.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractIncomingDomainMessage implements IncomingDomainMessage {

  // ===============================================================================================
  // STATE
  // ===============================================================================================
  private String messageId;
  private String rootId;
  private String messageBody;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract incoming domain message. */
  public AbstractIncomingDomainMessage() {
    super();
  }

  // ===============================================================================================
  // GETTERS
  // ===============================================================================================

  @Override
  public String getMessageId() {
    return messageId;
  }

  @Override
  public String getRootId() {
    return rootId;
  }

  @Override
  public String getMessageBody() {
    return messageBody;
  }

  // ===============================================================================================
  // SETTERS
  // ===============================================================================================

  /**
   * Sets message id.
   *
   * @param messageId the message id
   */
  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  /**
   * Sets root id.
   *
   * @param rootId the root id
   */
  public void setRootId(String rootId) {
    this.rootId = rootId;
  }

  /**
   * Sets message body.
   *
   * @param messageBody the message body
   */
  public void setMessageBody(String messageBody) {
    this.messageBody = messageBody;
  }
}
