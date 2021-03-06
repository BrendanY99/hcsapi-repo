
package com.sprint1.hcsapi;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sprint1.hcsapi.domain.DiagnosticCenter;
import com.sprint1.hcsapi.domain.DiagnosticTest;
import com.sprint1.hcsapi.domain.Role;
import com.sprint1.hcsapi.domain.Users;
import com.sprint1.hcsapi.repository.DiagnosticCenterRepository;
import com.sprint1.hcsapi.repository.DiagnosticTestRepository;
import com.sprint1.hcsapi.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

/**This InitialSetup class will add admin automatically when application is being run for first time only.
 * This class will initially set one Diagnostic center and  one Diagnostic Test when application is being run for first time.
 *@author devendra
 *
 */
@Component
public class InitialSetup implements CommandLineRunner{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DiagnosticTestRepository diagnosticTestRepository;
	
	@Autowired
	DiagnosticCenterRepository diagnosticCenterRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	/**
	 * This method is overriding the run method of CommandLineRunner.
	 */
	@Override
	public void run(String... args) throws Exception{
		Users users = userRepository.findByUsername("admin");
		if(users!=null){
			try {
				DiagnosticCenter diagnosticCenter = diagnosticCenterRepository.findById((long)1).get();
			}
			catch(Exception e) {
				DiagnosticCenter diagnosticCenter = new DiagnosticCenter();
				diagnosticCenter.setAddress("lane-3,rajgarh,pune");
				diagnosticCenter.setContactNo(8912323234L);
				diagnosticCenter.setEmail("Ut@gmail.com");
				diagnosticCenter.setName("Uptown Diagnostic Center");
				diagnosticCenterRepository.save(diagnosticCenter);
				DiagnosticTest diagnosticTest = new DiagnosticTest();
				diagnosticTest.setDiagnosticCenter(diagnosticCenter);
				diagnosticTest.setTestName("Unavailable");
				diagnosticTest.setTestStatus("OUT OF REACH");
				diagnosticTest.setPrice((double)0);
				diagnosticTest.setNormalValue("Non");
				diagnosticTest.setUnits("Non");
				
				diagnosticTestRepository.save(diagnosticTest);
			}
			return;
		}
		users = new Users();
		users.getRoles().add(Role.ROLE_ADMIN);
		users.getRoles().add(Role.ROLE_USER);
		users.setName("admin");
		users.setUsername("admin");
		users.setEmail("admin@something.com");     
		users.setPassword(passwordEncoder.encode("admin@123"));
		users.setGender("Male");
		users.setPhoneNo("9999999999");
		users.setAge(40);
		userRepository.save(users);

	}
}
