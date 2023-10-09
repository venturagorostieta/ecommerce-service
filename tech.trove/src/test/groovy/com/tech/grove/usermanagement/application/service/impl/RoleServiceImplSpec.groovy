package com.tech.grove.usermanagement.application.service.impl

import com.tech.grove.usermanagement.utils.TestUtilConstants
import com.tech.grove.usermanagement.utils.TestUtils
import com.tech.trove.usermanagement.application.service.impl.RoleServiceImpl
import com.tech.trove.usermanagement.infrastructure.database.mongodb.repository.RoleRepository
import spock.lang.Specification
import spock.lang.Subject

class RoleServiceImplSpec extends Specification {

    def roleRepository = Mock(RoleRepository)

    @Subject
    def subject = new RoleServiceImpl(roleRepository)

    def "test saveRole"() {
        given:
        def requestDto = TestUtils.buildRoleRequestDto()
        def productModel = TestUtils.buildRole()
        when:
        def result = subject.saveRole(requestDto)

        then:
        result != null
        result.name == TestUtilConstants.DEFAULT_ROLE_NAME
        1 * roleRepository.save(_) >> productModel
        0 * _
    }

    def "test findRoleById"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_ROLE_DOCUMENT_ID
        def roleModel = TestUtils.buildRole()
        when:
        def result = subject.findRoleById(requestDto)

        then:
        result != null
        result.name == TestUtilConstants.DEFAULT_ROLE_NAME
        1 * roleRepository.findById(_) >> Optional.of(roleModel)
        0 * _
    }

}
