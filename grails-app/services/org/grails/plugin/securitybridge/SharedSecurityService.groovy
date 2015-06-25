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

class SharedSecurityService implements SecurityBridge {
  static transactional = false
	def sharedSecurityBridge

	boolean hasProvider() {
		sharedSecurityBridge != null
	}

	/**
   * Execute the closure with the user and account object based on the passed id
   */
  def withUser(identity, Closure code) {
    getSecurityBridge(failOnError: true).withUser(identity, code)
  }


  /**
   * Get user id string i.e. "username" of the currently logged in user, from whatever
   * underlying security API is in force
	 */
  def getUserIdentity() {
  	securityBridge?.userIdentity
  }

  /**
   * Get acount id string i.e. "accountId" of the currently logged in user, from whatever
   * underlying security API is in force
	 */
  def getAccountIdentity() {
  	securityBridge?.accountIdentity
  }

  /**
   * Get the current user object from whatever active security provider we are using.
   */
  def getCurrentUser() {
  	securityBridge?.currentUser
  }

  /**
   * Returns the current account object of the logged in user
   * @return the implementation's account (for basic auth can just be the user object) object or null if nobody is logged in
   */
  def getCurrentAccount() {
    securityBridge?.currentAccount
  }


  /**
   * Get the display name of the currently logged in user.
   */
  def getCurrentUserDisplayName() {
  	securityBridge?.currentUserDisplayName
  }


  /**
	 * Check if the user is currently logged in.
	 */
  boolean isLoggedIn() {
  	securityBridge.isLoggedIn()
  }

  /**
	 * Check if the currently logged in user is authorized to perform an action on the passed object
	 * @param object The object with which we are dealing with.
	 * @param action The action you would like to perform
	 */
	boolean isAuthorized(object, action) {
	 	securityBridge?.isAuthorized(object, action)
	}

	/**
	 * {@inheritDoc}
	 */
  boolean hasPermission(permission, opts=null) {
    securityBridge?.hasPermission(permission, opts)
  }

  /**
   * Check if the currently logged in user has the specified role
   * @param role
   */
  boolean hasRole(role) {
    securityBridge?.hasRole(role)
  }

  boolean hasAnyRole(roles) {
    roles = roles instanceof Collection ? roles : [roles]
    roles.any { role ->
      getSecurityBridge()?.hasRole(role)
    }
  }

  /**
   * Store the request location for the security service to redirect to upon login success
   * @param request The request object
   */
  def storeLocation(request) {
    securityBridge?.storeLocation(request)
  }


  /**
   * Create a link to the specified security action
   * @param action One of "login", "logout", "signup"
   * @return Must return a Map of arguments to pass to g:link to create the link
   */
  Map createLink(String action) {
    getSecurityBridge(failOnError: true).createLink(action)
  }

  def ifAuthorized(object, action, Closure code) {
    def allowed = getSecurityBridge()?.isAuthorized(object, action)
    allowed ? code(getCurrentUser(),getCurrentAccount()) : null
  }

	SecurityBridge getSecurityBridge(options=[:]) {
		if (!sharedSecurityBridge) {
			def message = "An attempt was made to use the sharedSecurityBridge, but none has been defined. Please refer to the grails security bridge plugin docs for information on how to define a bridge."
			if(options.failOnError) {
				throw new IllegalArgumentException(message)
			} else {
				log.warn message
			}
		}
    sharedSecurityBridge
  }
}
