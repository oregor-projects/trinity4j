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

package com.oregor.trinity4j.api.clients.subscriber;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Abstract message dispatcher.
 *
 * @param <S> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractMessageDispatcher<S extends MessageSubscriber>
    implements MessageDispatcher {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private final MessageSubscriberRegistry<S> messageSubscriberRegistry;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract message dispatcher.
   *
   * @param messageSubscriberRegistry the message subscriber registry
   */
  public AbstractMessageDispatcher(MessageSubscriberRegistry<S> messageSubscriberRegistry) {
    this.messageSubscriberRegistry = messageSubscriberRegistry;
  }

  // ===============================================================================================
  // ABSTRACT
  // ===============================================================================================

  /**
   * Extract message type string.
   *
   * @param message the message
   * @return the string
   */
  protected abstract String extractMessageType(String message);

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public void dispatch(String message) {
    Set<S> subscribers = messageSubscriberRegistry.getSubscribersFor(extractMessageType(message));
    if (!subscribers.isEmpty()) {
      if (subscribers.size() > 1) {
        throw new IllegalStateException(
            "This dispatcher only handles a single subscriber per event type. Instead use "
                + "MessageSplitDispatcher along with TargetedMessageDispatcher. "
                + "Registered subscribers are: "
                + subscribers
                    .stream()
                    .map(s -> s.getClass().getSimpleName())
                    .collect(Collectors.joining(",")));
      }
      subscribers.stream().findFirst().orElseThrow(IllegalArgumentException::new).process(message);
    }
  }
}
