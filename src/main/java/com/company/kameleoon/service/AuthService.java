package com.company.kameleoon.service;

import com.company.kameleoon.dto.detail.ApiResponse;
import com.company.kameleoon.dto.request.AuthRequestDTO;
import com.company.kameleoon.dto.request.LoginDTO;
import com.company.kameleoon.entity.ProfileEntity;
import com.company.kameleoon.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${spring.mail.username}")
    private String fromEmail;
    private final ProfileRepository profileRepository;
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
            profileRepository.save(entity);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(dto.getEmail());
            simpleMailMessage.setSubject("Registration!");
            simpleMailMessage.setText("Successfully registred!");
            javaMailSender.send(simpleMailMessage);
            System.out.println("Check your email!");
        }
        return new ApiResponse<>("Success!", false);
    }


    public ApiResponse<?> login(LoginDTO dto) {
        ProfileEntity existingProfile = profileRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (existingProfile == null) {
            log.info("Profile not found!{}", dto);
            return new ApiResponse<>("Profile not found!", true);
        }

        return new ApiResponse<>("Success login!", false, existingProfile.getId());
    }
}
