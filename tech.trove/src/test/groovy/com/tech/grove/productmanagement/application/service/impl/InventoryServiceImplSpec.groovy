package com.tech.grove.productmanagement.application.service.impl

import com.tech.grove.productmanagement.utils.TestUtilConstants
import com.tech.grove.productmanagement.utils.TestUtils
import com.tech.trove.productmanagement.application.service.impl.InventoryServiceImpl
import com.tech.trove.productmanagement.infrastructure.database.mongodb.repository.InventoryRepository
import spock.lang.Specification
import spock.lang.Subject

class InventoryServiceImplSpec extends Specification {

    def inventoryRepository = Mock(InventoryRepository)

    @Subject
    def subject = new InventoryServiceImpl(inventoryRepository)

    def "test saveInventory"() {
        given:
        def requestDto = TestUtils.buildInventoryRequestDto()
        def inventoryModel = TestUtils.buildInventory()
        when:
        def result = subject.saveInventory(requestDto)

        then:
        result != null
        result.productId == TestUtilConstants.DEFAULT_PRODUCT_ID
        1 * inventoryRepository.save(_) >> inventoryModel
        0 * _
    }

    def "test findInventoryById"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_PRODUCT_ID
        def inventoryModel = TestUtils.buildInventory()
        when:
        def result = subject.findInventoryById(requestDto)

        then:
        result != null
        result.productId == TestUtilConstants.DEFAULT_PRODUCT_ID
        1 * inventoryRepository.findByProductId(_) >> Optional.of(inventoryModel)
        0 * _
    }

    def "test updateInventoryByProductId"() {
        given:
        def requestDto = TestUtils.buildInventoryUpdateRequestDto()
        def pathParam = TestUtilConstants.DEFAULT_PRODUCT_ID

        def inventoryModel = TestUtils.buildInventory()
        when:
        def result = subject.updateInventoryByProductId(requestDto, pathParam)

        then:
        result != null
        result.productId == TestUtilConstants.DEFAULT_PRODUCT_ID
        1 * inventoryRepository.findByProductId(_) >> Optional.of(inventoryModel)
        1 * inventoryRepository.save(_) >> inventoryModel
        0 * _
    }

    def "test removeInventoryById"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_PRODUCT_ID
        def inventoryModel = TestUtils.buildInventory()

        when:
        subject.removeInventoryById(requestDto)

        then:
        1 * inventoryRepository.findByProductId(_) >> Optional.of(inventoryModel)
        1 * inventoryRepository.deleteByProductId(_)
        0 * _
    }

}
