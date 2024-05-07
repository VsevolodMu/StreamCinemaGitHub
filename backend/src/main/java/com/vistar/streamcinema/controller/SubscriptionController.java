package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.SubscriptionsInDto;
import com.vistar.streamcinema.dto.out.SubscriptionsOutDto;
import com.vistar.streamcinema.entity.Subscriptions;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.SubscriptionsMapper;
import com.vistar.streamcinema.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionsMapper subscriptionsMapper;

    @GetMapping("/subscriptions")
    public List<SubscriptionsOutDto> showAllSubscriptions() {
        List<Subscriptions> allSubscriptions = subscriptionService.getAllSubscription();

        return allSubscriptions.stream().map(subscriptionsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/subscriptions/{id}")
    public SubscriptionsOutDto showSubscription(@PathVariable int id) {
        Subscriptions subscriptions = subscriptionService.getSubscription(id);
        if (subscriptions == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Подписка с id = " + id + " не найдена в базе данных.");
        }

        return subscriptionsMapper.toDTO(subscriptions);
    }

    @PostMapping("/subscriptions")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SubscriptionsOutDto addSubscription(@RequestBody SubscriptionsInDto subscriptionsInDto) {
        Subscriptions subscriptions = subscriptionsMapper.toEntity(subscriptionsInDto);
        subscriptions = subscriptionService.saveSubscription(subscriptions);

        return subscriptionsMapper.toDTO(subscriptions);
    }

    @PutMapping("/subscriptions/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public SubscriptionsOutDto updateSubscription(@PathVariable int id, @RequestBody SubscriptionsInDto subscriptionsInDto) {
        Subscriptions subscriptions = subscriptionsMapper.toEntity(subscriptionsInDto);
        subscriptions = subscriptionService.updateSubscription(id, subscriptions);

        return subscriptionsMapper.toDTO(subscriptions);
    }


    @DeleteMapping("/subscriptions/{id}")
    public String deleteSubscription(@PathVariable int id) {
        subscriptionService.deleteSubscription(id);
        return "\"information\": Подписка с id = " + id + " больше не существует в базе данных.";
    }

}
