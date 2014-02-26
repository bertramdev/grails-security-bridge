/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.plugin.securitybridge

abstract class AbstractSecurityBridge implements SecurityBridge {

  /**
   * {@inheritDoc}
   * @return null
   */
  def getCurrentUser() {
    null
  }

  /**
   * {@inheritDoc}
   * @return null
   */
  def getUserIdentity() {
    null
  }

  /**
   * {@inheritDoc}
   * @return null
   */
  def getCurrentAccount() {
    null
  }

  /**
   * {@inheritDoc}
   * @return null
   */
  def getAccountIdentity() {
    null
  }

  /**
   * {@inheritDoc}
   * @return null
   */
  def getCurrentUserDisplayName() {
    null
  }

  /**
   * {@inheritDoc}
   * @return false
   */
  boolean isLoggedIn() {
    false
  }

  /**
   * {@inheritDoc}
   * @return false
   */
  boolean isAuthorized(object, action) {
    false
  }

  /**
   * {@inheritDoc}
   * @return false
   */
  boolean hasRole(role) {
    false
  }

  /**
   * {@inheritDoc}
   * @return false
   */
  boolean hasPermission(permission, opts=null) {
    false
  }

  /**
   * {@inheritDoc}
   */
  def storeLocation(request) {
  }

  /**
   * {@inheritDoc}
   */
  def withUser(identity, Closure code) {
  }

  /**
   * {@inheritDoc}
   */
  Map createLink(String action) {
  }
}
