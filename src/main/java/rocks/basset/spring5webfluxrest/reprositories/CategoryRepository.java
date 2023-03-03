package rocks.basset.spring5webfluxrest.reprositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import rocks.basset.spring5webfluxrest.domain.Category;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
