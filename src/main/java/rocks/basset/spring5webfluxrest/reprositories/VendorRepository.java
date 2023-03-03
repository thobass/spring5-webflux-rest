package rocks.basset.spring5webfluxrest.reprositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import rocks.basset.spring5webfluxrest.domain.Vendor;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
