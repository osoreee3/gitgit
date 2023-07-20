package com.example.ckb.user;

import com.example.ckb.article.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser,Long> {
Optional<SiteUser> findByusername(String username);
    Page<Article> findAll(Specification<Article> spec, Pageable pageable);

}
