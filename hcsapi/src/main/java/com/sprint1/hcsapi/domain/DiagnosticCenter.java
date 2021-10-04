package com.sprint1.hcsapi.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class DiagnosticCenter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long dcID;
	
	//@NotBlank(message="dcName is required")
	private String dcName;
	
	private Long dcContactNo;

	private String dcEmail;
	
	/*@NotBlank(message="test/s is/are required")
	private List<Test> test;*/

	//@NotBlank(message="dcAddress is required")
	private String dcAddress;
	public DiagnosticCenter() {}

	public DiagnosticCenter(long dcID, @NotBlank(message = "dcName is required") String dcName,
			@NotBlank(message = "dcContactNo is required") Long dcContactNo, String dcEmail,
			@NotBlank(message = "dcAddress is required") String dcAddress) {
		super();
		this.dcID = dcID;
		this.dcName = dcName;
		this.dcContactNo = dcContactNo;
		this.dcEmail = dcEmail;
		this.dcAddress = dcAddress;
	}

	public long getDcID() {
		return dcID;
	}

	public void setDcID(long dcID) {
		this.dcID = dcID;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public Long getDcContactNo() {
		return dcContactNo;
	}

	public void setDcContactNo(Long dcContactNo) {
		this.dcContactNo = dcContactNo;
	}

	public String getDcEmail() {
		return dcEmail;
	}

	public void setDcEmail(String dcEmail) {
		this.dcEmail = dcEmail;
	}

	

	public String getDcAddress() {
		return dcAddress;
	}

	public void setDcAddress(String dcAddress) {
		this.dcAddress = dcAddress;
	}
}