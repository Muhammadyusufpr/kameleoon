package com.company.kameleoon.service;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.AuthRequestDTO;
import com.company.kameleoon.dto.request.LoginDTO;
import com.company.kameleoon.entity.ConfirmationTokenEntity;
import com.company.kameleoon.entity.ProfileEntity;
import com.company.kameleoon.repository.ConfirmationTokenRepository;
import com.company.kameleoon.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final ProfileRepository profileRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JavaMailSender javaMailSender;


    public ApiResponse<?> registration(AuthRequestDTO dto) {
        ProfileEntity existingProfile = profileRepository.findByEmail(dto.getEmail());

        if (existingProfile != null) {
            log.info("Email already exists!{}", dto.getEmail());
            return new ApiResponse<>("Email already exists", true);
        } else {
            ProfileEntity entity = new ProfileEntity();

            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setPassword(dto.getPassword());
            ProfileEntity save = profileRepository.save(entity);

            ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(save);

            ConfirmationTokenEntity savedConfirmation = confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(dto.getEmail());
            simpleMailMessage.setSubject("Complete Registration!");
            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setText("To confirm your account click here: "
                    + "http://localhost:8080/confirm-account?token" + savedConfirmation.getConfirmationToken());

            sendEmail(simpleMailMessage);
        }
        return new ApiResponse<>("Success!", false);
    }

    public ApiResponse<?> confirm(String token) {
        ConfirmationTokenEntity entity = confirmationTokenRepository.findByConfirmationToken(token);

        if (entity != null) {
            ProfileEntity profile = profileRepository.findByEmail(entity.getProfile().getEmail());

            profile.setEnable(true);
            profileRepository.save(profile);
            return new ApiResponse<>("Confirmed email!", false);
        }
        return new ApiResponse<>("The link is invalid or broken!", true);
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }


    public ApiResponse<?> login(LoginDTO dto) {
        ProfileEntity existingProfile = profileRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (existingProfile == null) {
            log.info("Profile not found!{}", dto);
            return new ApiResponse<>("Profile not found!", true);
        }

        return new ApiResponse<>("Success login!",false,existingProfile.getId());
    }
}
