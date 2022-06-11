package io.anthony.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.anthony.security.models.MyUserDetails;
import io.anthony.security.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUserName(username);
		
		user.orElseThrow(() -> new UsernameNotFoundException("Not found: "+ username));
		
		return user.map(MyUserDetails::new).get();
	}

}
