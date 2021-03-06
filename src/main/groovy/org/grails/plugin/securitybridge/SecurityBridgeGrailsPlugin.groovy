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

import grails.plugins.*

class SecurityBridgeGrailsPlugin extends Plugin {
    // the plugin version
    // the version or versions of Grails the plugin is designed for
    def grailsVersion   = "4.0.0 > *"

    def title = "Security Bridge Plugin" // Headline display name of the plugin
    def author = "David Estes"
    def authorEmail = "destes@bcap.com"
    def description = 'Defines a standard cross-plugin security bridge implementation for better decoupling of authentication in plugin heavy applications.'

    def developers = [
        [name: 'Jeremy Michael Crosbie', email: 'jcrosbie@bcap.com'],
        [name: 'William Chu', email: 'wchu@bcap.com']
    ]
    def documentation   = "http://bertramdev.github.io/grails-security-bridge"
    def license = "APACHE"
    def organization = [name: "Bertram Labs", url: "http://www.bertramlabs.com/"]
    def issueManagement = [system: "GITHUB", url: "https://github.com/bertramdev/grails-security-bridge/issues"]
    def scm = [url: "https://github.com/bertramdev/grails-security-bridge"]
}
