package com.example.se.qlsv.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.se.qlsv.dto.LoginRequestDTO;
import com.example.se.qlsv.dto.LoginResponseDTO;
import com.example.se.qlsv.entity.Manager;
import com.example.se.qlsv.entity.Student;
import com.example.se.qlsv.entity.User;
import com.example.se.qlsv.enumric.RoleName;
import com.example.se.qlsv.repository.ManagerRepository;
import com.example.se.qlsv.repository.UserRepository;
import com.example.se.qlsv.security.JwtUltilities;
import com.example.se.qlsv.service.StudentService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private JwtUltilities jwtUltilities;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequestDTO.getMssv(), loginRequestDTO.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			System.out.println("authorities=" + authorities);
			if (authorities.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unrole user access");
			}
			Object[] userRoles = authorities.toArray();
			String userRole = userRoles[0].toString();
			RoleName userRoleName = RoleName.valueOf(userRole);
			RoleName roleNameDTO = loginRequestDTO.getRoleName();
			if (!roleNameDTO.equals(userRoleName)) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not match role user account!");
			}
			User principalForceToUser = (User) authentication.getPrincipal();
			System.out.println("principalForceToUser=" + principalForceToUser.getId());
			Optional<User> theUser = userRepository.findById(principalForceToUser.getId());
			if (!theUser.isPresent()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not find the user!");
			}
			String token = jwtUltilities.generateToken(loginRequestDTO.getMssv(), userRoleName);
			long userId = principalForceToUser.getId();
			if (roleNameDTO.equals(RoleName.STUDENT)) {
				Student student = studentService.findByUserId(userId);
				long studentId = student.getId();
				LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
				loginResponseDTO.setObjectId(studentId);
				loginResponseDTO.setToken(token);
				return ResponseEntity.ok(loginResponseDTO);
			}
			if (roleNameDTO.equals(RoleName.TEACHER)) {
				return ResponseEntity.ok(null);
			}
			if (roleNameDTO.equals(RoleName.MANAGER)) {
				Manager manager = managerRepository.findByUserId(userId);
				long managerId = manager.getId();
				LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
				loginResponseDTO.setObjectId(managerId);
				loginResponseDTO.setToken(token);
				return ResponseEntity.ok(loginResponseDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Couldn't find the User Class account belong to!");
	}
}
