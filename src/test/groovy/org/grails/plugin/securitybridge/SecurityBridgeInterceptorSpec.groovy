package org.grails.plugin.securitybridge


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SecurityBridgeInterceptor)
class SecurityBridgeInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test securityBridge interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"securityBridge")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
