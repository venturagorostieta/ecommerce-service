package com.tech.grove.productmanagement.application.service.impl

import com.tech.grove.productmanagement.utils.TestUtilConstants
import com.tech.grove.productmanagement.utils.TestUtils
import com.tech.trove.productmanagement.application.service.impl.CategoryServiceImpl
import com.tech.trove.productmanagement.infrastructure.database.mongodb.repository.CategoryRepository
import spock.lang.Specification
import spock.lang.Subject

class CategoryServiceImplSpec extends Specification {

    def categoryRepository = Mock(CategoryRepository)

    @Subject
    def subject = new CategoryServiceImpl(categoryRepository)

    def "test saveCategory"() {
        given:
        def requestDto = TestUtils.buildCategoryRequestDto()
        def categoryModel = TestUtils.buildCategory()
        when:
        def result = subject.saveCategory(requestDto)

        then:
        result != null
        result.categoryId == TestUtilConstants.DEFAULT_CATEGORY_ID
        1 * categoryRepository.save(_) >> categoryModel
        0 * _
    }

    def "test findCategoryById"() {
        given:
        def requestParam = TestUtilConstants.DEFAULT_CATEGORY_ID
        def categoryModel = TestUtils.buildCategory()
        when:
        def result = subject.findCategoryById(requestParam)

        then:
        result != null
        result.categoryId == TestUtilConstants.DEFAULT_CATEGORY_ID
        1 * categoryRepository.findById(_) >> Optional.of(categoryModel)
        0 * _
    }
    
}
