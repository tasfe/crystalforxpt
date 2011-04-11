package com.combanc.common;

import java.io.ByteArrayInputStream;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JDKX509CertificateFactory;

import com.combanc.itsm.util.Mapx;
import com.combanc.itsm.util.FileUtil;
import com.combanc.itsm.util.StringFormat;
import com.combanc.itsm.util.StringUtil;


/**
 * ��ȡLicense��Ϣ
 * 
 * @Author ��$
 * @Date 2010-8-17
 * @Mail yangkun@combanc.com.cn
 */
public class LicenseInfo {
	public static boolean isLicenseValidity = true;

	public static boolean isIPValidity = true;

	public static boolean isUserCountValidity = true;

	private static String name;

	private static String product;

	private static String macAddress;

	private static int userLimit;

	private static Date endDate;

	static String cert =
			"LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tDQpNSUlDUnpDQ0FiQ2dBd0lCQWdJRVRIMjkxVEFO"
					+ "QmdrcWhraUc5dzBCQVFVRkFEQm5NUXN3Q1FZRFZRUUdFd0pEVGpFUU1BNEdBMVVFDQpDQk1IWW1W"
					+ "cGFtbHVaekVRTUE0R0ExVUVCeE1IWW1WcGFtbHVaekVRTUE0R0ExVUVDaE1IWTI5dFltRnVZekVR"
					+ "TUE0R0ExVUVDeE1IDQpZMjl0WW1GdVl6RVFNQTRHQTFVRUF4TUhZMjl0WW1GdVl6QWdGdzB4TURB"
					+ "NU1ERXdNalF6TXpOYUdBOHlNVEE1TURNeU5qQXlORE16DQpNMW93WnpFTE1Ba0dBMVVFQmhNQ1Ew"
					+ "NHhFREFPQmdOVkJBZ1RCMkpsYVdwcGJtY3hFREFPQmdOVkJBY1RCMkpsYVdwcGJtY3hFREFPDQpC"
					+ "Z05WQkFvVEIyTnZiV0poYm1NeEVEQU9CZ05WQkFzVEIyTnZiV0poYm1NeEVEQU9CZ05WQkFNVEIy"
					+ "TnZiV0poYm1Nd2daOHdEUVlKDQpLb1pJaHZjTkFRRUJCUUFEZ1kwQU1JR0pBb0dCQU1yekR5Nnlk"
					+ "cTRJdWJrS1htcEprSDJxdWl0YlBkcW1jMkJOdDlPUksxQkxCS1RvDQo4Mzg5Rm1NWVZseUg5anpv"
					+ "S29YY1l3VmJraStpSmtSdFJOaGovL2tLVTkzWkV5L1FYbDd4ek4xZUtiUGdZRXBrN2dOL0dEWU5l"
					+ "UkpRDQprNFhXaEt3QlplYTBnV0pyNWwxUHlkU1Nkb1FzYkFTS1RuTlhWWXhYU3Bnd2N6cEpBZ01C"
					+ "QUFFd0RRWUpLb1pJaHZjTkFRRUZCUUFEDQpnWUVBU0VyTEt2bkxKSmhncVhvaW94b29RZHdGbmlo"
					+ "Q0psbGJuNHdJTkNNazlHUjQzRTVHVWxBQngxN3lqeUY5N2JQVmR1ZVRnOGlqDQozYnFCa0dha0ox"
					+ "WVNnb2htTTdTcm9NeDZhaWpDb1BmYVdtYUdtVHI4eWVOYXNZbFJoZlVYN3VPblZka21DeXR6a3ly"
					+ "TWVPRU93RDhkDQpsWEdTZEc4TzRJWG9ZZWZKL09XUmFVcz0NCi0tLS0tRU5EIENFUlRJRklDQVRF"
					+ "LS0tLS0NCg==";

	static {
		Security.addProvider(new BouncyCastleProvider());
	}

	public synchronized static void init() {
		if (name == null) {
			update();
		}
	}

