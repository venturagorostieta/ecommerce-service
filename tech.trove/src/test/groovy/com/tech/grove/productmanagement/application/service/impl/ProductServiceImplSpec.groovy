package com.tech.grove.productmanagement.application.service.impl

import com.tech.grove.productmanagement.utils.TestUtilConstants
import com.tech.grove.productmanagement.utils.TestUtils
import com.tech.trove.productmanagement.application.service.impl.ProductServiceImpl
import com.tech.trove.productmanagement.infrastructure.database.mongodb.repository.ProductRepository
import spock.lang.Specification
import spock.lang.Subject

class ProductServiceImplSpec extends Specification {

    def productRepository = Mock(ProductRepository)

    @Subject
    def subject = new ProductServiceImpl(productRepository)

    def "test saveProduct"() {
        given:
        def requestDto = TestUtils.buildProductRequestDto()
        def productModel = TestUtils.buildProduct()
        when:
        def result = subject.saveProduct(requestDto)

        then:
        result != null
        result.productId == TestUtilConstants.DEFAULT_PRODUCT_ID
        1 * productRepository.save(_) >> productModel
        0 * _
    }

    def "test findProductById"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_PRODUCT_ID
        def productModel = TestUtils.buildProduct()
        when:
        def result = subject.findProductById(requestDto)

        then:
        result != null
        result.productId == TestUtilConstants.DEFAULT_PRODUCT_ID
        1 * productRepository.findByProductId(_) >> Optional.of(productModel)
        0 * _
    }

    def "test updateProduct"() {
        given:
        def requestDto = TestUtils.buildProductUpdateRequestDto()
        def pathParam = TestUtilConstants.DEFAULT_PRODUCT_ID

        def productModel = TestUtils.buildProduct()
        when:
        def result = subject.updateProduct(requestDto, pathParam)

        then:
        result != null
        result.productId == TestUtilConstants.DEFAULT_PRODUCT_ID
        1 * productRepository.findByProductId(_) >> Optional.of(productModel)
        1 * productRepository.save(_) >> productModel
        0 * _
    }

    def "test removeProductByProductId"() {
        given:
        def requestDto = TestUtilConstants.DEFAULT_PRODUCT_ID
        def productModel = TestUtils.buildProduct()
        when:
        subject.removeProductByProductId(requestDto)

        then:

        1 * productRepository.findByProductId(_) >> Optional.of(productModel)
        1 * productRepository.deleteByProductId(_)
        0 * _
    }
}
