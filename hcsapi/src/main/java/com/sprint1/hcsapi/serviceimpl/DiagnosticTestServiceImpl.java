package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

//import com.sprint1.hcsapi.domain.DiagnosticCenter;
import com.sprint1.hcsapi.domain.DiagnosticTest;
import com.sprint1.hcsapi.domain.TestResult;
import com.sprint1.hcsapi.exception.TestNameException;
import com.sprint1.hcsapi.repository.AppointmentRepository;
import com.sprint1.hcsapi.repository.DiagnosticCenterRepository;
import com.sprint1.hcsapi.repository.DiagnosticTestRepository;
import com.sprint1.hcsapi.repository.TestResultRepository;
import com.sprint1.hcsapi.service.DiagnosticTestService;

/**
 * This class will implement the Diagnostic test 
 * related business logics
 */
@Service
public class DiagnosticTestServiceImpl implements DiagnosticTestService {
    @Autowired
	private DiagnosticTestRepository diagnosticTestRepository;
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private DiagnosticCenterRepository diagnosticCenterRepository;
    
    @Autowired
    private TestResultRepository testResultRepository;
    
	/**
	 * This method is overriding the addOrUpdateDiagnosticTest method of Diagnostic Test Service
	 * This method is used to add or update a diagnostic test
	 */
	@Override
	public DiagnosticTest addOrUpdateDiagnosticTest(DiagnosticTest diagnosticTest,long dcId) {
		try {
			
			//This code below is to set the foreign key diagnostic Center with the id we get from the Diagnostic Center class
			diagnosticTest.setDiagnosticCenter(diagnosticCenterRepository.findById(dcId).get());
			
			//This is to check the changes we need which are specified in the postman body
			if(diagnosticTest.getId()!=null) {
				
				//This code below is to get the old details of that particular DiagnosticTest
				DiagnosticTest oldDiagnosticTest=diagnosticTestRepository.findById(diagnosticTest.getId()).get();
				
				if(diagnosticTest.getPrice()!=null) {
					oldDiagnosticTest.setPrice(diagnosticTest.getPrice());
				}
				
				if(diagnosticTest.getNormalValue()!=null) {
					oldDiagnosticTest.setNormalValue(diagnosticTest.getNormalValue());
				}
				
				if(diagnosticTest.getUnits()!=null) {
					oldDiagnosticTest.setUnits(diagnosticTest.getUnits());
				}
				return diagnosticTestRepository.save(oldDiagnosticTest);
			}
			
			//This code below is to simply save the DiagnosticTest
			return diagnosticTestRepository.save(diagnosticTest);
		}
		catch(Exception e)
		{
			throw new TestNameException("TestName "+diagnosticTest.getTestName().toUpperCase()+" already exists");
		}
	}
	
	/**
	 * This method is overriding the getAllTests method of Diagnostic Test Service
	 * This method is used by admin to find all tests
	 */
	@Override
	public Iterable<DiagnosticTest> getAllTests() {
	
		return diagnosticTestRepository.findAll();
	}
	
	/**
	 * This method is overriding the deleteTestByTestName method of Diagnostic Test Service
	 * This method is used to delete a test by putting the test Name
	 * throws exception if testName not found
	 */
	@Override
	public void deleteTestByTestName(String testName) {
			
		DiagnosticTest test=diagnosticTestRepository.findByTestName(testName);
		if(test==null)
			throw new TestNameException("TestName "+testName.toUpperCase()+" does not exists");
		diagnosticTestRepository.delete(test);
				
	}

	/**
	 * This method is overriding the getResult method of Diagnostic Test Service
	 * This method is used by the admin to create result of the test
	 */
	@Override
	public void getResult(TestResult testResult,long apId) {
		
       //get diagnostic test by fingById method in diagnostic repository
		
		DiagnosticTest diagnosticTest=appointmentRepository.findById(apId).get().getDiagnosticTest();
		
		//creating test result for the respective test id and appointment id
		TestResult result = new TestResult();
		
		//saving the appointment in result 
		result.setAppointment(appointmentRepository.findById(apId).get());
		result.setCondition(testResult.getCondition());
		result.setTestReading(testResult.getTestReading());
		
		//saving the test result
		testResultRepository.save(result);
		
		//changing the test status to available
		diagnosticTest.setTestStatus("Available");
		
		//saving test details
		diagnosticTestRepository.save(diagnosticTest);
		
	}

}
