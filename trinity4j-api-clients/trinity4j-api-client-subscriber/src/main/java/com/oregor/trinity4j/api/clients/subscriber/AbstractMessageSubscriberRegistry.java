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

import static java.util.Collections.emptyMap;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toMap;

import com.oregor.trinity4j.commons.assertion.Assertion;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * The type Abstract message subscriber registry.
 *
 * @param <S> the type parameter
 * @author Christos Tsakostas
 */
public abstract class AbstractMessageSubscriberRegistry<S extends MessageSubscriber>
    implements MessageSubscriberRegistry<S> {

  // ===============================================================================================
  // STATE / DEPENDENCIES
  // ===============================================================================================

  private final Map<String, Set<S>> messageTypeToSubscribers;

  private final Map<String, S> classToSubscriber;

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /**
   * Instantiates a new Abstract message subscriber registry.
   *
   * @param subscribers the subscribers
   */
  public AbstractMessageSubscriberRegistry(Optional<List<S>> subscribers) {
    this.messageTypeToSubscribers = new LinkedHashMap<>();

    if (subscribers.isPresent()) {
      subscribers.get().forEach(this::addMessageTypeToSubscribers);

      this.classToSubscriber =
          subscribers.get().stream().collect(toMap(s -> s.getClass().getSimpleName(), s -> s));
    } else {
      this.classToSubscriber = emptyMap();
    }
  }

  // ===============================================================================================
  // OVERRIDES
  // ===============================================================================================

  @Override
  public Set<S> getSubscribersFor(String messageType) {
    return messageTypeToSubscribers.getOrDefault(messageType, emptySet());
  }

  @Override
  public S get(String subscriberClass) {
    S subscriber = classToSubscriber.get(subscriberClass);
    if (subscriber == null) {
      throw new IllegalArgumentException(
          String.format("Could not lookup subscriber for class=%s", subscriberClass));
    }
    return subscriber;
  }

  // ===============================================================================================
  // PRIVATE
  // ===============================================================================================

  private void addMessageTypeToSubscribers(S subscriber) {
    Assertion.isNotNull(
        subscriber.getSupportedMessageTypes(), "subscriber.getSupportedMessageTypes() is required");

    subscriber
        .getSupportedMessageTypes()
        .forEach(
            messageType -> {
              Set<S> subscribers = new LinkedHashSet<>();
              if (messageTypeToSubscribers.containsKey(messageType)) {
                subscribers = messageTypeToSubscribers.get(messageType);
              }
              subscribers.add(subscriber);
              messageTypeToSubscribers.put(messageType, subscribers);
            });
  }
}
