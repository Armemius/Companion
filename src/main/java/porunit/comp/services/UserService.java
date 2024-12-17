package porunit.comp.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.Role;
import porunit.comp.data.domain.User;
import porunit.comp.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    //private final UserRepository userRepository;

    private static List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // User user = userRepository.findFirstByUsername(username);
       // if (user == null) {
       //     throw new UsernameNotFoundException("User not found");
        //}
        //List<Role> roles = new ArrayList<>();
        //roles.add(user.getUserRole());
        return null;
        //return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(roles));
    }
}