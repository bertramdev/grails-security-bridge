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

interface SecurityBridge {

	/**
	 * Returns the current user object if they are logged in
	 * @return the implementation's user object or null if nobody is logged in
	 */
	def getCurrentUser()

	/**
	 * Get the user Identifier.
	 * @return the user identity or null if nobody is logged in
	 */
	def getUserIdentity()

	/**
	 * Returns the current account object of the logged in user
	 * @return the implementation's account (for basic auth can just be the user object) object or null if nobody is logged in
	 */
	def getCurrentAccount()

	/**
	 * Returns the current users account identity. (Useful if multiple users are tied to one account)
	 * @return the account name or identity, null if nobody is logged in.
	 */
	def getAccountIdentity()

	/**
	 * Return the current users display name.
	 */
	def getCurrentUserDisplayName()

	/**
	 * Check if the user is currently logged in.
	 */
	boolean isLoggedIn()

	/**
	 * Check if the currently logged in user is authorized to perform an action on the passed object
	 * @param object The object with which we are dealing with.
	 * @param action The action you would like to perform
	 */
	boolean isAuthorized(object, action)

	/**
   * Check if the currently logged in user has the specified role
   * @param role
   */
	boolean hasRole(role)

	/**
	 * Check if the currently logged in user has the specified permission
	 * in any of his/her assigned roles.
	 * @param permission the name of the permission to check
	 * @param opts depending on then permissions model, this can be used to add
	 * configurability to permission.
	 */
	boolean hasPermission(permission, opts)

	/**
	 * Check if the currently logged in user has the specified permission
	 * in any of his/her assigned roles.
	 * @param permission the name of the permission to check
	 */
	boolean hasPermission(permission)


	/**
	 * Store the request location for the security service to redirect to upon login success
	 * @param request The request object
	 */
	def storeLocation(request)

	/**
	 * Execute code masquerading as the specified user, for the duration of the Closure block
	 * @return Whatever the closure returns
	 */
	def withUser(identity, Closure code)

	/**
   * Create a link to the specified security action
   * @param action One of "login", "logout", "signup"
   * @return Must return a Map of arguments to pass to g:link to create the link
   */
  Map createLink(String action)
}