	public synchronized static void update() {
		try {
			byte[] code = StringUtil.hexDecode(FileUtil.readText(Config.getClassesPath() + "license.dat").trim());
			JDKX509CertificateFactory certificatefactory = new JDKX509CertificateFactory();
			X509Certificate cer =
					(X509Certificate) certificatefactory.engineGenerateCertificate(new ByteArrayInputStream(StringUtil.base64Decode(cert)));
			PublicKey pubKey = cer.getPublicKey();
			ZCipher dc = new ZCipher();
			dc.init(Cipher.DECRYPT_MODE, pubKey);
			byte[] bs = new byte[code.length * 2];
			int indexBS = 0;
			int indexCode = 0;
			while ((code.length - indexCode) > 128) {// ÿ128�ֽ���һ�ν���
				indexBS += dc.doFinal(code, indexCode, 128, bs, indexBS);
				indexCode += 128;
			}
			indexBS += dc.doFinal(code, indexCode, code.length - indexCode, bs, indexBS);
			String str = new String(bs, 0, indexBS);
			Mapx map = StringUtil.splitToMapx(str, ";", "=");
			name = map.getString("Name");
			product = map.getString("Product");
			userLimit = Integer.parseInt(map.getString("UserLimit"));
			macAddress = map.getString("MacAddress");
			endDate = new Date(Long.parseLong(map.getString("TimeEnd")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���Licenseʱ�����������!");
			System.exit(0);
		}
	}

	public static String getLicenseRequest(String customer) {
		try {
			JDKX509CertificateFactory certificatefactory = new JDKX509CertificateFactory();
			X509Certificate cer =
					(X509Certificate) certificatefactory.engineGenerateCertificate(new ByteArrayInputStream(StringUtil.base64Decode(cert)));
			PublicKey pubKey = cer.getPublicKey();
			ZCipher ec = new ZCipher();
			ec.init(Cipher.ENCRYPT_MODE, pubKey);
			StringFormat sf = new StringFormat("Name=?;MacAddress=?");
			sf.add(customer);
			sf.add(SystemInfo.getMacAddress());
			byte[] bs = sf.toString().getBytes();
			byte[] code = new byte[(((bs.length - 1) / 117 + 1)) * 128];
			int indexBS = 0;
			int indexCode = 0;
			while ((bs.length - indexBS) > 117) {
				indexCode += ec.doFinal(bs, indexBS, 117, code, indexCode);
				indexBS += 117;
			}
			ec.doFinal(bs, indexBS, bs.length - indexBS, code, indexCode);
			return StringUtil.hexEncode(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean verifyLicense() {
		if(FileUtil.readText(Config.getClassesPath() + "license.dat")==null){
			return false;
		}
		try {
			byte[] code= StringUtil.hexDecode(FileUtil.readText(Config.getClassesPath() + "license.dat").trim());
			JDKX509CertificateFactory certificatefactory = new JDKX509CertificateFactory();
			X509Certificate cer =
					(X509Certificate) certificatefactory.engineGenerateCertificate(new ByteArrayInputStream(StringUtil.base64Decode(cert)));
			PublicKey pubKey = cer.getPublicKey();
			ZCipher dc = new ZCipher();
			dc.init(Cipher.DECRYPT_MODE, pubKey);
			byte[] bs = new byte[code.length * 2];
			int indexBS = 0;
			int indexCode = 0;
			while ((code.length - indexCode) > 128) {// ÿ128�ֽ���һ�ν���
				indexBS += dc.doFinal(code, indexCode, 128, bs, indexBS);
				indexCode += 128;
			}
			indexBS += dc.doFinal(code, indexCode, code.length - indexCode, bs, indexBS);
			String str = new String(bs, 0, indexBS);
			System.out.println(str);
			Mapx map = StringUtil.splitToMapx(str, ";", "=");
			String mac1 = map.getString("MacAddress");
			String mac2 = SystemInfo.getMacAddress();
			String[] arr = mac2.split(",");
			for (int i = 0; i < arr.length; i++) {
				if (mac1.indexOf(arr[i]) < 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean verifyDate() {
		Date endDate = LicenseInfo.getEndDate();
		if (endDate.getTime() <= System.currentTimeMillis()) {
		return false;
		}
		return true;
	}
	
	public static boolean verifyLicense(String license) {
		try {
			byte[] code = StringUtil.hexDecode(license);
			JDKX509CertificateFactory certificatefactory = new JDKX509CertificateFactory();
			X509Certificate cer =
					(X509Certificate) certificatefactory.engineGenerateCertificate(new ByteArrayInputStream(StringUtil.base64Decode(cert)));
			PublicKey pubKey = cer.getPublicKey();
			ZCipher dc = new ZCipher();
			dc.init(Cipher.DECRYPT_MODE, pubKey);
			byte[] bs = new byte[code.length * 2];
			int indexBS = 0;
			int indexCode = 0;
			while ((code.length - indexCode) > 128) {// ÿ128�ֽ���һ�ν���
				indexBS += dc.doFinal(code, indexCode, 128, bs, indexBS);
				indexCode += 128;
			}
			indexBS += dc.doFinal(code, indexCode, code.length - indexCode, bs, indexBS);
			String str = new String(bs, 0, indexBS);
			System.out.println(str);
			Mapx map = StringUtil.splitToMapx(str, ";", "=");
			String mac1 = map.getString("MacAddress");
			String mac2 = SystemInfo.getMacAddress();
			String[] arr = mac2.split(",");
			for (int i = 0; i < arr.length; i++) {
				if (mac1.indexOf(arr[i]) < 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isIPValidity() {
		init();
		return isIPValidity;
	}

	public static boolean isLicenseValidity() {
		init();
		return isLicenseValidity;
	}

	public static boolean isUserCountValidity() {
		init();
		return isUserCountValidity;
	}

	public static String getName() {
		init();
		return name;
	}

	public static String getProduct() {
		init();
		return product;
	}

	public static int getUserLimit() {
		init();
		return userLimit;
	}

	public static Date getEndDate() {
		init();
		return endDate;
	}

	public static void setEndDate(Date endDate) {
		LicenseInfo.endDate = endDate;
	}

	public static void setIPValidity(boolean isIPValidity) {
		LicenseInfo.isIPValidity = isIPValidity;
	}

	public static void setLicenseValidity(boolean isLicenseValidity) {
		LicenseInfo.isLicenseValidity = isLicenseValidity;
	}

	public static void setUserCountValidity(boolean isUserCountValidity) {
		LicenseInfo.isUserCountValidity = isUserCountValidity;
	}

	public static void setName(String name) {
		LicenseInfo.name = name;
	}

	public static void setProduct(String product) {
		LicenseInfo.product = product;
	}

	public static void setUserLimit(int userLimit) {
		LicenseInfo.userLimit = userLimit;
	}

	public static String getMacAddress() {
		init();
		return macAddress;
	}

	public static void setMacAddress(String macAddress) {
		LicenseInfo.macAddress = macAddress;
	}
	
	public final static void main(String[] args) {
		String code = getLicenseRequest("����Ƽ�");
		System.out.println(code);
	}
}
