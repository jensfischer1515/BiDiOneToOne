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

    @Test
    @Transactional
    public void should_persist() {
        Product product = Product.builder()
                .sku("123456")
                .availability(Availability.builder().state("ON_STOCK").build())
                .build();

        product = productRepository.save(product);

        then(product.getId()).isEqualTo(product.getAvailability().getId());
    }
}
