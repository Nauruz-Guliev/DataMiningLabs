package ru.kpfu.itis.gnt.hwpebble.repository;

import lombok.NonNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.gnt.hwpebble.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Transactional(readOnly = true)
    @NonNull
    Page<PostEntity> findAll(@NonNull Pageable pageable) throws DataAccessException;
}
