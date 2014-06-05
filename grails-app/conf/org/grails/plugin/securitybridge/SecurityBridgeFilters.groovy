package org.grails.plugin.securitybridge

class SecurityFilters {

	def grailsApplication
	def sharedSecurityService

	def filters = {

		/**
		* Filters all admin url mappings and verifies restrictions in controller defined by the @SpudSecure annotation
		*/
		spudAdmin(controller: '*', action: '*') {
			before = {
				def controllerClass = grailsApplication.getArtefactByLogicalPropertyName("Controller", controllerName)

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
					// TODO: Add flash error localized
					redirect(sharedSecurityService.createLink('login'))
					return false
				}
				return true
			}
		}
	}
}
