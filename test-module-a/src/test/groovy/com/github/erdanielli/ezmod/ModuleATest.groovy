package com.github.erdanielli.ezmod

import spock.lang.Specification

class ModuleATest extends Specification {

    def "should inject exported bean"() {
        when:
        def col = Injector.getInstance(type, allowNull)

        then:
        col.isEmpty()
        col.getClass() == impl

        where:
        type | impl       | allowNull
        List | LinkedList | true
        Map  | HashMap    | false
    }

    def "should fail if no implementation is found and is required"() {
        when:
        Injector.getInstance(Set)

        then:
        thrown(NoSuchImplementationException)
    }

    def "should return null if no implementation is found and is optional"() {
        expect:
        Injector.getInstance(Set, true) == null
    }

    def "should apply default module name to the containing package"() {
        given:
        def modules = new InjectorBean().modules

        expect:
        modules.find { it.class == ModuleA }.name() == 'com.github.erdanielli.ezmod'
        modules.find { it.class == ModuleB }.name() == 'ModuleB'
    }

}
