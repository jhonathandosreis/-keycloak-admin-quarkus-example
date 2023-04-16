package com.tecnologia.controller;

import com.tecnologia.dto.UserDto;
import com.tecnologia.model.User;
import com.tecnologia.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
@RolesAllowed("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    private UserRepository repository;

    @Inject
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @POST
    @Transactional
    public Response createUser( UserDto userDto ) {
        User user = new User();
        user.setNome(userDto.getNome());
        user.setSobreNome(userDto.getSobreNome());
        user.setIdade(userDto.getIdade());
        repository.persist(user);

        return Response.status(Response.Status.CREATED.getStatusCode()).entity(user).build();
    }

    @GET
    public Response listAllUser() {
        PanacheQuery<User> query = repository.findAll();
        List<UserDto> userDtos = query.stream()
                .map(users -> new UserDto(users.getNome(), users.getSobreNome(), users.getIdade()))
                .collect(Collectors.toList());
        return Response.ok(userDtos).build();
    }



    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteUser( @PathParam("id") Long id ) {
        User user = repository.findById(id);
        if(user != null) {
            repository.delete(user);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateUser( @PathParam("id") Long id, UserDto userDto ) {
        User user = repository.findById(id);
        if(user != null) {
            user.setNome(userDto.getNome());
            user.setSobreNome(userDto.getSobreNome());
            user.setIdade(userDto.getIdade());
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
