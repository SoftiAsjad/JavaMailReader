package com.javamail.example.repository;

import com.javamail.example.entity.EMail;
import com.javamail.example.utils.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import static com.javamail.example.utils.Constants.CORS_ORIGINS;

@RepositoryRestResource
@CrossOrigin(origins = CORS_ORIGINS)
public interface MailRepository extends JpaRepository<EMail, Long> {
}
