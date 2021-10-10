package com.casierni.todo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.casierni.todo.entity.Role;
import com.casierni.todo.entity.User;
import com.casierni.todo.repository.RoleRepository;
import com.casierni.todo.repository.UserRepository;
import com.casierni.todo.service.UserService;
import com.casierni.todo.web.dto.UserRegistrationDto;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository; 
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		 Set<Role> rs = new HashSet<Role>();
	        rs.add(roleRepository.findOneByName("USER"));
	        
		User user = new User(registrationDto.getFirstName(), registrationDto.getLastName(), 
				registrationDto.getEmail(), passwordEncoder.encode(registrationDto.getPassword()), 
				rs);
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getname(), user.getPassword(), grantedAuthorities);
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
