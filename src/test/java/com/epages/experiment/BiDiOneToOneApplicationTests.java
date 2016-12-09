package com.epages.experiment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BiDiOneToOneApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private Availability availability;
    private Long productId;

    @Test
    @Transactional
    public void should_persist() {
        product = Product.newInstance();
        product.setSku("123456");

        productId = productRepository.saveAndFlush(product).getId();
        product = productRepository.findAll().iterator().next();
        availability = product.getAvailability();

        then(product.getId()).isNotNull();
        then(availability.getOwner()).isNotNull();
        then(product.getId()).isEqualTo(availability.getProductId());
        then(availability.getOwner()).isSameAs(product);
    }
}
