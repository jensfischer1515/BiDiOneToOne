package com.epages.experiment;

import com.epages.experiment.availability.Availability;
import com.epages.experiment.product.Product;
import com.epages.experiment.product.ProductRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BiDiOneToOneApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private UUID productId;

    private Availability availability;
    private UUID availabilityId;

    @Test
    @Transactional
    public void should_persist() {
        product = new Product();
        product.setSku("123456");

        productId = productRepository.saveAndFlush(product).getId();
        product = productRepository.findAll().iterator().next();

        availability = product.getAvailability();
        availabilityId = availability.getId();

        availability.setState("OUT_OF_STOCK");
        productRepository.saveAndFlush(product);

        then(product.getId()).isNotNull();
        then(availability.getOwner()).isNotNull();
        then(productId).isEqualTo(availabilityId);
        then(availability.getOwner()).isSameAs(product);
    }
}
