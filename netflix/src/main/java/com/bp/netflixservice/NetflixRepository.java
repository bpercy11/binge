package com.bp.netflixservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NetflixRepository extends JpaRepository<Title, Long> {
}
