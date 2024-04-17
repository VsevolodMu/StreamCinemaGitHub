package com.vistar.streamcinema.repositories;

import com.vistar.streamcinema.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscriptions, Long> {
}
