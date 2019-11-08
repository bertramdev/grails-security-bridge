package org.grails.plugin.securitybridge

import grails.testing.web.interceptor.InterceptorUnitTest
import spock.lang.Specification

class SecurityBridgeInterceptorSpec extends Specification implements InterceptorUnitTest<SecurityBridgeInterceptor> {
    void "Test securityBridge interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"securityBridge")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
