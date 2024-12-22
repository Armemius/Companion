package porunit.comp.services;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.User;
import porunit.comp.data.domain.UserData;
import porunit.comp.data.dto.UserDataDTO;
import porunit.comp.repositories.UserDataRepository;
import porunit.comp.repositories.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;


    public void saveUserData(final UserDataDTO userDataDTO) {

        Optional<User> optionalUser = userRepository.findByLogin(userDataDTO.getUserLogin());
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get();
        Optional<UserData> optionalUserData = userDataRepository.findByUser(user);
        if (optionalUserData.isPresent()) {
            throw new UsernameNotFoundException("User data already exists");
        }

        UserData userData = UserData.builder()
                .user(user)
                .birthdate(LocalDate.parse(userDataDTO.getBirthdate()).atStartOfDay())
                .name(userDataDTO.getName())
                .surname(userDataDTO.getSurname())
                .build();

        userDataRepository.save(userData);
    }

}
