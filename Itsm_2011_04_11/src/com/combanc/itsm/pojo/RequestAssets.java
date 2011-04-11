package com.combanc.itsm.pojo;

/**
 * TaskCatId entity. @author MyEclipse Persistence Tools
 */

public class RequestAssets implements java.io.Serializable {

	// Fields
    private RequestAssetsId requestAssetsId;
	private Integer requestId;
	private Integer assetsId;
    private ServiceRequest serviceRequest;
    private AssetsBase assetsBase;

	// Constructors

	/** default constructor */
	public RequestAssets() {
	}

	/** full constructor */
	public RequestAssets(Integer requestId, Integer assetsId) {
		this.requestId = requestId;
		this.assetsId = assetsId;
	}

	// Property accessors


    public Integer getAssetsId() {
        return assetsId;
    }

    public RequestAssetsId getRequestAssetsId() {
		return requestAssetsId;
	}

	public void setRequestAssetsId(RequestAssetsId requestAssetsId) {
		this.requestAssetsId = requestAssetsId;
	}

	public ServiceRequest getServiceRequest() {
		return serviceRequest;
	}

	public void setServiceRequest(ServiceRequest serviceRequest) {
		this.serviceRequest = serviceRequest;
	}

	public AssetsBase getAssetsBase() {
		return assetsBase;
	}

	public void setAssetsBase(AssetsBase assetsBase) {
		this.assetsBase = assetsBase;
	}

	public void setAssetsId(Integer assetsId) {
        this.assetsId = assetsId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RequestAssets))
			return false;
		RequestAssets castOther = (RequestAssets) other;

		return ((this.getRequestId() == castOther.getRequestId()) || (this
				.getRequestId() != null
				&& castOther.getRequestId()!= null && this.getRequestId().equals(
				castOther.getRequestId())))
				&& ((this.getAssetsId() == castOther.getAssetsId()) || (this
						.getAssetsId() != null
						&& castOther.getAssetsId() != null && this.getAssetsId()
						.equals(castOther.getAssetsId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRequestId() == null ? 0 : this.getRequestId().hashCode());
		result = 37 * result
				+ (getAssetsId() == null ? 0 : this.getAssetsId().hashCode());
		return result;
	}

}