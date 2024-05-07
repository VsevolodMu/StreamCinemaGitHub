package com.vistar.streamcinema.mapper;

import com.vistar.streamcinema.dto.in.SubscriptionsInDto;
import com.vistar.streamcinema.dto.out.SubscriptionsOutDto;
import com.vistar.streamcinema.entity.Subscriptions;
import com.vistar.streamcinema.entity.Users;
import com.vistar.streamcinema.service.SubscriptionService;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = {SubscriptionService.class})
public abstract class SubscriptionsMapper {
    public abstract SubscriptionsOutDto toDTO(Subscriptions subscriptions);

    public abstract Subscriptions toEntity(SubscriptionsInDto subscriptionsOutDto);

    protected long toUserID(Users users) {

        return users.getId();
    }
}
