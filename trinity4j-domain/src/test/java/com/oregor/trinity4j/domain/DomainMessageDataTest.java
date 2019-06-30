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

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.Test;

/** @author Christos Tsakostas */
public class DomainMessageDataTest {

  @Test
  public void shouldCreateSomeDomainMessageData() {
    UUID id = UuidGenerator.timeBasedUuid();
    UUID rootId = UuidGenerator.timeBasedUuid();
    Integer rootVersion = 1;
    String messageName = "someMessageName";
    Integer messageVersion = 1;
    String messageBody = "someMessage";
    UUID userId = UuidGenerator.timeBasedUuid();
    String ipAddress = "127.0.0.1";
    LocalDateTime occurredOn = LocalDateTime.now();

    SomeDomainMessageData someDomainMessageData =
        new SomeDomainMessageData(
            id,
            occurredOn,
            rootId,
            rootVersion,
            DomainMessageType.EVENT,
            messageName,
            messageVersion,
            messageBody,
            userId,
            ipAddress);

    assertThat(someDomainMessageData).isNotNull();

    assertThat(someDomainMessageData.getMessageId()).isEqualTo(id);
    assertThat(someDomainMessageData.getOccurredOn()).isEqualTo(occurredOn);
    assertThat(someDomainMessageData.getRootId()).isEqualTo(rootId);
    assertThat(someDomainMessageData.getRootVersion()).isEqualTo(rootVersion);
    assertThat(someDomainMessageData.getMessageType()).isEqualTo(DomainMessageType.EVENT.name());
    assertThat(someDomainMessageData.getMessageName()).isEqualTo(messageName);
    assertThat(someDomainMessageData.getMessageVersion()).isEqualTo(messageVersion);
    assertThat(someDomainMessageData.getMessageBody()).isEqualTo(messageBody);
    assertThat(someDomainMessageData.getUserId()).isEqualTo(userId);
    assertThat(someDomainMessageData.getIpAddress()).isEqualTo(ipAddress);
  }
}
