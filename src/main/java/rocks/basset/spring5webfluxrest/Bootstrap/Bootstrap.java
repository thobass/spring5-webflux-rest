package rocks.basset.spring5webfluxrest.Bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import rocks.basset.spring5webfluxrest.domain.Category;
import rocks.basset.spring5webfluxrest.domain.Vendor;
import rocks.basset.spring5webfluxrest.reprositories.CategoryRepository;
import rocks.basset.spring5webfluxrest.reprositories.VendorRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap  implements CommandLineRunner {
    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count().block() == 0) {
            //load data
            log.info("#### LOADING DATA ON BOOTSTRAP #####");

            categoryRepository.save(Category.builder()
                    .description("Fruits").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Nuts").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Breads").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Meats").build()).block();

            categoryRepository.save(Category.builder()
                    .description("Eggs").build()).block();

            log.info("Loaded Categories: " + categoryRepository.count().block());

            vendorRepository.save(Vendor.builder()
                    .firstName("Joe")
                    .lastName("Buck").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Micheal")
                    .lastName("Weston").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Jessie")
                    .lastName("Waters").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Bill")
                    .lastName("Nershi").build()).block();

            vendorRepository.save(Vendor.builder()
                    .firstName("Jimmy")
                    .lastName("Buffett").build()).block();

            log.info("Loaded Vendors: " + vendorRepository.count().block());
        }
    }
}
