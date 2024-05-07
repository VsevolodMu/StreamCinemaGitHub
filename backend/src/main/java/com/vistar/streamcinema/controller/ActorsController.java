package com.vistar.streamcinema.controller;

import com.vistar.streamcinema.dto.in.ActorsInDto;
import com.vistar.streamcinema.dto.out.ActorsOutDto;
import com.vistar.streamcinema.entity.Actors;
import com.vistar.streamcinema.exception_handler.NotFoundException;
import com.vistar.streamcinema.mapper.ActorsMapper;
import com.vistar.streamcinema.service.ActorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ActorsController {

    private final ActorsService actorsService;

    private final ActorsMapper actorsMapper;

    @GetMapping("/actors")
    public List<ActorsOutDto> showAllActors() {
        List<Actors> actorsList = actorsService.getAllActors();

        return actorsList.stream().map(actorsMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/actors/{id}")
    public ActorsOutDto showActor(@PathVariable int id) {
        Actors actors = actorsService.getActor(id);
        if (actors == null) {
            throw new NotFoundException("Неверно уканазанный идентификатор. Актёр с id = " + id + " не найден в базе данных.");
        }

        return actorsMapper.toDTO(actors);
    }

    @PostMapping("/actors")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ActorsOutDto addActor(@RequestBody ActorsInDto actorsInDto) {
        Actors actors = actorsMapper.toEntity(actorsInDto);
        actors = actorsService.saveActors(actors);

        return actorsMapper.toDTO(actors);
    }

    @PutMapping("/actors/{id}")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ActorsOutDto updateActor(@PathVariable int id, @RequestBody ActorsInDto actorsInDto) {
        Actors actors = actorsMapper.toEntity(actorsInDto);
        actors = actorsService.updateActor(id, actors);

        return actorsMapper.toDTO(actors);
    }

    @DeleteMapping("/actors/{id}")
    public String deleteActor(@PathVariable int id) {
        actorsService.deleteActor(id);
        return "\"information\": Актёр с id = " + id + " больше не существует в базе данных.";
    }

}
