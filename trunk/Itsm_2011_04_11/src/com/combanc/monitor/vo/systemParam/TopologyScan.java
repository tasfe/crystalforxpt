package com.combanc.monitor.vo.systemParam;

import java.io.File;

/**
 * <p>
 * Title:拓扑轮询系统设置
 * </p>
 * <p>
 * Description:设置间隔、颜色等
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company:Combanc
 * </p>
 * 
 * @author 
 * @version 1.0
 */
public class TopologyScan {
	
	/**轮询间隔**/
	private String pollGap;
	/**是否在拓扑图中标识出向心广播包最多的节点**/
	private String maxBroadcast;
	/**是否在拓扑图中标识出向心广播包最多的节点**/
	private String maxFlow;
	/**背景颜色**/
	private String backColor;
	/**默认背景图片路径**/
	private String backPicPath;
	
	
	// 封装单个上传文件域的属性
	private File upload;
	// 封装单个上传文件类型的属性
	private String uploadContentType;
	// 封装单个上传文件名的属性
	private String uploadFileName;
	
	public String getPollGap() {
		return pollGap;
	}
	public void setPollGap(String pollGap) {
		this.pollGap = pollGap;
	}
	public String getMaxBroadcast() {
		return maxBroadcast;
	}
	public void setMaxBroadcast(String maxBroadcast) {
		this.maxBroadcast = maxBroadcast;
	}
	public String getMaxFlow() {
		return maxFlow;
	}
	public void setMaxFlow(String maxFlow) {
		this.maxFlow = maxFlow;
	}
	public String getBackColor() {
		return backColor;
	}
	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}
	public String getBackPicPath() {
		return backPicPath;
	}
	public void setBackPicPath(String backPicPath) {
		this.backPicPath = backPicPath;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	
	
}
