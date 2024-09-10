package ru.hxastur.todolist.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.hxastur.todolist.models.Author;

import java.util.Collection;
import java.util.List;

public class AuthorDetails implements UserDetails {
    private final Author author;

    public AuthorDetails(Author author){
        this.author = author;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.author.getPassword();
    }

    @Override
    public String getUsername() {
        return this.author.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public Author getAuthor(){
        return this.author;
    }
}
