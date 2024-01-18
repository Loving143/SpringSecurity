package com.smart.service;

import java.util.Calendar;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smart.entity.User;
import com.smart.repositories.UserRepositories;
import com.smart.request.RegisterUserRequest;
import com.smart.token.VerificationToken;
import com.smart.token.VerificationTokenRepository;

@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepositories userRepositories;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private VerificationTokenRepository veriRepo;
	
	@Override
	public User registerUser(RegisterUserRequest request) throws Exception {
		if(userRepositories.existsByEmail(request.getEmail()))
			throw new Exception(" User already exists.");
		String password=request.getPassword();
		request.setPassword(encoder.encode(password));
		User user =new User(request); 
		User registeredUser = userRepositories.save(user); 
		return registeredUser; 
	}
	@Override
	public void saveUserVerificationToken( String verificationToken, User theUser) {
		
		var verifi = new VerificationToken(verificationToken, theUser);
		veriRepo.save(verifi);
	}
	
    @Override
    public String validateToken(String theToken) {
        VerificationToken token = veriRepo.findByToken(theToken);
        if(token == null){
            return "Invalid verification token";
        }
        User user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
        	veriRepo.delete(token);
            return "Token already expired";
        }
        user.setEnabled(true);
        userRepositories.save(user);
        return "valid";
    }
	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		User userr = userRepositories.findById((long)id).orElseThrow(() -> new EntityNotFoundException("User not found with this id "));
		return userr;
	}

}
