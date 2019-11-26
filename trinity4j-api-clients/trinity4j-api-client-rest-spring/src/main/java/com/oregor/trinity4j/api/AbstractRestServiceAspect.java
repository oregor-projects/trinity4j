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

package com.oregor.trinity4j.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Abstract service aspect.
 *
 * @author Christos Tsakostas
 */
public abstract class AbstractRestServiceAspect {

  // ===============================================================================================
  // STATIC
  // ===============================================================================================

  private static final Logger LOG = LoggerFactory.getLogger(AbstractRestServiceAspect.class);

  // ===============================================================================================
  // CONSTRUCTOR(S)
  // ===============================================================================================

  /** Instantiates a new Abstract rest service aspect. */
  protected AbstractRestServiceAspect() {
    super();
  }

  // ===============================================================================================
  // FUNCTIONALITY
  // ===============================================================================================

  /**
   * Around object.
   *
   * @param proceedingJoinPoint the proceeding join point
   * @return the object
   * @throws Throwable the throwable
   */
  protected Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    Signature signature = proceedingJoinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Class<?> returnType = methodSignature.getReturnType();

    try {
      return proceedingJoinPoint.proceed();
    } catch (ApiException e) {
      return returnType.getDeclaredConstructor(ApiError.class).newInstance(e.getApiError());
    } catch (Exception e) {
      // Wen should NEVER reach here
      LOG.error(
          String.format(
              "Unexpected Exception, which should have NEVER been occurred=%s", e.getMessage()),
          e);
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
