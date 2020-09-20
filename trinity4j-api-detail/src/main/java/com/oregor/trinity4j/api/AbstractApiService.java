/*-
 * ==========================LICENSE_START=================================
 * TRINITY4J: A set of Domain-Driven Design Libraries for Java Applications
 * ========================================================================
 * Copyright (C) 2019 - 2020 Christos Tsakostas, OREGOR LTD
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

package com.oregor.trinity4j.api;

import com.oregor.trinity4j.commons.assertion.Assertion;
import java.math.BigInteger;
import java.util.UUID;

/**
 * The type Abstract api service.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractApiService {

  /**
   * Ensures a valid uuid.
   *
   * @param incomingUuid the incoming uuid
   * @return the uuid
   */
  protected UUID ensureUuid(String incomingUuid) {
    Assertion.isNotNull(incomingUuid, "incoming UUID is required");

    String[] components = incomingUuid.split("-");

    if (components.length == 5) {
      return UUID.fromString(incomingUuid);
    } else {
      BigInteger bi1 = new BigInteger(incomingUuid.substring(0, 16), 16);
      BigInteger bi2 = new BigInteger(incomingUuid.substring(16, 32), 16);
      UUID uuid = new UUID(bi1.longValue(), bi2.longValue());
      return uuid;
    }
  }
}
