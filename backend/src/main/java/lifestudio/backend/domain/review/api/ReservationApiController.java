package lifestudio.backend.domain.review.api;


import lifestudio.backend.domain.review.domain.Reservation;
import lifestudio.backend.domain.review.dto.ReservationDto;
import lifestudio.backend.domain.review.service.ReservationService;
import lifestudio.backend.domain.studio.domain.Studio;
import lifestudio.backend.domain.studio.service.StudioService;
import lifestudio.backend.domain.user.domain.User;
import lifestudio.backend.domain.user.service.UserService;
import lifestudio.backend.global.common.response.Response;
import lifestudio.backend.global.common.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservationApiController {

    private final ReservationService reservationService;

    private final StudioService studioService;

    private final UserService userService;

    private final ResponseService responseService;

    @PostMapping("/api/reservations")
    public Response createReservation(@RequestBody @Valid ReservationDto.createReq dto){

        Studio studio = studioService.findById(dto.getStudioId());
        User user = userService.findById(dto.getUserId());
        Reservation reservation = Reservation.builder()
                .studio(studio)
                .user(user)
                .reservationTime(dto.getReservationTime())
                .color(dto.getColor())
                .background(dto.getBackground())
                .people(dto.getPeople())
                .requirement(dto.getRequirement())
                .approve(dto.getApprove())
                .build();

        Long id = reservationService.createReservation(reservation);
        return responseService.getSingleResponse(new ReservationDto.Res(reservationService.findById(id)));

    }

    @GetMapping("/api/reservations/{id}")
    public Response getReservation(@PathVariable final long id) {
        return responseService.getSingleResponse(new ReservationDto.Res(reservationService.findById(id)));
    }

    @GetMapping("/api/reservations")
    public Response getReservations(@RequestParam(required = false) Long studioId, @RequestParam(required = false) Long userId) {

        List<Reservation> findReservations;
        if (studioId == null & userId == null) {
            findReservations = reservationService.findAll();
        } else if (studioId == null){
            findReservations = reservationService.findByUserId(userId);
        } else {
            findReservations = reservationService.findByStudioId(studioId);
        }


        List<ReservationDto.Res> collect = findReservations.stream()
                .map(r -> new ReservationDto.Res(r))
                .collect(Collectors.toList());
        return responseService.getListResponse(collect);
    }

    @PutMapping("api/reservations/{id}")
    public Response updateReservation(@PathVariable final long id) {
        reservationService.updateById(id);
        return responseService.getSingleResponse(new ReservationDto.Res(reservationService.findById(id)));
    }


    @DeleteMapping("/api/reservations/{id}")
    public Response deleteReservation(@PathVariable final long id) {
        Reservation reservation = reservationService.findById(id);
        reservationService.deleteById(id);
        return responseService.getSuccessResponse();
    }
}
