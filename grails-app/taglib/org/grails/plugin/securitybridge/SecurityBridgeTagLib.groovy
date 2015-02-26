package org.grails.plugin.securitybridge

class SecurityBridgeTagLib {
	static namespace = 'security'

	def sharedSecurityService

	/**
	 * Executes the body of the tag is the user is logged in.
	 */
	def ifLoggedIn = {attrs, body ->
		if(sharedSecurityService.isLoggedIn()) {
			out << body()
		}
	}

	/**
	 * Executes the body of the tag is the user is not logged in.
	 */
	def ifNotLoggedIn = {attrs, body ->
		if(!sharedSecurityService.isLoggedIn()) {
			out << body()
		}
	}

	/**
	 * Outputs the value of the property on the current user.  No checking is performed
	 * to determine if the property given is valid or not.
	 * @attr property REQUIRED the name of the property to retrieve on the current user.
	 * @throws NullPointerException if the 'property' property is not provided.
	 */
	def currentUserProperty = {attrs ->
		def prop = attrs.remove('property')
		if(!prop) {
			throw new NullPointerException("'property' attribute missing")
		}

		out << sharedSecurityService.currentUser."${prop}"
	}

	/**
	 * Executes the body of the tag is the user is authorized to access a
	 * controller or action (optional)
	 * @attr controller OPTIONAL the name of the controller to check.  If not given
	 * then the current controller is used.
	 * @attr action OPTIONAL the name of the action to check.  If not given the
	 * current action is used.
	 */
	def ifAuthorized = {attrs, body ->
		if(checkAuthorized(attrs)) {
			out << body()
		}
	}

	/**
	 * Executes the body of the tag is the user IS NOT authorized to access a
	 * controller or action (optional)
	 * @attr controller OPTIONAL the name of the controller to check.  If not given
	 * then the current controller is used.
	 * @attr action OPTIONAL the name of the action to check.  If not given the
	 * current action is used.
	 */
	def ifNotAuthorized = {attrs, body ->
		if(!checkAuthorized(attrs)) {
			out << body()
		}
	}

	/**
	 * Creates a anchor for a link of the given name.  Other than 'action' all other attributes
	 * passed are added to the anchor tag.
	 * @attr action REQUIRED the action to use for creating the link.
	 * @throws NullPointerException if the action attribute is missing.
	 */
	def createLink = {attrs, body ->
		def action = attrs.remove('action')
		if(!action) {
			throw new NullPointerException("'action' attribute missing")
		}
		out << "<a href='" << g.createLink(sharedSecurityService.createLink(action)) << "'" <<
			(attrs.collect {k,v -> "${k}='${v}'"}.join(' ')) << '>'
		out << body()
		out << '</a>'
	}

	private checkAuthorized(attrs) {
		def controller = attrs.remove('controller') ?: controllerName
		def action = attrs.remove('action') ?: actionName

		sharedSecurityService.isAuthorized(grailsApplication.getArtefactByLogicalPropertyName('Controller', controller), action)
	}
}