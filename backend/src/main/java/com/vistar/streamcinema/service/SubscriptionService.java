package com.vistar.streamcinema.service;

import com.vistar.streamcinema.entity.Subscriptions;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.repositories.SubscriptionRepository;
import com.vistar.streamcinema.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public List<Subscriptions> getAllSubscription() {
        return subscriptionRepository.findAll();
    }

    @Transactional
    public Subscriptions saveSubscription(Subscriptions subscriptions) {
        subscriptionRepository.save(subscriptions);

        return subscriptions;
    }

    public Subscriptions getSubscription(long id) {
        Subscriptions subscriptions = null;
        Optional<Subscriptions> optional = subscriptionRepository.findById(id);
        if (optional.isPresent()) {
            subscriptions = optional.get();
        }
        return subscriptions;
    }

    @Transactional
    public Subscriptions updateSubscription(long id, Subscriptions subscriptions) {
        Subscriptions subscription = subscriptionRepository.findById(id).orElseThrow(() -> new NotFoundException("Невозможно обновить данные. Подписка с id = " + id + " не найдена в базе данных."));
        subscription.setUserID(subscriptions.getUserID());
        subscription.setSubscribedAt(subscriptions.getSubscribedAt());
        subscription.setSubscribedUntil(subscriptions.getSubscribedUntil());
        return subscriptionRepository.save(subscription);
    }

    @Transactional
    public void deleteSubscription(long id) {
        subscriptionRepository.deleteById(id);
    }

    private final UsersRepository usersRepository;

    public Users toUsers(long userID) {
        return usersRepository.findById(userID).orElseThrow();
    }

}
