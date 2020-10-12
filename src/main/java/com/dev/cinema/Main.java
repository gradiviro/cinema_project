package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");
    static final MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    static final CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);
    static final MovieSessionService sessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    static final UserService userService
            = (UserService) injector.getInstance(UserService.class);
    static final ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        movieService.add(movie);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("IMAX");
        cinemaHallService.add(cinemaHall);

        MovieSession session = new MovieSession();
        session.setCinemaHall(cinemaHall);
        session.setMovie(movie);
        session.setShowTime(LocalDateTime.now());
        sessionService.add(session);

        User user = new User();
        user.setEmail("test@test.com");
        user.setPassword("1234");
        userService.add(user);

        shoppingCartService.registerNewShoppingCart(user);
        shoppingCartService.addSession(session, user);
        System.out.println(shoppingCartService.getByUser(user));
    }
}

