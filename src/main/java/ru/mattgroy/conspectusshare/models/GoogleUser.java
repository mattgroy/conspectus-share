package ru.mattgroy.conspectusshare.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class GoogleUser implements OAuth2User {

    private final OAuth2User oauth2User;

    public GoogleUser(OAuth2User oauth2User) {
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

    public String getEmail() {
        return oauth2User.getAttribute("email");
    }

    public String getGivenName() {
        return oauth2User.getAttribute("given_name");
    }

    public String getFamilyName() {
        return oauth2User.getAttribute("family_name");
    }

    public String getSub() {
        return oauth2User.getAttribute("sub");
    }
}
