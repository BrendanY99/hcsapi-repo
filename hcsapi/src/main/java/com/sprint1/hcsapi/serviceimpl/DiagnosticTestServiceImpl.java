package com.sprint1.hcsapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.hcsapi.domain.DiagnosticTest;
import com.sprint1.hcsapi.exception.TestNameException;
import com.sprint1.hcsapi.repository.DiagnosticTestRepository;
import com.sprint1.hcsapi.service.DiagnosticTestService;

@Service
public class DiagnosticTestServiceImpl implements DiagnosticTestService {
    @Autowired
	private DiagnosticTestRepository diagnosticTestRepository;
    
	@Override
	public DiagnosticTest addOrUpdateDiagnosticTest(DiagnosticTest diagnosticTest) {
		try {
	     //	Throw Exception if diagnostic test already exists
		diagnosticTest.setTestName(diagnosticTest.getTestName());
        // save diagnostic test if it is created for first time	
        // update diagnostic test if id is provided	
		return diagnosticTestRepository.save(diagnosticTest);
		}
		catch(Exception e)
		{
			throw new TestNameException("TestName "+diagnosticTest.getTestName().toUpperCase()+" already exists");
		}
	}
	
	@Override
	public Iterable<DiagnosticTest> getAllTests() {
      // returns all available dianostic tests		
		return diagnosticTestRepository.findAll();
	}
	
	@Override
	public DiagnosticTest findByTestName(String testName) {
       //returns found diagnostic test
		DiagnosticTest diagnosticTest=diagnosticTestRepository.findByTestName(testName);
		//throws exception if diagnostic test not found
		if(diagnosticTest==null)
			throw new TestNameException("TestName "+testName+" does not exists");
		return diagnosticTest;
	}

	@Override
	public void deleteTestByTestName(String testName) {
        //delete found diagnostic test
        //throws exception if testName not found		
		DiagnosticTest test=findByTestName(testName);
		diagnosticTestRepository.delete(test);
				
	}


}
