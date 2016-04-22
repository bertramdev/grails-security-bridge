package org.grails.plugin.securitybridge

import grails.core.GrailsApplication
import org.grails.web.util.GrailsApplicationAttributes

class SecurityBridgeInterceptor {

	GrailsApplication grailsApplication
	SharedSecurityService sharedSecurityService
	int order = HIGHEST_PRECEDENCE

	SecurityBridgeInterceptor() {
		matchAll()
	}

	boolean before() { 
		def controllerClass = request.getAttribute(GrailsApplicationAttributes.GRAILS_CONTROLLER_CLASS)

		if(!controller) {
				return true
		}
		def action
		if(controllerClass) {
			action = controllerClass.clazz.declaredMethods.find { it.name == actionName }
			if(!action) {
				action = applicationContext.getBean(controllerClass.fullName).class.declaredFields.find { field -> field.name == actionName }
			}
		}
		def annotation = action?.getAnnotation(Secure)

		if(!annotation && controllerClass) {
			annotation = controllerClass.clazz.getAnnotation(Secure)
		}

		if(!annotation) {
			return true  //No Security Restrictions
		}

		if(!sharedSecurityService.hasAnyRole(annotation.value())) {
			sharedSecurityService.storeLocation(request)
		
			redirect(sharedSecurityService.createLink('login'))
			return false
		}

		return true
	}

	boolean after() { true }

	void afterView() {
	// no-op
	}

}
