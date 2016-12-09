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

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BiDiOneToOneApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void should_persist() {
        Product product = new Product();
        product.setSku("123456");

        productRepository.save(product);

        Availability availability = product.getAvailability();

        then(product.getId()).isNotNull();
        then(product.getId()).isEqualTo(availability.getId());
        then(availability.getOwner()).isNotNull();
        then(availability.getOwner()).isSameAs(product);
    }

    @Test
    @Transactional
    public void should_keep_product_optlock_when_changing_availability() {
        Product product = new Product();
        product.setSku("123456");

        Long previousOptLock = productRepository.save(product).getOptLock();

        product.getAvailability().setState("OUT_OF_STOCK");
        productRepository.save(product);

        then(previousOptLock).isEqualTo(product.getOptLock());
    }
}
