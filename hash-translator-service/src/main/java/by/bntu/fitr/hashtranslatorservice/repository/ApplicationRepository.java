package by.bntu.fitr.hashtranslatorservice.repository;

import by.bntu.fitr.hashtranslatorservice.entity.Application;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationRepository extends MongoRepository<Application, String> {

}
