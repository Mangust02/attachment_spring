package uz.demo.attachment_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.demo.attachment_spring.entity.PhotoInfo;


@Repository
public interface PhotoInfoRepository extends JpaRepository<PhotoInfo, Long> {
}
